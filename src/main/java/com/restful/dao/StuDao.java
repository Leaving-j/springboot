package com.restful.dao;

import com.restful.bo.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StuDao extends JpaRepository<Student, Integer> {
}
