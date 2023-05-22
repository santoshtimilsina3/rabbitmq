package com.santosh.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.queue.json.name}")
    private String jsonQueue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.routing.json.key}")
    private String jsonRoutingKey;

    //Spring bean for rabbitmq Queue
    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    @Bean
    public Queue jsonQueue() {
        return new Queue(jsonQueue);
    }

    //rabbitmq exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }


    //binding between queue and exchange using routing key
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    //Binding jsonqueue with exchange
    @Bean
    public Binding jsonBinding() {
        return BindingBuilder.bind(jsonQueue())
                .to(exchange())
                .with(jsonRoutingKey);
    }
//Rabbitmq automatically configures these below three configurations
    //ConnectionFactory
    //RabbitMqTemplate
    //RabitAdmin


    //making the rabbitmq accept json type
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
