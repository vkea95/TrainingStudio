package mock.spring.context;

import mock.spring.bean.TestBean;
import org.junit.Test;

/**
 * @author yihua.huang@dianping.com
 */
public class ApplicationContextTest {

    @Test
    public void test() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
        TestBean testBean = (TestBean) applicationContext.getBean("test");
        testBean.sayHi();
    }
}
