package leetcode.com.pickup1.hard;

import java.util.HashMap;

/**
 * Created by tclresearchamerica on 7/6/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/lru-cache/
 * ****************************************************
 * Description:
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following
 * operations: get and set.
 * <p>
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
 * it should invalidate the least recently used item before inserting a new item.
 * ****************************************************
 * Thoughts:
 * 想不起来这个思路了,感觉就在脑海中.还是先从数据结构入手,HashSet,List,HashMap, 再加上单向链表,双向链表
 * 根据Key-Value pair判断,需要HashMap,因为需要在超出capacity的时候,进行删除,那么就需要另一个数据结构,协助完成工作,
 * 所以永久层就是HashMap,控制层
 * --->
 * 看了第一遍的答案后,才发现自己忘记考虑链表了,用双向链表
 * ****************************************************
 * Time: 30 mins
 * Beat: 6%
 * Bug: 3
 * ****************************************************
 * Hindsight:
 * 1.明白这个LRU的基本算法啦,需要双向链表和hashMap配合使用
 * 2.但是implement得过程中,还是可以模块化一些操作的,如isolate node,和 移动node至头部,这个要在算法表现的清楚些,
 * 不然要一团代码写在那里就完了.
 * 3.在set node的时候,需要明白调整链表顺序是必须的,
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No146_LRU_Cache {
}

class DoubleNode {
    public DoubleNode prev, next;
    public int val, key;

    public DoubleNode(int key, int val) {
        this.val = val;
        this.key = key;
    }
}

class LRUCache {

    DoubleNode tail, head;

    HashMap<Integer, DoubleNode> hashMap = new HashMap<>();
    int capacity = 0;


    public LRUCache(int capacity) {
        this.capacity = capacity;
        tail = new DoubleNode(-1, -1);
        head = new DoubleNode(-1, -1);
        head.next = tail;
        head.prev = tail;
        tail.prev = head;
        tail.next = head;
    }

    public int get(int key) {
        if (hashMap.containsKey(key)) {
            DoubleNode node = hashMap.get(key);
            arrangeNode(node);
            return node.val;
        } else {
            return -1;
        }

    }

    public void set(int key, int value) {
        insertNode(key, value);
    }

    private void insertNode(int key, int value) {
        //bug2:如果含有key,那么就更新val
        if (hashMap.containsKey(key)) {
            hashMap.get(key).val = value;
            //bug3:只要修改value 就要修改队列元素顺序呢
            arrangeNode(hashMap.get(key));
            return;
        }

        if (hashMap.size() >= this.capacity) {
            int delKey = tail.prev.key;
            //delete the last used node
            hashMap.remove(delKey);
            tail.prev.prev.next = tail;
            tail.prev = tail.prev.prev;
        }
        DoubleNode newNode = new DoubleNode(key, value);
        hashMap.put(key, newNode);

        //bug1:此处应该讲node节点放到head部
        newNode.prev = head;
        newNode.next = head.next;
        head.next.prev = newNode;
        head.next = newNode;


    }

    private void arrangeNode(DoubleNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }
}