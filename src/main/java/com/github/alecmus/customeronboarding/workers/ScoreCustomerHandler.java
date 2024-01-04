package com.github.alecmus.customeronboarding.workers;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.client.api.worker.JobHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ScoreCustomerHandler implements JobHandler {

    private static Logger logger = LoggerFactory.getLogger(ScoreCustomerHandler.class);

    @Override
    public void handle(JobClient client, ActivatedJob job) throws Exception {
        final Map<String, Object> inputVariables = job.getVariablesAsMap();
        final String customerName = (String)inputVariables.get("customerName");

        logger.info("Scoring customer: " + customerName);
        final Map<String, Object> outputVariables = new HashMap<>();
        outputVariables.put("score", 42);
        client.newCompleteCommand(job.getKey()).variables(outputVariables).send().join();
    }
}
