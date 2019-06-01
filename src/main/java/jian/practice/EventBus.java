package jian.practice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventBus {
    private static volatile EventBus instace;
    private Map<Object, List<SubscribleMethod>> eventTypesCache = new HashMap<>();

    private EventBus() {

    }

    public static EventBus getDefault() {
        if (instace == null) {
            synchronized (EventBus.class) {
                if (instace == null)
                    instace = new EventBus();
            }
        }
        return instace;
    }

    public void register(Object subscriber) {
        List<SubscribleMethod> subscribleMethods = eventTypesCache.get(subscriber);
        if (subscribleMethods == null) {
            subscribleMethods = findSubscriberMethods(subscriber);
            eventTypesCache.put(subscriber, subscribleMethods);
        }
    }

    private List<SubscribleMethod> findSubscriberMethods(Object subscriber) {
        /**
         * 线程安全
         */
        List<SubscribleMethod> subscribleMethods = new CopyOnWriteArrayList<>();
        Class<?> clazz = subscriber.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        //循环查找父类的接受方法
        while (clazz != null) {
            String name = clazz.getName();
            if (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.")) {
                break;
            }

            for (Method method : methods) {
                Subscribe subscribe = method.getAnnotation(Subscribe.class);//拿到方法上的注解
                if (subscribe == null) {

                    continue;
                }

                //拿到方法的参数数组
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length != 1)//如果方法中的参数不是一个，就抛错
                    throw new RuntimeException("eventBus must be one parameter!");
                Class<?> parameterType = parameterTypes[0];
                ThreadMode threadMode = subscribe.value();
                SubscribleMethod subscribleMethod = new SubscribleMethod(method, threadMode, parameterType);
                subscribleMethods.add(subscribleMethod);
            }
            clazz = clazz.getSuperclass();
        }

        return subscribleMethods;
    }

    public void post(Object event) {
        Set<Object> set = eventTypesCache.keySet();
        Iterator<Object> iterator = set.iterator();
        while (iterator.hasNext()) {
            Object subscriber = iterator.next();
            List<SubscribleMethod> subscribleMethods = eventTypesCache.get(subscriber);
            for (SubscribleMethod subscribleMethod : subscribleMethods) {
                if (subscribleMethod.getEventType().isAssignableFrom(event.getClass())) {
                    invoke(subscriber, subscribleMethod, event);
                }
            }
        }

    }

    private void invoke(Object subscriber, SubscribleMethod subscribleMethod, Object event) {
        try {
            subscribleMethod.getMethod().invoke(subscriber, event);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        System.out.println("开始：");

        Person person1 = new Person("马先生", 220181);
        Person person2 = new Person("李先生", 220193);
        Person person3 = new Person("王小姐", 220186);

        Map<Number, Person> map = new HashMap<Number, Person>();
        map.put(person1.getId_card(), person1);
        map.put(person2.getId_card(), person2);
        map.put(person3.getId_card(), person3);

        // HashMap
        System.out.println("HashMap，无序：");
        for (Iterator<Number> it = map.keySet().iterator(); it.hasNext(); ) {
            Person person = map.get(it.next());
            System.out.println(person.getId_card() + " " + person.getName());
        }

        // TreeMap
        System.out.println("TreeMap，升序：");
        TreeMap<Number, Person> treeMap = new TreeMap<Number, Person>();
        treeMap.putAll(map);
        for (Iterator<Number> it = treeMap.keySet().iterator(); it.hasNext(); ) {
            Person person = treeMap.get(it.next());
            System.out.println(person.getId_card() + " " + person.getName());
        }

        System.out.println("TreeMap，降序：");
        TreeMap<Number, Person> treeMap2 =
                new TreeMap<Number, Person>(Collections.reverseOrder());
        treeMap2.putAll(map);
        for (Iterator it = treeMap2.keySet().iterator(); it.hasNext(); ) {
            Person person = (Person) treeMap2.get(it.next());
            System.out.println(person.getId_card() + " " + person.getName());
        }

        System.out.println("结束！");
    }
}

class Person {

    int id;
    String name;

    public int getId_card() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Person(String name, int date) {
        this.name = name;
        this.id = date;
    }

}