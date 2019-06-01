package ctc.com.viii.ch15.p03.solution1;

public class Run_Philosopher {
    public static int size = 3;

    public static int leftOf(int i) {
        return i;
    }

    public static int rightOf(int i) {
        return (i + 1) % size;
    }

    public static void main(String[] args) {
        // create resources

        Chopstick[] chopsticks = new Chopstick[size + 1];
        for (int i = 0; i < size + 1; i++) {
            chopsticks[i] = new Chopstick();
        }
        // create consumers
        Philosopher[] philosophers = new Philosopher[size];
        for (int i = 0; i < size; i++) {
            // config resources for consumers
            Chopstick left = chopsticks[leftOf(i)];
            Chopstick right = chopsticks[rightOf(i)];
            philosophers[i] = new Philosopher(i, left, right);
        }
        // consume
        for (Philosopher philosopher : philosophers) {
            // stard thread
            philosopher.start();
        }
    }
}
