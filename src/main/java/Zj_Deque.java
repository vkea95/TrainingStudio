import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by JianZhang on 1/13/18.
 */
public class Zj_Deque<Item> implements Iterable<Item> {

    private int size = 0;
    private DequeNode firstNode = null;
    private DequeNode lastNode = null;

    public Zj_Deque() {
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) throw new java.lang.IllegalArgumentException();
        if (firstNode == null) {
            firstNode = new DequeNode(item);
            lastNode = firstNode;
        } else {
            DequeNode tmpNode = new DequeNode(item);
            tmpNode.next = firstNode;
            firstNode.prev = tmpNode;
            firstNode = tmpNode;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) throw new java.lang.IllegalArgumentException();
        if (lastNode == null) {
            firstNode = new DequeNode(item);
            lastNode = firstNode;
        } else {
            DequeNode tmpNode = new DequeNode(item);
            lastNode.next = tmpNode;
            tmpNode.prev = lastNode;
            lastNode = lastNode.next;
        }
        size++;
    }

    public Item removeFirst() {

        if (firstNode == null) {
            throw new NoSuchElementException();
        }

        Item result = firstNode.val;
        if (firstNode.next == null) {
            lastNode = null;
            firstNode = null;
        } else {
            firstNode.next.prev = null;
            firstNode = firstNode.next;
        }
        size--;
        return result;
    }

    public Item removeLast() {

        if (lastNode == null) {
            throw new NoSuchElementException();
        }

        Item result = lastNode.val;
        if (lastNode.prev == null) {
            lastNode = null;
            firstNode = null;
        } else {
            lastNode.prev.next = null;
            lastNode = lastNode.prev;
        }
        size--;
        return result;
    }


    public Iterator<Item> iterator() {
        return new DequeIterator();

    }


    private class DequeNode {
        public Item val;
        public DequeNode next = null;
        public DequeNode prev = null;

        public DequeNode(Item value) {
            val = value;
        }

    }

    private class DequeIterator implements Iterator<Item> {

        private DequeNode current;

        public DequeIterator() {
            current = firstNode;
        }

        @Override
        public boolean hasNext() {
//            bug1: I used to code as 'current.next!=null'
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null) throw new NoSuchElementException();
            Item result = current.val;
            current = current.next;
            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    class Result {

        /*
         * Complete the 'ada' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts INTEGER year as parameter.
         */


    }


    public static void main(String[] args) {
        int year = 2018;
        int result = 1;
        String octFirstWeekDay = String.format("%04d-10-22", year);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(format.parse(octFirstWeekDay));
            result = calendar.get(calendar.DAY_OF_WEEK);
        } catch (ParseException e) {
            System.out.println("invalid input year:" + e);
            result = -1;
        }

        result = result <= 2 ? (9 - result) : (16 - result);


        Zj_Deque<Integer> rq = new Zj_Deque<Integer>();
        rq.addFirst(12);
        rq.addLast(13);
        rq.removeFirst();
        rq.removeLast();
        int[][] envelopes = new int[2][2];
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> {
            if (envelopes[a][0] == envelopes[b][0]) {
                return envelopes[b][1] - envelopes[a][1];
            } else {
                return envelopes[a][0] - envelopes[b][0];
            }
        });
    }

}