package com.guorong;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
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
		AbstractBeanDefinition myBeanDefinition = BeanDefinitionBuilder
				.genericBeanDefinition(MyBean.class)
				.setLazyInit(true)
				.getBeanDefinition();
		context.registerBeanDefinition(MyBean.class.getName(), myBeanDefinition);

		context.refresh();

		MyBean bean = context.getBean(MyBean.class);
		Assertions.assertTrue(bean.awareMessage);
		context.close();
	}


	public static class MyBean implements MessageAware {
		public boolean awareMessage;

		public MyBean() {
			this.awareMessage = false;
		}

		@Override
		public void setAwareMessage(boolean awareMessage) {
			this.awareMessage = awareMessage;
		}

		@Override
		public String toString() {
			return "MyBean{" +
					"awareMessage='" + awareMessage + '\'' +
					'}';
		}
	}

	// 标记接口
	public interface MessageAware {
		void setAwareMessage(boolean awareMessage);
	}

	public static class MyBeanPostProcessor implements BeanPostProcessor {
		@Override
		public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
			System.out.println(String.format("bean--->>>%s  beanName--->>>%s", bean, beanName));
			if (!(bean instanceof MessageAware)) {
				return bean;
			}
			MessageAware messageAware = (MessageAware) bean;
			messageAware.setAwareMessage(true);
			return messageAware;
		}

		@Override
		public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
			return bean;
		}
	}

}
