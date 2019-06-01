package jian.concurrent.chapter12;

public class LazyEnumSingleton {

    private LazyEnumSingleton() {

    }

    public static LazyEnumSingleton getInstance() {
        return EnumHolder.INSTANCE.getInstance();
    }

    private enum EnumHolder {
        INSTANCE;
        private LazyEnumSingleton instance;

        private byte[] data = new byte[1024];

        EnumHolder() {
            this.instance = new LazyEnumSingleton();

        }

        private LazyEnumSingleton getInstance() {
            return instance;
        }
    }

}
