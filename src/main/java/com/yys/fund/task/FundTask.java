package com.yys.fund.task;

import com.alibaba.fastjson.JSONArray;
import com.yys.fund.entity.FFundNetWorth;
import com.yys.fund.entity.FFundNetWorthTemp;
import com.yys.fund.mapper.FFundInfoMapper;
import com.yys.fund.mapper.FFundLevelMapper;
import com.yys.fund.mapper.FFundNetWorthMapper;
import com.yys.fund.mapper.UFundTransactionMapper;
import com.yys.fund.utils.SendRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FundTask {
    @Autowired
    private FFundInfoMapper fundInfoMapper;
    @Autowired
    private FFundLevelMapper fundLevelMapper;
    @Autowired
    private FFundNetWorthMapper fundNetWorthMapper;

    @Autowired
    private UFundTransactionMapper fundTransactionMapper;


    /**
     * 时时更新每个基金当天
     */
    //    @Scheduled(cron = "* */4 * * * ?")
//        @Scheduled(cron = " 0 54 2 * * *")
    @Scheduled(cron = " 0 */5 7-16 * * mon,tue,wed,thu,fri,sat")
    public void task1() {
        for (int i = 0; i < 1000; i++) {
            Map map = new HashMap();
            map.put("page", i * 100);
            map.put("limit", 100);
            List<Map> resultMap = fundInfoMapper.findFundInfoList(map);
            if (resultMap != null && resultMap.size() > 0) {
                for (int j = 0; j < resultMap.size(); j++) {
                    Map fundDataMySQL = resultMap.get(j);
                    Map fundDateNet = SendRequest.getFundDataListTwo(String.valueOf(fundDataMySQL.get("fundCode")), new Date().getTime());
                    if (fundDateNet == null) {
                        continue;
                    }
                    FFundNetWorthTemp fundNetWorth = new FFundNetWorthTemp();
                    fundNetWorth.setFundInfoCode(String.valueOf(fundDataMySQL.get("fundCode")));
                    fundNetWorth.setFundNetWorth(Double.valueOf(String.valueOf(fundDateNet.get("fundNetWorth"))));
                    fundNetWorth.setFundDay(new Date(Long.parseLong(String.valueOf(fundDateNet.get("fundDay")))));
                    //更新参数值
                    double maxNetWorth = Double.parseDouble(fundDataMySQL.get("maxNetWorth").toString());
                    double volatilityValue = Double.parseDouble(String.valueOf(fundDataMySQL.get("volatilityValue")));
                    double d = maxNetWorth * (1 - volatilityValue);
                    int levelNumber = 0;
                    double levelFront = maxNetWorth;
                    double levelBehind = d;
                    while (fundNetWorth.getFundNetWorth() < d) {
                        levelFront = d;
                        d = d * (1 - volatilityValue);
                        levelBehind = d;
                        levelNumber++;
                    }
                    fundNetWorth.setLevelNumber(levelNumber);
                    fundNetWorth.setLevelFront(levelFront);
                    fundNetWorth.setLevelBehind(levelBehind);
                    fundNetWorth.setRiseFall(Double.parseDouble(fundDateNet.get("riseFall").toString()));
                    fundNetWorth.setSettlementNewWorth(Double.parseDouble(fundDateNet.get("settlementNewWorth").toString()));
                    try{
                        fundNetWorth.setSettlementDay(new SimpleDateFormat("yyyy-MM-dd").parse(fundDateNet.get("settlementDay").toString()));
                    }catch ( Exception e){

                    }

                    if (fundDataMySQL.get("fundNetWorthTempId") == null || "".equals(fundDataMySQL.get("fundNetWorthTempId"))) {
                        fundNetWorthMapper.deleteFundNetWorthTemp(fundNetWorth.getFundInfoCode());
                        fundNetWorthMapper.addFundNetWorthTemp(fundNetWorth);
                    } else {
                        fundNetWorthMapper.updateFundNetWorthTemp(fundNetWorth);
                    }

                    //更新用户买入信息
                    fundTransactionMapper.updateFundTransactionPurchaseForTemp(fundNetWorth);


                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                break;
            }
        }
    }

    /**
     * 更新每天基金的真实基金净值  每天凌晨1点执行一次：0 0 1 * * ?
     */
//    @Scheduled(cron = "* */5 * * * ?")
    @Scheduled(cron = " 0 */10 0-2 * * mon,tue,wed,thu,fri,sat")
//    @Scheduled(cron = " 0 */3 8-23 * * mon,tue,wed,thu,fri,sat")
    public void task2() {
        for (int i = 0; i < 1000; i++) {
            Map map = new HashMap();
            map.put("page", i * 100);
            map.put("limit", 100);
            List<Map> resultMap = fundInfoMapper.findFundInfoList(map);
            if (resultMap != null && resultMap.size() > 0) {
                for (int j = 0; j < resultMap.size(); j++) {
                    Map fundDataMySQL = resultMap.get(j);

                    //查询基金信息
                    Map fundDateNet = SendRequest.getFundDataListOne(String.valueOf(fundDataMySQL.get("fundCode")), new Date().getTime());
                    String fundDateNetMaxNetWorth = String.valueOf(fundDateNet.get("maxNetWorth"));
                    String fundDataMySQLMaxNetWorth = String.valueOf(fundDataMySQL.get("maxNetWorth"));
                    //更新分红
                    Map bonusNetWorthMap=new HashMap();
                    bonusNetWorthMap.put("bonusNetWorth",fundDateNet.get("bonusNetWorth"));
                    bonusNetWorthMap.put("fundCode",fundDataMySQL.get("fundCode"));
                    fundInfoMapper.updateFundInfoForBonusNetWorth(bonusNetWorthMap);

                    //判断是否有最大净值
                    if (fundDateNet.get("maxNetWorth") != null && !fundDateNetMaxNetWorth.equals(fundDataMySQLMaxNetWorth)) {
                        fundDataMySQL.put("maxNetWorth", fundDateNet.get("maxNetWorth"));
                        fundDataMySQL.put("maxNetWorthDate", fundDateNet.get("maxNetWorthDate"));
                        fundDataMySQL.put("fundName", fundDateNet.get("fundName"));
                        Map mapFundLevel = SendRequest.getMapFundLevelForTask(Double.parseDouble(fundDataMySQL.get("maxNetWorth").toString()), String.valueOf(fundDataMySQL.get("fundCode")), Double.parseDouble(String.valueOf(fundDataMySQL.get("volatilityValue"))));
                        //更新基金信息的最大值净值和最大净值日期
                        fundInfoMapper.updateFundInfoForNetWorth(fundDataMySQL);

                        //添加基金等级
                        if (fundDataMySQL.get("fundLevelId") == null || "".equals(fundDataMySQL.get("fundLevelId"))) {
                            Map mapFundLevel2 = SendRequest.getMapFundLevelForTask(Double.parseDouble(fundDataMySQL.get("maxNetWorth").toString()), String.valueOf(fundDataMySQL.get("fundCode")), Double.parseDouble(String.valueOf(fundDataMySQL.get("volatilityValue"))));
                            fundLevelMapper.addFundLevel(mapFundLevel2);
                        } else {
                            //更新基金等级
                            fundLevelMapper.updateFundLevel(mapFundLevel);
                        }

                    }
                    //更新历史净值
                    Integer fundNetWorthCount = fundNetWorthMapper.findFundNetWorthListCount(fundDataMySQL);
                    if (fundNetWorthCount == null) {
                        fundNetWorthCount = 0;
                    }
                    JSONArray fundDataArray = (JSONArray) fundDateNet.get("fundDataStr");
                    for (int x = fundDataArray.size(); x > fundNetWorthCount.intValue(); x--) {
                        FFundNetWorth fundNetWorth = new FFundNetWorth();
                        fundNetWorth.setFundInfoCode(String.valueOf(fundDataMySQL.get("fundCode")));
                        fundNetWorth.setFundNetWorth(Double.valueOf(String.valueOf(fundDataArray.getJSONObject((x - 1)).get("y"))));
                        fundNetWorth.setFundDay(new Date(Long.parseLong(String.valueOf(fundDataArray.getJSONObject((x - 1)).get("x")))));
                        //更新参数值
                        double maxNetWorth = Double.parseDouble(fundDateNet.get("maxNetWorth").toString());
                        double volatilityValue = Double.parseDouble(String.valueOf(fundDataMySQL.get("volatilityValue")));
                        double d = maxNetWorth * (1 - volatilityValue);
                        int levelNumber = 0;
                        double levelFront = maxNetWorth;
                        double levelBehind = d;
                        while (fundNetWorth.getFundNetWorth() < d) {
                            levelFront = d;
                            d = d * (1 - volatilityValue);
                            levelBehind = d;
                            levelNumber++;
                        }
                        fundNetWorth.setLevelNumber(levelNumber);
                        fundNetWorth.setLevelFront(levelFront);
                        fundNetWorth.setLevelBehind(levelBehind);
                        fundNetWorthMapper.addFundNetWorth(fundNetWorth);
                    }

                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            } else {
                break;
            }
        }
        this.task1();
    }




    /**
     * 更新每天的买入净值信息
     */
    @Scheduled(cron = "* */2 * * * ?")
    public void task3() {
        fundTransactionMapper.updateFundTransactionPurchaseForTask();
        fundTransactionMapper.updateFundTransactionSellForTask();
        fundTransactionMapper.updateFundTransactionMinimumInitialForTask();
        fundTransactionMapper.updateFundTransactionMinimumForTask();
        fundTransactionMapper.updateFundTransactionTotalAmountForTask();
        fundTransactionMapper.updateFundTransactionTodayMoneyExpectForTask();
        fundTransactionMapper.updateFundTransactionTodayMoneyPurchasedForTask();

    }


    //更新任务
    @Async
    public void taskForInfo() {
        this.task2();
        this.task1();
    }

    @Async
    public void taskForTransaction() {
        this.task3();
    }
}
