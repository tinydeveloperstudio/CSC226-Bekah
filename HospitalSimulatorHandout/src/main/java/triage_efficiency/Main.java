/**
 * Author: Beck Bledsoe
 * Version: 09/20/2026
 * Summary: This class creates a patient set based on an input size, sorts it by ID, and implements the tests in the main method.
 *          Each search method prints the patient data to a string and prints the time took in ns to complete the search.
 */

package triage_efficiency;

import java.util.Arrays;

import patient_intake.Patient;

public class Main {
    public static void main(String[] args) {
        Patient[] generatedPatients = generatePatients(100);    //Generates patient data with input size
        EfficiencyTester newTest = new EfficiencyTester();  //Creates a new class reference variable for EfficiencyTester

        generatedPatients = sortByPatientId(generatedPatients);     //Sorts patients by ID

        //Linear search found example
        newTest.linearSearch(generatedPatients, "P000100");
        //Linear search not-found example
        newTest.linearSearch(generatedPatients, "P000101");

        //Binary search found example
        newTest.binarySearch(generatedPatients, "P00099");
        //Binary search not-found example
        newTest.binarySearch(generatedPatients, "P000101");

        //Ternary search found example
        newTest.logNSearch(generatedPatients, "P00098");
        //Ternary search not-found example
        newTest.logNSearch(generatedPatients, "P000101");
    }

    /**
     * REQUIRED (80%): Generate sample patient data for testing.
     *
     * Build an array of Patient objects with realistic IDs, names, complaints,
     * and triage information so you can test each search method.
     */
    public static Patient[] generatePatients(int count) {
        if (count > 0) {
            //Initializes the sample patient information for selecting at random.
            String[] firstNames = {"Sarah", "Tom", "Pete", "Callie", "Kris", "Aaron", "John", "Helen", "Bert", "Jess"};
            String[] lastNames = {"Johnson", "Williams", "Garcia", "Smith", "Grant", "O'Neil", "Thompson", "Aslack", "Killwin", "Kronkwitz"};
            int[] ages = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
            int triageLevel = 1;
            int[] hourArrived = {12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22};
            String[] roomNumbers = {"Room1001", "Room1002", "Room1003", "Room1004", "Room1005", "Room1006", "Room1007", "Room1008", "Room1009", "Room1010"};
            String[] chiefComplaint = {"Internal bleeding", "Drooped face", "Shortness of breath", "Amnesia", "Broken bone", "Joint effusion", "Headache", "Fever", "Nausea", "Sore throat"};
            String[] insuranceID = {"INS12345678", "INS35213555", "INS8675309", "INS0000001", "INS7345789", "INS4445556", "INS0246810", "INS9999998", "INS4769832", "INS1010101"};
            String currentStage = "Waiting";
            //Creates the patient array based on patient count
            Patient[] patients = new Patient[count];

            //Loops through patient count to create each patient at random and input them into the incremented index
            for (int i = 0; i < count; i++){
                //Gets a random chiefComplaint and assigns it to a variable used for triage level
                String newChiefComplaint = chiefComplaint[(int)(Math.random() * 10)];

                //Sets the triage level based on the chief complaint and its severity
                if (newChiefComplaint.equals("Internal bleeding") || newChiefComplaint.equals("Drooped face") || newChiefComplaint.equals("Shortness of breath")) {
                    triageLevel = 1;
                }
                else if (newChiefComplaint.equals("Amnesia") || newChiefComplaint.equals("Broken bone")){
                    triageLevel = 2;
                }
                else if (newChiefComplaint.equals("Joint effusion") || newChiefComplaint.equals("Headache") || newChiefComplaint.equals("Fever")) {
                    triageLevel = 3;
                }
                else if (newChiefComplaint.equals("Nausea")) {
                    triageLevel = 4;
                }
                else if (newChiefComplaint.equals("Sore throat")) {
                    triageLevel = 5;
                }

                //Sets the current stage to a value depending on the triage level and its severity
                if (triageLevel == 1 || triageLevel == 2) {
                    currentStage = "Receiving care from doctor";
                }
                else if (triageLevel == 3 || triageLevel == 4) {
                    currentStage = "Meeting with nurse";
                }
                else if (triageLevel == 5) {
                    currentStage = "Waiting";
                }

                //Creates the patient data and inputs it into the index
                patients[i] = new Patient("P000"+ (i+1), 
                    firstNames[(int)(Math.random() * 10)], lastNames[(int)(Math.random() * 10)], ages[(int)(Math.random() * 10)], 
                    newChiefComplaint, triageLevel, currentStage, roomNumbers[(int)(Math.random() * 10)], hourArrived[(int)(Math.random() * 10)], 
                    insuranceID[(int)(Math.random() * 10)]);
            }
            //Returns the patients array for use
            return patients;
        }
        else {
            //If the count is less than or equal to zero, returns null and gives a message of why
            System.out.println("Count must be greater than zero to generate a patient array.");
            return null;
        }
    }

    /**
     * REQUIRED (80%): Sort patients by patientID before binary search.
     *
     * The binary-search version only works on an array sorted by patientID.
     */
    public static Patient[] sortByPatientId(Patient[] patients) {
        //If the patients array is not null, sorting takes place
        if (patients != null) {
            if (patients.length > 1) {
                Patient tempPatient1 = patients[0];

                //Iterates through the array by array length
                for(int i = 0; i < patients.length - 1; i++) {
                    //Compares array values and swaps depending on PatientID
                    for (int j = i + 1; j < patients.length - 1; j++) {
                        if (Integer.parseInt(patients[i].getPatientID().substring(1)) > 
                            Integer.parseInt(patients[j].getPatientID().substring(1))) {
                                tempPatient1 = patients[i];
                                patients[i] = patients[j];
                                patients[j] = tempPatient1;
                            }
                    }
                }
            }
            //This for loop sorts the patients array in ascending order for patientID. It uses a substring starting at index 1 
            //of the string to remove the letter P from the beginning of the string
            return patients; //Returns the updated patients array that has been sorted by patientID
        }
        else {
            return null;    //Returns null if the array is not initialized and null
        }
    }
}
