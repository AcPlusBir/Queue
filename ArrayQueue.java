package queue;

// n = count of elements;
// Inv: n >= 0 and  i = 0 .. n - 1: a[i] != null
// Inv: n' = n and  i = 0 .. n - 1: a[i]' = a[i]

public class ArrayQueue {
    private int size = 0, left = 0;
    private Object[] elements = new Object[20];
    // Pre: left >= 0 and size >= 0
    private int next() {
        return (left + size) % elements.length;
    }
    // Post: ind: a[n - 1] = elements[ind]

    // Pre: element != null
    public void enqueue(Object elem) {
        assert elem != null;
        elements[next()] = elem;
        ensureCapacity(++size);
    }
    // Post: n' = n + 1 and  i =0 .. n - 1: a[i]' = a[i] and a[n]' = element

    // Pre: capacity >= 0
    private void ensureCapacity(int capacity) {
        if (capacity < elements.length - 1) {
            return;
        }
        Object[] newElements = new Object[2 * capacity];
        int ind = left, newInd = 0;
        while (ind != next()) {
            newElements[newInd] = elements[ind];
            ind = (ind + 1) % elements.length;
            newInd++;
        }
        elements = newElements;
        left = 0;
    }
    // Post: capacity < elements.length and Inv

    // Pre: n > 0
    public Object element() {
        assert size > 0;
        return elements[left];
    }
    // Post: realNum = a[0] and Inv

    // Pre: n > 0
    public Object dequeue() {
        assert size > 0;
        Object front = elements[left];
        size--;
        left = (left + 1) % elements.length;
        return front;
    }
    // Post: realNum = a[0] and n' = n - 1 and  i = 1 .. n - 1: a[i - 1]' = a[i]

    // Pre: Inv
    public int size() {
        return size;
    }
    // Post: realNum = n and Inv

    // Pre: Inv
    public boolean isEmpty() {
        return size == 0;
    }
    // Post: realNum = (n == 0) and Inv

    // Post: Inv
    public void clear() {
        elements = new Object[5];
        size = 0;
        left = 0;
    }
    // Pre: n = 0

    // Pre: element != null
    public void push (Object elem) {
        assert elem != null;
        ensureCapacity(++size);
        left--;
        //?
        if (left == -1)
            left = elements.length - 1;
        //
        elements[left] = elem;
    }
    // Post: n' = n + 1 and a[0]' = a and  i = 0 .. n - 1: a[i + 1]' = a[i]

    // Pre: n > 0
    public Object peek() {
        assert size > 0;
        int indReturn = (next() - 1 == -1) ? elements.length - 1 : next() - 1;
        return elements[indReturn];
    }
    // Post: realNum = a[n - 1] and n' = n and  i = 0 .. n - 1: a[i]' = a[i]

    // Pre: n > 0
    public Object remove() {
        assert size > 0;
        int indReturn = (next() - 1 == -1) ? elements.length - 1 : next() - 1;
        size--;
        return elements[indReturn];
    }
    // Post: realNum = a[n - 1] and n' = n - 1 and  i = 0 .. n - 2: a[i]' = a[i]
}