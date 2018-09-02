package leetcode.com.tag.design;

import java.util.*;

import java.util.HashMap;

public class LFUCache {
    int capacity;
    LRUCache lruHead;
    LRUCache lruTail;
    Map<Integer, DoubleNode> keyNodeMap;
    Map<Integer, LRUCache> countLRUMap;//key :  LRU


    public LFUCache(int capacity) {
        this.capacity = capacity;
        keyNodeMap = new HashMap<>();
        countLRUMap = new HashMap<>();

        lruHead = new LRUCache(-1, 0);
        lruTail = new LRUCache(-1, -1);
        countLRUMap.put(0, lruHead); //bug: NPE 在connectLRUcache是
        countLRUMap.put(-1, lruTail); //bug: NPE
        lruHead.next = lruTail;
        lruTail.prev = lruHead;

    }

    public int get(int key) {
        if (!keyNodeMap.containsKey(key)) {
            return -1;
        }
        DoubleNode crtNode = keyNodeMap.get(key);

        upgradeNode(crtNode);
        return crtNode.value;
    }

    private void upgradeNode(DoubleNode crtNode) {

        LRUCache crtCache = countLRUMap.get(crtNode.accessCount);

        crtCache.isolate(crtNode);
        crtNode.accessCount++;
        addNodeToLRUCacheLinkedList(crtNode);
        isolateEmptyLRUCache(crtCache);
    }

    private void addNodeToLRUCacheLinkedList(DoubleNode crtNode) {
        if (!countLRUMap.containsKey(crtNode.accessCount)) {
            countLRUMap.put(crtNode.accessCount, new LRUCache(0, crtNode.accessCount));
            connectLRUCache(countLRUMap.get(crtNode.accessCount - 1), countLRUMap.get(crtNode.accessCount));
        }
        countLRUMap.get(crtNode.accessCount).put(crtNode);
    }

    private void isolateEmptyLRUCache(LRUCache targetLRUCache) {

        if (targetLRUCache.size == 0) {
            targetLRUCache.prev.next = targetLRUCache.next;
            targetLRUCache.next.prev = targetLRUCache.prev;
            countLRUMap.remove(targetLRUCache.accessCount);
        }
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (keyNodeMap.containsKey(key)) {
            DoubleNode crtNode = keyNodeMap.get(key);
            crtNode.value = value;//update
            upgradeNode(crtNode);
        } else {
            //clear the useless node
            if (keyNodeMap.size() >= capacity) {
                LRUCache delTargetLRUCache = lruHead.next;
                DoubleNode delNode = delTargetLRUCache.tail.prev;
                delTargetLRUCache.isolate(delNode);

                keyNodeMap.remove(delNode.key); // forget to remove the delnode, NPE happened

                isolateEmptyLRUCache(delTargetLRUCache);
            }

            //initialization node
            DoubleNode crtNode = new DoubleNode(key, value, 1);
            keyNodeMap.put(key, crtNode);// forget to add the crtNode
            addNodeToLRUCacheLinkedList(crtNode);
        }

    }

    private void connectLRUCache(LRUCache prev, LRUCache crt) {

        crt.next = prev.next;
        prev.next = crt;
        crt.next.prev = crt;
        crt.prev = prev;
    }

    class LRUCache {
        DoubleNode head;
        DoubleNode tail;
        int size;
        int accessCount;
        LRUCache next;
        LRUCache prev;

        public LRUCache(int size, int accessCount) {
            head = new DoubleNode(-1, -1, -1);
            tail = new DoubleNode(-1, -1, -1);
            head.next = tail;
            tail.prev = head;
            this.size = size;
            this.accessCount = accessCount;
        }


        public void isolate(DoubleNode crt) {
            crt.prev.next = crt.next;
            crt.next.prev = crt.prev;
            this.size--;
        }

        public void put(DoubleNode crt) {
            crt.next = head.next;
            head.next.prev = crt;
            crt.prev = head;
            head.next = crt;
            size++;
        }

    }

    class DoubleNode {
        DoubleNode prev;
        DoubleNode next;
        int value;
        int key;// forget to add key : it can't handle delete operation
        int accessCount;

        public DoubleNode(int key, int value, int accessCount) {
            this.value = value;
            this.key = key;
            this.accessCount = accessCount;
        }
    }
}
