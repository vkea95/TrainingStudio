package mock.spring.aop.aspect;

public interface PointcutAdvisor extends Advisor {
    Pointcut getPointcut();
}
