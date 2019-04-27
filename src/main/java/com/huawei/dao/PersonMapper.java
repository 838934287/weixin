package com.huawei.dao;

import java.util.List;

import com.huawei.model.PersonModel;

public interface PersonMapper {
    /**
     * 插入一条记录
     * @param person
     */
    void insert(PersonModel person);
    
    /**
     * 查询所有
     * @return
     */
    List<PersonModel> queryAll();
}