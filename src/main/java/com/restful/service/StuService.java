package com.restful.service;

import com.restful.bo.Student;

import java.util.List;
import java.util.Optional;

public interface StuService {
    // jpa
    public void addStudent(Student student);

    public void deleteStudent(int id);

    public void updateStudent(Student student);

    public Optional<Student> findStudentById(int id);

    public List<Student> findAllStudent();

    // mybatis
    public void addStudentByMybatis(Student student);

    public void deleteStudentByMybatis(int id);

    public void updateStudentByMybatis(Student student);

    public Student findStudentByIdByMybatis(int id);

    public List<Student> findAllStudentByMybatis();

    // redis
    public void addOrUpdateStudentByRedis(Student student);

    public void deleteStudentByRedis(int id);

    public String findStudentByIdByRedis(int id);

}
