package com.github.alecmus.customeronboarding.workers;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.client.api.worker.JobHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class AddCustomerToCrmHandler implements JobHandler {

    RestTemplate restTemplate;

    public AddCustomerToCrmHandler(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static Logger logger = LoggerFactory.getLogger(AddCustomerToCrmHandler.class);

    public static String ENDPOINT_CRM = "http://localhost:8080/crm/customer";

    @Override
    public void handle(JobClient client, ActivatedJob job) throws Exception {
        final Map<String, Object> inputVariables = job.getVariablesAsMap();
        final String customerName = (String)inputVariables.get("customerName");

        logger.info("Adding customer to CRM via REST: " + customerName);
        String request = "someData";
        restTemplate.put(ENDPOINT_CRM, request);
        client.newCompleteCommand(job.getKey()).send().join();
    }
}
