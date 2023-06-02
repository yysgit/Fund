package com.yys.fund.controller;

import com.yys.fund.constant.ExceptionConstant;
import com.yys.fund.entity.DbUser;
import com.yys.fund.entity.FFundInfo;
import com.yys.fund.service.FFundInfoService;
import com.yys.fund.service.FFundLevelMoneyService;
import com.yys.fund.service.UUserFundService;
import com.yys.fund.service.impl.FundLevelServiceImpl;
import com.yys.fund.task.FundTask;
import com.yys.fund.utils.ResultUtil;
import com.yys.fund.utils.SendRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Describe:
 * -------------------
 * User: yangyongsheng
 * Date: 2019/06/25 17:37:12
 * Email: 1095737364@qq.com
 */
@Controller
@RequestMapping("fund/fundInfo")
public class FundInfoController {

    private static final Logger logger = LoggerFactory.getLogger(FundInfoController.class);

    @Autowired
    private FFundInfoService fundInfoService;

    @Autowired
    private UUserFundService userFundService;

    @Autowired
    private FFundLevelMoneyService fundLevelMoneyService;
    @Autowired
    private FundLevelServiceImpl fundLevelService;

    @Autowired
    private FundTask fundTask;


    @RequestMapping("/fundInfo.html")
    public String fundHtml() {
        return "fundInfo";
    }


    @RequestMapping("/fundHighcharts.html")
    public String fundHighcharts(){

        return "fundHighcharts.html";
    }
    @RequestMapping("/fundHighchartsForBonusNetWorth")
    @ResponseBody
    public ResultUtil fundHighchartsForBonusNetWorth() {
        Map map = new HashMap();
        map.put("page", 0);
        map.put("limit", 10000);
        List<Map> mapList = fundInfoService.findFundInfoList(map);
        Map fundMapBonusNetWorth = new HashMap();
        Map fundMapVolatilityValue = new HashMap();
        for (Map map1 : mapList) {
            fundMapBonusNetWorth.put(map1.get("fundCode"), map1.get("bonusNetWorth"));
            fundMapVolatilityValue.put(map1.get("fundCode"), (1-Double.parseDouble(map1.get("volatilityValue").toString())));
        }


        Map resultMap=new HashMap();
        resultMap.put("fundMapBonusNetWorth",fundMapBonusNetWorth);
        resultMap.put("fundMapVolatilityValue",fundMapVolatilityValue);
        resultMap.put("fundList",mapList);

        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setData(resultMap);
        resultUtil.setMsg("查询成功!");
        resultUtil.setCode(ExceptionConstant.SUCCESS_HTTPREUQEST);
        return resultUtil;
    }

    @RequestMapping("getFundDataListOne")
    @ResponseBody
    public String getFundDataListOne(HttpServletRequest request,String fundId) {
        long timestamp=System.currentTimeMillis();
        return SendRequest.getFundDataListThree(fundId,timestamp);
    }





    /**
     * 添加基金信息
     *
     * @param request
     * @param fFundInfo
     * @return
     */
    @RequestMapping("/addFundInfo")
    @ResponseBody
    public ResultUtil addFundInfo(HttpServletRequest request, @RequestBody FFundInfo fFundInfo) {
        try {
            DbUser dbUser=(DbUser)request.getSession().getAttribute("dbUser");
            if(dbUser==null){
                return ResultUtil.error("添加失败,未登录!");
            }

            fFundInfo.setCreateUserId(dbUser.getId());
            fFundInfo.setUpdateUserId(dbUser.getId());
            fundInfoService.addFundInfo(fFundInfo);
            fundTask.taskForInfo();
            return ResultUtil.success("添加成功!");
        } catch (Exception e) {
            logger.error("添加基金信息错误: " + e.getMessage());
            return ResultUtil.error("添加失败!");
        }
    }

    @RequestMapping("/addUserFund")
    @ResponseBody
    public ResultUtil addUserFund(HttpServletRequest request, @RequestBody Map map) {
        try {
            DbUser dbUser=(DbUser)request.getSession().getAttribute("dbUser");
            if(dbUser==null){
                return ResultUtil.error("添加失败,未登录!");
            }
            map.put("userId",dbUser.getId());
            fundLevelMoneyService.addFundLevelMoney(map);
            userFundService.addUserFund(map);
            return ResultUtil.success("添加成功!");
        } catch (Exception e) {
            logger.error("添加基金信息错误: " + e.getMessage());
            return ResultUtil.error("添加失败,请勿重复添加!");
        }
    }


