package com.yys.fund.service.impl;

import com.yys.fund.entity.DbUser;
import com.yys.fund.mapper.DbUserMapper;
import com.yys.fund.mapper.UFundTransactionMapper;
import com.yys.fund.mapper.UUserFundMapper;
import com.yys.fund.service.UFundTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Describe:
 * -------------------
 * User: yangyongsheng
 * Date: 2019/06/25 19:34:53
 * Email: 1095737364@qq.com
 */
@Service
public class UFundTransactionServiceImpl implements UFundTransactionService {
    @Autowired
    private UFundTransactionMapper fundTransactionMapper;
    @Autowired
    private UUserFundMapper userFundMapper;
    @Autowired
    private DbUserMapper userMapper;

    @Override
    public List<Map> findFundTransactionPurchaseList(Map map) {
        return fundTransactionMapper.findFundTransactionPurchaseList(map);
    }

    @Override
    public Integer findFundTransactionPurchaseCount(Map map) {
        return fundTransactionMapper.findFundTransactionPurchaseCount(map);
    }

    @Override
    public List<Map> findFundTransactionPurchaseListGroupBy(Map map) {
        return fundTransactionMapper.findFundTransactionPurchaseListGroupBy(map);
    }

    @Override
    public Integer findFundTransactionPurchaseCountGroupBy(Map map) {
        return fundTransactionMapper.findFundTransactionPurchaseCountGroupBy(map);
    }

    @Override
    public Integer addUserFundTtransactionPurchase(Map map) {
        return fundTransactionMapper.addUserFundTtransactionPurchase(map);
    }

    @Override
    public List<Map> findFundTransactionSellList(Map map) {
        return fundTransactionMapper.findFundTransactionSellList(map);
    }

    @Override
    public Integer findFundTransactionSellCount(Map map) {
        return fundTransactionMapper.findFundTransactionSellCount(map);
    }

    @Override
    @Transactional
    public Integer addUserFundTtransactionSell(Map map) {
        Integer int1 = fundTransactionMapper.addUserFundTtransactionSell(map);
        Integer int2 = fundTransactionMapper.updateUserFundTtransactionPurchase(map);
        return int1 + int2;
    }

    @Override
    public int deleteUserFundTransaction(Map map) {
        return fundTransactionMapper.deleteUserFundTransaction(map);
    }

    @Override
    public int deleteUserFundTransactionForCode(Map map) {
        return fundTransactionMapper.deleteUserFundTransactionForCode(map);
    }

    @Override
    public int deleteUserFundTransactionSell(Map map) {
        Integer int2 = fundTransactionMapper.updateUserFundTtransactionPurchaseforDelete(map);
        Integer int1 = fundTransactionMapper.deleteUserFundTransactionSell(map);
        return int1 + int2;
    }


