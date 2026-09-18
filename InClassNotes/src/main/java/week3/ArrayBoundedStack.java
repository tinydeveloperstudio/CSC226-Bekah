package week3;

public class ArrayBoundedStack<T> implements StackInterface<T> {
    private final int DEFAULTCAP = 100;
    private T[] elements;
    private int topIndex=-1;

    public ArrayBoundedStack(){
        elements = (T[]) new Object[DEFAULTCAP];
    }
    public ArrayBoundedStack(int maxSize){
        elements = (T[]) new Object[maxSize];
    }
    public boolean isEmpty()
    {
        if (elements.length > 0) {
            return false;   //Returns false if the bounded stack is not empty
        }
        else {
            return true;    //Returns true if the bounded stack is empty
        }
    }

    public boolean isFull()
    {
        if (elements.length == DEFAULTCAP) {
            return true;    //Returns true if the bounded stack is at its cap size
        }
        else {
            return false;   //Returns false if the bounded stack is not at its cap size
        }
    }
    
    public void push(T element)
    {
        elements[topIndex + 1] = element;   //Adds the element to the top index
        topIndex++;     //Increments the topIndex up one value
    }
    
    public void pop()
    {
        elements[topIndex + 1] = null;  //Changes the value at the top index to null
        topIndex--;     //Increments the topIndex down one value
    }
    
    public T top()
    {
      return elements[topIndex + 1];    //Returns the element at the top index value
    }
}