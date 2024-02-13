package de.opstream.shoppinglist.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(
		title = "Shopping List API",
		version = "1.0",
		description = "REST API for managing a Shopping List."
))
@SpringBootApplication
public class ShoppingListApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingListApiApplication.class, args);
	}

}
