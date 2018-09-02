package bittiger.io.thread;

public class Singleton {

    private static Singleton singletonInstance;
    private static Object object = new Object(); // control the synchronization

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (singletonInstance == null) {    // All threads check
            synchronized ((object)) {
                if (singletonInstance == null) {  // Threads hold lock check
                    singletonInstance = new Singleton();
                }
            }
        }
        return singletonInstance;
    }
}
