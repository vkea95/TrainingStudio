package myjava.com.collection;

/**
 * Created by tclresearchamerica on 5/29/16.
 * reference:
 * http://www.javamadesoeasy.com/2015/02/hashmap-custom-implementation.html
 * ************************************************
 * Thoughts:
 * 基本了解啦hashMap的背后的原理,
 * 1.关键就是hash之后的linkedlist,所以每次访问put,get,display都要用hash判断下
 * 2.在创建Entry的时候,里面有next
 * ************************************************
 * ************************************************
 * ************************************************
 */
public class HashMapCustom<K, V> {

    private Entry<K, V>[] table;//Array of Entry
    private int capacity = 4;//Initial capacity of HashMap

    static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }


    public HashMapCustom() {
        table = new Entry[capacity];
    }

    /**
     * Method allows you put key-value pair in HashMapCustom.
     * If the map already contains a mapping for the key, the old value is replaced.
     * Note: method does not allows you to put null key though it allows null values.
     * Implementation allows you to put custom objects as a key as well.
     * Key Features: implementation provides you with following features:-
     * >provide complete functionality how to override equals method.
     * >provide complete functionality how to override hashCode method.
     *
     * @param newKey
     * @param data
     */
    public void put(K newKey, V data) {
        if (newKey == null) {
            return;//
        }

        //calculate hash of key
        int hash = hash(newKey);//hash 在0~capacity-1
        //create new entry -->
        Entry<K, V> newEntry = new Entry<>(newKey, data, null);//next 设成null

        //if table doesn't contain any entry, story entry there
        if (table[hash] == null) {//
            table[hash] = newEntry;
        } else {
            Entry<K, V> previous = null;
            Entry<K, V> current = table[hash];

            while (current != null) {
                if (current.key.equals(newKey)) {
                    if (previous == null) {//node has to be insert on first of bucket.
                        newEntry.next = current.next;
                        table[hash] = newEntry;
                        return;
                    } else {
                        newEntry.next = current.next;
                        previous.next = newEntry;
                        return;
                    }
                }
                previous = current;
                current = current.next;
            }
            previous.next = newEntry;
        }

    }

    /**
     * Method returns value corresponding to key.
     *
     * @param key
     */
    public V get(K key) {
        //哈希
        int hash = hash(key);
        if (table[hash] == null) {
            return null;
        } else {
            Entry<K, V> temp = table[hash];
            while (temp != null) {
                if (temp.key == key) {
                    return temp.value;
                }
                temp = temp.next;
            }
        }
        return null;
    }

    /**
     * Method removes key-value pair from HashMapCustom.
     *
     * @param key
     */
    public boolean remove(K key) {
        int hash = hash(key);
        if (table[hash] == null) {
            return false;
        } else {
            Entry<K, V> previous = null;
            Entry<K, V> current = table[hash];

            while (current != null) {
                if (current.key.equals(key)) {
                    if (previous == null) {
                        table[hash] = table[hash].next;
                        return true;
                    } else {
                        previous.next = current.next;
                        return true;
                    }
                }
                previous = current;
                current = current.next;
            }
            return false;
        }

    }


    /**
     * Method displays all key-value pairs present in HashMapCustom.,
     * insertion order is not guaranteed, for maintaining insertion order
     * refer LinkedHashMapCustom.
     * <p>
     * //     * @param key
     */
    public void display() {
        for (int i = 0; i < capacity; i++) {
            System.out.println("hash Key:" + i);
            if (table[i] != null) {
                Entry<K, V> entry = table[i];
                while (entry != null) {
                    System.out.print("{" + entry.key + "=" + entry.value + "}");
                    entry = entry.next;
                }
                System.out.println();
            }
        }
    }


    /**
     * Method implements hashing functionality, which helps in finding the appropriate
     * bucket location to store our data.
     * This is very important method, as performance of HashMapCustom is very much
     * dependent on  this method's implementation.
     *
     * @param key
     */
    private int hash(K key) {

        return Math.abs(key.hashCode() % capacity);
    }


}

