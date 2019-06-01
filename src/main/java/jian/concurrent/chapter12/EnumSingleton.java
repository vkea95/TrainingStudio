package jian.concurrent.chapter12;

public enum EnumSingleton {
    INSTANCE;

    private byte[] data = new byte[1024];

    EnumSingleton() {
        System.out.println("INSTANCE will be initialized immediately");

    }

    public static void method() {
        //调用这个方法，就会主动使用Singleton, INSTANCE会被实例化
    }

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }
}
