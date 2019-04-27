package com.huawei.service;

import java.util.List;

import com.huawei.model.*;;

public interface IPersonService {

    /**
     * 加载全部的person
     * @return
     */
    //List<Person> loadPersons();

	List<PersonModel> loadPersons();
}