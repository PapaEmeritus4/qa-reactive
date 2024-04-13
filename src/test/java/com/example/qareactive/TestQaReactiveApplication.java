package com.example.qareactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestQaReactiveApplication {

	public static void main(String[] args) {
		SpringApplication.from(QaReactiveApplication::main).with(TestQaReactiveApplication.class).run(args);
	}

}
