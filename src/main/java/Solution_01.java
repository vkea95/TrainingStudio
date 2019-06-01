import edu.princeton.cs.algs4.In;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Solution_01 {

    public static void main(String[] args) {
        Solution_01 s1 = new Solution_01();
        Solution_01 hashmap = new Solution_01();
        hashmap.addKey("kelsey");
        hashmap.addKey("kelsey");
        hashmap.addKey("hello");
        hashmap.addKey("hello");
        hashmap.addKey("Hi");
        hashmap.addKey("Hi");
        hashmap.addKey("Hi");
        hashmap.addKey("That'sme");
        hashmap.addKey("hello");
        hashmap.addKey("kelsey");
        hashmap.addKey("kelsey");
        System.out.println(hashmap.getKeyWithMaxCount());
    }


    //bug: 定义好countMap指向的是该count的最后一个
    Pair head;
    Pair tail;
    Map<String, Pair> map;
    Map<Integer, Pair> countMap;

    public Solution_01() {
        map = new HashMap<>();
        countMap = new HashMap<>();
        countMap.put(0, head);
        countMap.put(-1, tail);
        head = new Pair("", 0);
        tail = new Pair("", -1);
        connectPair(head, tail);
    }

    // bug remove return type
    private void removePair(Pair target) {
        target.prev.next = target.next;
        target.next.prev = target.prev;
        target.next = null; // garbage collection
        target.prev = null;

    }

    public void addKey(String key) {
        Pair target = null;
        if (!map.containsKey(key)) {

            target = new Pair(key, 1);
            map.put(key, target);
            connectPair(head, target);
//            if (!countMap.containsKey(1)) {
//                countMap.put(1, target);
//            }
        } else {
            target = map.get(key);
            //如果该target是该count的最后一个
//            bug: countMap的key该是target.count，而不是target.key
            if (countMap.get(target.count) == target) {
                //如果该count不止一个元素
                if (target.prev.count == target.count) {
                    countMap.put(target.count, target.prev);

                } else {
                    //如果该count只有一个元素
                    // removeKey --> remove直接就可以remove element，不需要key
                    countMap.remove(target.count);
                }

//                // 此时不需要移动这个 target，因为他是count的最后一个，自然就可以放到自己合适的位置
//                target.increment();
//
//                if (!countMap.containsKey(target.count)) {
//                    countMap.put(target.count, target);
//                }
            } else {
//                target.increment();
//
//                if (!countMap.containsKey(target.count)) {
//                    countMap.put(target.count, target);
//                }

                removePair(target);

                connectPair(countMap.get(target.count), target);

            }
            target.increment();

            // bug: 不能清掉，否则不好找下一个节点
            // target.increment();
            // removePair(target);
        }

        if (!countMap.containsKey(target.count)) {
            countMap.put(target.count, target);
        }
    }

    private void connectPair(Pair first, Pair second) {
        second.next = first.next;
        if (first.next != null) {
            first.next.prev = second;
        }
        first.next = second;
        second.prev = first;
    }

    public int getCountForKey(String key) {
        return map.getOrDefault(key, head).count;

    }

    public void decrementKey(String key) {

    }

    public List<String> getKeyWithMaxCount() {
        List<String> list = new ArrayList<>();
        if (tail.prev.count == 0) {
            return list;
        }
        int max = tail.prev.count;
        Pair temp = tail.prev;
        while (temp.count == max) {
            list.add(temp.key);
            temp = temp.prev;
        }
        return list;
    }
}

class Pair {
    public String key;
    public int count;
    public Pair next;
    public Pair prev;

    //bug:没有count的话，无法区分head和tail这俩指针
    public Pair(String key, int count) {
        this.key = key;
        this.count = count;
    }

    public boolean decrement() {
        count--;
        return count > 0;
    }

    public boolean increment() {
        count++;
        return count > 0;
    }
}
/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 * 1 3
 * 1 4
 * 2
 * <p>
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */