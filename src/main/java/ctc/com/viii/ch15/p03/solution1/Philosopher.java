package ctc.com.viii.ch15.p03.solution1;

public class Philosopher extends Thread {
    private final int maxPause = 100;
    private int bites = 10;
    private int id;
    private Chopstick left;
    private Chopstick right;

    public Philosopher(int id, Chopstick left, Chopstick right) {
        this.id = id;
        this.left = left;
        this.right = right;
    }

    public void eat() {
        System.out.println("Philosopher " + id + ": start eating");
        if (pickUp()) {
            chew();
            putDown();
            System.out.println("Philosopher " + id + ": done eating");
        } else {
            System.out.println("Philosopher " + id + ": gave up on eating");
        }
    }

    public boolean pickUp() {
        pause();
        if (!left.pickUp()) {
            return false;
        }
        pause();
        if (!right.pickUp()) {
            return false;
        }
        pause();
        return true;
    }

    public void putDown() {
        right.putDown();
        left.putDown();
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
