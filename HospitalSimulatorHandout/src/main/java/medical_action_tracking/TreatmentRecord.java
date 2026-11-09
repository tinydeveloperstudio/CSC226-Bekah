/**
* Author: Beck Bledsoe
* Version: 10/09/2026
* Summary: This script contains the main functionality for the treatment record for each patient.
*/

package medical_action_tracking;

import staff_assignment.Seniority;

public class TreatmentRecord {
    private String patientID;
    private String treatmentName;
    private String timestamp;
    private String performedBy;

    public TreatmentRecord(String patientID, String treatmentName, String timestamp) {
        this.patientID = patientID;
        this.treatmentName = treatmentName;
        this.timestamp = timestamp;
    }

    public TreatmentRecord(String patientID, String treatmentName, String timestamp, String performedBy) {
        this.patientID = patientID;
        this.treatmentName = treatmentName;
        this.timestamp = timestamp;
        this.performedBy = performedBy;
    }
    

    public String getPatientID() {
        //Returns the patient ID.
        return patientID;
    }

    public String getTreatmentName() {
        //Returns the treatment description.
        return treatmentName;
    }

    public String getTimestamp() {
        //Returns the timestamp.
        return timestamp;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    @Override
    public String toString() {
        //Returns "timestamp | patientID | treatmentName".
        return (timestamp + " | " + patientID + " | " + treatmentName);
    }
}
