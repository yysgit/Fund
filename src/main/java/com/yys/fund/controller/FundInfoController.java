package com.yys.fund.controller;

import com.yys.fund.constant.ExceptionConstant;
import com.yys.fund.entity.DbUser;
import com.yys.fund.entity.FFundInfo;
import com.yys.fund.service.FFundInfoService;
import com.yys.fund.service.FFundLevelMoneyService;
import com.yys.fund.service.UUserFundService;
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
@RequestMapping("fund/fundInfo")
public class FundInfoController {

    private static final Logger logger = LoggerFactory.getLogger(FundInfoController.class);

    @Autowired
    private FFundInfoService fundInfoService;

    @Autowired
    private UUserFundService userFundService;

    @Autowired
    private FFundLevelMoneyService fundLevelMoneyService;

    @RequestMapping("/fundInfo.html")
    public String fundHtml() {
        return "fundInfo";
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
            fundInfoService.addFundInfo(fFundInfo);
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
            fundInfoService.updateFundInfo(fFundInfo);
            return ResultUtil.success("更新成功!");
        } catch (Exception e) {
            logger.error("更新基金信息错误: " + e.getMessage());
            return ResultUtil.error("更新失败!");
        }
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
