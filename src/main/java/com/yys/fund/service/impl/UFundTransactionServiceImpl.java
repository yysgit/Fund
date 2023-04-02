package com.yys.fund.service.impl;

import com.yys.fund.mapper.UFundTransactionMapper;
import com.yys.fund.service.UFundTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Map> findFundTransactionPurchaseListGroupBy(Map map) {
        return fundTransactionMapper.findFundTransactionPurchaseListGroupBy(map);
    }

    @Override
    public Integer findFundTransactionPurchaseCountGroupBy(Map map) {
        return fundTransactionMapper.findFundTransactionPurchaseCountGroupBy(map);
    }

    @Override
    public Integer addUserFundTtransactionPurchase(Map map) {
        return fundTransactionMapper.addUserFundTtransactionPurchase(map);
    }

    @Override
    public List<Map> findFundTransactionSellList(Map map) {
        return fundTransactionMapper.findFundTransactionSellList(map);
    }

    @Override
    public Integer findFundTransactionSellCount(Map map) {
        return fundTransactionMapper.findFundTransactionSellCount(map);
    }

    @Override
    @Transactional
    public Integer addUserFundTtransactionSell(Map map) {
        Integer int1=  fundTransactionMapper.addUserFundTtransactionSell(map);
        Integer int2= fundTransactionMapper.updateUserFundTtransactionPurchase(map);
        return int1+int2;
    }

    @Override
    public int deleteUserFundTransaction(Map map) {
        return fundTransactionMapper.deleteUserFundTransaction(map);
    }

    @Override
    public int deleteUserFundTransactionSell(Map map) {
        Integer int2= fundTransactionMapper.updateUserFundTtransactionPurchaseforDelete(map);
        Integer int1=  fundTransactionMapper.deleteUserFundTransactionSell(map);
        return int1+int2;
    }



}
