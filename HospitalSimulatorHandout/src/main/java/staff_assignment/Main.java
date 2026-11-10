package staff_assignment;

public class Main {
    //TODO, create a main file to demonstrate your roster: add staff, assign treatments, undo one, release cases,
    //and show each treatment's performedBy staffId

    public static void main(String[] args) {
        //Creates a general test roster that is not randomized
        StaffRoster generalStaffRoster = new StaffRoster();
        StaffMember testAttending = new StaffMember("S0001", "John Jones", Seniority.ATTENDING);
        StaffMember testResident = new StaffMember("S0002", "Sally O'Neil", Seniority.RESIDENT);
        StaffMember testNurse = new StaffMember("S0003", "Brock Hallow", Seniority.NURSE);
        StaffMember testTech = new StaffMember("S0004", "Tina Kronwelitz", Seniority.TECH);

        //Adds staff to the general test roster
        generalStaffRoster.addStaff(testAttending);
        generalStaffRoster.addStaff(testResident);
        generalStaffRoster.addStaff(testNurse);
        generalStaffRoster.addStaff(testTech);

        //Assigns one patient to the nurse and prints the case load
        generalStaffRoster.assignTreatment("P001", "Vaccination", "12:00", Seniority.NURSE);
        System.out.println(testNurse.getName() + " " + testNurse.getCaseLoad());

        //Assigns five patients to the roster. One for every seniority level because the required is tech
        //Prints the final case loads
        for (int i = 0; i < 5; i++) {
            generalStaffRoster.assignTreatment("P00" + i, "Check Up",  i + ":00", Seniority.TECH);
        }
        System.out.println(testTech.getName() + " " + testTech.getCaseLoad());
        System.out.println(testNurse.getName() + " " + testNurse.getCaseLoad());
        System.out.println(testResident.getName() + " " + testResident.getCaseLoad());
        System.out.println(testAttending.getName() + " " + testAttending.getCaseLoad());

        //Adds another assignment to attending, prints the case load, undoes the assignment, and prints again
        generalStaffRoster.assignTreatment("P008", "Stitches", "18:00", Seniority.ATTENDING);
        System.out.println(testAttending.getName() + " " + testAttending.getCaseLoad());
        generalStaffRoster.undoLastAssignment();
        System.out.println(testAttending.getName() + " " + testAttending.getCaseLoad());
        generalStaffRoster.displayRoster();

        //Releases the nurse's assignment
        System.out.println(testNurse.getName() + " " + testNurse.getCaseLoad());
        generalStaffRoster.release(testNurse);
        generalStaffRoster.displayRoster();
    }
}
