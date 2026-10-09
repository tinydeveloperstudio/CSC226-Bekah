package medical_action_tracking;

public class TreatmentHistory {
    private LinkedStack<TreatmentRecord> allRecords;

    public TreatmentHistory() {
        allRecords = new LinkedStack<>();
    }

    public void addTreatment(String patientID, String treatment, String timestamp) {
        //This is a small worked example: creates the record, then pushes it on the stack.
        TreatmentRecord record = new TreatmentRecord(patientID, treatment, timestamp);
        allRecords.push(record);
    }

    /**
     *Removes and returns the newest record in the hospital-wide log.
     *
     * @return the newest record, or null when the log is empty
     */
    public TreatmentRecord undoLastAction() {
        //Pops and returns the newest record. Empty pop returns null.
        if (allRecords.peek() == null) {
            return null;
        }
        else {
            return allRecords.pop();
        }
    }

    /**
     *Returns the complete log newest-first without changing the stack.
     */
    public String displayHistory() {
        //Uses the stack's top-to-bottom string representation.
        String toReturn = allRecords.toString();
        return toReturn;
    }
}
