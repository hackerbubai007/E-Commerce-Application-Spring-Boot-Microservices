package com.e_com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableRedisRepositories
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CartMicroservice1Application {

	public static void main(String[] args) {
		SpringApplication.run(CartMicroservice1Application.class, args);
	}

}
