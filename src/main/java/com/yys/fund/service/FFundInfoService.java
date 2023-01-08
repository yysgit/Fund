package com.yys.fund.service;

import com.yys.fund.entity.FFundInfo;

import java.util.List;
import java.util.Map;


public interface FFundInfoService {


    /**
     * 添加基金信息
     * @param fundInfo
     * @return
     */
    int addFundInfo(FFundInfo fundInfo);

    /**
     * 更新基金信息
     * @param fundInfo
     * @return
     */
    int updateFundInfo(FFundInfo fundInfo);

    /**
     * 删除基金信息
     * @param fundInfo
     * @return
     */
    int deleteFundInfo(FFundInfo fundInfo);
    /**
     * 查询所有的基金信息
     * @return
     */


    List<Map> findFundInfoList(Map map);
    Integer findFundInfoListCount(Map map);


    /**
     * 查询编号是否重复
     * @param fundInfo
     */
    List<Map> findFundInfoByCode(FFundInfo fundInfo);

}