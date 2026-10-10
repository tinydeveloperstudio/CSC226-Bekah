/**
* Author: Beck Bledsoe
* Version: 10/09/2026
* Summary: This script contains the main functionality for the LinkedQueue class.
*/

package medical_action_tracking;

public class LinkedQueue<T> {
    private class Node {
        private T data;
        private Node next;

        private Node(T data) {
            this.data = data;
            next = null;
        }

        private Node GetNext() {
            return next;
        }

        private void SetNext(Node next) {
            this.next = next;
        }

        private T GetData() {
            return (T) data;
        }
    }

    private Node front;
    private Node rear;
    private Node current;
    private int size;
    private int maxCapacity;

    public LinkedQueue() {
        //Initializes an empty queue.
        front = null;
        rear = null;
        size = 0;
    }

    //Constructor for bounded linked queue with max capacity
    public LinkedQueue(int maxCapacity) {
        front = null;
        rear = null;
        size = 0;
        this.maxCapacity = maxCapacity;
    }

    public void enqueue(T item) {
        if (item == null) {
            throw new IllegalArgumentException("The item to be enqueued cannot be null.");
        }
        current = new Node(item);
        //Adds a node at the rear. Updates both references when the queue is empty.
        if (isEmpty()) {
            rear = current;
            front = current;
        }
        else {
            rear.SetNext(current);
            rear = current;
        }
        size++;
    }

    public void enqueueBounded(T item) {
        if (item == null) {
            throw new IllegalArgumentException("The item to be enqueued cannot be null.");
        }
        //Adds a node at the rear. Updates both references when the queue is empty. Rejects items if the queue is full.
        if (!isFull()) {
            current = new Node(item);
            if (isEmpty()) {
                rear = current;
                front = current;
            }
            else {
                rear.SetNext(current);
                rear = current;
            }
            size++;
        }
        else {
            throw new IllegalArgumentException("Max capacity is reached, please dequeue before trying to enqueue another item.");
        }
    }

    public T dequeue() {
        //Removes and return the front item, or return null if empty.
        //When removing the last item, makes both front and rear null.
        T toReturn;
        if (isEmpty()) {
            return null;
        }
        
        if (size == 1) {
            toReturn = peekFront();
            front = null;
            rear = front;
            size--;
            return toReturn;
        }
        else {
            toReturn = peekFront();
            front = front.GetNext();
            size--;
            return toReturn;
        }
    }

    //I researched the Insert Restricted Queue on GeeksForGeeks, which restricts enqueueing to the end of the queue,
    //but allows for dequeueing from the front or the rear. The implemented dequeue function is below for the alternate type of queue.
    public T restrictedDequeue(String dequeuePosition) {
        T toReturn;
        if (isEmpty()) {
            return null;
        }

        if (dequeuePosition.toLowerCase().equals("front")) {
            if (size == 1) {
                toReturn = peekFront();
                front = null;
                rear = front;
                size--;
                return toReturn;
            }
            else {
                toReturn = peekFront();
                front = front.GetNext();
                size--;
                return toReturn;
            }
        }
        else if (dequeuePosition.toLowerCase().equals("rear")) {
            if (size == 1) {
                toReturn = peekFront();
                front = null;
                rear = front;
                size--;
                return toReturn;
            }
            else {
                current = front;
                for (int i = 1; i < size-1; i++) {
                    current = current.GetNext();
                }
                toReturn = rear.GetData();
                rear = current;
                rear.SetNext(null);
                size--;
                return toReturn;
            }
        }
        else {
            throw new IllegalArgumentException("Did not specify if dequeue is at front or rear. Please type front or rear into the restrictedDequeue argument.");
        }
    }

    public T peekFront() {
        //Returns the front item without removing it, or null if empty.
        if (front == null) {
            return null;
        }
        else {
            return front.GetData();
        }
    }

    public boolean isEmpty() {
        //Determines whether the queue contains any items.
        if (size == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isFull() {
        if (size == maxCapacity) {
            return true;
        }
        else {
            return false;
        }
    }

    public int size() {
        //Returns the number of queued items.
        return size;
    }

    //Recursive size counter for queue
    public int recursiveSize(Node current) {
        if (current == null) {
            return 0;
        }
        current = current.GetNext();
        int count = recursiveSize(current);
        return 1 + count;
    }

    //Peeks the front node for recursive count (not the front node's data, but the node itself)
    public Node peekFrontNode() {
        return front;
    }

    @Override
    public String toString() {
        //Recursively builds a string from front to rear without changing the queue.
        String toReturn = "";
        current = front;
        while(current != null) {
            if (toReturn != "") {
                toReturn = toReturn + ", " + current.GetData().toString();
            }
            else {
               toReturn = current.GetData().toString(); 
            }
            current = current.GetNext();
        }
        return "[" + toReturn + "]";
    }

    private void appendNodesRecursively(Node current, StringBuilder result) {
        //Adds the current node's data, then recursively visit current.next.
        //Stops at the null-node base case.
        if (current.GetNext() != null) {
            if (size == 0) {
                front = current;
                rear = current;
            }
            else {
                rear.SetNext(current);
                rear = current;
            }
            result.append(rear.GetData().toString());
            current = current.GetNext();
            appendNodesRecursively(current, result);
        }
    }
}
