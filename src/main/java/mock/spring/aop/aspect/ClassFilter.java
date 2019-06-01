package mock.spring.aop.aspect;

public interface ClassFilter {

    boolean matches(Class targetClass);
}
