package com.example.batch.example;

import com.example.batch.example.entity.Customer;
import com.example.batch.example.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomerItemWriter implements ItemWriter<Customer> {

    private final CustomerRepository customerRepository;

    public CustomerItemWriter(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public void write(Chunk<? extends Customer> chunk) throws Exception {
        log.info("Writing customers to the database");
        customerRepository.saveAll(chunk.getItems());
    }
}
