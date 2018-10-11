package com.restful.dao;

import com.restful.bo.Student;

import java.util.List;

public interface StuRedisDao {
    Boolean deleteByPrimaryKey(Integer id);

    void insertOrUpdate(Student record);

    String selectByPrimaryKey(Integer id);

}
