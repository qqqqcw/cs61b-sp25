import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T>{
    private Node sentinel;
    private int size;

    private class Node {
        public T item;
        public Node next;
        public Node pre;
    }
    public LinkedListDeque61B() {
        this.sentinel = new Node();
        this.sentinel.next = this.sentinel;
        this.sentinel.pre = this.sentinel;

    }
    @Override
    public void addFirst(T x) {
        Node a = new Node();
        a.item = x;
        a.next = this.sentinel.next;
        a.next.pre = a;
        this.sentinel.next = a;
        a.pre = this.sentinel;
        this.size++;
    }

    @Override
    public void addLast(T x) {
        Node a = new Node();
        a.item = x;
        a.pre = this.sentinel.pre;
        a.pre.next = a;
        a.next = this.sentinel;
        this.sentinel.pre = a;
        this.size++;
    }

    @Override
    public List<T> toList() {
        List<T> ReturnList = new ArrayList<>();
        Node a = this.sentinel.next;
        while (a != this.sentinel) {
            ReturnList.add(a.item);
            a = a.next;
        }
        return ReturnList;
    }

    @Override
    public boolean isEmpty() {
        return this.sentinel.next == this.sentinel;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        T ReturnElement = this.sentinel.next.item;
        this.sentinel.next.next.pre = this.sentinel;
        this.sentinel.next = this.sentinel.next.next;
        size--;
        return ReturnElement;
    }

    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        T ReturnElement = this.sentinel.pre.item;
        this.sentinel.pre.pre.next = this.sentinel;
        this.sentinel.pre = this.sentinel.pre.pre;
        size--;
        return ReturnElement;
    }

    @Override
    public T get(int index) {
        if(index >= this.size || index < 0) {
            return null;
        }
        Node a = this.sentinel.next;
        for(int i = 0; i < index; i++) {
            a = a.next;
        }
        return a.item;
    }

    @Override
    public T getRecursive(int index) {
        if(index >= this.size || index < 0) {
            return null;
        }
        return getRecursiveHelper(index, this.sentinel.next);
    }

    private T getRecursiveHelper(int index, Node node) {
        if (index == 0) {
            return node.item;
        }
        return getRecursiveHelper(index - 1, node.next);
    }
}
