package com.yys.fund.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UFundTransactionMapper {

    List<Map> findFundTransactionPurchaseList(Map map);
    Integer findFundTransactionPurchaseCount(Map map);

}