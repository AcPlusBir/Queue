package queue;

// n = count of elements;
// Inv: n >= 0 and  i = 0 .. n - 1: a[i] != null
// Inv: n' = n and  i = 0 .. n - 1: a[i]' = a[i]

public class ArrayQueueModule {
    private static int size = 0, left = 0;
    private static Object[] elements = new Object[20];
    // Pre: left >= 0 and size >= 0
    private static int next() {
        return (left + size) % elements.length;
    }
    // Post: ind: a[n - 1] = elements[ind]

    // Pre: capacity >= 0
    private static void ensureCapacity(int capacity) {
        if (capacity < elements.length - 1) {
            return;
        }
        Object[] newElements = new Object[2 * capacity];
        int ind = left, newInd = 0;
        while (ind != next()) {
            newElements[newInd++] = elements[ind];
            ind = (ind + 1) % elements.length;
        }
        elements = newElements;
        left = 0;
    }
    // Post: capacity < elements.length and InvSave

    // Pre: element != null
    public static void enqueue(Object elem) {
        assert elem != null;
        elements[next()] = elem;
        ensureCapacity(++size);
    }
    // Post: n' = n + 1 and  i = 0 .. n - 1: a[i]' = a[i] and a[n]' = element

    // Pre: n > 0
    public static Object element() {
        assert size > 0;
        return elements[left];
    }
    // Post: realNum = a[0] and InvSave

    // Pre: n > 0
    public static Object dequeue() {
        assert size > 0;
        Object front = elements[left];
        size--;
        left = (left + 1) % elements.length;
        return front;
    }
    // Post: realNum = a[0] and n' = n - 1 and  i = 1 .. n - 1: a[i - 1]' = a[i]

    // Pre: Inv
    public static int size() {
        return size;
    }
    // Post: realNum = n and InvSave

    // Pre: Inv
    public static boolean isEmpty() {
        return size == 0;
    }
    // Post: realNum = (n == 0) and InvSave

    // Post: Inv
    public static void clear() {
        elements = new Object[5];
        size = 0;
        left = 0;
    }
    // Pre: n = 0

    // Pre: element != null
    public static void push (Object elem) {
        assert elem != null;
        ensureCapacity(++size);
        left--;
        //?
        if (left == -1)
            left = elements.length - 1;
        //
        elements[left] = elem;
    }
    // Post: n' = n + 1 and a[0]' = element and  i = 0 .. n - 1: a[i + 1]' = a[i]

    // Pre: n > 0
    public static Object peek() {
        assert size > 0;
        int indReturn = (next() - 1 == -1) ? elements.length - 1 : next() - 1;
        return elements[indReturn];
    }
    // Post: realNum = a[n - 1] and n' = n and  i = 0 .. n - 1: a[i]' = a[i]

    // Pre: n > 0
    public static Object remove() {
        assert size > 0;
        int indReturn = (next() - 1 == -1) ? elements.length - 1 : next() - 1;
        size--;
        return elements[indReturn];
    }
    // Post: realNum = a[n - 1] and n' = n - 1 and  i = 0 .. n - 2: a[i]' = a[i]
}