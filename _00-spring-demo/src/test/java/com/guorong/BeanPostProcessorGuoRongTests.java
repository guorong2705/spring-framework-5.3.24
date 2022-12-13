package com.guorong;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.StaticApplicationContext;

/**
 * bean后置处理器测试
 */
class BeanPostProcessorGuoRongTests {

	@Test
	void test() {
		StaticApplicationContext context = new StaticApplicationContext();
		// context.registerSingleton("TestAwareBeanProcessor", TestAwareBeanProcessor.class);
		AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
				.genericBeanDefinition(TestAwareBeanProcessor.class)
				.setLazyInit(true).getBeanDefinition();
		context.registerBeanDefinition("TestAwareBeanProcessor", beanDefinition);
		context.refresh();
		TestAwareBeanProcessor bean = context.getBean(TestAwareBeanProcessor.class);
		System.out.println(bean);
	}

	public static class TestAwareBeanProcessor implements ApplicationContextAware {

		private String name = "测试Aware";

		private ApplicationContext applicationContext;

		@Override
		public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
			this.applicationContext = applicationContext;
		}

		@Override
		public String toString() {
			return "TestAwareBeanProcessor{" +
					"name='" + name + '\'' +
					", applicationContext=" + applicationContext +
					'}';
		}
	}

}
