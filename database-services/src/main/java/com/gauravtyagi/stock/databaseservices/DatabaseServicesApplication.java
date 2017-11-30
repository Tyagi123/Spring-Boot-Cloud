package com.gauravtyagi.stock.databaseservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableEurekaClient
@SpringBootApplication
@EnableJpaRepositories(basePackages="com.gauravtyagi.stock.databaseservices.repository")
public class DatabaseServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseServicesApplication.class, args);
	}
}
