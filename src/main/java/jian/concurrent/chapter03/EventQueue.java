package jian.concurrent.chapter03;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

public class EventQueue {
    public static void main(String[] args) {

        EventQueue eq = new EventQueue();
        new Thread(() -> {
            for (; ; ) {
                eq.offer(new EventQueue.Event());
            }
        }, "producer").start();
        new Thread(() -> {
            for (; ; ) {
                eq.take();
                try {
                    TimeUnit.MILLISECONDS.sleep(10);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "consumer").start();
    }

    private final int max;

    static class Event {//此处是为了锁？

    }

    private final Deque<Event> events = new LinkedList<>();

    private final static int DEFAULT_MAX = 10;

    public EventQueue() {// it's a good solution
        this(DEFAULT_MAX);
    }

    public EventQueue(int max) {
        this.max = max;
    }

    public void offer(Event event) {
        synchronized (events) {
            while (events.size() >= max) {// if -> while, if 只适用于一读一写两个线程。
                try {
                    console("the queue is full.");
                    events.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }

            console("the new event is submitted");
            events.addLast(event);
            events.notify();
        }
    }

    public Event take() {
        synchronized (events) {
            while (events.isEmpty()) {// if -> while
                try {
                    console("the queue is empty.");
                    events.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Event event = events.getFirst();
            this.events.notify();
            console("the event " + event + " is handled.");

            return event;
        }
    }


    private static void console(String msg) {
        System.out.printf("%s:%s \n", currentThread().getName(), msg);
    }

}
