package leetcode.com.pickup1.medium;

import java.util.*;

/**
 * Created by tclresearchamerica on 9/14/16.
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
 * thought:
 * <p>
 * ****************************************************
 * Hindsight:
 * 1.PriorityQueue需要添加自己的Comparator才可以正常比较
 * 2.PriorityQueue的Iterator并不能保证按照顺序遍历
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No347_Top_K_Frequent_Elements {
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();

        //中间可以增加一步,先对nums从小到大排序的过程
        //然后根据nums前后关系,将放入map的过程进行简化
        for (int num : nums) {
            if (map.containsKey(num)) map.put(num, map.get(num) + 1);
            else map.put(num, 1);

        }
        PriorityQueue<MyEntity> queue = new PriorityQueue<>(new MyComparator());
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            queue.offer(new MyEntity(entry.getKey(), entry.getValue()));
            if (queue.size() > k) queue.poll();
        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            result.add(queue.poll().key);
        }

        return result;
    }
}

class MyComparator implements Comparator<MyEntity> {
    public int compare(MyEntity e1, MyEntity e2) {
        if (e1.value < e2.value) return -1;
        else return -1;
    }
}

class MyEntity {
    int key;
    int value;

    public MyEntity(int key, int value) {
        this.key = key;
        this.value = value;
    }
}