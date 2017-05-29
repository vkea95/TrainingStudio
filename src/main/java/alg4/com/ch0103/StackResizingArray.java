package alg4.com.ch0103;

import java.util.Iterator;

/**
 * Created by JianZhang on 12/11/16.
 */
public class StackResizingArray<Item> implements Iterable<Item> {

    private Item[] a = (Item[]) new Object[1];//stack items
    private int N = 0;

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void resize(int maxSize) {
        //move the element to the new array of size max
        Item[] temp = (Item[]) new Object[maxSize];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public void push(Item item) {
        //add item to top of stack
        if (N == a.length) resize(2 * a.length);
        a[N++] = item;
    }

    public Item pop() {
        Item item = a[N];
        a[N--] = null;//avoid loitering
        if (N > 0 && N == a.length / 4) resize(a.length / 2); //-->avoid frequent resize
        return item;
    }


    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {

        private int i = N;//

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            return a[i--];
        }

        public void remove() {

        }
    }
}
