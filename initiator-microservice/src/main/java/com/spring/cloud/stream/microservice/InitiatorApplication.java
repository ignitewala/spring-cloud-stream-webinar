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
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.cloud.stream.tasks.Order;
import com.spring.cloud.stream.tasks.Types;

@SpringBootApplication
@EnableBinding(value = Processor.class)
@RestController
public class InitiatorApplication {
	private static final String COOK = "/cook";
	private static final Logger log = LoggerFactory.getLogger(InitiatorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(InitiatorApplication.class, args);
	}

	@Autowired
	private Source source;

	@GetMapping(COOK)
	public String publishOrder(@RequestParam("menu") String menu) {
		Order order = new Order(Types.Cook, menu);
		source.output().send(MessageBuilder.withPayload(order).build());
		log.info("sent {} ", order);
		return "Request sent";
	}

	
}
