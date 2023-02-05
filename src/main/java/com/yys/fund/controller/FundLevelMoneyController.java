package com.yys.fund.controller;

import com.yys.fund.constant.ExceptionConstant;
import com.yys.fund.entity.DbUser;
import com.yys.fund.service.FFundLevelMoneyService;
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
@RequestMapping("fund/fundLevelMoney")
public class FundLevelMoneyController {

    private static final Logger logger = LoggerFactory.getLogger(FundLevelMoneyController.class);

    @Autowired
    private FFundLevelMoneyService fundLevelMoneyService;

    @RequestMapping("/fundLevelMoney.html")
    public String fundHtml() {
        return "fundLevelMoney";
    }


    /**
     * 基金等级管理页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/findFundLevelMoneyList")
    @ResponseBody
    public ResultUtil findFundLevelMoneyList(HttpServletRequest request, @RequestBody Map map) {
        try {
            ResultUtil resultUtil = new ResultUtil();
            DbUser dbUser = (DbUser) request.getSession().getAttribute("dbUser");
            if (dbUser == null) {
                return ResultUtil.error("查询失败,未登录!");
            }
            map.put("userId", dbUser.getId());
            resultUtil.setData(fundLevelMoneyService.findFundLevelMoneyList(map));
            resultUtil.setCount(fundLevelMoneyService.findFundLevelMoneyListCount(map));
            resultUtil.setMsg("查询成功!");
            resultUtil.setCode(ExceptionConstant.SUCCESS_HTTPREUQEST);

            return resultUtil;
        } catch (Exception e) {
            logger.error("查询基金等级错误: " + e.getMessage());
            return ResultUtil.error("查询失败!");
        }
    }

    /**
     * 更新文章菜单
     *
     * @param request
     * @param fundType
     * @return
     */
    @RequestMapping("/updateFundLevelMoney")
    @ResponseBody
    public ResultUtil updateFundType(HttpServletRequest request, @RequestBody Map fundType) {
        try {
            DbUser dbUser=(DbUser)request.getSession().getAttribute("dbUser");
            if(dbUser==null){
                return ResultUtil.error("更新失败,未登录!");
            }
            fundLevelMoneyService.updateFundLevelMoney(fundType);
            return ResultUtil.success("更新成功!");
        } catch (Exception e) {
            logger.error("更新基金类型错误: " + e.getMessage());
            return ResultUtil.error("更新失败!");
        }
    }

}
