package jian.alg.com;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;

public class ConsistentHashing<T> implements Hashing<T> {


    public static void main(String[] args) {
        List<ServerNode> serverNodeList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            serverNodeList.add(new ServerNode("srv" + i, "password", "192.168.1." + i, String.valueOf(i)));
        }


        Hashing<ServerNode> hashing = new ConsistentHashing(serverNodeList, 1);
        hashing.addServerNode(new ServerNode("New srv", "password", "192.168.1.1", String.valueOf(100)));
        hashing.removeServerNode(serverNodeList.get(0));
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            ServerNode t = hashing.getServerNode(String.valueOf(i));
            map.putIfAbsent(t.servName, 0);
            map.put(t.servName, map.get(t.servName) + 1);
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("name: " + entry.getKey() + "   value: " + entry.getValue());
        }
    }


    TreeMap<Long, T> treeMap;
    Map<T, List<String>> nodeListMap;

    int virtualNumder;

    ConsistentHashing(List<T> serverNodeList, int virtualNumber) {
        this.virtualNumder = virtualNumber;
        nodeListMap = new HashMap<>();
        treeMap = new TreeMap<>();
        for (T t : serverNodeList) {
            nodeListMap.putIfAbsent(t, new ArrayList<>());
        }
        setupHashCircle();
    }

    private void setupHashCircle() {
        for (Map.Entry<T, List<String>> entry : nodeListMap.entrySet()) {
            addServerNode(entry.getKey());
        }
    }

    @Override
    public void addServerNode(T t) {
        nodeListMap.putIfAbsent(t, new ArrayList<>());
        for (int i = 0; i < virtualNumder; i++) {
            String tmp = t.toString() + "-node-" + i;
            Long hash = getHasing(tmp);
            treeMap.put(hash, t);
            nodeListMap.get(t).add(tmp);
        }
    }

    @Override
    public void removeServerNode(T t) {
        for (String tmp : nodeListMap.get(t)) {
            Long hash = getHasing(tmp);
            treeMap.remove(hash);
        }
        nodeListMap.clear();
    }

    @Override
    public T getServerNode(String val) {
        Long hashing = getHasing(val);
        SortedMap<Long, T> tail = treeMap.tailMap(hashing);
        return treeMap.get(tail.size() == 0 ? treeMap.firstKey() : tail.firstKey());
    }


    /**
     * MurMurHash算法，是非加密HASH算法，性能很高，
     * 比传统的CRC32,MD5，SHA-1（这两个算法都是加密HASH算法，复杂度本身就很高，带来的性能上的损害也不可避免）
     * 等HASH算法要快很多，而且据说这个算法的碰撞率很低.
     * http://murmurhash.googlepages.com/
     */
    @Override
    public Long getHasing(String key) {
        ByteBuffer buf = ByteBuffer.wrap(key.getBytes());
        int seed = 0x1234ABCD;

        ByteOrder byteOrder = buf.order();
        buf.order(ByteOrder.LITTLE_ENDIAN);

        long m = 0xc6a4a7935bd1e995L;
        int r = 47;

        long h = seed ^ (buf.remaining() * m);

        long k;
        while (buf.remaining() >= 8) {
            k = buf.getLong();

            k *= m;
            k ^= k >>> r;
            k *= m;

            h ^= k;
            h *= m;
        }

        if (buf.remaining() > 0) {
            ByteBuffer finish = ByteBuffer.allocate(8).order(
                    ByteOrder.LITTLE_ENDIAN);
            // for big-endian version, do this first:
            // finish.position(8-buf.remaining());
            finish.put(buf).rewind();
            h ^= finish.getLong();
            h *= m;
        }

        h ^= h >>> r;
        h *= m;
        h ^= h >>> r;


        buf.order(byteOrder);
        return h;
    }

}
