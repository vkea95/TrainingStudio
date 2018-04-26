import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by JianZhang on 1/13/18.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queueArray = null;
    private int size = 0;

    public RandomizedQueue() {
        queueArray = (Item[]) new Object[1];
    }


    private void resizeArray(int capacity) {
//        at this time, we just use the heights of array, it's enough
        Item[] tmpItemArray = (Item[]) new Object[capacity];

        for (int i = 0; i < size; i++) {
            tmpItemArray[i] = this.queueArray[i];
        }
        this.queueArray = tmpItemArray;

    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void enqueue(Item item) {
        if (item == null) throw new java.lang.IllegalArgumentException();

        if (this.queueArray.length == this.size) {
            resizeArray(2 * size);
        }
        this.queueArray[size++] = item;
//        this.isFullArray[heights] = true;

    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int index = StdRandom.uniform(size);
        Item result = this.queueArray[index];
        // bug: wrong inde: this.queueArray[heights] ->this.queueArray[heights-1]
        this.queueArray[index] = this.queueArray[size - 1];
        this.queueArray[--size] = null;
//        bug: adding condition heights>0 -->avoid
        if (size > 0 && size == (this.queueArray.length / 4)) resizeArray(this.queueArray.length / 4);
        return result;
    }

    public Item sample() {

        if (isEmpty()) throw new NoSuchElementException();
        int index = StdRandom.uniform(size);
        return this.queueArray[index];
    }

    public Iterator<Item> iterator() {
        return new RandomizeQueueIterator();
    }


    private class RandomizeQueueIterator implements Iterator<Item> {

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
            if (cloneSize <= 0) throw new NoSuchElementException();
            int index = StdRandom.uniform(cloneSize);
            Item result = cloneArray[index];
            cloneArray[index] = cloneArray[cloneSize - 1];
            cloneArray[--cloneSize] = null;
            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(620);
        rq.dequeue();
        rq.isEmpty();
        rq.isEmpty();
        rq.isEmpty();
        rq.enqueue(661);
    }
}
