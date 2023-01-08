package com.yys.fund.service;

import java.util.List;
import java.util.Map;

/**
 * Describe:
 * -------------------
 * User: yangyongsheng
 * Date: 2019/06/25 19:34:16
 * Email: 1095737364@qq.com
 */
public interface FFundLevelService {

    /**
     * 添加基金等级
     *
     * @param map
     * @return
     */
    int addFundLevel(Map map);

    /**
     * 更新基金等级
     *
     * @param map
     * @return
     */
    int updateFundLevel(Map map);

    /**
     * 查询分页基金等级列表
     *
     * @return
     */
    List<Map> findFundLevelList(Map map);

    Integer findFundLevelListCount(Map map);


    /**
     * 通过基金代码查询基金等级
     *
     * @param map
     */
    List<Map> findFundLevelByFundInfoCode(Map map);
}
