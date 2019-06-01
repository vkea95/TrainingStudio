package mock.spring.aop.aspect;

import mock.spring.bean.factory.BeanFactory;

public interface BeanFactoryAware {

    void setBeanFactory(BeanFactory beanFactory) throws Exception;
}
