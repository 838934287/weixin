package com.huawei.dao;

import java.util.List;

import com.huawei.model.WeixinUserModel;

public interface WeixinUserModelMapper {
    int deleteByPrimaryKey(int id);
    int deleteUserByOpenId(String string);
    int insert(WeixinUserModel record);

    int insertSelective(WeixinUserModel record);
/**
 * 查询用户是否存在
 * 
 * @param id
 * @return
 */
    WeixinUserModel selectByPrimaryKey(String openId);

    int updateByPrimaryKeySelective(WeixinUserModel record);

    int updateByPrimaryKey(WeixinUserModel record);

    

}