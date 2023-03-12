package com.yys.fund.controller;

import com.yys.fund.constant.ExceptionConstant;
import com.yys.fund.entity.DbUser;
import com.yys.fund.service.UFundTransactionService;
import com.yys.fund.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Describe:
 * -------------------
 * User: yangyongsheng
 * Date: 2019/06/25 17:37:12
 * Email: 1095737364@qq.com
 */
@Controller
@RequestMapping("fund/userFundTransaction/")
public class UserFundTransactionController {

    private static final Logger logger = LoggerFactory.getLogger(UserFundTransactionController.class);


    @Autowired
    private UFundTransactionService fundTransactionService;

    @RequestMapping("/userFundTransaction.html")
    public String fundHtml() {
        return "userFundTransaction";
    }


    /**
     * 我的买入基金列表
     *
     * @param request
     * @return
     */
    @RequestMapping("/findFundTransactionPurchaseList")
    @ResponseBody
    public ResultUtil findFundTransactionPurchaseList(HttpServletRequest request, @RequestBody Map map) {
        try {
            ResultUtil resultUtil = new ResultUtil();
            DbUser dbUser = (DbUser) request.getSession().getAttribute("dbUser");
            if (dbUser == null) {
                return ResultUtil.error("查询失败,未登录!");
            }
            map.put("userId", dbUser.getId());
            resultUtil.setData(fundTransactionService.findFundTransactionPurchaseList(map));
            resultUtil.setCount(fundTransactionService.findFundTransactionPurchaseCount(map));
            resultUtil.setMsg("查询成功!");
            resultUtil.setCode(ExceptionConstant.SUCCESS_HTTPREUQEST);

            return resultUtil;
        } catch (Exception e) {
            logger.error("查询基金买入错误: " + e.getMessage());
            return ResultUtil.error("查询失败!");
        }
    }


    /**
     * 我的卖出基金列表
     *
     * @param request
     * @return
     */
    @RequestMapping("/findFundTransactionSellList")
    @ResponseBody
    public ResultUtil findFundTransactionSellList(HttpServletRequest request, @RequestBody Map map) {
        try {
            ResultUtil resultUtil = new ResultUtil();
            DbUser dbUser = (DbUser) request.getSession().getAttribute("dbUser");
            if (dbUser == null) {
                return ResultUtil.error("查询失败,未登录!");
            }
            map.put("userId", dbUser.getId());
            resultUtil.setData(fundTransactionService.findFundTransactionSellList(map));
            resultUtil.setCount(fundTransactionService.findFundTransactionSellCount(map));
            resultUtil.setMsg("查询成功!");
            resultUtil.setCode(ExceptionConstant.SUCCESS_HTTPREUQEST);

            return resultUtil;
        } catch (Exception e) {
            logger.error("查询基金卖出错误: " + e.getMessage());
            return ResultUtil.error("查询失败!");
        }
    }


    /**
     * 添加买入基金
     *
     * @param request
     * @param map
     * @return
     */
    @RequestMapping("/addUserFundTtransactionPurchase")
    @ResponseBody
    public ResultUtil addUserFundTtransactionPurchase(HttpServletRequest request, @RequestBody Map map) {
        try {
            DbUser dbUser = (DbUser) request.getSession().getAttribute("dbUser");
            if (dbUser == null) {
                return ResultUtil.error("查询失败,未登录!");
            }
            map.put("userId", dbUser.getId());
            //判断名称是否重复
            Integer num = fundTransactionService.addUserFundTtransactionPurchase(map);
            return ResultUtil.success("卖出成功!");
        } catch (Exception e) {
            logger.error("卖出基金错误: " + e.getMessage());
            return ResultUtil.error("卖出失败!");
        }
    }

    /**
     * 添加卖出基金
     *
     * @param request
     * @param map
     * @return
     */
    @RequestMapping("/addUserFundTtransactionSell")
    @ResponseBody
    public ResultUtil addUserFundTtransactionSell(HttpServletRequest request, @RequestBody Map map) {
        try {
            DbUser dbUser = (DbUser) request.getSession().getAttribute("dbUser");
            if (dbUser == null) {
                return ResultUtil.error("查询失败,未登录!");
            }
            map.put("userId", dbUser.getId());
            //判断名称是否重复
            Integer num = fundTransactionService.addUserFundTtransactionSell(map);
            return ResultUtil.success("添加成功!");
        } catch (Exception e) {
            logger.error("添加买入基金错误: " + e.getMessage());
            return ResultUtil.error("添加失败!");
        }
    }


    /**
     * 删除基金买入
     *
     * @param request
     * @param map
     * @return
     */
    @RequestMapping("/deleteUserFundTransaction")
    @ResponseBody
    public ResultUtil deleteUserFundTransaction(HttpServletRequest request, @RequestBody Map map) {
        try {
            DbUser dbUser = (DbUser) request.getSession().getAttribute("dbUser");
            if (dbUser == null) {
                return ResultUtil.error("删除失败,未登录!");
            }
            map.put("userId", dbUser.getId());
            fundTransactionService.deleteUserFundTransaction(map);
            return ResultUtil.success("删除成功!");
        } catch (Exception e) {
            logger.error("删除基金买入错误: " + e.getMessage());
            return ResultUtil.error("删除失败!");
        }
    }

    /**
     * 删除基金卖出
     *
     * @param request
     * @param map
     * @return
     */
    @RequestMapping("/deleteUserFundTransactionSell")
    @ResponseBody
    public ResultUtil deleteUserFundTransactionSell(HttpServletRequest request, @RequestBody Map map) {
        try {
            DbUser dbUser = (DbUser) request.getSession().getAttribute("dbUser");
            if (dbUser == null) {
                return ResultUtil.error("删除失败,未登录!");
            }
            map.put("userId", dbUser.getId());
            fundTransactionService.deleteUserFundTransactionSell(map);
            return ResultUtil.success("删除成功!");
        } catch (Exception e) {
            logger.error("删除基金卖出错误: " + e.getMessage());
            return ResultUtil.error("删除失败!");
        }
    }
}


