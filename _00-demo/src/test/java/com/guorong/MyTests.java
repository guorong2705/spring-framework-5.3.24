package com.guorong;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.entity.Student;

public class MyTests {

	@Test
	public void test() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
		beanDefinition.setBeanClass(Student.class);
		beanFactory.registerBeanDefinition("student", beanDefinition);
		System.out.println(beanFactory.getBean(Student.class));
	}
}
