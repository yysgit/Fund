package com.yys.fund.service.impl;

import com.yys.fund.entity.FFundType;
import com.yys.fund.mapper.FFundTypeMapper;
import com.yys.fund.service.FFundTypeService;
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
public class FundTypeServiceImpl implements FFundTypeService {
    @Autowired
    private FFundTypeMapper fundTypeMapper;

    @Override
    public int addFundType(FFundType fundType) {
        return fundTypeMapper.addFundType(fundType);
    }
    


    @Override
    public int updateFundType(FFundType fundType) {
        return fundTypeMapper.updateFundType(fundType);
    }

    @Override
    public int deleteFundType(FFundType fundType) {
        return fundTypeMapper.deleteFundType(fundType);
    }

    @Override
    public List<FFundType> findFundTypeList(Map map) {
        return fundTypeMapper.findFundTypeList(map);
    }

    @Override
    public List<FFundType> findFundTypeAllList() {
        return fundTypeMapper.findFundTypeAllList();
    }

    @Override
    public Integer findFundTypeListCount(Map map) {
        return fundTypeMapper.findFundTypeListCount(map);
    }

    @Override
    public List<FFundType> findFundTypeByNameAndNumber(FFundType fundType) {
        return fundTypeMapper.findFundTypeByNameAndNumber(fundType);
    }
}
