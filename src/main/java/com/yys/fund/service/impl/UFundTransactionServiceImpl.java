package com.yys.fund.service.impl;

import com.yys.fund.mapper.UFundTransactionMapper;
import com.yys.fund.service.UFundTransactionService;
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
public class UFundTransactionServiceImpl implements UFundTransactionService {
    @Autowired
    private UFundTransactionMapper fundTransactionMapper;

    @Override
    public List<Map> findFundTransactionPurchaseList(Map map) {
        return fundTransactionMapper.findFundTransactionPurchaseList(map);
    }

    @Override
    public Integer findFundTransactionPurchaseCount(Map map) {
        return fundTransactionMapper.findFundTransactionPurchaseCount(map);
    }

    @Override
    public Integer addUserFundTtransactionPurchase(Map map) {
        return fundTransactionMapper.addUserFundTtransactionPurchase(map);
    }
}
