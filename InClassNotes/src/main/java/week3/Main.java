package week3;
import week1.*;

public class Main {
    public static void main(String args[]) {
        ArrayBoundedStack<Car> temp = new ArrayBoundedStack<>();

        temp.isEmpty();
        temp.isFull();
        temp.push(new Car(123456789));
        temp.pop();
        temp.top();
    }
}