/**
 * Author: Beck Bledsoe
 * Version: 09/20/2026
 * Summary: This class implements the custom tests for each search method. It includes testing that a null array returns null
 *          for all three search methods, testing that a patient set that has IDs that are unordered are correctly sorted for binary and ternary search,
 *          and that the generatePatients method requires a count greater than 0. 
*/

package triage_efficiency;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import patient_intake.Patient;

public class StudentAlgorithmTests {
    private final EfficiencyTester tester = new EfficiencyTester();
    private Patient[] patients1;
    private Patient[] patients2;
    private Main mainTest = new Main();

    //This test reviews that a patient array that is null returns null.
    //This is helpful for giving information to the search system user 
    //if a dataset hasn't actually been initialized with data is being 
    //searched with a linear search method.
    @Test
    void TestLinearSearchWithNoEntries() {
        Patient result = tester.linearSearch(patients1, "P0006");
        assertNull(result, "Patient array with no entries should return null.");
    }

    //This test reviews that a patient array that is null returns null.
    //This is helpful for giving information to the search system user 
    //if a dataset hasn't actually been initialized with data is being 
    //searched with a binary search method.
    @Test
    void TestBinarySearchWithNoEntries() {
        Patient result = tester.linearSearch(patients1, "P0006");
        assertNull(result, "Patient array with no entries should return null.");
    }
    
    //This test reviews that a patient array that is null returns null.
    //This is helpful for giving information to the search system user 
    //if a dataset hasn't actually been initialized with data is being 
    //searched with a ternary search method.
    @Test
    void TestTernarySearchWithNoEntries() {
        Patient result = tester.linearSearch(patients1, "P0006");
        assertNull(result, "Patient array with no entries should return null.");
    }
    
    //This test reviews that an unordered array of patients is sorted before binary search.
    //This is helpful for ensuring that the sort function works correctly and patients can still be found.
    @Test
    void TestSortIDForBinarySearch() {
        patients1 = new Patient[7];
        patients1[0] = new Patient("P00003", "Tom", "Johnson", 5, "Sore throat", 5, "Waiting", "Room1001", 7, "INS014526");
        patients1[1] = new Patient("P00002", "Jane", "Kleen", 80, "Broken bone", 2, "Meeting with doctor", "Room1002", 16, "INS115348");
        patients1[2] = new Patient("P00007", "Brock", "O'Connor", 27, "Nausea", 4, "Meeting with nurse", "Room1003", 8, "INS854777");
        patients1[3] = new Patient("P00004", "Sally", "Hampton", 46, "Fever", 3, "Meeting with nurse", "Room1004", 1, "INS123456");
        patients1[4] = new Patient("P00001", "Jared", "Cleveland", 1, "Shortness of breath", 1, "Meeting with doctor", "Room1005", 12, "INS666777");
        patients1[5] = new Patient("P00006", "Yasmine", "Garcia", 55, "Amnesia", 2, "Meeting with doctor", "Room1006", 24, "INS154876");
        patients1[6] = new Patient("P00005", "Teddy", "Roosevelt", 101, "Nausea", 4, "Meeting with nurse", "Room1007", 5, "INS111111");
        patients1 = mainTest.sortByPatientId(patients1);
        Patient result = tester.binarySearch(patients1, "P00003");
        assertEquals("P00003", result.getPatientID(), "Binary search should find patient with ID P00003 after sorting.");
    }

    //This test reviews that an unordered array of patients is sorted before ternary search.
    //This is helpful for ensuring that the sort function works correctly and patients can still be found.
    @Test
    void TestSortIDForTernarySearch() {
        patients2 = new Patient[7];
        patients2[0] = new Patient("P00003", "Tom", "Johnson", 5, "Sore throat", 5, "Waiting", "Room1001", 7, "INS014526");
        patients2[1] = new Patient("P00002", "Jane", "Kleen", 80, "Broken bone", 2, "Meeting with doctor", "Room1002", 16, "INS115348");
        patients2[2] = new Patient("P00007", "Brock", "O'Connor", 27, "Nausea", 4, "Meeting with nurse", "Room1003", 8, "INS854777");
        patients2[3] = new Patient("P00004", "Sally", "Hampton", 46, "Fever", 3, "Meeting with nurse", "Room1004", 8, "INS123456");
        patients2[4] = new Patient("P00001", "Jared", "Cleveland", 1, "Shortness of breath", 1, "Meeting with doctor", "Room1005", 12, "INS666777");
        patients2[5] = new Patient("P00006", "Yasmine", "Garcia", 55, "Amnesia", 2, "Meeting with doctor", "Room1006", 24, "INS154876");
        patients2[6] = new Patient("P00005", "Teddy", "Roosevelt", 101, "Nausea", 4, "Meeting with nurse", "Room1007", 5, "INS111111");
        patients2 = mainTest.sortByPatientId(patients2);
        Patient result = tester.logNSearch(patients2, "P00001");
        assertEquals("P00001", result.getPatientID(), "Ternary search should find patient with ID P00001 after sorting.");
    }

    //This test reviews that the generate patients function returns null if the count is zero.
    //This helps ensure that patient arrays are only created if there are actually a number of patients to store.
    @Test
    void TestGeneratePatientsWithZeroCount() {
        Patient[] result = mainTest.generatePatients(0);
        assertNull(result, "Generated patient list should return null with a count equal to zero.");
    }

    //This test reviews that the generate patients function returns null if the count is negative.
    //This helps ensure that patient arrays are only created if there are actually a number of patients to store.
    @Test
    void TestGeneratePatientsWithNegativeCount() {
        Patient[] result = mainTest.generatePatients(-1);
        assertNull(result, "Generated patient list should return null with a count less than to zero.");
    }
}
