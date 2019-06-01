package core.thread.forkjoin;

import alg4.com.ch0501.Count;
import edu.princeton.cs.algs4.In;
import sun.nio.cs.ext.MacHebrew;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest {
    public static void main(String[] args) {

        List<Integer> st = new ArrayList<>();
        st.add(100);
        st.add(99);

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put(i, i);
        }

        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        System.out.println(map.size());
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            int key = entry.getKey();
            int value = entry.getValue();

            iterator.remove();
//            map.entrySet().remove(entry);
            System.out.println(map.size());


        }

        for (int i = 0; i < 10; i++) {
            map.put(i, i);
        }
        System.out.println(map.size());
        //bug: itreator的for循环，最后一个；的语句是do nothing
        for (Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Integer, Integer> entry = it.next();
            it.remove();
//            map.entrySet().remove(entry);
            System.out.println(map.size());
        }

//        complicated problem
        HashMap<HashMap<String, Integer>, Integer> myHashMap = new HashMap<>();
        HashMap<String, Integer> temp = new HashMap<>();
        temp.put("1", 1);
        temp.put("2", 2);
        myHashMap.put(temp, 3);
        for (Iterator<Map.Entry<HashMap<String, Integer>, Integer>>
             it = myHashMap.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<HashMap<String, Integer>, Integer> item = it.next();
            item.getKey().remove("1");
            System.out.println(myHashMap.size());
            it.remove();
            System.out.println(myHashMap.size());
        }

        List<Integer> list = new ArrayList<>();
        list.add(1);
        int ii = list.iterator().next();
        final int size = 10000000;
        double[] numbers = new double[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = Math.random();
        }
        Counter counter = new Counter(numbers, 0, numbers.length, new Filter() {
            @Override
            public boolean accept(double t) {
                return t > 0.5;
            }
        });

        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(counter);
        System.out.println(counter.join());
    }
}

interface Filter {
    boolean accept(double t);
}

// 这个是一个新的task类型
/*
Behind the scenes, the fork-join framework uses an effective heuristic for balancing the workload among available
threads, called work stealing. Each worker thread has a deque (double-ended queue) for tasks.
A worker thread pushes subtasks onto the head of its own deque. (Only one thread accesses the head,
so no locking is required.) When a worker thread is idle, it “steals” a task from the tail of another deque.
Since the large subtasks are at the tail, such stealing is rare
 */
class Counter extends RecursiveTask<Integer> {
    public static final int THRESHOLD = 1000;
    private double[] values;
    private int from;
    private int to;
    private Filter filter;

    public Counter(double[] values, int from, int to, Filter filter) {
        this.values = values;
        this.from = from;
        this.to = to;
        this.filter = filter;
    }

    @Override
    protected Integer compute() {
        if (to - from < THRESHOLD) {
            int count = 0;
            for (int i = from; i < to; i++) {
                if (filter.accept(values[i])) {
                    count++;
                }
            }
            return count;
        } else {
            int mid = (from + to) >>> 1;
            Counter first = new Counter(values, from, mid, filter);
            Counter second = new Counter(values, mid, to, filter);
            invokeAll(first, second);
            return first.join() + second.join();
        }
    }
}

