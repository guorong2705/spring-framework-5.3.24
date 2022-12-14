### AbstractAutowireCapableBeanFactory
doCreateBean(String beanName, RootBeanDefinition mbd, @Nullable Object[] args)方法中：
- `instanceWrapper = createBeanInstance(beanName, mbd, args)`：实例化 Bean
- `populateBean(beanName, mbd, instanceWrapper)`：填充bean定义中的属性值
- `exposedObject = initializeBean(beanName, exposedObject, mbd)`：初始化Bean

initializeBean(beanName, exposedObject, mbd) 方法中：
- `wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName)`: 应用 bean 的后置处理器初始化前方法
- `invokeInitMethods(beanName, wrappedBean, mbd)`：调用 InitializingBean 接口的 afterPropertiesSet()方法 和 bean 定义中的初始化方法
- `wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName)`: 应用 bean 后置处理器初始化后的方法