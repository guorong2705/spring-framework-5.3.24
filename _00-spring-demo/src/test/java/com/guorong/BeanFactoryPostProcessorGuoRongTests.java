package com.guorong;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.support.StaticApplicationContext;

/**
 * 自定义测试 BeanFactoryPostProcessor
 */
class BeanFactoryPostProcessorGuoRongTests {

	@Test
	void test() {
		StaticApplicationContext context = new StaticApplicationContext();
		context.addBeanFactoryPostProcessor(beanFactoryPostProcessor);
		context.refresh();
		CustomBean bean01 = context.getBean(CustomBean.class);
		System.out.println(bean01);
		CustomBean bean02 = context.getBean(CustomBean.class);
		System.out.println(bean02);
		System.out.println(String.format("bean01==bean02: %s", bean01 == bean02));
	}

	public static class CustomBean {
		private String name;
		private Integer age;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return "CustomBean{" +
					"name='" + name + '\'' +
					", age=" + age +
					'}';
		}
	}

	// 自定义后置处理器
	private BeanFactoryPostProcessor beanFactoryPostProcessor = (ConfigurableListableBeanFactory beanFactory) -> {
		// 注册一个bean的定义
		BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry) beanFactory;
		AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
				.genericBeanDefinition(CustomBean.class)
				.addPropertyValue("name", "张三")
				.addPropertyValue("age", 32)
				.setScope(BeanDefinition.SCOPE_SINGLETON)
				.getBeanDefinition();
		beanDefinitionRegistry.registerBeanDefinition("customBean", beanDefinition);
	};

}
