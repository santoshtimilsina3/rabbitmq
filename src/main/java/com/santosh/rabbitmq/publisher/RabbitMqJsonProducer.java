package com.santosh.rabbitmq.publisher;

import com.santosh.rabbitmq.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqJsonProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.json.key}")
    private String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqProducer.class);

    private RabbitTemplate rabbitTemplate;

    RabbitMqJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(UserDto userDto) {
        LOGGER.info(String.format("Json message sent ->", userDto.toString()));
        rabbitTemplate.convertAndSend(exchange, routingKey, userDto);
    }
}
