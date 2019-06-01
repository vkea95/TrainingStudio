package ctc.com.viii.ch15.p03.solution2;


public class Philosopher extends Thread {
    private final int maxPause = 100;
    private int bites = 10;
    private int id;
    private Chopstick lower;
    private Chopstick higher;

    public Philosopher(int id, Chopstick left, Chopstick right) {
        this.id = id;
        // 分配chopstick 给lower和higher
        if (left.getNumber() < right.getNumber()) {
            this.lower = left;
            this.higher = right;

        } else {
            this.higher = left;
            this.lower = right;
        }

    }

    public void eat() {
        System.out.println("Philosopher " + id + ": start eating");
        pickUp();
        chew();
        putDown();
        System.out.println("Philosopher " + id + ": done eating");
    }

    public void pickUp() {
        pause();
        lower.pickUp();
        pause();
        higher.pickUp();
        pause();
    }

    public void putDown() {
        higher.putDown();
        lower.putDown();
    }

    public void chew() {
        System.out.println("Philosopher " + id + ": eating");
        pause();
    }


    public void pause() {
        try {
            int pause = randomIntInRange(0, maxPause);
            Thread.sleep(pause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < bites; i++) {
            eat();
        }
    }

    public static int randomInt(int n) {
        return (int) (Math.random() * n);
    }

    public static int randomIntInRange(int min, int max) {
        return randomInt(max + 1 - min) + min;
    }


}
