package com.restful.controller;

import com.google.gson.Gson;
import com.restful.bo.Student;
import com.restful.mq.MqSender;
import com.restful.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(value = "/stu")
public class StuController {
    //添加分支
    @Autowired
    private StuService stuService;

    @Autowired
    private MqSender mqSender;

    /**
     * 增加
     *
     * @param student
     * @return
     */
    @PostMapping("/add")
    public String addOneStudent(Student student) {
        // jpa 方式
        //this.stuService.addStudent(student);
        // mybatis 方式
        //this.stuService.addStudentByMybatis(student);
        // redis 方式
        this.stuService.addOrUpdateStudentByRedis(student);
        return "success";
    }

    /**
     * 增加
     *
     * @param student
     * @return
     */
    @PostMapping("/addOne")
    public void addOneStudentByAjax(@RequestBody Student student) {
        // jpa
        //this.stuService.addStudent(student);
        // mybaits
        this.stuService.addStudentByMybatis(student);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public void deleteStudent(@PathVariable("id") int id) {
        // this.stuService.deleteStudent(id);
        // this.stuService.deleteStudentByMybatis(id);
        this.stuService.deleteStudentByRedis(id);
    }

    /**
     * 修改
     *
     * @param student
     */
    @PostMapping("/update")
    public void updateStudent(Student student) {
        //this.stuService.updateStudent(student);
        this.stuService.updateStudentByMybatis(student);
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @GetMapping("/findOne/{id}")
    public String findStudentById(@PathVariable("id") int id) {
        // jpa
        /*Optional<Student> studentById = this.stuService.findStudentById(id);
        return studentById.get();*/
        // mybatis
        //return this.stuService.findStudentByIdByMybatis(id);
        // redis
        return this.stuService.findStudentByIdByRedis(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping("/findAll")
    public List<Student> findAllStudent() {
        //return this.stuService.findAllStudent();
        return this.stuService.findAllStudent();
    }

    /**
     * 存一个对象到redis，并且通过mq存到数据库
     *
     * @param student
     * @return
     */
    @PostMapping(value = "/addStuByMq")
    public String addStudentByMq(@RequestBody Student student) {
        // 先存到redis
        stuService.addOrUpdateStudentByRedis(student);
        Gson gson = new Gson();
        String stuJson = gson.toJson(student);
        // 给mq发消息存数据库
        mqSender.sendStu(stuJson);
        return "success";
    }

}
