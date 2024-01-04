package com.github.alecmus.customeronboarding;

import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// attempts deploying the diagram on startup using zeebe.client.cloud creds in properties
@Deployment(resources = "classpath:customer-onboarding.bpmn")
@SpringBootApplication
public class CustomerOnboardingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerOnboardingApplication.class, args);
	}

}
