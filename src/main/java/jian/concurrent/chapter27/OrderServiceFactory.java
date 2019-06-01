package jian.concurrent.chapter27;

public final class OrderServiceFactory {
    private final static ActiveMessageQueue activeMessageQueue = new ActiveMessageQueue();
    private OrderServiceFactory(){

    }

    public static OrderService toActivateObject(OrderService orderService) {
        return null;
    }
}
