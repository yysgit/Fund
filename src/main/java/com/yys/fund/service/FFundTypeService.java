package com.yys.fund.service;

import com.yys.fund.entity.FFundType;

import java.util.List;
import java.util.Map;

/**
 * Describe:
 * -------------------
 * User: yangyongsheng
 * Date: 2019/06/25 19:34:16
 * Email: 1095737364@qq.com
 */
public interface FFundTypeService {

    /**
     * 添加基金类型
     * @param fundType
     * @return
     */
    int addFundType(FFundType fundType);

    /**
     * 更新基金类型
     * @param fundType
     * @return
     */
    int updateFundType(FFundType fundType);

    /**
     * 删除基金类型
     * @param fundType
     * @return
     */
    int deleteFundType(FFundType fundType);
    /**
     * 查询分页的基金类型
     * @return
     */
    List<FFundType> findFundTypeList(Map map);
    Integer findFundTypeListCount(Map map);

    /**
     * 查询所有基金类型
     * @return
     */
    List<FFundType> findFundTypeAllList();
    /**
     * 查询名称和编号是否重复
     * @param fundType
     */
    List<FFundType> findFundTypeByNameAndNumber(FFundType fundType);

}
