package com.yys.fund.service.impl;

import com.yys.fund.entity.FFundInfo;
import com.yys.fund.mapper.FFundInfoMapper;
import com.yys.fund.service.FFundInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Describe:
 * -------------------
 * User: yangyongsheng
 * Date: 2019/06/25 19:34:53
 * Email: 1095737364@qq.com
 */
@Service
public class FFundInfoServiceImpl implements FFundInfoService {
    @Autowired
    private FFundInfoMapper fundInfoMapper;

    @Override
    public int addFundInfo(FFundInfo fundInfo) {
        return fundInfoMapper.addFundInfo(fundInfo);
    }


    @Override
    public int updateFundInfo(FFundInfo fundInfo) {
        return fundInfoMapper.updateFundInfo(fundInfo);
    }

    @Override
    public int deleteFundInfo(FFundInfo fundInfo) {
        return fundInfoMapper.deleteFundInfo(fundInfo);
    }

    @Override
    public List<Map> findFundInfoList(Map map) {
        return fundInfoMapper.findFundInfoList(map);
    }

    @Override
    public Integer findFundInfoListCount(Map map) {
        return fundInfoMapper.findFundInfoListCount(map);
    }

    @Override
    public List<Map> findFundInfoByCode(FFundInfo fundInfo) {
        return fundInfoMapper.findFundInfoByCode(fundInfo);
    }

}
