package com.dealflowbus.maineurekanamingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MainEurekaNamingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainEurekaNamingServerApplication.class, args);
	}

}
