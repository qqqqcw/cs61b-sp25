import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T>{
    private Node sentinel;
    private int size;

    public class Node {
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
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
