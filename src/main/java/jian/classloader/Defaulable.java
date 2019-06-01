package jian.classloader;

public interface Defaulable {

    default String notRequired() {
        return "Default implementation";
    }

}
//
//private static class DefaultableImpl implements Defaulable {
//
//}
//
//private static class OverridaleImpl implements Defaulable {
//    @Override
//    public String notRequired() {
//        return "Overridden implementation";
//    }
//}