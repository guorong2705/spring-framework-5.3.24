package com.guorong.demo.config;

import com.guorong.demo.entity.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextConfig {

	@Bean
	public Student student() {
		return new Student();
	}
}
