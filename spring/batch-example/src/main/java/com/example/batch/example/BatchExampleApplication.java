package com.example.batch.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BatchExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchExampleApplication.class, args); // BatchConfig에 설정된 job이 실행된다
	}

}
