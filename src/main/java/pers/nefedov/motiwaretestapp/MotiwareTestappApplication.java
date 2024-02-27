package pers.nefedov.motiwaretestapp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Motiware test application API",
				description = "Projects control system", version = "1.0.0",
				contact = @Contact(
						name = "Sergei Nefedov",
						email = "s.nefedov@zohomail.com"
				)
		)
)
public class MotiwareTestappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MotiwareTestappApplication.class, args);
	}

}
