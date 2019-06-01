package mock.spring.bean.factory;

import mock.spring.bean.BeanDefinition;
import mock.spring.bean.PropertyValue;
import mock.spring.bean.PropertyValues;
import mock.spring.bean.TestBean;
import mock.spring.bean.io.ResourceLoader;
import mock.spring.bean.xml.XmlBeanDefinitionReader;
import org.junit.Test;

import java.util.Map;

public class BeanFactoryTest {

    @Test
    public void test() throws Exception {
        // 1.读取配置
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc.xml");



        // 2.初始化BeanFactory并注册bean
        AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }

//        3. initialize all the beans
        beanFactory.preInstantiateSingletons();

        TestBean testBean = (TestBean) beanFactory.getBean("test");
        testBean.sayHi();
    }
}
// step 1:
// using BeanDefinition to wrap the real bean, with the help of map, retrieve bean.
// but creating bean should be in the factory.
// thoughts: next step put creating operation into factory, but how to do it
// as in java, define interface, and then the class
// step 2: bean should come from factory, so define abstract factory class with abstract method doCreateBean
// because class name in xml could create bean, class name is the only path to bean. Class is the bridge.
// put bean, className, Class into definition could help us create bean
// step 3: how about properties -> name, value, then how about list structure?
// property is defined inside the BeanDefinition, and set the value through class reflection
// 换到context后，beanFactory中只保留了一个方法，registerBeanDefinition被移除了
// context中增加了一个refresh方法，一个configuration的成员变量，abstractBeanFactory作为参数被传进来，默认用的是autowire。。。
