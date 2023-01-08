package com.yys.fund.service;

import java.util.List;
import java.util.Map;

public interface UFundTransactionService {

    List<Map> findFundTransactionPurchaseList(Map map);
    Integer findFundTransactionPurchaseCount(Map map);
}