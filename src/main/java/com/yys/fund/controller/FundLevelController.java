package com.yys.fund.controller;

import com.yys.fund.constant.ExceptionConstant;
import com.yys.fund.entity.FFundLevel;
import com.yys.fund.service.FFundLevelService;
import com.yys.fund.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("fund/fundLevel")
public class FundLevelController {

    private static final Logger logger = LoggerFactory.getLogger(FundLevelController.class);

    @Autowired
    private FFundLevelService fundLevelService;

    @RequestMapping("/fundLevel.html")
    public String fundHtml() {
        return "fundLevel";
    }


    /**
     * 基金等级管理页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/findFundLevelList")
    @ResponseBody
    public ResultUtil findFundLevelList(HttpServletRequest request, @RequestBody Map map) {
        try {
            ResultUtil resultUtil = new ResultUtil();

            resultUtil.setData(fundLevelService.findFundLevelList(map));
            resultUtil.setCount(fundLevelService.findFundLevelListCount(map));
            resultUtil.setMsg("查询成功!");
            resultUtil.setCode(ExceptionConstant.SUCCESS_HTTPREUQEST);

            return resultUtil;
        } catch (Exception e) {
            logger.error("查询基金等级错误: " + e.getMessage());
            return ResultUtil.error("查询失败!");
        }
    }
}
