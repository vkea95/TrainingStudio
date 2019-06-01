package jian.java8.function;

public class test {

    public static void main(String[] args) {

    }

    private static class DefaultableImpl implements Defaulable {

        @Override
        public void method() {

        }
    }
    private static class OverrideableImpl implements Defaulable {

        @Override
        public void method() {

        }

        public String notRequired(){
            return "Overridden implementation";
        }
    }
}
