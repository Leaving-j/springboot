package com.restful.mq;

import com.google.gson.Gson;
import com.restful.bo.Student;
import com.restful.service.StuService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

public class MqReceiver {

    @Autowired
    private StuService stuService;

    /**
     * mq接收json数据做数据库持久化操作
     * @param stu
     */
    @RabbitHandler
    @RabbitListener(queues = "api.payment")
    public void receiverStu(String stu) {
        Gson gson = new Gson();
        Student student = gson.fromJson(stu,Student.class);
        stuService.addStudentByMybatis(student);
    }
}
