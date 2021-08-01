package io.dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringAopApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext app = SpringApplication.run(SpringAopApplication.class, args);
		SpringAopApplication appClass = app.getBean("springAopApplication", SpringAopApplication.class);
		appClass.hello("input hello");
		appClass.around();
	}

	@GetMapping("/hello")
	public String hello(String name) {
		return "output hello";
	}

	@GetMapping("/around")
	@Log
	public String around() {
		return "around";
	}
}
