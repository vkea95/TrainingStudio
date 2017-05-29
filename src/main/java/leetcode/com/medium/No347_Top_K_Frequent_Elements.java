package leetcode.com.medium;

import java.util.*;

/**
 * Created by tclresearchamerica on 5/13/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/top-k-frequent-elements/
 * ****************************************************
 * Description:
 * Given a non-empty array of integers, return the k most frequent elements.
 * <p>
 * For example,
 * Given [1,1,1,2,2,3] and k = 2, return [1,2].
 * <p>
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * ****************************************************
 * Analysis:
 * 1.放入HashMap:
 * 2.但是出结果的时候,会麻烦些,毕竟需要多数量排序,但是需要显示的是键值
 * 3.没有发现哪个java类提供<key,value> pair,所以自己整了个数据类型,然后封装comparator,虽然结构复杂些,但是逻辑很清楚
 * 4.还有一个point,就是先对输入的数组进行排序,然后,再放入PriorityQueue中,这样就不必每次都要操作这个Pair的值(value+1)啦
 * ****************************************************
 * [1,1,1,2,2,3]
 * 2
 * ****************************************************
 */
public class No347_Top_K_Frequent_Elements {
    public static void main(String[] args) {

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(9);
        queue.add(8);
        queue.add(18);
        queue.add(1);
        queue.add(66);
        Iterator<Integer> it =queue.iterator();
        while (it.hasNext()){
            System.out.println(it.next());

        }
        System.out.println("-------------");

        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
        No347_Top_K_Frequent_Elements obj = new No347_Top_K_Frequent_Elements();
//        int[] nums = {1, 1, 1, 2, 2, 3};
        int[] nums = {1, 2};
//        obj.topKFrequent(nums, 2);

    }

    public List<Integer> topKFrequent_fastest(int[] nums, int k) {


        List<Integer> rst = new ArrayList<>();
        PriorityQueue<MyEntry> priorityQueue = new PriorityQueue<>(new MyEntryCompare());
        if (nums == null || nums.length == 0) return rst;
        Arrays.sort(nums);
        int count = 1;
        for (int i = 1; i < nums.length; i++) {

            if (nums[i] != nums[i - 1]) {
                priorityQueue.offer(new MyEntry(nums[i - 1], count));
                count = 1;
            } else {
                count++;
            }
        }
        priorityQueue.offer(new MyEntry(nums[nums.length - 1], count));

        //bug3:因为循环中会对priorityQueue的进行poll操作,所以其长度一直在不断缩小,
        // 那么就不能在for循环的条件判断中使用priorityQueue.size()!!!极其重要哦!!!!
        int size = priorityQueue.size();
        for (int i = 0; i < k && i < size; i++) {
            rst.add(priorityQueue.poll().key);
        }

        return rst;

    }


    public List<Integer> topKFrequent_Slow(int[] nums, int k) {
        List<Integer> rst = new ArrayList<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        PriorityQueue<HashMap.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>(new EntryCompare());

        for (int num : nums) {

            if (hashMap.containsKey(num)) {
                hashMap.put(num, hashMap.get(num) + 1);
            } else {
                hashMap.put(num, 1);
            }
        }
        for (HashMap.Entry<Integer, Integer> entry : hashMap.entrySet()) {

            priorityQueue.add(entry);

            if (priorityQueue.size() > k) priorityQueue.poll();

        }

        for (HashMap.Entry<Integer, Integer> entry : priorityQueue)
            rst.add(entry.getKey());

        return rst;
    }
}

class MyEntryCompare implements Comparator<MyEntry> {

    @Override
    public int compare(MyEntry o1, MyEntry o2) {
        if (o1.value > o2.value) return -1;
        return 1;
    }
}

class MyEntry {
    Integer key;
    Integer value;

    public MyEntry(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class EntryCompare implements Comparator<HashMap.Entry<Integer, Integer>> {

    @Override
    public int compare(HashMap.Entry<Integer, Integer> o1, HashMap.Entry<Integer, Integer> o2) {

        if (o1.getValue() > o2.getValue()) return 1;
        else return -1;

    }
}
