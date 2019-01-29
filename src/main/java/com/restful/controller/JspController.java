package com.restful.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.restful.bo.Student;
import com.restful.service.StuService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @program: restfulproject
 * @description: description
 * @author: lw
 * @create: 2019-01-23 11:03
 **/
@Controller
@RequestMapping(value = "/jsp")
public class JspController {

    private static final Logger logger = LogManager.getLogger(JspController.class);

    @Autowired
    private StuService stuService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/list")
    public String list(Model mv) {
        List<Student> students = this.stuService.findAllStudent();
        Gson gson = new Gson();
        String stuJson = gson.toJson(students);

        // 只能处理字符串
        stringRedisTemplate.opsForValue().set("stuJson",stuJson);
        String redisStr = stringRedisTemplate.opsForValue().get("stuJson");

        // 可以处理对象
       /* redisTemplate.opsForValue().set("stuJson", students);
        List<Student> redisStu = (List<Student>) redisTemplate.opsForValue().get("stuJson");*/

        mv.addAttribute("students", students);
        return "student";
    }
}
