package com.restful.controller;

import com.restful.bo.Student;
import com.restful.service.StuService;
import com.restful.utils.RSADecrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/stu")
public class StuController {

    @Autowired
    private StuService stuService;

    @PostMapping("/add")
    public String addOneStudent(Student student) {
        //jpa 方式
        //this.stuService.addStudent(student);
        //mybatis 方式
        this.stuService.addStudentByMybatis(student);
        return "success";
    }

    @PostMapping("/addOne")
    public void addOneStudentByAjax(@RequestBody Student student) {
        // jpa
        //this.stuService.addStudent(student);
        // mybaits
        this.stuService.addStudentByMybatis(student);
    }

    @GetMapping("/delete/{id}")
    public void deleteStudent(@PathVariable("id") int id) {
        // this.stuService.deleteStudent(id);
        this.stuService.deleteStudentByMybatis(id);
    }

    @PostMapping("/update")
    public void updateStudent(Student student) {
        //this.stuService.updateStudent(student);
        this.stuService.updateStudentByMybatis(student);
    }

    @GetMapping("/findOne/{id}")
    public Student findStudentById(@PathVariable("id") int id) {
        /*Optional<Student> studentById = this.stuService.findStudentById(id);
        return studentById.get();*/
        return this.stuService.findStudentByIdByMybatis(id);
    }

    @GetMapping("/findAll")
    public List<Student> findAllStudent() {
        //return this.stuService.findAllStudent();
        return this.stuService.findAllStudent();
    }

    @PostMapping("/crypter")
    public void decrypter(String pwd) {
        pwd = pwd.replace(" ","+");
        String str = RSADecrypter.decryptByPrivateKey(pwd,RSADecrypter.PRIVATEKEY_NEW,6);
        System.out.println("RSA解密后的字符串："  + str);
    }
}
