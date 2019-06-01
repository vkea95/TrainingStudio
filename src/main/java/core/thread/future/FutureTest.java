package core.thread.future;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.stream.IntStream;

public class FutureTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.sort((a, b) -> a.compareTo(b));


        Scanner in = new Scanner(System.in);
        System.out.println("Enter base directory (e.g. ..): ");
        String directory = in.nextLine();
        System.out.println("Enter keyword (e.g. volatile) :");
        String keyword = in.nextLine();

        MatchCounter matchCounter = new MatchCounter(new File(directory), keyword);
        FutureTask<Integer> task = new FutureTask<>(matchCounter);
        Thread t = new Thread(task);
        t.start();
        try {
            System.out.println(task.get() + " matching files.");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}


class MatchCounter implements Callable<Integer> {
    private File directory;
    private String keyword;
    private int count;

    public MatchCounter(File directory, String keyword) {
        this.directory = directory;
        this.keyword = keyword;
    }

    /*
    it's the business logic
     */
    @Override
    public Integer call() {
        count = 0;
        try {
            File[] files = directory.listFiles();
            List<Future<Integer>> results = new ArrayList<>();
            for (File file : files) {
                if (file.isDirectory()) {
                    //create a new callable instance
                    Thread.sleep(5000);
                    MatchCounter counter = new MatchCounter(file, keyword);
                    // create a task
                    // FutureTask is a concrete class that
                    // implements both Runnable and Future
                    // Create the FutureTask with Callable

                    FutureTask<Integer> task = new FutureTask<>(counter);
                    results.add(task);
                    // As it implements Runnable, create Thread
                    // with FutureTask
                    Thread t = new Thread(task);
                    t.start();
                } else {
                    if (search(file)) {
                        count++;
                    }
                }
            }
            for (Future<Integer> r : results) {
                try {
                    count += r.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }
        } catch (InterruptedException e) {

        }
        return count;
    }

    public boolean search(File file) {
        try {
            try (Scanner in = new Scanner(file)) {
                boolean found = false;
                while (!found && in.hasNextLine()) {
                    String line = in.nextLine();
                    if (line.contains(keyword)) {
                        found = true;
                        break;
                    }
                    return found;
                }
            }
        } catch (IOException e) {
            return false;
        }
        return false;
    }
}
