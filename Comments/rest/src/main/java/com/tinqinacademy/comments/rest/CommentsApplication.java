package com.tinqinacademy.comments.rest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@OpenAPIDefinition(
		info = @Info(
				title = "Comments",
				description = "The REST API of Comments"
		)
)

@SpringBootApplication
@ComponentScan(basePackages = "com.tinqinacademy.comments")

public class CommentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommentsApplication.class, args);
	}

}
