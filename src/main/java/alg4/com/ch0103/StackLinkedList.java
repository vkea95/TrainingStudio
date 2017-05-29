package alg4.com.ch0103;

import java.util.Iterator;

/**
 * Created by JianZhang on 12/11/16.
 */
public class StackLinkedList<Item> implements Iterable<Item> {

    private Node first;
    private int N;

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }


    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node currentNode = first;

        public boolean hasNext() {
            return currentNode != null;//-->not currentNode.next==null
        }

        public Item next() {
            Item item = currentNode.item;
            currentNode = currentNode.next;

            return item;
        }

        public void remove() {

        }
    }

    private class Node {
        Item item;
        Node next;
    }
}
