package com.qianjin.ssm.userservice;

import com.qianjin.ssm.userservice.logic.component.DemoSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserServiceApplication.class)
public class UserServiceApplicationTests {

    @Autowired
    private DemoSender sender;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testRabbitMq() {
        String content = "这是一条mq消息 ";

        for (int i = 0; i < 10; i++) {
            sender.sendMsg(content + i);
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
