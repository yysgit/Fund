package com.yys.fund.service.impl;

import com.yys.fund.mapper.FFundLevelMoneyMapper;
import com.yys.fund.service.FFundLevelMoneyService;
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
public class FundLevelMoneyServiceImpl implements FFundLevelMoneyService {
    @Autowired
    private FFundLevelMoneyMapper fundLevelMoneyMapper;

    @Override
    public int addFundLevelMoney(Map map) {
        return fundLevelMoneyMapper.addFundLevelMoney(map);
    }

    @Override
    public int updateFundLevelMoney(Map map) {
        return fundLevelMoneyMapper.updateFundLevelMoney(map);
    }

    @Override
    public List<Map> findFundLevelMoneyList(Map map) {
        return fundLevelMoneyMapper.findFundLevelMoneyList(map);
    }

    @Override
    public Integer findFundLevelMoneyListCount(Map map) {
        return fundLevelMoneyMapper.findFundLevelMoneyListCount(map);
    }

    @Override
    public List<Map> findFundLevelMoneyByFundInfoCode(Map map) {
        return fundLevelMoneyMapper.findFundLevelMoneyByFundInfoCode(map);
    }
}
