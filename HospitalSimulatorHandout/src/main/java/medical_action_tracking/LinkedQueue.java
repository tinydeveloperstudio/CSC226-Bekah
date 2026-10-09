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

    public LinkedQueue() {
        //Initializes an empty queue.
        front = null;
        rear = null;
        size = 0;
    }

    public void enqueue(T item) {
        if (item == null) {
            throw new IllegalArgumentException("The item to be enqueued cannot be null.");
        }
        current = new Node(item);
        //Adds a node at the rear. Updates both references when the queue is empty.
        if (size == 0) {
            rear = current;
            front = current;
        }
        else {
            rear.SetNext(current);
            rear = current;
        }
        size++;
    }

    public T dequeue() {
        //Removes and return the front item, or return null if empty.
        //When removing the last item, makes both front and rear null.
        T toReturn;
        if (isEmpty()) {
            return null;
        }
        else if (size == 1) {
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

    public int size() {
        //Returns the number of queued items.
        return size;
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
