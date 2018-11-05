package com.restful;

import com.restful.mq.springboot.Tut1Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestfulprojectApplicationTests {

    @Autowired
    private Tut1Sender tut1Sender;

    // 测试不使用交换机的queue
    @Test
    public void sender() {
        tut1Sender.send();
    }

    // 测试只匹配一层路径的queue
    @Test
    public void senderCore() {
        tut1Sender.sendCore();
    }

    // 测试匹配多层路径的queue
    @Test
    public void senderPayment() {
        tut1Sender.sendPayment();
    }

}
