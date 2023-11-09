package cn.itcast.mq;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class customer {

//    @RabbitListener(queues = "simple.queue")
//    public void listen(String s) throws IOException {
//        System.out.println(s + " ---- ");
//    }

    @RabbitListener(queues = "simple.queue")
    public void listen_work(String s) throws IOException, InterruptedException {
        Thread.sleep(20);
        System.out.println(s + " ---- 1 ---- ");
    }

    @RabbitListener(queues = "simple.queue")
    public void listen_work_2(String s) throws IOException, InterruptedException {
        Thread.sleep(100);
        System.out.println(s + " ---- 2 ----");
    }

    @RabbitListener(queues = "yuri.queue_1")
    public void listen_fanout(String s) throws IOException, InterruptedException {
        System.out.println(s + " ---- 1 ---- ");
    }

    @RabbitListener(queues = "yuri.queue_2")
    public void listen_fanout_2(String s) throws IOException, InterruptedException {
        System.out.println(s + " ---- 2 ----");
    }

    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(name = "yuri.direct_1"),
        exchange = @Exchange(value = "yuri.direct", type = ExchangeTypes.DIRECT),
        key = {"red","yellow"}
    ))
    public void listen_direct(String s) throws IOException, InterruptedException {
        System.out.println(s + " ---- red yellow ---- ");
    }

    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(name = "yuri.direct_2"),
        exchange = @Exchange(value = "yuri.direct", type = ExchangeTypes.DIRECT),
        key = {"red","blue"}
    ))
    public void listen_direct_2(String s) throws IOException, InterruptedException {
        System.out.println(s + " ---- red blue ----");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "yuri.topic_1"),
            exchange = @Exchange(value = "yuri.topic", type = ExchangeTypes.TOPIC),
            key = "#.news"
    ))
    public void listen_topic(String s) throws IOException, InterruptedException {
        System.out.println(s + " ---- topic news ---- ");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "yuri.topic_2"),
            exchange = @Exchange(value = "yuri.topic", type = ExchangeTypes.TOPIC),
            key = "china.#" // # 匹配多个 * 匹配一个
    ))
    public void listen_topic_2(String s) throws IOException, InterruptedException {
        System.out.println(s + " ---- topic china ----");
    }

    @RabbitListener(queues = "yuri.object_queue")
    public void listenObject(Map<String, String> map) throws IOException, InterruptedException {
        System.out.println(map + " ---- object ----");
    }
}
