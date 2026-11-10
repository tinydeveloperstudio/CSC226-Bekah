package staff_assignment;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SampleStudentStaffTest {

    StaffRoster testRoster;

    @BeforeEach
    void setUp() {
        //Creates a test roster and adds sample staff
        testRoster = new StaffRoster();
    }

    //Checks that a null seniority value throws an exception when adding a staff member
    @Test 
    void testNullSeniorityValueThrowsException() {
        StaffMember testNull = new StaffMember("S0005", "Beck Bledsoe", null);
        assertThrows(IllegalArgumentException.class,
                () -> testRoster.addStaff(testNull));
    }

    //Checks that null values throw an exception when assigning treatment
    @Test
    void testNullAssignTreatmentThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> testRoster.assignTreatment(null, "Stitches", "5:00", Seniority.NURSE));
        assertThrows(IllegalArgumentException.class,
                () -> testRoster.assignTreatment("P005", null, "5:00", Seniority.NURSE));
        assertThrows(IllegalArgumentException.class,
                () -> testRoster.assignTreatment("P005", "Stitches", null, Seniority.NURSE));
        assertThrows(IllegalArgumentException.class,
                () -> testRoster.assignTreatment("P005", "Stitches", "5:00", null));
    }

    //Checks that a null staff member cannot be released
    @Test 
    void testNullStaffReleaseThrowsException() {
        StaffMember testNull = null;
        assertThrows(IllegalArgumentException.class,
                () -> testRoster.release(testNull));
    }
}
