package com.yys.fund.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.yys.fund.constant.ExceptionConstant;
import com.yys.fund.entity.FFundType;
import com.yys.fund.service.FFundTypeService;
import com.yys.fund.utils.ResultUtil;
import com.yys.fund.utils.StringISNULLUtil;
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
@RequestMapping("fund/fundType")
public class FundTypeController {

    private static final Logger logger = LoggerFactory.getLogger(FundTypeController.class);

    @Autowired
    private FFundTypeService fundTypeService;

    @RequestMapping("/fundType.html")
    public String fundHtml() {
        return "fundType";
    }


    /**
     * 添加基金类型
     *
     * @param request
     * @param fundType
     * @return
     */
    @RequestMapping("/addFundType")
    @ResponseBody
    public ResultUtil addFundType(HttpServletRequest request, @RequestBody FFundType fundType) {
        try {
            //判断名称是否重复
            List<FFundType> list = fundTypeService.findFundTypeByNameAndNumber(fundType);
            if (list != null && list.size() > 0) {
                return ResultUtil.error("添加失败,名称和编号重复!");
            }
            fundTypeService.addFundType(fundType);
            return ResultUtil.success("添加成功!");
        } catch (Exception e) {
            logger.error("添加基金类型错误: " + e.getMessage());
            return ResultUtil.error("添加失败!");
        }
    }


    /**
     * 更新文章菜单
     *
     * @param request
     * @param fundType
     * @return
     */
    @RequestMapping("/updateFundType")
    @ResponseBody
    public ResultUtil updateFundType(HttpServletRequest request, @RequestBody FFundType fundType) {
        try {

            //判断名称是否重复
            List<FFundType> list = fundTypeService.findFundTypeByNameAndNumber(fundType);
            if (list != null && list.size() > 0) {
                return ResultUtil.error("添加失败,名称和编号重复!");
            }
            fundTypeService.updateFundType(fundType);
            return ResultUtil.success("更新成功!");
        } catch (Exception e) {
            logger.error("更新基金类型错误: " + e.getMessage());
            return ResultUtil.error("更新失败!");
        }
    }


    /**
     * 删除基金类型
     *
     * @param request
     * @param fundType
     * @return
     */
    @RequestMapping("/deleteFundType")
    @ResponseBody
    public ResultUtil deleteFundType(HttpServletRequest request, @RequestBody FFundType fundType) {
        try {
            fundType.setDeleteStatus(1);
            fundTypeService.deleteFundType(fundType);
            return ResultUtil.success("删除成功!");
        } catch (Exception e) {
            logger.error("删除基金类型错误: " + e.getMessage());
            return ResultUtil.error("删除失败!");
        }
    }


    /**
     * 基金类型管理页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/findFundTypeList")
    @ResponseBody
    public ResultUtil findFundTypeList(HttpServletRequest request, @RequestBody Map map) {
        try {
            ResultUtil resultUtil = new ResultUtil();

            resultUtil.setData(fundTypeService.findFundTypeList(map));
            resultUtil.setCount(fundTypeService.findFundTypeListCount(map));
            resultUtil.setMsg("查询成功!");
            resultUtil.setCode(ExceptionConstant.SUCCESS_HTTPREUQEST);

            return resultUtil;
        } catch (Exception e) {
            logger.error("查询基金类型错误: " + e.getMessage());
            return ResultUtil.error("查询失败!");
        }
    }

    /**
     * 基金类型管理页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/findFundTypeAllList")
    @ResponseBody
    public ResultUtil findFundTypeAllList(HttpServletRequest request) {
        try {
            ResultUtil resultUtil = new ResultUtil();

            resultUtil.setData(fundTypeService.findFundTypeAllList());
            resultUtil.setMsg("查询成功!");
            resultUtil.setCode(ExceptionConstant.SUCCESS_HTTPREUQEST);

            return resultUtil;
        } catch (Exception e) {
            logger.error("查询基金类型错误: " + e.getMessage());
            return ResultUtil.error("查询失败!");
        }
    }


}
