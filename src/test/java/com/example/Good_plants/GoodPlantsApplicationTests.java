package com.example.Good_plants;

import com.example.Good_plants.controller.PostmanController;
import com.example.Good_plants.entity.Customer;
import com.example.Good_plants.entity.Order;
import com.example.Good_plants.entity.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GoodPlantsApplicationTests {
	@Autowired
	private PostmanController postmanController;
	@Test
	public void contextLoads() {
		assertThat(postmanController).isNotNull();
	}
	@Test
	public void testGetCustomers() {
		ResponseEntity<List<Customer>> responseEntity = postmanController.getCustomers();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();
	}
	@Test
	public void testGetOrders() {
		ResponseEntity<List<Order>> responseEntity = postmanController.getOrders();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();
	}
	@Test
	public void testGetPositions() {
		ResponseEntity<List<Product>> responseEntity = postmanController.getPostions();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();
	}
}
