package com.github.alecmus.customeronboarding.workers;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.worker.JobWorker;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Component
public class Workers {

    @Autowired
    private ZeebeClient client;

    @Autowired
    private RestTemplate restTemplate;

    @PostConstruct
    public void createWorkers() {
        JobWorker scoreCustomerWorker = client.newWorker()
                .jobType("scoreCustomer")
                .handler(new ScoreCustomerHandler())
                .timeout(Duration.ofSeconds(10).toMillis())
                .open();

        JobWorker addCustomerToCrm = client.newWorker()
                .jobType("addCustomerToCrm")
                .handler(new AddCustomerToCrmHandler(restTemplate))
                .timeout(Duration.ofSeconds(10).toMillis())
                .open();
    }
}
