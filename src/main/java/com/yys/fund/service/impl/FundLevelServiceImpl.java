package com.yys.fund.service.impl;

import com.yys.fund.mapper.FFundLevelMapper;
import com.yys.fund.service.FFundLevelService;
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
public class FundLevelServiceImpl implements FFundLevelService {
    @Autowired
    private FFundLevelMapper fundLevelMapper;

    @Override
    public int addFundLevel(Map map) {
        return fundLevelMapper.addFundLevel(map);
    }

    @Override
    public int updateFundLevel(Map map) {
        return fundLevelMapper.updateFundLevel(map);
    }

    @Override
    public List<Map> findFundLevelList(Map map) {
        return fundLevelMapper.findFundLevelList(map);
    }

    @Override
    public Integer findFundLevelListCount(Map map) {
        return fundLevelMapper.findFundLevelListCount(map);
    }

    @Override
    public List<Map> findFundLevelByFundInfoCode(Map map) {
        return fundLevelMapper.findFundLevelByFundInfoCode(map);
    }
}
