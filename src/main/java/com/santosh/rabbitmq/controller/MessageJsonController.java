package com.santosh.rabbitmq.controller;

import com.santosh.rabbitmq.dto.UserDto;
import com.santosh.rabbitmq.publisher.RabbitMqJsonProducer;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MessageJsonController {

    private final RabbitMqJsonProducer rabbitMqJsonProducer;

    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody UserDto userDto) {
        rabbitMqJsonProducer.sendJsonMessage(userDto);
        return ResponseEntity.ok("json message sent to broker");
    }

}
