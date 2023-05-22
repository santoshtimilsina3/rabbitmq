package com.santosh.rabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable{
    private int id;
    private String fistName;
    private String lastName;
}
