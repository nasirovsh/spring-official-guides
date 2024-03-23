package com.example.restservice;

import com.example.consumingrest.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestServiceApplication {

	private static final Logger log = LoggerFactory.getLogger(RestServiceApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(RestServiceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	@Profile("!test")
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Quote quote = restTemplate.getForObject(
					"http://localhost:8080/api/random", Quote.class);
			log.info(quote.toString());
		};
	}

	// run the application by using ./gradlew bootRun
	// or
	// build the JAR file by using ./gradlew build
	// java -jar build/libs/gs-consuming-rest-0.1.0.jar
	// java -jar target/gs-consuming-rest-0.1.0.jar

}
