package com.yys.fund.service;

import java.util.List;
import java.util.Map;

public interface UFundTransactionService {

    List<Map> findFundTransactionPurchaseList(Map map);
    Integer findFundTransactionPurchaseCount(Map map);
    List<Map> findFundTransactionSellList(Map map);
    Integer findFundTransactionSellCount(Map map);
    Integer addUserFundTtransactionPurchase(Map map);
    Integer addUserFundTtransactionSell(Map map);
    int deleteUserFundTransaction(Map map);
    int deleteUserFundTransactionSell(Map map);

}