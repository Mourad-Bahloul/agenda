package com.hc.agenda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@SpringBootApplication
@EnableWebMvc
public class AgendaApplication {

	/**@Bean
	public void cors(CorsRegistry registry) {

				registry.addMapping("/**")
						.allowedOrigins("http://localhost:3000","http://localhost")
						.allowedMethods("GET", "POST", "PUT", "DELETE")
						.allowedHeaders("*")
						.allowCredentials(true);


	}**/

	public static void main(String[] args) {
		SpringApplication.run(AgendaApplication.class, args);
	}

}
