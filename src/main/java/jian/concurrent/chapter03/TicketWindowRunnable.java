package jian.concurrent.chapter03;

public class TicketWindowRunnable {

    private int index = 1;
    private final static int MAX = 500;
    private final static Object MUTEX = new Object();
}
