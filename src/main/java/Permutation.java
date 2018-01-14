import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by JianZhang on 1/13/18.
 */
public class Permutation {

    public static void main(String[] args) {
        int k = Integer.valueOf(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String ss = StdIn.readString();
            for (String str : ss.split(" ")) {
                randomizedQueue.enqueue(str);
            }
        }
        for (int i = 0; i < k; i++) StdOut.println(randomizedQueue.dequeue());
    }
}
