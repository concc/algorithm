package cn.edu.hbuas.heap;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class PriorityQueue<Key> implements Iterable<Key> {

    private Key[] pq;

    private int N;

    private Comparator<Key> comparator;

    public PriorityQueue(){
        this(1);
    }

    public PriorityQueue(int initCapacity) {
        pq = (Key[]) new Object[initCapacity + 1];
        N = 0;
    }

    public PriorityQueue(Comparator<Key> comparator) {
        this(1, comparator);
    }

    public PriorityQueue(int initCapacity, Comparator<Key> comparator) {
        this(initCapacity);
        this.comparator = comparator;
    }


    public PriorityQueue(Key[] keys) {
        N  = keys.length;
        pq = (Key[]) new Object[keys.length + 1];
        for (int i = 0; i < N; i++) {
            pq[i + 1] = keys[i];
        }
        for (int k = N/2; k >= 1; k--) {
            sink(k);
        }
        assert isMaxHeap();
    }


    private void sink(int k) {
        while (2 * k <= N) {
            // 找到子节点
            int j = 2 * k;
            // 比较左右子节点谁大，并将大的值赋值给当前子节点
            if (j < N && less(j, j + 1)) j++;
            // 如果子节点小与需下沉节点，结束
            if (!less(k , j)) break;
            swap(k, j);
            k = j;
        }
    }

    private boolean isMaxHeap() {
        return isMaxHeap(1);
    }

    private boolean isMaxHeap(int k) {
        if (k > N) {
            return true;
        }
        int left = 2 * k;
        int right = 2 * k + 1;
        if (left <= N && less(k , left)) return false;
        if (right <= N && less(k, right)) return false;
        return isMaxHeap(left) && isMaxHeap(right);
    }

    private void resize(int capacity) {
        assert capacity > N;
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i <= N; i++){
            temp[i] = pq[i];
        }
        pq = temp;
    }

    private int size() {
        return N;
    }



    private boolean isEmpty() {
        return N == 0;
    }

    private void swim (int k){
        // 父节点与子节点比较，如果父节点比子节点小则交换
        while (k > 1 && less(k/2, k)) {
            swap(k/2, k);
            // 重置当前节点
            k = k / 2;
        }
    }





    private void swap(int i , int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private boolean less(int i, int j) {
        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
        }
        else {
            return comparator.compare(pq[i], pq[j]) < 0;
        }
    }



    public void insert(Key key) {
        if (N >= pq.length - 1) {
            resize(2 * pq.length);
        }
        pq[++N] = key;
        swim(N);
        assert isMaxHeap();
    }

    public Key delMax() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Key max = pq[1];
        swap(1, N--);
        sink(1);
        pq[N+1] = null;
        if (N > 0 && (N == (pq.length - 1)/4)) {
            resize(pq.length / 2);
        }
        assert isMaxHeap();
        return max;
    }

    @Override
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }


    private class HeapIterator implements  Iterator<Key> {

        private PriorityQueue<Key> copy;

        public HeapIterator() {
            if (comparator == null) {
                copy = new PriorityQueue<Key>(size());
            }
            else {
                copy = new PriorityQueue<>(size(), comparator);
            }
            for (int i = 1; i <= N; i++) {
                copy.insert(pq[i]);
            }
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public Key next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return  copy.delMax();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }


}
