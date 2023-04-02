package com.yys.fund.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FFundLevelMapper {


    /**
     * 添加基金等级
     * @param map
     * @return
     */
    int addFundLevel(Map map);

    /**
     * 更新基金等级
     * @param map
     * @return
     */
    int updateFundLevel(Map map);


    int deleteFundLevel(String fundInfoCode);

    /**
     * 查询分页基金等级列表
     * @return
     */
    List<Map> findFundLevelList(Map map);
    Integer findFundLevelListCount(Map map);


    /**
     * 通过基金代码查询基金等级
     * @param map
     */
    List<Map> findFundLevelByFundInfoCode(Map map);
}