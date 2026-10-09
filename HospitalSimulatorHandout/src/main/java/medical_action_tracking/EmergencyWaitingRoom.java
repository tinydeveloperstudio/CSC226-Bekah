package medical_action_tracking;

import patient_intake.Patient;

public class EmergencyWaitingRoom {
    private LinkedQueue<Patient> level1;
    private LinkedQueue<Patient> level2;
    private LinkedQueue<Patient> level3;
    private LinkedQueue<Patient> level4;

    public EmergencyWaitingRoom() {
        //Creates one empty FIFO queue for each triage level.
        level1 = new LinkedQueue<Patient>();
        level2 = new LinkedQueue<Patient>();
        level3 = new LinkedQueue<Patient>();
        level4 = new LinkedQueue<Patient>();
    }

    /**
     *Adds a patient to the FIFO lane matching their triage level.
     *
     * @return true if added; false for null patients or levels outside 1-4
     */
    public boolean addPatient(Patient patient) {
        //Validates the patient and place them in the matching lane.
        if (patient != null) {
            if (patient.getTriageLevel() == 1) {
                level1.enqueue(patient);
            }
            else if (patient.getTriageLevel() == 2) {
                level2.enqueue(patient);
            }
            else if (patient.getTriageLevel() == 3) {
                level3.enqueue(patient);
            }
            else if (patient.getTriageLevel() == 4) {
                level4.enqueue(patient);
            }
            else {
                System.out.println("Patient has a triage level outside the accepted values of 1 and 4.");
                return false;
            }
            return true;
        }
        else {
            System.out.println("Patient is null. Please ensure the patient has been created/stored correctly.");
            return false;
        }
    }

    /**
     * Removes the next patient, checking triage level 1 before level 2, and so on.
     */
    public Patient nextPatient() {
        //Dequeues from the first non-empty lane, or returns null if all are empty.
        Patient returnPatient;
        if (level1.peekFront() != null) {
            returnPatient = level1.peekFront();
            level1.dequeue();
            return returnPatient;
        }
        else if (level2.peekFront() != null) {
            returnPatient = level2.peekFront();
            level2.dequeue();
            return returnPatient;
        }
        else if (level3.peekFront() != null) {
            returnPatient = level3.peekFront();
            level3.dequeue();
            return returnPatient;
        }
        else if (level4.peekFront() != null) {
            returnPatient = level4.peekFront();
            level4.dequeue();
            return returnPatient;
        }
        else {
            return null;
        }
    }

    public boolean isEmpty() {
        //Returns true only when all four lanes are empty.
        if (size() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public int size() {
        //Returns the total number of patients across all four lanes.
        int totalSize = level1.size() + level2.size() + level3.size() + level4.size();
        return totalSize;
    }
}
