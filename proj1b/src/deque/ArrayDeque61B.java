package deque;

import java.util.*;

import static java.lang.Math.floorMod;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] ArrayDeque;
    private int FirstIndex;
    private int LastIndex;
    private int size;
    private int capacity = 8;

    public ArrayDeque61B() {
        this.ArrayDeque = (T[]) new Object[capacity];
        this.FirstIndex = 0;
        this.LastIndex = 0;
        this.size = 0;

    }

    private void resize(int newcapacity) {
        T[] a = (T[]) new Object[newcapacity];
        for (int i = 0; i < size; i++) {
            a[i] = ArrayDeque[floorMod(i + FirstIndex, capacity)];
        }
        FirstIndex = 0;
        LastIndex = size;
        capacity = newcapacity;
        ArrayDeque = a;
    }

    @Override
    public void addFirst(T x) {
        if (size == capacity) {
            resize(capacity * 2);
        }
        // 先移动索引再填入元素，确保FirstIndex始终指向第一个元素
        FirstIndex = floorMod(FirstIndex - 1, capacity);
        this.ArrayDeque[FirstIndex] = x;
        size++;
    }

    @Override
    public void addLast(T x) {
        if (size == capacity) {
            resize(capacity * 2);
        }
        this.ArrayDeque[LastIndex] = x;
        LastIndex = floorMod(LastIndex + 1, capacity);
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> ReturnList = new ArrayList<>();
        int index = FirstIndex;
        for (int i = 0; i < size; i++) {
            ReturnList.add(ArrayDeque[index]);
            index = floorMod(index + 1, capacity);
        }
        return ReturnList;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
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
        T ReturnElement = ArrayDeque[FirstIndex];
        ArrayDeque[FirstIndex] = null;
        FirstIndex = floorMod(FirstIndex + 1, capacity);
        size--;
        if (capacity >= 16 && size < capacity / 4) {
            resize(capacity / 2);
        }
        return ReturnElement;
    }

    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        LastIndex = floorMod(LastIndex - 1, capacity);
        T ReturnElement = ArrayDeque[LastIndex];
        ArrayDeque[LastIndex] = null;
        size--;
        if (capacity >= 16 && size < capacity / 4) {
            resize(capacity / 2);
        }
        return ReturnElement;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        int trueIndex = floorMod(index + FirstIndex, capacity); // 有点难想
        return ArrayDeque[trueIndex];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    public class ArrayDequeIterator implements Iterator<T> {

        private int returnedNumber;
        private int currentIndex;

        public ArrayDequeIterator () {
            returnedNumber = 0;
            currentIndex = FirstIndex;
        }
        @Override
        public boolean hasNext() {
            return returnedNumber < size;
        }

        @Override
        public T next() {
            if (hasNext()) {
                T item = ArrayDeque[currentIndex];
                currentIndex = floorMod(currentIndex + 1, capacity);
                returnedNumber++;
                return item;
            }
            throw new java.util.NoSuchElementException();
        }
    }


    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Deque61B<?>) {
            Deque61B<?> otherDeque = (Deque61B<?>) other;
            if (this.size != otherDeque.size()) {
                return false;
            }
            Iterator<T> thisIter = this.iterator();
            Iterator<?> otherIter = otherDeque.iterator();
            for (int i = 0; i < this.size; i++) {
                if (!Objects.equals(thisIter.next(), otherIter.next())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public String toString() {
        return toList().toString();
    }
}
