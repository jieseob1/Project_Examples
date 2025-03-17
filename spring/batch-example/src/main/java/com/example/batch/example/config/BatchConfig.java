package com.example.batch.example.config;

import com.example.batch.example.entity.Customer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    private final JobRepository jobRepository; // 배치 작업의 실행 상태를 저장하고 관리하는 역할
    /**
     *  배치 실행 상태를 데이터베이스에 저장하고 관리하는 역할이다.
     * 배치 실행 정보를 데이터베이스에 저장 (JobExecution, StepExecution 등)
     * 배치 작업을 재시작할 수 있도록 상태 유지
     * 현재 실행 중인 배치 작업의 상태를 확인할 수 있음
     */
    private final PlatformTransactionManager transactionManager; //  트랜잭션을 관리하는 역할
    /**
     * 배치에서 트랜잭션이 필요한 이유
     * 배치 작업이 실행될 때 Step 단위로 트랜잭션이 적용돼야 함.
     * chunk(10)으로 설정하면 10개 단위로 트랜잭션이 걸려야 함.
     * 만약 배치 실행 중 에러가 발생하면, 현재 처리 중인 chunk는 롤백됨.
     */
    private final ItemReader<Customer> itemReader;
    private final ItemProcessor<Customer, Customer> itemProcessor;
    private final ItemWriter<Customer> itemWriter;

    public BatchConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager, ItemReader<Customer> itemReader, ItemProcessor<Customer, Customer> itemProcessor, ItemWriter<Customer> itemWriter) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
        this.itemReader = itemReader;
        this.itemProcessor = itemProcessor;
        this.itemWriter = itemWriter;
    }

    @Bean
    public Step customerStep() {
        return new StepBuilder("customerStep", jobRepository)
                .<Customer, Customer>chunk(10, transactionManager) // 10개씩 처리하도록 설정
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter) // itemReader, itemProcessor, itemWriter가 순차적으로 실행됨
                .build();
    }

    @Bean
    public Job customerJob() { // 얘를 실행하면, customerStep()을 실행하게 됨
        return new JobBuilder("customerJob", jobRepository) // 배치 작업의 이름 설정
                .incrementer(new RunIdIncrementer())
                .start(customerStep())
                .build();
        /**
         * 한마디로! "customerJob"은 배치 작업을 식별하는 이름이고, 실행 기록을 관리하는 데 사용됨.
         * 배치 실행 이력을 데이터베이스에 저장할 때, job 이름을 기준으로 관맇ㄴ다.
         * 동일한 Job을 여러 번 실행할 경우, JobExecution을 구별할 수 있다.
         * 특정 Job을 조회할 때, "customerJob"이름을 사용해서 찾을 수 있다.
         */
    }
}
