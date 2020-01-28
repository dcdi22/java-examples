package com.trilogyed.tuitionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TuitionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TuitionServiceApplication.class, args);
	}

}
