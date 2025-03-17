package com.example.batch.example.processor;

import com.example.batch.example.entity.Customer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class NamePrefixProcessor implements ItemProcessor<Customer, Customer> {

    @Override
    public Customer process(Customer customer) throws Exception {
        customer.setName("Processed_" + customer.getName());
        return customer;
    }
}
