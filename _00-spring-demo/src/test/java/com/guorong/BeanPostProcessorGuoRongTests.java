package com.guorong;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.StaticApplicationContext;

/**
 * bean后置处理器测试
 */
class BeanPostProcessorGuoRongTests {

	@Test
	void test() {
		StaticApplicationContext context = new StaticApplicationContext();
		// BeanPostProcessor 作为 BeanDefinition 来注册
		AbstractBeanDefinition beanPostProcessorBeanDefinition = BeanDefinitionBuilder
				.genericBeanDefinition(MyBeanPostProcessor.class)
				.getBeanDefinition();
		context.registerBeanDefinition(MyBeanPostProcessor.class.getSimpleName(), beanPostProcessorBeanDefinition);
		// 注册 Bean 定义信息
		AbstractBeanDefinition stringBeanDefinition = BeanDefinitionBuilder.genericBeanDefinition(String.class)
				.addConstructorArgValue("张三")
				.getBeanDefinition();
		context.registerBeanDefinition("m-string", stringBeanDefinition);
		context.refresh();
	}

	public static class MyBeanPostProcessor implements BeanPostProcessor {
		@Override
		public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
			System.out.println(String.format("bean--->>>%s  beanName--->>>%s", bean, beanName));
			return bean;
		}
	}

}
