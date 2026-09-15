package week1;

import java.util.List;

public class Counter {

    // 2. Add a private field
    private int count = 0;
    // 3. Write the default constructor
    public Counter(){
    }
    // 4. Add an alternate constructor
    public Counter(String CounterName){
    }
    // 5. Write an accessor method
    public int GetCount(){
        return count;
    }
    // 6. Add an update method
    public void Update(){
        RecordSize();
        count++;
    }
    // 7. Overload increment
    public void Update(int CountIncrement){
        RecordSize();
        count = count + CountIncrement;
    }
    // 8. Add a reset method
    public void ResetCount(){
        count = 0;
    }
    //9. Add a fixed-size history array that records the last 5 states of the counter. All logic must happen inside of the object and be hidden from the user.
    private void RecordSize(){
        int[] historyList = new int[5];
        for (int i = 0; i <= 4; i++){
            historyList[i] = GetCount();
        }
    }
}