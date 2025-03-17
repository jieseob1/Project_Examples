package com.example.batch.example.config;

import com.example.batch.example.entity.Customer;
import com.example.batch.example.processor.NamePrefixProcessor;
import com.example.batch.example.processor.UpperCaseProcessor;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class BatchProcessorConfig {

    @Bean
    public CompositeItemProcessor<Customer,Customer> compositeItemProcessor(
            UpperCaseProcessor uppercaseProcessor,
            NamePrefixProcessor namePrefixProcessor
    ){
        CompositeItemProcessor<Customer,Customer> compositeItemProcessor = new CompositeItemProcessor<>();
        compositeItemProcessor.setDelegates(Arrays.asList(uppercaseProcessor, namePrefixProcessor));
        return compositeItemProcessor;
    }
}
