package com.github.alecmus.customeronboarding.rest;

import io.camunda.zeebe.client.ZeebeClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import java.util.HashMap;

@RestController
public class CustomerOnboardingRestController {

    @Autowired
    private ZeebeClient client;

    @Value("${recipient.demo.email}")
    private String recipientDemoEmail;

    Logger logger = LoggerFactory.getLogger(CustomerOnboardingRestController.class);

    @PutMapping("/customer")
    public ResponseEntity<CustomerOnboardingResponse> onboardCustomer(ServerWebExchange exchange) {
        onboardCustomer();
        return ResponseEntity.accepted().build();
    }

    public void onboardCustomer() {
        logger.info("onboarding customer");

        HashMap<String, Object> variables = new HashMap<>();
        variables.put("automaticProcessing", true);
        variables.put("someInput", "yeah");
        variables.put("customerName", "Alec Musasa");
        variables.put("customerEmail", recipientDemoEmail);

        client.newCreateInstanceCommand()
                .bpmnProcessId("customer-onboarding")
                .latestVersion()
                .variables(variables)
                .send().join();
    }

    public static class CustomerOnboardingResponse {
    }
}
