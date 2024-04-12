package com.sadyrdas.accountmanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableEurekaClient
public class AccountManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountManagementServiceApplication.class, args);
	}

}
