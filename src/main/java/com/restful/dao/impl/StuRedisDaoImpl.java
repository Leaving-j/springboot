package com.restful.dao.impl;

import com.google.gson.Gson;
import com.restful.bo.Student;
import com.restful.dao.StuRedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StuRedisDaoImpl implements StuRedisDao {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public Boolean deleteByPrimaryKey(Integer id) {
        return redisTemplate.opsForValue().getOperations().delete(String.valueOf(id));
    }

    @Override
    public void insertOrUpdate(Student record) {
        Gson gson = new Gson();
        String stuGson = gson.toJson(record);
        redisTemplate.opsForValue().set(String.valueOf(record.getId()), stuGson);
    }

    @Override
    public String selectByPrimaryKey(Integer id) {
        return redisTemplate.opsForValue().get(String.valueOf(id));
    }

}
