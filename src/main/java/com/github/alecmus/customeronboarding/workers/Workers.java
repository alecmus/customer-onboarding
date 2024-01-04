package com.github.alecmus.customeronboarding.workers;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.worker.JobWorker;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Component
public class Workers {

    private static Logger logger = LoggerFactory.getLogger(Workers.class);

    private ZeebeClient client;
    private RestTemplate restTemplate;

    @Autowired
    public Workers(ZeebeClient client, RestTemplate restTemplate) {
        this.client = client;
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    public void createWorkers() {
        logger.info("Creating workers");

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

        logger.info("Done creating workers");
    }
}
