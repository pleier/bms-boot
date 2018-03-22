package com.github.pleier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author : pleier
 * @date : 2018-03-22
 */
@SpringBootApplication
public class BmsAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(BmsAdminApplication.class, args);
	}
}
