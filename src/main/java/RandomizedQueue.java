import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/**
 * Created by JianZhang on 1/13/18.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queueArray = null;
    private int size = 0;


    private void resizeArray(int capacity) {
//        at this time, we just use the size of array, it's enough
        Item[] tmpItemArray = (Item[]) new Object[capacity];
//        boolean[] tmpIsFullArray = new boolean[capacity];

        for (int i = 0; i < capacity; i++) {
//            tmpIsFullArray[i] = this.isFullArray[i];
            tmpItemArray[i] = this.queueArray[i];
        }
//        this.isFullArray = tmpIsFullArray;
        this.queueArray = tmpItemArray;

    }

    public RandomizedQueue() {
//        isFullArray = new boolean[1];
        queueArray = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void enqueue(Item item) {
        if (this.queueArray.length == this.size) {
            resizeArray(2 * size);
        }
        this.queueArray[size++] = item;
//        this.isFullArray[size] = true;

    }

    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int index = StdRandom.uniform(size);
        Item result = this.queueArray[index];
        this.queueArray[index] = this.queueArray[size];
//        this.isFullArray[index] = this.isFullArray[size];
        this.queueArray[size--] = null;
//        this.isFullArray[size--] = false;
        if (size == (this.queueArray.length / 4)) resizeArray(this.queueArray.length / 4);
        return result;
    }

    public Item sample() {

        if (isEmpty()) throw new java.util.NoSuchElementException();
        int index = StdRandom.uniform(size);
        return this.queueArray[index];
    }

    public Iterator<Item> iterator() {
        return new RandomizeQueueIterator();
    }


    private class RandomizeQueueIterator implements Iterator {

        private Item[] cloneArray = null;
        private int cloneSize = 0;

        public RandomizeQueueIterator() {
            cloneSize = size;
            cloneArray = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                cloneArray[i] = queueArray[i];
            }
        }

        @Override
        public boolean hasNext() {
            return cloneSize != 0;
        }

        @Override
        public Item next() {
            int index = StdRandom.uniform(cloneSize);
            Item result = cloneArray[index];
            cloneArray[index] = cloneArray[cloneSize];
            cloneArray[cloneSize--] = null;
            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {

    }
}
