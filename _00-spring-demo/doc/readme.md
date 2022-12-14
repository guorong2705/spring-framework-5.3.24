### bean 生命周期
**Bean 工厂实现应尽可能支持标准的 bean 生命周期接口。全套初始化方法及其标准顺序是：**
- BeanNameAware 的setBeanName
- BeanClassLoaderAware 的setBeanClassLoader
- BeanFactoryAware 的setBeanFactory
- EnvironmentAware 的setEnvironment
- EmbeddedValueResolverAware 的setEmbeddedValueResolver
- ResourceLoaderAware 的setResourceLoader （仅在应用程序上下文中运行时适用）
- ApplicationEventPublisherAware 的setApplicationEventPublisher （仅在应用程序上下文中运行时适用）
- MessageSourceAware 的setMessageSource （仅在应用程序上下文中运行时适用）
- ApplicationContextAware 的setApplicationContext （仅在应用程序上下文中运行时适用）
- ServletContextAware 的setServletContext （仅在 Web 应用程序上下文中运行时适用）
- BeanPostProcessors 的postProcessBeforeInitialization方法
- InitializingBean 的afterPropertiesSet
- 自定义init-method定义
- BeanPostProcessors 的postProcessAfterInitialization方法

**在关闭 bean 工厂时，将应用以下生命周期方法：**
- DestructionAwareBeanPostProcessors 的postProcessBeforeDestruction方法
- DisposableBean 的destroy
- 自定义destroy-method定义

### AbstractAutowireCapableBeanFactory
doCreateBean(String beanName, RootBeanDefinition mbd, @Nullable Object[] args)方法中：
- `instanceWrapper = createBeanInstance(beanName, mbd, args)`：实例化 Bean
- `populateBean(beanName, mbd, instanceWrapper)`：填充bean定义中的属性值以及自动装配
- `exposedObject = initializeBean(beanName, exposedObject, mbd)`：初始化Bean

initializeBean(beanName, exposedObject, mbd) 方法中：
- `wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName)`: 应用 bean 的后置处理器初始化前方法
- `invokeInitMethods(beanName, wrappedBean, mbd)`：调用 InitializingBean 接口的 afterPropertiesSet()方法 和 bean 定义中的初始化方法
- `wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName)`: 应用 bean 后置处理器初始化后的方法