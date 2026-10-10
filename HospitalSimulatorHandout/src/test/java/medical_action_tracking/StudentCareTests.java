/**
 * Author: Beck Bledsoe
 * Version: 10/09/2026
 * Summary: Student created test funtions for various methods in the Medical Action Tracking folder.
 */

package medical_action_tracking;

import org.junit.jupiter.api.Test;
import patient_intake.Patient;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.GenericArrayType;

import javax.security.sasl.SaslClientFactory;

public class StudentCareTests {
    //Tests that items cannot be added to a full queue
    @Test
    void TestCannotAddItemsToFullQueue() {
        LinkedQueue<Integer> queue = new LinkedQueue(10);
        for (int i = 1; i <= 10; i++) {
            queue.enqueueBounded(i);
            System.out.println(queue.toString());
        }
        assertThrows(IllegalArgumentException.class, () -> queue.enqueueBounded(11));
        queue.dequeue();
        queue.enqueueBounded(11);
        System.out.println(queue.toString());
    }

    //Tests that patients in the emergency waiting room cannot be added to a full triage level queue
    @Test
    void TestCannotAddItemsToFullTriageQueue() {
        EmergencyWaitingRoom waitingRoom = new EmergencyWaitingRoom(1);
        Patient testPatient1 = new Patient("P100", "Sarah", "Sully", 8, "Internal Bleeding", 1, "Waiting", "Room:100", 12, "INS100");
        Patient testPatient2 = new Patient("P101", "Jimmy", "Neutron", 70, "Seizure", 1, "Meeting with Doctor", "Room:101", 1, "INS400");
        //Tests triage level 1
        waitingRoom.addPatientBounded(testPatient1);
        assertFalse(waitingRoom.addPatientBounded(testPatient2));
        
        //Sets triage level to 2 and tests
        testPatient1.setTriageLevel(2);
        testPatient2.setTriageLevel(2);
        waitingRoom.addPatientBounded(testPatient1);
        assertFalse(waitingRoom.addPatientBounded(testPatient2));
        
        //Sets triage level to 3 and tests
        testPatient1.setTriageLevel(3);
        testPatient2.setTriageLevel(3);
        waitingRoom.addPatientBounded(testPatient1);
        assertFalse(waitingRoom.addPatientBounded(testPatient2));

        //Sets triage level to 4 and tests
        testPatient1.setTriageLevel(4);
        testPatient2.setTriageLevel(4);
        waitingRoom.addPatientBounded(testPatient1);
        assertFalse(waitingRoom.addPatientBounded(testPatient2));
    }

    //Tests that items cannot be pushed to a full stack
    @Test
    void TestCannotAddItemsToFullStack() {
        LinkedStack<Integer> stack = new LinkedStack(10);
        for (int i = 1; i <= 10; i++) {
            stack.pushBounded(i);
            System.out.println(stack.toString());
        }
        assertThrows(IllegalArgumentException.class, () -> stack.pushBounded(11));
        stack.pop();
        stack.pushBounded(11);
        System.out.println(stack.toString());
    }

    //Tests the alternate queue type of input restricted with dequeue funtionality at both the front and rear
    @Test
    void TestAlternateQueueType() {
        LinkedQueue<Integer> inputRestrictedQueue = new LinkedQueue();
        inputRestrictedQueue.enqueue(1);
        System.out.println(inputRestrictedQueue.toString());
        assertThrows(IllegalArgumentException.class, () -> inputRestrictedQueue.restrictedDequeue("middle"));
        System.out.println("Middle not an accepted dequeue position. Queue is still: " + inputRestrictedQueue.toString());
        assertEquals(1, inputRestrictedQueue.restrictedDequeue("front"));
        System.out.println(inputRestrictedQueue.toString());
        inputRestrictedQueue.enqueue(1);
        inputRestrictedQueue.enqueue(2);
        inputRestrictedQueue.enqueue(3);
        System.out.println(inputRestrictedQueue.toString());
        assertEquals(1, inputRestrictedQueue.restrictedDequeue("FROnt"));
        assertEquals(3, inputRestrictedQueue.restrictedDequeue("rEaR"));
        System.out.println(inputRestrictedQueue.toString());
    }

    //Tests the recursive size counter for queues compared to the size method that tracks the size variable
    @Test
    void TestRecursiveCountComparedToQueueSize() {
        LinkedQueue<Integer> queue = new LinkedQueue();
        for (int i = 1; i <= 20; i++){
            queue.enqueue(i);
        }
        System.out.println(queue.toString());
        System.out.println(queue.size());
        assertEquals(20, queue.recursiveSize(queue.peekFrontNode()));
        System.out.println(queue.recursiveSize(queue.peekFrontNode()));
    }

    //Tests the recursive size counter for stacks compared to the size method which tracks the size variable
    @Test
    void TestRecursiveCountComparedToStackSize() {
        LinkedStack<Integer> stack = new LinkedStack();
        for (int i = 1; i <= 15; i++){
            stack.push(i);
        }
        System.out.println(stack.toString());
        System.out.println(stack.size());
        assertEquals(15, stack.recursiveSize(stack.peekBottomNode()));
        System.out.println(stack.recursiveSize(stack.peekBottomNode()));
    }
}
