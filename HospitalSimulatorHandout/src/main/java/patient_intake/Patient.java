/**
 * Author: Beck Bledsoe
 * Version: 09/16/2026
 * 
 */

package patient_intake;

/**
 * Summary: This class contains the functionality for the patient. It includes getter and setter methods
 *          as well as a constructor and toString method.
 * Patient
 */
public class Patient extends Person {
    private String patientID;
    private String chiefComplaint;
    private int triageLevel;
    private String currentStage;
    private String assignedRoom;
    private int arrivalHour;
    private String insuranceID;

    public Patient(String patientID, String firstName, String lastName, int age, 
                   String chiefComplaint, int triageLevel, String currentStage, 
                   String assignedRoom, int arrivalHour, String insuranceID) {
        super(firstName, lastName, age);
        this.patientID = patientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.chiefComplaint = chiefComplaint;
        this.triageLevel = triageLevel;
        this.currentStage = currentStage;
        this.assignedRoom = assignedRoom;
        this.arrivalHour = arrivalHour;
        this.insuranceID = insuranceID;
    }

    // Getters
    public String getPatientID() {
        return patientID; //Returns the patient ID.
    }

    public String getFirstName() {
        return firstName; //Returns the first name.
    }

    public String getLastName() {
        return lastName; //Returns the last name.
    }

    public int getAge() {
        return age; //Returns the age.
    }

    public String getChiefComplaint() {
        return chiefComplaint; //Returns the chief complaint.
    }

    public int getTriageLevel() {
        return triageLevel; //Returns the triage level.
    }

    public String getCurrentStage() {
        return currentStage; //Returns the current stage.
    }

    public String getAssignedRoom() {
        return assignedRoom; //Returns the assigned room.
    }

    public int getArrivalHour() {
        return arrivalHour; //Returns the arrival hour.
    }

    public String getInsuranceID() {
        return insuranceID; //Returns the insurance ID.
    }

    // Setters
    public void setPatientID(String patientID) {
        this.patientID = patientID; //Updates the patient ID.
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName; //Updates the first name.
    }

    public void setLastName(String lastName) {
        this.lastName = lastName; //Updates the last name.
    }

    public void setAge(int age) {
        if (age >= 0 && age <= 120) {
            this.age = age; //Updates the age.
        }
        else {
            System.out.print(IllegalArgumentException.class);
            System.out.println("Error: Age must be between 0 and 120. Please ensure the patient's age is within this range.");
        }
    }

    public void setChiefComplaint(String chiefComplaint) {
        this.chiefComplaint = chiefComplaint; //Updates the chief complaint.
    }

    public void setTriageLevel(int triageLevel) {
        this.triageLevel = triageLevel; //Updates the triage level.
    }

    public void setCurrentStage(String currentStage) {
        this.currentStage = currentStage; //Updates the current stage.
    }

    public void setAssignedRoom(String assignedRoom) {
        this.assignedRoom = assignedRoom; //Updates the assigned room.
    }

    public void setArrivalHour(int arrivalHour) {
        this.arrivalHour = arrivalHour; //Updates the arrival hour.
    }

    public void setInsuranceID(String insuranceID) {
        this.insuranceID = insuranceID; //Updates the insurance ID.
    }

    @Override
    public String toString() {
        return "Class Name:" + Patient.class.getName() + "\nPatient Data: " + "\nPatient ID: " + patientID + "\nName: " + firstName + 
        " " + lastName + "\nAge: " + age + "\nChief Complaint: " + chiefComplaint + "\nTriage Level: " + triageLevel + "\nCurrent Stage: "
        + currentStage + "\nAssigned Room: " + assignedRoom + "\nArrival Hour: " + arrivalHour + "\nInsurance ID: " + insuranceID; 
        //Returns a useful representation of a patient.
    }
}