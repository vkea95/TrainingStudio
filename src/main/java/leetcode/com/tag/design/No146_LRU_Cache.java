package leetcode.com.tag.design;

import java.util.*;

/**
 * Created by JianZhang on 10/7/17.
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 * <p>
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * <p>
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 * <p>
 * Example:
 * <p>
 * LRUCache cache = new LRUCache( 2 );//capacity
 * <p>
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // returns 1
 * cache.put(3,3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.put(4,4);    // evicts key 1
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 * Solutions:
 * 1. 要维持一个链表,来保证顺序,对队列进行淘汰,get只是提高顺位--若存在,put则是将顺位提高到最高
 * 2. follow up则是要求用O(1) 的时间复杂度
 * 3. 要用一个HashMap来对数据进行保存和处理
 * 4. 此次还是看了原来的答案才开始写的,但用链表来控制顺序的这个概念已经建立了,只是没想到,直接利用链表的操作就可以完成我们想完成的动作
 */

public class No146_LRU_Cache {

    //    bug1: 要通过头尾链表来控制,链表的删除和增加
    private LRUNode head;
    private LRUNode tail;
    int capacity = 0;
    private Map<Integer, LRUNode> map;

    public No146_LRU_Cache(int capacity) {
        head = new LRUNode(-1, -1);
        tail = new LRUNode(-1, -1);
        //connect the link

        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
        map = new HashMap<>(capacity);

    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        //bug 3: forget to move the node to lruHead
//        LRUNode obj = map.get(key);
        LRUNode obj = isolate(map.get(key));
        moveToHead(obj);
        return obj.value;
    }

    public void put(int key, int value) {
        //bug 4: 此处可以使用既成的方法 get 或 isolate 方法哦-----------------> remember
        if (get(key) != -1) {
            //bug 5: 要update value哦
            map.get(key).setValue(value);
            return;
        }
        //此处需要新作节点啦
        if (map.size() >= capacity) {
            //利用现成的方法修理节点
            LRUNode tmpNode = isolate(tail.prev);
            map.remove(tmpNode.key);
        }
        LRUNode lruNode = new LRUNode(key, value);
        map.put(key, lruNode);
        moveToHead(lruNode);

    }

    private void moveToHead(LRUNode lruNode) {
        lruNode.next = head.next;
        head.next.prev = lruNode;
        head.next = lruNode;
        lruNode.prev = head;
    }

    //bug : 通过双向链表来完成摘链和组链操作
    private LRUNode isolate(LRUNode lruNode) {
        //双向摘链
        lruNode.prev.next = lruNode.next;
        lruNode.next.prev = lruNode.prev;
        return lruNode;
    }


    private class LRUNode {
        private int value;
        private int key;
        public LRUNode next;
        public LRUNode prev;

        public LRUNode(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
