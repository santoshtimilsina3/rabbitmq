package com.santosh.rabbitmq.consumer;

import com.santosh.rabbitmq.dto.UserDto;
import com.santosh.rabbitmq.publisher.RabbitMqProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqJsonConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqProducer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void consumeJsonMessage(UserDto userDto) {
        LOGGER.info(String.format("Received message ->", userDto.toString()));
    }
}
