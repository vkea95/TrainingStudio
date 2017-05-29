package alg4.com.ch0103;

import java.util.Iterator;

/**
 * Created by JianZhang on 5/22/17.
 */
public class FixedCapacityStack<Item> implements Iterable<Item> {
    private Item[] a = (Item[]) new Object[1];//stack entries
    private int N = 0;//size

//    public FixedCapacityStack() {
//        a = (Item[]) new Object[1];
////        N = 1;
//    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
//        if ()
        if (N == a.length) resize(2 * a.length);
        a[N++] = item;
    }

    public Item pop() {
        Item item = a[--N];
        a[N] = null; //避免对象游离
//        Bug1: 删除元素后,才需要resize,而不是先resize
        if (N > 0 && N == a.length / 4) resize(a.length / 2);

        return item;
    }

    private void resize(int maxSize) {
        Item[] b = (Item[]) new Object[maxSize];
        for (int i = 0; i < N; i++)
            b[i] = a[i];

        a = b;

    }

    public boolean isFull() {
        return N == a.length;
    }

    public static void main(String[] args) {
        FixedCapacityStack<Integer> fixedCapacityStack = new FixedCapacityStack<>();

        for (int i = 0; i < 100; i++) {
            fixedCapacityStack.push(i);
            System.out.println(fixedCapacityStack.isFull());


        }
        for (int i = 0; i < 100; i++)
            System.out.println(fixedCapacityStack.pop());
    }

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {

        //bug2: 要留下当时的index
        private int i = N;

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            //bug3:index要自减一
            return a[--i];
        }

        ;

        public void remove() {
        }
    }
}


