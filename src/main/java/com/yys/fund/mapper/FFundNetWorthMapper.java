package com.yys.fund.mapper;

import com.yys.fund.entity.FFundNetWorth;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FFundNetWorthMapper {


    /**
     * 添加基金类型
     * @param fundNetWorth
     * @return
     */
    int addFundNetWorth(FFundNetWorth fundNetWorth);
    int addFundNetWorthTemp(FFundNetWorth fundNetWorth);

    /**
     * 更新基金类型
     * @param fundNetWorth
     * @return
     */
    int updateFundNetWorth(FFundNetWorth fundNetWorth);
    int updateFundNetWorthTemp(FFundNetWorth fundNetWorth);

    /**
     * 删除基金类型
     * @param fundInfoCode
     * @return
     */
    int deleteFundNetWorth(String fundInfoCode);

    int deleteFundNetWorthTemp(String fundInfoCode);

    /**
     * 查询所有的基金类型
     * @return
     */
    List<FFundNetWorth> findFundNetWorthList(Map map);
    Integer findFundNetWorthListCount(Map map);

    /**
     * 查询所有基金类型
     * @return
     */
    List<FFundNetWorth> findFundNetWorthAllList();

    /**
     * 查询名称和编号是否重复
     * @param fundNetWorth
     */
    List<FFundNetWorth> findFundNetWorthByNameAndNumber(FFundNetWorth fundNetWorth);
}