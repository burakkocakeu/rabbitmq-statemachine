package com.arch.handson.component;

import com.arch.handson.HandsonApplication;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RabbitRunner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitReceiver receiver;

    public RabbitRunner(RabbitTemplate rabbitTemplate, RabbitReceiver receiver) {
        this.rabbitTemplate = rabbitTemplate;
        this.receiver = receiver;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending rabbitmq message...");
        rabbitTemplate.convertAndSend(HandsonApplication.topicExchangeName, "foo-bar.baz", "Hello from RabbitMQ!");
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }
}
