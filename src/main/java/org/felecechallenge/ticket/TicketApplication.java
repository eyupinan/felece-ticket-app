package org.felecechallenge.ticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"org.felecechallenge.ticket"})
@EnableJpaRepositories(basePackages = {"org.felecechallenge.ticket"})
@EntityScan(basePackages = {"org.felecechallenge.ticket.model"})
public class TicketApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketApplication.class, args);
	}

}
