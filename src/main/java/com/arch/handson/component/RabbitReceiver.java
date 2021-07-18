package com.arch.handson.component;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * TITLE: Create a RabbitMQ Message Receiver [T:#1-1]
 *
 * RabbitReceiver is a POJO that defines a method for receiving rabbitmq messages.
 * When you register it to receive messages, you can name it anything you want.
 */
@Component
public class RabbitReceiver {

    /*
    For convenience, this POJO also has a CountDownLatch.
    This lets it signal that the message has been received.
    This is something you are not likely to implement in a production application.
     */
    private CountDownLatch latch = new CountDownLatch(1);

    public void rabbitMQMessage(String message) {
        System.out.println("RabbitMQ Received <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
