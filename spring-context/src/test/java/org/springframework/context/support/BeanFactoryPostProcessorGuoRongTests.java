package org.springframework.context.support;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * 自定义测试 BeanFactoryPostProcessor
 */
class BeanFactoryPostProcessorGuoRongTests {

	@Test
	void test() {
		StaticApplicationContext context = new StaticApplicationContext();
	}


	public static class GuoRongBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

		@Override
		public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

		}
	}
}
