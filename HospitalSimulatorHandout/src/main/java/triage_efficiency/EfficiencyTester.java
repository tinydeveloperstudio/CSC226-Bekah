/**
 * Author: Beck Bledsoe
 * Version: 09/20/2026
 * Summary: This class implements the three main search methods and tests different Big O time complexities.
 *          Each search method times itself in nanoseconds to help review the time efficiency.
 */

package triage_efficiency;

import java.util.*;
import patient_intake.Patient;

public class EfficiencyTester {

    /**
     * REQUIRED (80%): Implement linear search.
     *
     * Search through the patient array one element at a time until the matching
     * patientID is found. Return the Patient if it exists; otherwise return null.
     *
     * This method must run in O(n) time.
     */
    //This search works by incrementing one entry at a time and comparing values at the index
    public Patient linearSearch(Patient[] patients, String pid) {
        long startTime = System.nanoTime();
        //If the patients array is not null, the search is started
        if (patients != null) {
            for (int i = 0; i < patients.length; i++){  //Searches the array linearly by looping through each entry
                if (patients[i].getPatientID().equals(pid)) {
                    long endTime = System.nanoTime();
                    System.out.println("\nThe linear search took " + (endTime - startTime) + "ns");
                    System.out.println(patients[i].toString());
                    return patients[i]; //Returns the patient with the matching ID
                }
            }
        }
        else {
            long endTime = System.nanoTime();
            System.out.println("\nThe linear search took " + (endTime - startTime) + "ns");
            System.out.println("Patients array is null.");
            return null; //Returns null if no patient is found with the matching ID
        }
        long endTime = System.nanoTime();
        System.out.println("\nThe linear search took " + (endTime - startTime) + "ns");
        System.out.println("No patient found with that matching ID.");
        return null; //Returns null if no patient is found with the matching ID
    }

    /**
     * REQUIRED (80%): Implement binary search.
     *
     * This method works only on an array that is sorted by patientID.
     * Repeatedly divide the search range in half until the target is found.
     *
     * This method must run in O(log n) time.
     */
    //This search works by breaking the array into halves and searching based on a midpoint.
    //It helps narrow the search quicker with the midpoint and adjusting the range based on the compared indexes
    public Patient binarySearch(Patient[] patients, String pid) {
        long startTime = System.nanoTime();
        int lowestIDIndex = 0;  //LowestIDIndex is set to 0
        int highestIDIndex = patients.length - 1;   //HighestIDIndex is set to the index of the patient with highest ID in the sorted array
        int middleIDIndex = lowestIDIndex + (highestIDIndex - lowestIDIndex)/2;
        //The middle ID index is calculated using the lowestIDIndex plus half the distance between the lowest and highest
        //This is done instead of simply (highest + lowest)/2 to avoid overflow (according to a question on stack overflow posted by ishan pandey)


        if (patients != null) {   //Only allows for searching of arrays with entries
            while(highestIDIndex >= lowestIDIndex) {
                middleIDIndex = lowestIDIndex + (highestIDIndex - lowestIDIndex)/2;

                if (patients[middleIDIndex].getPatientID().equalsIgnoreCase(pid)) { //Searches for matches at the midpoint
                    long endTime = System.nanoTime();
                    System.out.println("\nThe binary search took " + (endTime - startTime) + "ns");
                    System.out.println(patients[middleIDIndex].toString());
                    return patients[middleIDIndex];
                }

                if (Integer.parseInt(pid.substring(1)) < Integer.parseInt(patients[middleIDIndex].getPatientID().substring(1))) {
                    highestIDIndex = middleIDIndex - 1;
                }
                //If the patientID parsed as an integer is lower than the patientID at the middle index,
                //the highestIDIndex is updated to be the middleIDIndex - 1

                else if (Integer.parseInt(pid.substring(1)) > Integer.parseInt(patients[middleIDIndex].getPatientID().substring(1))) {
                    lowestIDIndex = middleIDIndex + 1;
                }
                //If the patientID parsed as an integer is higher than the patientID at the middle index,
                //the lowestIDIndex is updated to be the middleIDIndex + 1
            }
        }
        else {
            long endTime = System.nanoTime();
            System.out.println("\nThe binary search took " + (endTime - startTime) + "ns");
            System.out.println("Patient array is null.");
            return null;    //Returns null when no match is found or the array is empty
        }
        long endTime = System.nanoTime();
        System.out.println("\nThe binary search took " + (endTime - startTime) + "ns");
        System.out.println("No patient found with that matching ID.");
        return null;    //Returns null when no match is found or the array is empty
    }

