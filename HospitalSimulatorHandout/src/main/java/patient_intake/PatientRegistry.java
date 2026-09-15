package patient_intake;
// Switch from using ArrayList to a flat array implementation for patient storage.

public class PatientRegistry {
    //Flat array to store patients and a size field to track the number of stored patients.
    private Patient[] patientRegistry;
    private int size; // track actual number of patients

    //Initial capacity for the flat array. Can be adjusted as needed.
    private static final int INITIAL_CAPACITY = 10;

    public PatientRegistry() {
        patientRegistry = new Patient[INITIAL_CAPACITY];
        size = 0;
        //Creates the initial array and sets the starting size.
    }

    public void addPatient(Patient patient) {
        if (size >= patientRegistry.length) {
            //Expands the array if it's full.
            Patient[] newRegistry = new Patient[patientRegistry.length + 1];
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
            if (patientRegistry[i].getPatientID().equals(patientID)) {
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
            if (patientRegistry[i].getPatientID().equals(patientID)) {
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
            return null; //Invalid index for the patient registry which returns null.
        }
        Patient removedPatient = patientRegistry[index];
        for (int i = index; i < size - 1; i++) {
            patientRegistry[i] = patientRegistry[i + 1];
        }
        patientRegistry[size - 1] = null;
        size--;
        return removedPatient;
    }

    /**
     * Updates a patient in the registry by matching patientID.
     * @param updatedPatient The patient with updated information
     * @return true if patient was found and updated, false otherwise
     */
    public boolean updatePatient(Patient updatedPatient) {
        for (int i = 0; i < size; i++) {
            if (patientRegistry[i].getPatientID().equals(updatedPatient.getPatientID())) {
                patientRegistry[i] = updatedPatient;
                return true; //Replaces the patient with the same ID.
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "Patient Registry: " + "\n Size: " + size + "Patients: " + patientRegistry.toString(); 
        //Returns a useful representation of the registry.
    }

}


