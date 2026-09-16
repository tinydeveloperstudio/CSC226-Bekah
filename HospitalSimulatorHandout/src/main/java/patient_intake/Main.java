/**
 * Author: Beck Bledsoe
 * Version: 09/16/2026
 * 
 */

package patient_intake;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Summary: This class contains main functionality for the HospitalSimulator which reads .csv files.
 *          After scanning the file and separating by line and part, it assigns parts to the newPatient
 *          and adds the newPatient to the registry.
 * Main
 */
public class Main {
   public static void main(String[] args) {
      String filePath = "src/main/java/patient_intake/patients.csv";
      PatientRegistry patients = new PatientRegistry();

      try (Scanner fileReader = new Scanner(new File(filePath))) {
         String patientID = "";
         String name = "";
         int age = 0;
         String chiefComplaint = "";
         int triageLevel = 0;
         String currentStage = "";
         String assignedRoom = "";
         int arrivalHour = 0;
         String insuranceID = "";
         String firstName = "";
         String lastName = "";
         Patient patient;

         if (fileReader.hasNextLine()) {
            fileReader.nextLine(); //Skip the CSV header
         }

         while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            String[] parts = line.split(",");
            patientID = parts[0];
            name = parts[1];
            age = Integer.parseInt(parts[2]);
            chiefComplaint = parts[3];
            triageLevel = Integer.parseInt(parts[4]);
            currentStage = parts[5];
            assignedRoom = parts[6];
            arrivalHour = Integer.parseInt(parts[7]);
            insuranceID = parts[8];

            String[] fullName = name.split(" ");
            firstName = fullName[0];
            lastName = fullName[1];

            patient = new Patient(patientID, firstName, lastName, age, chiefComplaint, triageLevel, currentStage, 
               assignedRoom, arrivalHour, insuranceID);
            patients.addPatient(patient);
         }

         System.out.println("Class Name:" + Main.class.getName() + "\nPatient records loaded successfully." + "\nPatient Name: " + firstName + " " + lastName 
         + "\nPatient ID: " + patientID + "\nAge: " + age + "\nChief Complaint: " + chiefComplaint + "\nTriage Level: " 
         + triageLevel + "\nCurrent Stage: " + currentStage + "\nAssigned Room: " + assignedRoom + "\nArrival Hour: " 
         + arrivalHour + "\nInsurance ID: " + insuranceID);
      } catch (FileNotFoundException exception) {
         System.out.println("Error: File not found. Check that the patient csv file exists and the path is correct.");
      }
   }
}