    /**
     * OPTIONAL (+5%): Implement a different O(log n) search algorithm.
     *
     * Pick one of the following approaches and implement it:
     * - Exponential search
     * - Jump search
     * - Ternary search
     *
     * Add a short comment above the method explaining:
     * - which algorithm you chose
     * - where you learned about it
     * - why it works
     */
    //Ternary search learned from GeeksforGeeks
    //This search works by breaking the array into thirds and searching based on two midpoints.
    //It helps narrow the search quicker with the two midpoints and adjusting the range based on the compared indexes
    public Patient logNSearch(Patient[] patients, String pid) {
        long startTime = System.nanoTime();
        int lowestIDIndex = 0;  //LowestIDIndex is set to 0
        int highestIDIndex = patients.length - 1;   //HighestIDIndex is set to the index of the patient with highest ID in the sorted array
        int middleIDIndex1 = lowestIDIndex + (highestIDIndex - lowestIDIndex)/3;
        int middleIDIndex2 = highestIDIndex - (highestIDIndex - lowestIDIndex)/3;
        //The middleIDIndex1 is calculated using the lowestIDIndex plus half the distance between the lowest and highest divided by 3
        //The middleIDIndex2 is calculated using the highestIDIndex minus half the distance between the lowest and highest divided by 3


        if (patients != null) {   //Only allows for searching of arrays with entries
            while(lowestIDIndex <= highestIDIndex) {
                middleIDIndex1 = lowestIDIndex + (highestIDIndex - lowestIDIndex)/3;
                middleIDIndex2 = highestIDIndex - (highestIDIndex - lowestIDIndex)/3;

                if (patients[middleIDIndex1].getPatientID().equalsIgnoreCase(pid)) { //Searches for matches at midpoint1
                    long endTime = System.nanoTime();
                    System.out.println("\nThe ternary search took " + (endTime - startTime) + "ns");
                    System.out.println(patients[middleIDIndex1].toString());
                    return patients[middleIDIndex1]; //Returns the patient with the matching patientID at midpoint1
                }

                if (patients[middleIDIndex2].getPatientID().equalsIgnoreCase(pid)) {   //Searches for matches at midpoint2
                    long endTime = System.nanoTime();
                    System.out.println("\nThe ternary search took " + (endTime - startTime) + "ns");
                    System.out.println(patients[middleIDIndex2].toString());
                    return patients[middleIDIndex2]; //Returns the patient with the matching patientID at midpoint2
                }

                if (Integer.parseInt(pid.substring(1)) < Integer.parseInt(patients[middleIDIndex1].getPatientID().substring(1))) {
                    highestIDIndex = middleIDIndex1 - 1;
                }
                //If the patientID parsed as an integer is lower than the patientID at the middle index 1,
                //the highestIDIndex is updated to be the middleIDIndex1 minus 1
                //The middleIDIndex1 is updated with the new lowestIDIndex value which shrinks the window of search from the lower third closer to the middle of the array
                else if (Integer.parseInt(pid.substring(1)) > Integer.parseInt(patients[middleIDIndex2].getPatientID().substring(1))) {
                    lowestIDIndex = middleIDIndex2 + 1;
                }
                //If the patientID parsed as an integer is higher than the patientID at the middle index 2,
                //the highestIDIndex is updated to be the middleIDIndex2 plus 1
                //The middleIDIndex2 is updated with the new lowestIDIndex value which shrinks the window of search from the upper third closer to the middle of the array
                else {
                    highestIDIndex = middleIDIndex2 - 1;
                    lowestIDIndex = middleIDIndex1 + 1;
                }
                //If the patientID parsed as an integer is neither higher than the patientID at the middleIDIndex2 or lower than the middleIDIndex1,
                //the highestIDIndex is updated to be the middleIDIndex2 minus 1 and the lowestIDIndex is the middleIDIndex1 plus 1
                //The middleIDIndex1 & 2 are updated with the new lowest and highest IDIndex values which shrinks the window of search closer to the ends of the array on both sides
            }
        }
        else {
            long endTime = System.nanoTime();
            System.out.println("\nThe ternary search took " + (endTime - startTime) + "ns");
            System.out.println("Patient array is null.");
            return null;    //Returns null when no match is found or the array is empty
        }
        long endTime = System.nanoTime();
        System.out.println("\nThe ternary search took " + (endTime - startTime) + "ns");
        System.out.println("No patient found with that matching ID.");
        return null;    //Returns null when no match is found or the array is empty
    }

    public void timeDemo() {
        long startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            int x = 5 + 5;
        }
        long endTime = System.nanoTime();

        System.out.println("The example addition took: " + (endTime - startTime) + " ns");

        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            int x = 5 * 5;
        }
        endTime = System.nanoTime();
        System.out.println("The example multiplication took: " + (endTime - startTime) + " ns");
    }
}
