package com.github.alecmus.customeronboarding.fakes;

import com.github.alecmus.customeronboarding.rest.CustomerOnboardingRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

@RestController
public class CrmServiceRestController {

    private Logger logger = LoggerFactory.getLogger(CustomerOnboardingRestController.class);

    @PutMapping("/crm/customer")
    public ResponseEntity<String> addCustomerToCrmFake(ServerWebExchange exchange) {
        logger.info("CRM REST API called");
        return ResponseEntity.ok().build();
    }
}
