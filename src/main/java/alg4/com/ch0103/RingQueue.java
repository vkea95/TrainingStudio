package alg4.com.ch0103;

import java.util.EmptyStackException;

/**
 * Created by JianZhang on 5/25/17.
 */
public class RingQueue<Item> {
    private Node<Item> lastNode = null;

    private int count;

    public RingQueue()

    {
        lastNode = new Node(null);
        lastNode.next = lastNode;

    }

    public void enQueue(Item item) {
        Node<Item> tmpNode = new Node<Item>(item);
        Node<Item> head = lastNode.next;
        lastNode.next = tmpNode;
        lastNode = lastNode.next;
        lastNode.next = head;
        count++;
    }

    public Item deQueue() {
        Node<Item> result = null;
        if (lastNode.next == lastNode) {
            throw new EmptyStackException();
        } else {
            result = lastNode.next;
            lastNode.next = lastNode.next.next;
        }
        count--;
        return result.item;

    }
}

class Node<Item> {

    Item item;
    //    Node prev;
    Node next;

    public Node(Item item) {
        this.item = item;
    }
}
