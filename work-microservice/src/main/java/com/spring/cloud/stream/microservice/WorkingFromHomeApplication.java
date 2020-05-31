package com.spring.cloud.stream.microservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.web.bind.annotation.RestController;

import com.spring.cloud.stream.tasks.Order;

@SpringBootApplication
@EnableBinding(value = Processor.class)
@RestController
public class WorkingFromHomeApplication {
	private static final int NOT_MORE_THAN_EIGHT_HOURS_A_DAY = 5*1000;
	private static final Logger log = LoggerFactory.getLogger(WorkingFromHomeApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WorkingFromHomeApplication.class, args);
	}


	@StreamListener(target = Sink.INPUT)
	public void listenForOrder(Order order) throws Exception{
		log.info(" received new order [" + order + "] ");
		work_I_mean_sleep(NOT_MORE_THAN_EIGHT_HOURS_A_DAY);
		log.info("I'm done. Lets watch Netflix ");
	}


	private void work_I_mean_sleep(long howLong) throws InterruptedException {
		Thread.sleep(howLong);
	}

	
}
