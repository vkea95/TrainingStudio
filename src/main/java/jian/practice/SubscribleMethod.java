package jian.practice;

import java.lang.reflect.Method;

//实现SubscribleMethod类，管理EventBus接受方法的一些属性,方法名、注解、参数
public class SubscribleMethod {

    private Method method;//方法
    private ThreadMode threadMode;//这个方法所在那个线程
    private Class<?> eventType;//参数类型

    public SubscribleMethod(Method method, ThreadMode threadMode, Class<?> eventType) {
        this.method = method;
        this.threadMode = threadMode;
        this.eventType = eventType;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public ThreadMode getThreadMode() {
        return threadMode;
    }

    public void setThreadMode(ThreadMode threadMode) {
        this.threadMode = threadMode;
    }

    public Class<?> getEventType() {
        return eventType;
    }

    public void setEventType(Class<?> eventType) {
        this.eventType = eventType;
    }
}
