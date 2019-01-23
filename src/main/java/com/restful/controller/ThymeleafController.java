package com.restful.controller;

import com.restful.bo.Student;
import com.restful.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @program: restfulproject
 * @description: description
 * @author: lw
 * @create: 2019-01-23 11:03
 **/
@Controller
@RequestMapping(value = "/thymeleaf")
public class ThymeleafController {

    @Autowired
    private StuService stuService;

    @RequestMapping(value = "/list")
    public String list(Model mv) {
        List<Student> students = this.stuService.findAllStudent();
        mv.addAttribute("students", students);
        return "student";
    }
}
