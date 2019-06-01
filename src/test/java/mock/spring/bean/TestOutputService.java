package mock.spring.bean;

import org.junit.Assert;

public class TestOutputService {

    private TestBean testBean;

    public void output(String text) {
        Assert.assertNotNull(testBean);
        System.out.println(text);
    }

    public TestBean getTestBean() {
        return testBean;
    }

    public void setTestBean(TestBean testBean) {
        this.testBean = testBean;
    }
}
