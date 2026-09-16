/**
 * Author: Beck Bledsoe
 * Version: 09/16/2026
 * 
 */

package patient_intake;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Summary: This class checks various additional tests for the Hospital Simulator code.
 *          Each test is documented with its summary and its importance.
 * StudentAdditionalTest
 */
public class StudentAdditionalTest {
    
    private Patient testPatient1;
    private Patient testPatient2;
    private Main testMain;

    @BeforeEach
    void setUp() {
        testMain = new Main();
        //Creates a first test patient using the patient constructor
        testPatient1 = new Patient("P007", "Tim", "Stan O'Connel", 5, "Drooped Face", 2, "Waiting", 
            "Room2002", 04, "INS8675309");

        //Creates a second test patient using the patient constructor
        testPatient2 = new Patient ("P006", "", "", 47, "Broken Arm", 1, "Waiting", 
            "Room005", 16, "INS1234567");
    }

    /**
     * Summary: This test checks that the patient with two last names has their name correctly set.
     *          This is important for people who have multiple last names that need the hospital's services.
     */
    @Test
    void testPatientWithTwoLastNames() {
        assertEquals("Stan O'Connel", testPatient1.getLastName(), "Last name should be Stan O'Connel.");
    }

    /**
     * Summary: This test checks that a patient with no name is correctly assigned an empty first and last name.
     *          This is important for emergency cases where a patient's name is not immediately known.
     */
    @Test
    void testPatientWithNoName() {
        assertEquals("", testPatient2.getFirstName(), "Person with no first name should have the field be empty.");
        assertEquals("", testPatient2.getLastName(), "Person with no last name should have the field be empty.");
    }

    /**
     * Summary: This test checks that a patient with a patient just born can have an age of 0.
     *          This is important for checking that an the age filter includes 0 for babies just born in the hospital.
     */
    @Test
    void testPatientJustBorn() {
        testPatient1.setAge(0);
        assertEquals(0, testPatient1.getAge(), "Age should be 0 when just born.");
    }

    /**
     * Summary: This test checks that a patient with no insurance has an empty insurance ID.
     *          This is important for patients with no insurance who still need the hospital's services.
     */
    @Test
    void testPatientWithNoInsurance() {
        testPatient1.setInsuranceID("");
        assertEquals("", testPatient1.getInsuranceID(), "Patient with no insurance should have the field be empty.");
    }

    /**
     * Summary: This test checks for duplicate entries for patient ID and lets the staff know when one is found.
     *          This is important for testing if patients have the same patient ID to let the staff know.
     */
    @Test
    void testPatientDuplicateID() {
        testPatient1.setPatientID(testPatient2.getPatientID());
        assertTrue(testPatient1.getPatientID().contains(testPatient2.getPatientID()), 
        "Patient 1 and Patient 2 have duplicate IDs.");
        
    }

    /**
     * Summary: This test checks that the toString method in the main class contains the class name.
     *          This is important to ensure that the toString method is clear for the main class.
     */
    @Test
    void testMainToString() {
        String result = testMain.toString();
        assertTrue(result.contains("Main"), "Class Main's toString method should contain class name.");
    }
}


