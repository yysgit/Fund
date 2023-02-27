package com.yys.fund.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FFundLevelMoneyMapper {


    /**
     * 添加基金等级
     * @param map
     * @return
     */
    int addFundLevelMoney(Map map);

    /**
     * 更新基金等级
     * @param map
     * @return
     */
    int updateFundLevelMoney(Map map);

    /**
     * 查询分页基金等级列表
     * @return
     */
    List<Map> findFundLevelMoneyList(Map map);
    Integer findFundLevelMoneyListCount(Map map);


    /**
     * 通过基金代码查询基金等级
     * @param map
     */
    List<Map> findFundLevelMoneyByFundInfoCode(Map map);

    /**
     * 删除用户基金等级金额
     * @param map
     * @return
     */
    int deleteLevelMoneyByFundInfoCode(Map map);

}