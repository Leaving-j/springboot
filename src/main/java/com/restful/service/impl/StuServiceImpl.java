package com.restful.service.impl;

import com.restful.bo.Student;
import com.restful.dao.StuDao;
import com.restful.dao.StuMybaitsDao;
import com.restful.dao.StuRedisDao;
import com.restful.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StuServiceImpl implements StuService {
    @Autowired
    private StuDao stuDao;
    @Autowired
    private StuMybaitsDao stuMybaitsDao;
    @Autowired
    private StuRedisDao stuRedisDao;

    @Override
    public void addStudent(Student student) {
        stuDao.save(student);
    }

    @Override
    public void deleteStudent(int id) {
        stuDao.deleteById(id);
    }

    @Override
    public void updateStudent(Student student) {
        stuDao.save(student);
    }

    @Override
    public Optional<Student> findStudentById(int id) {
        return stuDao.findById(id);
    }

    @Override
    public List<Student> findAllStudent() {
        return stuDao.findAll();
    }

    @Override
    public void addStudentByMybatis(Student student) {
        stuMybaitsDao.insertSelective(student);
    }

    @Override
    public void deleteStudentByMybatis(int id) {
        stuMybaitsDao.deleteByPrimaryKey(id);
    }

    @Override
    public void updateStudentByMybatis(Student student) {
        stuMybaitsDao.updateByPrimaryKeySelective(student);
    }

    @Override
    public Student findStudentByIdByMybatis(int id) {
        return stuMybaitsDao.selectByPrimaryKey(id);
    }

    @Override
    public List<Student> findAllStudentByMybatis() {
        return stuMybaitsDao.seletAll();
    }

    /**
     * redis 方式存储
     */

    @Override
    public void addOrUpdateStudentByRedis(Student student) {
        stuRedisDao.insertOrUpdate(student);
    }

    @Override
    public void deleteStudentByRedis(int id) {
        stuRedisDao.deleteByPrimaryKey(id);
    }

    @Override
    public String findStudentByIdByRedis(int id) {
        return stuRedisDao.selectByPrimaryKey(id);
    }
}
