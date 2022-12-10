package org.springframework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.entity.Student;

@Configuration
public class SpringConfig {

	@Bean
	public Student student() {
		return new Student();
	}
}
