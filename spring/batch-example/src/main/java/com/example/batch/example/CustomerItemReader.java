package com.example.batch.example;

import com.example.batch.example.entity.Customer;
import com.example.batch.example.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CustomerItemReader implements ItemReader<Customer> {

    private final CustomerRepository customerRepository;
    private List<Customer> customers;
    private int idx = 0;

    public CustomerItemReader(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (customers == null) {
            log.info("Reading customers from the database");
            customers = customerRepository.findAll();
        }
        log.info("Reading customers from the database");
        return idx < customers.size() ? customers.get(idx++) : null; // idx가 customers.size()보다 작으면 customers.get(idx)를 반환하고 idx를 증가시킴
    }
}
