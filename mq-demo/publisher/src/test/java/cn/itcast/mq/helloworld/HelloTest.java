package cn.itcast.mq.helloworld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void hello() {
        String yuri = "yuri is yes";
        rabbitTemplate.convertAndSend("simple.queue", yuri);
    }

    @Test
    public void work() throws InterruptedException {
        for(int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend("simple.queue", "this is " + i + " msg");
            System.out.println("this is " + i + " msg");
            Thread.sleep(20);
        }
    }

    @Test
    public void fanoutTest() {
        rabbitTemplate.convertAndSend("yuri.fanout", "", "hello every one!");
    }

    @Test
    public void directTest() {
        rabbitTemplate.convertAndSend("yuri.direct", "red", "hello every one!");
    }

    @Test
    public void topicTest() {
        rabbitTemplate.convertAndSend("yuri.topic", "china.news", "hello every one!");
    }

    @Test
    public void objectTest() {
        Map<String, String> map = new HashMap<>();
        map.put("yuri", "is yes");
        map.put("this", "is good");
        rabbitTemplate.convertAndSend("yuri.object_queue",  map);
    }
}
