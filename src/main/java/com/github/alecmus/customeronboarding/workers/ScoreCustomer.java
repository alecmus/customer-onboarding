package com.github.alecmus.customeronboarding.workers;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ScoreCustomer {

    private static Logger logger = LoggerFactory.getLogger(ScoreCustomer.class);

    /*
     * Injecting JobClient and ActivatedJob to JobWorker.
     * Returning a map of output variables to pass back to the process engine.
     */
    @JobWorker(type = "scoreCustomer")
    public Map<String, Object> scoreCustomer(JobClient client, ActivatedJob job) {
        final Map<String, Object> inputVariables = job.getVariablesAsMap();
        final String customerName = (String)inputVariables.get("customerName");

        logger.info("Scoring customer: " + customerName);
        final Map<String, Object> outputVariables = new HashMap<>();
        outputVariables.put("score", 42);
        return outputVariables;
    }
}
