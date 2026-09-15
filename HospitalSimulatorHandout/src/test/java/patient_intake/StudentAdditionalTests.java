package patient_intake;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class StudentAdditionalTests {
    private PatientRegistry registry;
    private Patient testPatient1;
    private Patient testPatient2;
    private Patient testPatient3;

    @BeforeEach
    void setUp() {
        registry = new PatientRegistry();

        //Creates test patients using the constructor
        testPatient1 = new Patient( //Tests for a patient with a middle name included with last name.
            "P001", "Jack", "Jimbo James", 55,
            "Drooping face", 5, "Waiting", "Room2005", 00, "INS7753252");
        testPatient2 = new Patient( //Tests for a patient with an age below 0.
            "P002", "Sarah", "Lovehead", -10, 
            "Born 10 years early", 2, "Waiting", "Room504", 00, "INS3333333");
        testPatient3 = new Patient(//Tests for a patient with no last name.
            "P003", "Tim", "", 7, 
            "Chest Pain", 3, "Waiting", "Room004", 00, "INS8675309");
    }
}
