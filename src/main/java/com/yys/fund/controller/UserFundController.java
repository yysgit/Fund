package com.yys.fund.controller;

import com.yys.fund.constant.ExceptionConstant;
import com.yys.fund.entity.DbUser;
import com.yys.fund.service.FFundLevelMoneyService;
import com.yys.fund.service.UUserFundService;
import com.yys.fund.utils.DateUtil;
import com.yys.fund.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Describe:
 * -------------------
 * User: yangyongsheng
 * Date: 2019/06/25 17:37:12
 * Email: 1095737364@qq.com
 */
@Controller
@RequestMapping("fund/userFund")
public class UserFundController {

    private static final Logger logger = LoggerFactory.getLogger(UserFundController.class);

    @Autowired
    private UUserFundService userFundService;

    @Autowired
    private FFundLevelMoneyService fundLevelMoneyService;

    @RequestMapping("/userFund.html")
    public String fundHtml() {
        return "userFund";
    }


    @RequestMapping("/userFundStatistics.html")
    public String userFundStatistics() {
        return "userFundStatistics";
    }



    @RequestMapping("/fundUserFundIncomeStatistics")
    @ResponseBody
    public ResultUtil fundUserFundIncomeStatistics(HttpServletRequest request) {
        try {
            ResultUtil resultUtil = new ResultUtil();

            DbUser dbUser = (DbUser) request.getSession().getAttribute("dbUser");
            if (dbUser == null) {
                return ResultUtil.error("查询失败,未登录!");
            }
            Map map=new HashMap();
            map.put("userId", dbUser.getId());
            map.put("income_1", DateUtil.getPastDate(1));
            map.put("income_2", DateUtil.getWeekFirstDate());
            map.put("income_3", DateUtil.getMoonFirstDate());
            map.put("income_4", DateUtil.getLastMoonFirstDate());
            map.put("income_5", DateUtil.getYearFirstDate());
            resultUtil.setData(userFundService.fundUserFundIncomeStatistics(map));
            resultUtil.setMsg("查询成功!");
            resultUtil.setCode(ExceptionConstant.SUCCESS_HTTPREUQEST);

            return resultUtil;
        } catch (Exception e) {
            logger.error("查询基金信息错误: " + e.getMessage());
            return ResultUtil.error("查询失败!");
        }
    }




    @RequestMapping("/fundUserFundTradeStatistics")
    @ResponseBody
    public ResultUtil fundUserFundTradeStatistics(HttpServletRequest request) {
        try {
            ResultUtil resultUtil = new ResultUtil();

            DbUser dbUser = (DbUser) request.getSession().getAttribute("dbUser");
            if (dbUser == null) {
                return ResultUtil.error("查询失败,未登录!");
            }
            Map map=new HashMap();
            map.put("userId", dbUser.getId());
            map.put("trade_1", DateUtil.getPastDate(1));
            map.put("trade_2", DateUtil.getWeekFirstDate());
            map.put("trade_3", DateUtil.getMoonFirstDate());
            map.put("trade_4", DateUtil.getLastMoonFirstDate());
            map.put("trade_5", DateUtil.getYearFirstDate());
            resultUtil.setData(userFundService.fundUserFundTradeStatistics(map));
            resultUtil.setMsg("查询成功!");
            resultUtil.setCode(ExceptionConstant.SUCCESS_HTTPREUQEST);

            return resultUtil;
        } catch (Exception e) {
            logger.error("查询基金信息错误: " + e.getMessage());
            return ResultUtil.error("查询失败!");
        }
    }
    /**
     * 基金信息管理页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/findUserFundList")
    @ResponseBody
    public ResultUtil findUserFundList(HttpServletRequest request, @RequestBody Map map) {
        try {
            ResultUtil resultUtil = new ResultUtil();

            DbUser dbUser = (DbUser) request.getSession().getAttribute("dbUser");
            if (dbUser == null) {
                return ResultUtil.error("查询失败,未登录!");
            }
            map.put("userId", dbUser.getId());

            map.put("fundday1", DateUtil.getPastDate(0));
            map.put("fundday2", DateUtil.getPastDate(1));
            map.put("fundday3", DateUtil.getPastDate(2));
            map.put("fundday4", DateUtil.getPastDate(3));
            map.put("fundday5", DateUtil.getPastDate(4));
            map.put("fundday6", DateUtil.getPastDate(5));
            map.put("fundday7", DateUtil.getPastDate(6));
            map.put("fundday8", DateUtil.getPastDate(7));
            map.put("fundday9", DateUtil.getPastDate(8));
            map.put("fundday10", DateUtil.getPastDate(9));


            resultUtil.setData(userFundService.findUserFundList(map));
            resultUtil.setCount(userFundService.findUserFundListCount(map));
            resultUtil.setMsg("查询成功!");
            resultUtil.setCode(ExceptionConstant.SUCCESS_HTTPREUQEST);

            return resultUtil;
        } catch (Exception e) {
            logger.error("查询基金信息错误: " + e.getMessage());
            return ResultUtil.error("查询失败!");
        }
    }

    /**
     * 删除基金信息
     *
     * @param request
     * @param map
     * @return
     */
    @RequestMapping("/deleteUserFund")
    @ResponseBody
    public ResultUtil deleteUserFund(HttpServletRequest request, @RequestBody Map map) {
        try {
            map.put("deleteStatus", 1);
            DbUser dbUser = (DbUser) request.getSession().getAttribute("dbUser");
            if (dbUser == null) {
                return ResultUtil.error("删除失败,未登录!");
            }
            map.put("userId", dbUser.getId());
            userFundService.deleteUserFund(map);
            return ResultUtil.success("删除成功!");
        } catch (Exception e) {
            logger.error("删除基金信息错误: " + e.getMessage());
            return ResultUtil.error("删除失败!");
        }
    }

    /**
     * 更新文章菜单
     *
     * @param request
     * @param map
     * @return
     */
    @RequestMapping("/updateUserFund")
    @ResponseBody
    public ResultUtil updateUserFund(HttpServletRequest request, @RequestBody Map map) {
        try {
            DbUser dbUser = (DbUser) request.getSession().getAttribute("dbUser");
            if (dbUser == null) {
                return ResultUtil.error("保存失败,未登录!");
            }
            map.put("userId", dbUser.getId());
            userFundService.updateUserFund(map);
            return ResultUtil.success("保存成功!");
        } catch (Exception e) {
            logger.error("保存基金信息错误: " + e.getMessage());
            return ResultUtil.error("保存失败!");
        }
    }



}
