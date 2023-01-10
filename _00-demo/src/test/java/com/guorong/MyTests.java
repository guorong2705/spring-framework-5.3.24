package com.guorong;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import java.io.Serializable;

public class MyTests {

	@Test
	public void test() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		// Student
		AbstractBeanDefinition studentBeanDefinition = BeanDefinitionBuilder
				.genericBeanDefinition(Student.class)
				.setLazyInit(true)
				.addDependsOn(Teacher.class.getSimpleName())
				.getBeanDefinition();
		// Teacher
		AbstractBeanDefinition teacherBeanDefinition = BeanDefinitionBuilder
				.genericBeanDefinition(Object.class)
				.setLazyInit(true)
				.addDependsOn(Student.class.getSimpleName())
				.getBeanDefinition();

		beanFactory.registerBeanDefinition(Student.class.getSimpleName(), studentBeanDefinition);
		beanFactory.registerBeanDefinition(Teacher.class.getSimpleName(), teacherBeanDefinition);

		beanFactory.getBean(Student.class);
	}

	@Test
	public void test01() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		// Student
		AbstractBeanDefinition studentBeanDefinition = BeanDefinitionBuilder
				.genericBeanDefinition(Student.class)
				.setLazyInit(true)
				.getBeanDefinition();
		beanFactory.registerBeanDefinition(Student.class.getSimpleName(), studentBeanDefinition);
		beanFactory.getBean(Student.class);
	}



	@Getter
	@Setter
	public static class Student implements Serializable {
		private String name;
		private Integer age;
	}

	@Getter
	@Setter
	public static class Teacher implements Serializable {
		private String name;
		private Integer age;
	}
}
