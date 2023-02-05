package com.yys.fund.mapper;

import com.yys.fund.entity.FFundNetWorth;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UFundTransactionMapper {

    List<Map> findFundTransactionPurchaseList(Map map);
    Integer findFundTransactionPurchaseCount(Map map);

    Integer addUserFundTtransactionPurchase(Map map);

    Integer updateFundTransactionPurchaseForTask();

    Integer updateFundTransactionPurchaseForTemp( FFundNetWorth fundNetWorth);
}