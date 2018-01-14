import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by JianZhang on 1/13/18.
 */
public class Deque<Item> implements Iterable<Item> {
    private int size = 0;
    private DequeNode firstNode = null;
    private DequeNode lastNode = null;

    public Deque() {
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

    public static void main(String[] args) {
        Deque<Integer> rq = new Deque<Integer>();
        rq.addFirst(12);
        rq.addLast(13);
        rq.removeFirst();
        rq.removeLast();
    }
}

