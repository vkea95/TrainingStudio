package jian.concurrent.chapter27;

import java.util.LinkedList;

public class ActiveMessageQueue {

    private final LinkedList<ActiveMessage> message = new LinkedList<>();

    public ActiveMessageQueue() {
        new ActiveDaemon(this);
    }


    public void offer(ActiveMessage activeMessage) {
        synchronized (this) {
            message.addLast(activeMessage);
            this.notify();
        }
    }

    public ActiveMessage take() {
        synchronized (this) {
            while (message.isEmpty()) {
                try {
                    this.wait();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            return message.removeFirst();
        }
    }
}

class ActiveDaemon extends Thread {
    private final ActiveMessageQueue queue;


    public ActiveDaemon(ActiveMessageQueue queue) {
        super("ActiveDaemonThread");
        this.queue = queue;
        setDaemon(true);
    }

    @Override
    public void run() {
        for (; ; ) {
            ActiveMessage activeMessage = this.queue.take();
            activeMessage.execute();
        }
    }
}