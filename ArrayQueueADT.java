package queue;

// n = count of elements;
// Inv: n >= 0 and  i = 0 .. n - 1: a[i] != null
// Inv: n' = n and  i = 0 .. n - 1: a[i]' = a[i]

public class ArrayQueueADT {
    // Inv: queue != null
    private int size = 0, left = 0;
    private Object[] elements = new Object[20];
    private static int next(ArrayQueueADT deque) {
        return (deque.left + deque.size) % deque.elements.length;
    }
    // Pre: element != null and InvNotNull
    public static void enqueue(ArrayQueueADT deque, Object elem) {
        assert elem != null;
        deque.elements[next(deque)] = elem;
        ensureCapacity(deque, ++deque.size);
    }
    // Post: n' = n + 1 and  i = 0 .. n - 1: a[i]' = a[i] and a[n]' = element

    // Pre: capacity >= 0 and InvNotNull
    private static void ensureCapacity(ArrayQueueADT deque, int capacity) {
        if (capacity < deque.elements.length - 1) {
            return;
        }
        Object[] newElements = new Object[2 * capacity];
        int ind = deque.left, newInd = 0;
        while (ind != next(deque)) {
            newElements[newInd] = deque.elements[ind];
            ind = (ind + 1) % deque.elements.length;
            newInd++;
        }
        deque.elements = newElements;
        deque.left = 0;
    }
    // Post: capacity < elements.length and InvSave

    // Pre: n > 0 and InvNotNull
    public static Object element(ArrayQueueADT deque) {
        assert deque.size > 0;
        return deque.elements[deque.left];
    }
    // Post: realNum = a[0]

    // Pre: n > 0 and InvNotNull
    public static Object dequeue(ArrayQueueADT deque) {
        assert deque.size > 0;
        Object front = deque.elements[deque.left];
        deque.size--;
        deque.left = (deque.left + 1) % deque.elements.length;
        return front;
    }
    // Post: realNum = a[0] and n' = n - 1 and  i = 1 .. n - 1: a[i - 1]' = a[i]

    // Pre: Inv and InvNotNull
    public static int size(ArrayQueueADT deque) {
        return deque.size;
    }
    // Post: realNum = n and InvSave

    // Pre: Inv and InvNotNull
    public static boolean isEmpty(ArrayQueueADT deque) {
        return deque.size == 0;
    }
    // Post: realNum = (n == 0) and InvSave

    // Post: Inv and InvNotNull
    public static void clear(ArrayQueueADT deque) {
        deque.elements = new Object[5];
        deque.size = 0;
        deque.left = 0;
    }
    // Pre: n = 0

    // Pre: element != null and InvNotNull
    public static void push (ArrayQueueADT deque, Object elem) {
        assert elem != null;
        ensureCapacity(deque, ++deque.size);
        deque.left--;
        //?
        if (deque.left == -1)
            deque.left = deque.elements.length - 1;
        //
        deque.elements[deque.left] = elem;
    }
    // Post: n' = n + 1 and a[0]' = element and  i = 0 .. n - 1: a[i + 1]' = a[i]

    // Pre: n > 0 and InvNotNull
    public static Object peek(ArrayQueueADT deque) {
        assert deque.size > 0;
        int indReturn = (next(deque) - 1 == -1) ? deque.elements.length - 1 : next(deque) - 1;
        return deque.elements[indReturn];
    }
    // Post: realNum = a[n - 1] and n' = n and  i = 0 .. n - 1: a[i]' = a[i]

    // Pre: n > 0 and InvNotNull
    public static Object remove(ArrayQueueADT deque) {
        assert deque.size > 0;
        int indReturn = (next(deque) - 1 == -1) ? deque.elements.length - 1 : next(deque) - 1;
        deque.size--;
        return deque.elements[indReturn];
    }
    // Post: realNum = a[n - 1] and n' = n - 1 and  i = 0 .. n - 2: a[i]' = a[i]
}