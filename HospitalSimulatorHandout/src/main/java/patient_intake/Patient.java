package patient_intake;

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
        patientID = this.patientID;
        firstName = this.firstName;
        lastName = this.lastName;
        age = this.age;
        chiefComplaint = this.chiefComplaint;
        triageLevel = this.triageLevel;
        currentStage = this.currentStage;
        assignedRoom = this.assignedRoom;
        arrivalHour = this.arrivalHour;
        insuranceID = this.insuranceID;
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
        patientID = this.patientID; //Updates the patient ID.
    }

    public void setFirstName(String firstName) {
        firstName = this.firstName; //Updates the first name.
    }

    public void setLastName(String lastName) {
        lastName = this.lastName; //Updates the last name.
    }

    public void setAge(int age) {
        if (age >= 0 && age <= 120) {
            age = this.age; //Updates the age.
        } else {
            System.out.println("Error: Age must be between 0 and 120. Please ensure the patient's age is within this range.");
        }
    }

    public void setChiefComplaint(String chiefComplaint) {
        chiefComplaint = this.chiefComplaint; //Updates the chief complaint.
    }

    public void setTriageLevel(int triageLevel) {
        triageLevel = this.triageLevel; //Updates the triage level.
    }

    public void setCurrentStage(String currentStage) {
        currentStage = this.currentStage; //Updates the current stage.
    }

    public void setAssignedRoom(String assignedRoom) {
        assignedRoom = this.assignedRoom; //Updates the assigned room.
    }

    public void setArrivalHour(int arrivalHour) {
        arrivalHour = this.arrivalHour; //Updates the arrival hour.
    }

    public void setInsuranceID(String insuranceID) {
        insuranceID = this.insuranceID; //Updates the insurance ID.
    }

    @Override
    public String toString() {
        return "Patient Data: " + "\nName: " + firstName + " " + lastName + "\nAge: " + age + "\nChief Complaint: " 
        + chiefComplaint + "\nTriage Level: " + triageLevel + "\nCurrent Stage: " + currentStage + "\nAssigned Room: " 
        + assignedRoom + "\nArrival Hour: " + arrivalHour + "\nInsurance ID: " + insuranceID; 
        //Returns a useful representation of a patient.
    }
}