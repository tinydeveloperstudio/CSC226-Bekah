package medical_action_tracking;

public class LinkedStack<T> {
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

    private Node top;
    private Node bottom;
    private Node current;
    private int size;

    public LinkedStack() {
        top = null;
        bottom = null;
        size = 0;
    }

    public void push(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Items to be pushed cannot be null.");
        }

        current = new Node(item);
        //Rejects null items, then links a new node at the top and updates size.
        if (size == 0){
            bottom = current;
            top = current;
        }
        else {
            top.SetNext(current);
            top = current;
        }
        size++;
    }

    public T pop() {
        //Removes and returns the top item, or returns null when empty.
        T toReturn;
        if (size > 1) {
            current = bottom;
            for (int i = 1; i < size; i++) {
                current = current.GetNext();
            }
            toReturn = peek();
            top = current;
            top.SetNext(null);
        }
        else if (size == 1){
            top = bottom;
            toReturn = peek();
            top = null;
            bottom = top;
        }
        else {
            return null;
        }
        size--;
        return toReturn;
    }

    public T peek() {
        //Returns the top item without removing it, or null when empty.
        if (isEmpty()) {
            return null;
        }
        else {
            return top.GetData();
        }
    }

    public boolean isEmpty() {
        //Determines whether the stack contains any items.
        if (size <= 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public int size() {
        //Returns the number of stacked items.
        return size;
    }

    @Override
    public String toString() {
        //Builds [top, next, ...] by traversing the stack without changing it.
        String toReturn = "";
        current = bottom;
        while (current != null) {
            if (toReturn != "") {
                toReturn = current.GetData().toString() + ", " + toReturn;
            }
            else {
                toReturn = current.GetData().toString();
            }
            current = current.GetNext();
        }
        return "[" + toReturn + "]";
    }
}
