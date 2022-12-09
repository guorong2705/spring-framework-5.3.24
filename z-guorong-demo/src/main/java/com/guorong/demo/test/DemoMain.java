package com.guorong.demo.test;

import com.guorong.demo.config.ContextConfig;
import com.guorong.demo.entity.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DemoMain {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ContextConfig.class);
		Student bean01 = context.getBean(Student.class);
	}
}
