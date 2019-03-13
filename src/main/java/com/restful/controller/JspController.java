package com.restful.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.restful.bo.Student;
import com.restful.service.StuService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

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
        logger.info("----- 查询列表 -----");
        List<Student> students = this.stuService.findAllStudent();
        Gson gson = new Gson();
        String stuJson = gson.toJson(students);

        // 只能处理字符串
        stringRedisTemplate.opsForValue().set("stuJson", stuJson);
        String redisStr = stringRedisTemplate.opsForValue().get("stuJson");

        // 可以处理对象
       /* redisTemplate.opsForValue().set("stuJson", students);
        List<Student> redisStu = (List<Student>) redisTemplate.opsForValue().get("stuJson");*/

        mv.addAttribute("students", students);
        return "student";
    }

    @GetMapping("/findOne/{id}")
    @ResponseBody
    public Student findStudentById(@PathVariable("id") int id) {
        return this.stuService.findStudentByIdByMybatis(id);
    }

    @RequestMapping(value = "/rest")
    public String rest(Model mv) {
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        String url = "http://localhost:8080/jsp/findOne/2";
        Student student = restTemplate.getForObject(url, Student.class);

        return "student";
    }

}
