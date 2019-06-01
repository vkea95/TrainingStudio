package mock.spring.bean;

public class TestBean {
    private TestOutputService outputService;
    private String content;

    public void sayHi() {
        System.out.println("hi there " + content);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TestOutputService getOutputService() {
        return outputService;
    }

    public void setOutputService(TestOutputService outputService) {
        this.outputService = outputService;
    }
}
