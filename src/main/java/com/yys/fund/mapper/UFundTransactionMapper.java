package com.yys.fund.mapper;

import com.yys.fund.entity.FFundNetWorth;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UFundTransactionMapper {

    List<Map> findFundTransactionPurchaseList(Map map);
    Integer findFundTransactionPurchaseCount(Map map);

    List<Map> findFundTransactionSellList(Map map);
    Integer findFundTransactionSellCount(Map map);

    Integer addUserFundTtransactionPurchase(Map map);
    Integer addUserFundTtransactionSell(Map map);
    Integer updateUserFundTtransactionPurchase(Map map);


    int deleteUserFundTransactionSell(Map map);
    Integer updateUserFundTtransactionPurchaseforDelete(Map map);

    Integer updateFundTransactionPurchaseForTask();
    Integer updateFundTransactionSellForTask();

    Integer updateFundTransactionPurchaseForTemp( FFundNetWorth fundNetWorth);
    int deleteUserFundTransaction(Map map);
}