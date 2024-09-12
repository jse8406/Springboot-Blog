package me.jse.blog.jselog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class JselogApplication {

	public static void main(String[] args) {
		SpringApplication.run(JselogApplication.class, args);
	}

}
