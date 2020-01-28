package com.company.rsvpservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RsvpServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RsvpServiceApplication.class, args);
	}

}
