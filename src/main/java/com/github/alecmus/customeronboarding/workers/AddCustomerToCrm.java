package com.github.alecmus.customeronboarding.workers;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class AddCustomerToCrm {

    private static Logger logger = LoggerFactory.getLogger(AddCustomerToCrm.class);

    public static String ENDPOINT_CRM = "http://localhost:8080/crm/customer";

    RestTemplate restTemplate;

    public AddCustomerToCrm(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /*
     * Using the @Variable annotation to inject a specific variable into the JobWorker
     */
    @JobWorker(type = "addCustomerToCrm")
    public void addCustomerToCrm(@Variable String customerName) {
        logger.info("Adding customer to CRM via REST: " + customerName);
        String request = "someData";
        restTemplate.put(ENDPOINT_CRM, request);
    }
}
