package SpringBootDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class SpringBootDemo {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemo.class, args);
	}
}
