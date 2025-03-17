package com.example.batch.example.processor;

import com.example.batch.example.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class UpperCaseProcessor implements ItemProcessor<Customer, Customer> {

    private static final Logger log = LoggerFactory.getLogger(UpperCaseProcessor.class);

    @Override
    public Customer process(final Customer item) throws Exception {
        log.info("Processing customer information: {}", item);
        item.setEmail(item.getEmail().toUpperCase()); // 이메일을 대문자로 변경
        return item;
    }
}