    @Override
    public void findFundTransactionAll() {
        List<DbUser> userList = userMapper.findUserByRoleId(2);
        userList.forEach(dbUser -> {
            Map map = new HashMap();
            map.put("userId", dbUser.getId());
            map.put("page", 0);
            map.put("limit", 1000);
            List<Map> userFundList = userFundMapper.findUserFundList(map);

            userFundList.forEach(map1 -> {
                List<Map> userFundTransaction = fundTransactionMapper.findFundTransactionAll(map1);
                //今年,去年,前年,近一年,累积

                //全部累计总投入金额
                int allCumulativeTotalAmount = 0;
                int allCumulativeTotalAmountFundAmount = 0;
                //今年以来总投入金额
                int sinceThisYearOnAmount = 0;
                int sinceThisYearOnAmountFundAmount = 0;
                //近一年以来总投入金额
                int inThePastYearAmount = 0;
                int inThePastYearAmountFundAmount = 0;
                //去年以来总投入金额
                int sinceLastYearAmount = 0;
                int sinceLastYearAmountFundAmount = 0;
                //前年以来总投入金额
                int sinceTwoYearsAgoAmount = 0;
                int sinceTwoYearsAgoAmountFundAmount = 0;


                for (Map map2 : userFundTransaction) {
                    //全部累计总投入金额
                    if ("1".equals(map2.get("type"))) {
                        allCumulativeTotalAmountFundAmount += Float.valueOf(map2.get("amountAll").toString());
                        if (allCumulativeTotalAmountFundAmount > allCumulativeTotalAmount) {
                            allCumulativeTotalAmount = allCumulativeTotalAmountFundAmount;
                        }
                    }
                    if ("2".equals(map2.get("type"))) {
                        allCumulativeTotalAmountFundAmount -= Float.valueOf(map2.get("amountAll").toString());
                        if (allCumulativeTotalAmountFundAmount < 0) {
                            allCumulativeTotalAmountFundAmount = 0;
                        }
                    }


                    //今年以来总投入金额
                    Timestamp timestamp = (Timestamp) map2.get("fundTransactionTime");

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                    Date date = new Date();
                    String year = sdf.format(date);
                    Date d = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        d = formatter.parse(year + "-01-01 00:00:00");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if (d.getTime() < timestamp.getTime()) {

                        if ("1".equals(map2.get("type"))) {
                            sinceThisYearOnAmountFundAmount += Float.valueOf(map2.get("amountAll").toString());
                            if (sinceThisYearOnAmountFundAmount > sinceThisYearOnAmount) {
                                sinceThisYearOnAmount = sinceThisYearOnAmountFundAmount;
                            }
                        }
                        if ("2".equals(map2.get("type"))) {
                            sinceThisYearOnAmountFundAmount -= Float.valueOf(map2.get("amountAll").toString());
                            if (sinceThisYearOnAmountFundAmount < 0) {
                                sinceThisYearOnAmountFundAmount = 0;
                            }
                        }
                    }

                    //近一年以来总投入金额
                    Date dateNew = new Date();
                    if ((dateNew.getTime() - 31536000000L) < timestamp.getTime()) {

                        if ("1".equals(map2.get("type"))) {
                            inThePastYearAmountFundAmount += Float.valueOf(map2.get("amountAll").toString());
                            if (inThePastYearAmountFundAmount > inThePastYearAmount) {
                                inThePastYearAmount = inThePastYearAmountFundAmount;
                            }
                        }
                        if ("2".equals(map2.get("type"))) {
                            inThePastYearAmountFundAmount -= Float.valueOf(map2.get("amountAll").toString());
                            if (inThePastYearAmountFundAmount < 0) {
                                inThePastYearAmountFundAmount = 0;
                            }
                        }
                    }


                    //去年以来总投入金额
                    try {
                        d = formatter.parse((Integer.valueOf(year) - 1) + "-01-01 00:00:00");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (d.getTime() < timestamp.getTime()) {
                        if ("1".equals(map2.get("type"))) {
                            sinceLastYearAmountFundAmount += Float.valueOf(map2.get("amountAll").toString());
                            if (sinceLastYearAmountFundAmount > sinceLastYearAmount) {
                                sinceLastYearAmount = sinceLastYearAmountFundAmount;
                            }
                        }
                        if ("2".equals(map2.get("type"))) {
                            sinceLastYearAmountFundAmount -= Float.valueOf(map2.get("amountAll").toString());
                            if (sinceLastYearAmountFundAmount < 0) {
                                sinceLastYearAmountFundAmount = 0;
                            }
                        }

                    }

                    //前年以来总投入金额
                    try {
                        d = formatter.parse((Integer.valueOf(year) - 2) + "-01-01 00:00:00");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (d.getTime() < timestamp.getTime()) {
                        if ("1".equals(map2.get("type"))) {
                            sinceTwoYearsAgoAmountFundAmount += Float.valueOf(map2.get("amountAll").toString());
                            if (sinceTwoYearsAgoAmountFundAmount > sinceTwoYearsAgoAmount) {
                                sinceTwoYearsAgoAmount = sinceTwoYearsAgoAmountFundAmount;
                            }
                        }
                        if ("2".equals(map2.get("type"))) {
                            sinceTwoYearsAgoAmountFundAmount -= Float.valueOf(map2.get("amountAll").toString());
                            if (sinceTwoYearsAgoAmountFundAmount < 0) {
                                sinceTwoYearsAgoAmountFundAmount = 0;
                            }
                        }
                    }

                }


                System.out.println("fundName:" + map1.get("fundName") + "fundCode:" + map1.get("fundCode") + "\n   allCumulativeTotalAmount:" + allCumulativeTotalAmount + "\n   sinceThisYearOnAmount:" + sinceThisYearOnAmount + "\n   inThePastYearAmount:" + inThePastYearAmount + "\n   sinceTwoYearsAgoAmount:" + sinceTwoYearsAgoAmount + "\n   sinceLastYearAmount:" + sinceLastYearAmount);

                Map mapParam = new HashMap();
                mapParam.put("fundCode", map1.get("fundCode"));
                mapParam.put("userId", dbUser.getId());
                mapParam.put("allCumulativeTotalAmount", allCumulativeTotalAmount);
                mapParam.put("sinceThisYearOnAmount", sinceThisYearOnAmount);
                mapParam.put("inThePastYearAmount", inThePastYearAmount);
                mapParam.put("sinceLastYearAmount", sinceLastYearAmount);
                mapParam.put("sinceTwoYearsAgoAmount", sinceTwoYearsAgoAmount);
                userFundMapper.updateUserFundForAmount(mapParam);
            });
        });

    }


}
