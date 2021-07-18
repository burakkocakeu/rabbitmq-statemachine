package com.arch.handson;

import com.arch.handson.component.RabbitReceiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * TITLE: Register the Listener and Send a Message [T:#1-2]
 *
 * Spring AMQP’s RabbitTemplate provides everything you need to send and receive messages with RabbitMQ. However, you need to:
 *  • Configure a message listener container.
 *  • Declare the queue, the exchange, and the binding between them.
 *  • Configure a component to send some messages to test the listener.
 *
 * Spring Boot automatically creates a connection factory and a RabbitTemplate, reducing the amount of code you have to write.
 *
 * You will use RabbitTemplate to send messages, and you will register a Receiver with the message listener container to receive messages.
 * The connection factory drives both, letting them connect to the RabbitMQ server.
 */
@SpringBootApplication
public class HandsonApplication {

    public static final String topicExchangeName = "spring-boot-exchange";

    static final String queueName = "spring-boot";

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(RabbitReceiver receiver) {
        return new MessageListenerAdapter(receiver, "rabbitMQMessage");
    }

    public static void main(String[] args) {
        SpringApplication.run(HandsonApplication.class, args).close();
    }

}
