package com.example.Good_plants.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {
    private String name;
    private String password;
    private String roles;
}