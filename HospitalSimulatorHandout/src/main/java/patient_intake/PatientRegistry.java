/**
 * Author: Beck Bledsoe
 * Version: 09/16/2026
 * 
 */

package patient_intake;
// Switch from using ArrayList to a flat array implementation for patient storage.

/**
 * Summary: This class contains the main functionality for entering patients into the stored registry.
 *          It stores patient data into an array, can update and remove patients, and can search the 
 *          array for patients by index or by patientID.
 * PatientRegistry
 */
public class PatientRegistry {
    //Flat array to store patients and a size field to track the number of stored patients.
    private Patient[] patientRegistry;
    private int size; // track actual number of patients

    //Initial capacity for the flat array. Can be adjusted as needed.
    private static final int INITIAL_CAPACITY = 0;

    public PatientRegistry() {
        patientRegistry = new Patient[INITIAL_CAPACITY];
        size = 0;
        //Creates the initial array and sets the starting size.
    }

    public void addPatient(Patient patient) {
        if (size >= patientRegistry.length) {
            //Expands the array if it's full.
            Patient[] newRegistry = new Patient[size + 1];
            for (int i = 0; i < patientRegistry.length; i++) {
                newRegistry[i] = patientRegistry[i];
            }
            patientRegistry = newRegistry;
        }
        patientRegistry[size] = patient;
        size++;
        //Adds a patient to the registry.
    }

    /**
     * Returns the patients currently stored in the registry.
     * The optional encapsulation extension requires returning a defensive copy.
     */
    public Patient[] getPatientRegistry() {
        return patientRegistry; //Returns the patients currently stored.
    }

    public Patient getPatientByID(String patientID) {
        for (int i = 0; i < size; i++) {
            if (patientRegistry[i].getPatientID() == patientID) {
                return patientRegistry[i];
            }
        }
        return null; //Searches for and returns the matching patient by ID or null if not found.
    }

    /**
     * Removes a patient from the registry by patientID.
     * @param patientID The ID of the patient to remove
     * @return true if patient was found and removed, false otherwise
     */
    public boolean removePatient(String patientID) {
        for (int i = 0; i < size; i++) {
            if (patientRegistry[i].getPatientID() == patientID) {
                for (int j = i; j < size - 1; j++) {
                    patientRegistry[j] = patientRegistry[j + 1];
                }
                patientRegistry[size - 1] = null; //Deletes the patient from the registry and shifts later elements left.
                size--;
                return true; //Patient is found and removed.
            }
        }
        return false; //Patient is not found in the registry and not removed.
    }

    /**
     * Removes a patient from the registry by index.
     * @param index The index of the patient to remove
     * @return the removed Patient, or null if index is invalid
     */
    public Patient removePatient(int index) {
        if (index < 0 || index >= size) {
            return null;    //Index is outside the size of the patientRegistry, so returns null.
        }
        Patient removedPatient = patientRegistry[index];
        for (int i = index; i < size - 1; i++) {
            patientRegistry[i] = patientRegistry[i + 1];
        }
        patientRegistry[size - 1] = null;       //Deletes the patient from the registry and shifts entries left.
        size--;     //Brings the size of the patientRegistry down for the removed patient and returns that removed patient.
        return removedPatient;
    }

    /**
     * Updates a patient in the registry by matching patientID.
     * @param updatedPatient The patient with updated information
     * @return true if patient was found and updated, false otherwise
     */
    public boolean updatePatient(Patient updatedPatient) {
        for (int i = 0; i < size; i++) {
            if (patientRegistry[i].getPatientID() == updatedPatient.getPatientID()) {
                patientRegistry[i] = updatedPatient;
                return true; //Replaces the patient with the same ID.
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "Class Name: " + PatientRegistry.class.getName() + "\nPatient Registry: " + "\nsize: " + size 
        + "Patients: " + patientRegistry.toString(); 
        //Returns a useful representation of the registry.
    }

}


