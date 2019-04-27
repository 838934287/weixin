package com.huawei.dao;

import com.huawei.model.WeixinUserOrderModel;

public interface WeixinUserOrderModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WeixinUserOrderModel record);

    int insertSelective(WeixinUserOrderModel record);

    WeixinUserOrderModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WeixinUserOrderModel record);

    int updateByPrimaryKey(WeixinUserOrderModel record);
}