package cn.itcast.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("yuri.fanout");
    }

    @Bean
    public Queue queue() {
        return new Queue("yuri.queue_1");
    }

    @Bean
    public Queue objectQueue() {
        return new Queue("yuri.object_queue");
    }

    @Bean
    public Queue queue_2() {
        return new Queue("yuri.queue_2");
    }

    @Bean
    public Binding binding(Queue queue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    @Bean
    public Binding binding_2(Queue queue_2, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue_2).to(fanoutExchange);
    }

}