    /**
     * 更新文章菜单
     *
     * @param request
     * @param fFundInfo
     * @return
     */
    @RequestMapping("/updateFundInfo")
    @ResponseBody
    public ResultUtil updateFundInfo(HttpServletRequest request, @RequestBody FFundInfo fFundInfo) {
        try {
            DbUser dbUser=(DbUser)request.getSession().getAttribute("dbUser");
            if(dbUser==null){
                return ResultUtil.error("更新失败,未登录!");
            }

            //更新利率等级
            double dnew= fFundInfo.getVolatilityValue();
            double dold= 0.05;
            if(dnew-dold!=0){
                Map mapFundLevel2 = getMapFundLevelForTask(Double.parseDouble(fFundInfo.getMaxNetWorth().toString()), String.valueOf(fFundInfo.getFundCode()), Double.parseDouble(String.valueOf(fFundInfo.getVolatilityValue())));
                fundLevelService.updateFundLevel(mapFundLevel2);
            }



            fundInfoService.updateFundInfo(fFundInfo);
            return ResultUtil.success("更新成功!");
        } catch (Exception e) {
            logger.error("更新基金信息错误: " + e.getMessage());
            return ResultUtil.error("更新失败!");
        }
    }

    private Map getMapFundLevelForTask(double maxNetWorth, String fundCode, double volatilityValue) {
        //更新等级
        Map mapFundLevel = new HashMap();
        mapFundLevel.put("fundInfoCode", fundCode);
        double d = maxNetWorth * (1 - volatilityValue);
        mapFundLevel.put("level1", d);
        d = d * (1 - volatilityValue);
        mapFundLevel.put("level2", d);
        d = d * (1 - volatilityValue);
        mapFundLevel.put("level3", d);
        d = d * (1 - volatilityValue);
        mapFundLevel.put("level4", d);
        d = d * (1 - volatilityValue);
        mapFundLevel.put("level5", d);
        d = d * (1 - volatilityValue);
        mapFundLevel.put("level6", d);
        d = d * (1 - volatilityValue);
        mapFundLevel.put("level7", d);
        d = d * (1 - volatilityValue);
        mapFundLevel.put("level8", d);
        d = d * (1 - volatilityValue);
        mapFundLevel.put("level9", d);
        d = d * (1 - volatilityValue);
        mapFundLevel.put("level10", d);
        d = d * (1 - volatilityValue);
        mapFundLevel.put("level11", d);
        d = d * (1 - volatilityValue);
        mapFundLevel.put("level12", d);
        d = d * (1 - volatilityValue);
        mapFundLevel.put("level13", d);
        d = d * (1 - volatilityValue);
        mapFundLevel.put("level14", d);
        d = d * (1 - volatilityValue);
        mapFundLevel.put("level15", d);
        d = d * (1 - volatilityValue);
        mapFundLevel.put("level16", d);
        d = d * (1 - volatilityValue);
        mapFundLevel.put("level17", d);
        d = d * (1 - volatilityValue);
        mapFundLevel.put("level18", d);
        d = d * (1 - volatilityValue);
        mapFundLevel.put("level19", d);
        d = d * (1 - volatilityValue);
        mapFundLevel.put("level20", d);
        return mapFundLevel;
    }
    /**
     * 删除基金信息
     *
     * @param request
     * @param fundInfo
     * @return
     */
    @RequestMapping("/deleteFundInfo")
    @ResponseBody
    public ResultUtil deleteFundInfo(HttpServletRequest request, @RequestBody FFundInfo fundInfo) {
        try {
            DbUser dbUser=(DbUser)request.getSession().getAttribute("dbUser");
            if(dbUser==null){
                return ResultUtil.error("删除失败,未登录!");
            }
            fundInfo.setDeleteStatus(1);
            fundInfoService.deleteFundInfo(fundInfo);
            return ResultUtil.success("删除成功!");
        } catch (Exception e) {
            logger.error("删除基金信息错误: " + e.getMessage());
            return ResultUtil.error("删除失败!");
        }
    }


    /**
     * 基金信息管理页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/findFundInfoList")
    @ResponseBody
    public ResultUtil findFundInfoList(HttpServletRequest request, @RequestBody Map map) {
        try {
            DbUser dbUser=(DbUser)request.getSession().getAttribute("dbUser");
            if(dbUser==null){
                return ResultUtil.error("查询失败,未登录!");
            }
            ResultUtil resultUtil = new ResultUtil();

            resultUtil.setData(fundInfoService.findFundInfoList(map));
            resultUtil.setCount(fundInfoService.findFundInfoListCount(map));
            resultUtil.setMsg("查询成功!");
            resultUtil.setCode(ExceptionConstant.SUCCESS_HTTPREUQEST);

            return resultUtil;
        } catch (Exception e) {
            logger.error("查询基金信息错误: " + e.getMessage());
            return ResultUtil.error("查询失败!");
        }
    }


}
