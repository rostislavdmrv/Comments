package com.tinqinacademy.comments.rest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@OpenAPIDefinition(
		info = @Info(
				title = "Comments",
				description = "The REST API of Comments"
		)
)

@SpringBootApplication
@ComponentScan(basePackages = "com.tinqinacademy.comments")
@EntityScan(basePackages = "com.tinqinacademy.comments.persistence.models.entities")
@EnableJpaRepositories(basePackages = "com.tinqinacademy.comments.persistence.repositories")
@EnableFeignClients
public class CommentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommentsApplication.class, args);
	}

}
