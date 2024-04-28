package com.example.Good_plants.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDTO {
    private String amount;
    private String product_set;
}
