package com.spring.cloud.stream.microservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import com.spring.cloud.stream.tasks.Order;
import com.spring.cloud.stream.tasks.Types;

@SpringBootApplication
@EnableBinding(value = Processor.class)
@RestController
public class CookingApplication {
	private static final int COOKING_TIME = 2000;
	private static final Logger log = LoggerFactory.getLogger(CookingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CookingApplication.class, args);
	}

	@StreamListener(target = Sink.INPUT)
	@SendTo(value = Source.OUTPUT)
	public Order listenForOrder(Order order) throws Exception{
        //int i = 2/0;
		log.info(" received new order [" + order + "] ");
		cook();
		log.info("My breakfast is ready!!!");
		return new Order(Types.Work, "Look at the kanban board and pick a story -> after processing ="+order.getInstruction());
	}

	private void cook() throws InterruptedException {
		Thread.sleep(COOKING_TIME);
	}

}
