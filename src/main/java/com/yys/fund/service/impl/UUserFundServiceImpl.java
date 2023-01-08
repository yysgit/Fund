package com.yys.fund.service.impl;

import com.yys.fund.mapper.UUserFundMapper;
import com.yys.fund.service.UUserFundService;
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
public class UUserFundServiceImpl implements UUserFundService {
    @Autowired
    private UUserFundMapper userFundMapper;

    @Override
    public int addUserFund(Map map) {
        return userFundMapper.addUserFund(map);
    }


    @Override
    public int updateUserFund(Map map) {
        return userFundMapper.updateUserFund(map);
    }

    @Override
    public int deleteUserFund(Map map) {
        return userFundMapper.deleteUserFund(map);
    }

    @Override
    public List<Map> findUserFundList(Map map) {
        return userFundMapper.findUserFundList(map);
    }

    @Override
    public Integer findUserFundListCount(Map map) {
        return userFundMapper.findUserFundListCount(map);
    }

    @Override
    public List<Map> findUserFundByCode(Map map) {
        return userFundMapper.findUserFundByCode(map);
    }

}
