package com.guorong;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.support.StaticApplicationContext;

public class FactoryBeanTests {


	private static final String MY_FACTORY_BEAN_OBJECT_VALUE = "哈哈";

	@Test
	public void test() {
		StaticApplicationContext staticContext = new StaticApplicationContext();
		AbstractBeanDefinition myFactoryBeanDefinition = BeanDefinitionBuilder
				.genericBeanDefinition(MyFactoryBean.class)
				.getBeanDefinition();

		staticContext.registerBeanDefinition("myFactoryBean", myFactoryBeanDefinition);
		Object value = staticContext.getBean("myFactoryBean");
		Assertions.assertTrue(!MyFactoryBean.class.isInstance(value));
		Assertions.assertEquals(MY_FACTORY_BEAN_OBJECT_VALUE, value);

		// 获取 MyFactoryBean 实例
		Object bean = staticContext.getBean("&myFactoryBean");
		Assertions.assertTrue(MyFactoryBean.class.isInstance(bean));
	}





	public static class MyFactoryBean implements FactoryBean<String> {

		@Override
		public String getObject() throws Exception {
			return MY_FACTORY_BEAN_OBJECT_VALUE;
		}

		@Override
		public Class<?> getObjectType() {
			return String.class;
		}
	}
}
