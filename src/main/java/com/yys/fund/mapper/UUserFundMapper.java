package com.yys.fund.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UUserFundMapper {


    /**
     * 添加基金信息
     * @param map
     * @return
     */
    int addUserFund(Map map);


    /**
     * 更新基金信息
     * @param map
     * @return
     */
    int updateUserFund(Map map);
    int updateUserFundForNetWorth(Map map);

    /**
     * 删除基金信息
     * @param map
     * @return
     */
    int deleteUserFund(Map map);
    /**
     * 查询所有的基金信息
     * @return
     */


    List<Map> findUserFundList(Map map);
    Integer findUserFundListCount(Map map);


    /**
     * 查询编号是否重复
     * @param map
     */
    List<Map> findUserFundByCode(Map map);
}