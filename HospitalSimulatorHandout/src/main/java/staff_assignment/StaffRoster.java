package staff_assignment;

import java.io.InvalidObjectException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.management.RuntimeErrorException;

import medical_action_tracking.TreatmentHistory;
import medical_action_tracking.TreatmentRecord;

public class StaffRoster {
    // Week 3 stack-based history; every assignment is recorded here so it can be undone.
    private final TreatmentHistory history = new TreatmentHistory();

    // Outer map groups staff by seniority; each inner set is sorted by StaffMember's ordering.
    private final TreeMap<Seniority, TreeSet<StaffMember>> tiers = new TreeMap<>();

    private TreeSet<StaffMember> techTree = new TreeSet<>();
    private TreeSet<StaffMember> nurseTree = new TreeSet<>();
    private TreeSet<StaffMember> residentTree = new TreeSet<>();
    private TreeSet<StaffMember> attendingTree = new TreeSet<>();
    private Iterator<StaffMember> staffIterator;

    // TODO: Add the staff member to the correct tier, and reject a duplicate staffId.
    public void addStaff(StaffMember staff) {
        //If the staff is null or the staffId already exists in the tree, throw an exception
        if (staff == null || findById(staff.getStaffId()) != null) {
            throw new IllegalArgumentException("Staff must not be null & cannot add duplicate entries.");
        }

        //If the staff seniority is null, throw an exception
        if (staff.getSeniority() == null) {
            throw new IllegalArgumentException("Staff seniority level cannot be null.");
        }

        if (staff.getSeniority().equals(Seniority.TECH)) {
            techTree.add(staff);
            tiers.put(Seniority.TECH, techTree);
        }
        else if (staff.getSeniority().equals(Seniority.NURSE)) {
            nurseTree.add(staff);
            tiers.put(Seniority.NURSE, nurseTree);
        }
        else if (staff.getSeniority().equals(Seniority.RESIDENT)) {
            residentTree.add(staff);
            tiers.put(Seniority.RESIDENT, residentTree);
        }
        else if (staff.getSeniority().equals(Seniority.ATTENDING)) {
            attendingTree.add(staff);
            tiers.put(Seniority.ATTENDING, attendingTree);
        }
    }

    // TODO: Pick the least-loaded staff member at or above "required", raise their load by 1, and return them.
    public StaffMember assign(Seniority required) {  
        if (required == null) {
            throw new NoSuchElementException("Required seniority level cannot be null.");
        }

        StaffMember lowestCaseLoad = null;

        for (TreeSet<StaffMember> staffTree : tiers.tailMap(required).values()) {
            staffIterator = staffTree.iterator();
            while (staffIterator.hasNext()) {
                StaffMember currentStaff = staffIterator.next();
                if (isBetterAssignment(currentStaff, lowestCaseLoad)) {
                    lowestCaseLoad = currentStaff;
                }
            }
        }
        //If the while loops through and no staff are found with the right requirements, this
        //checks if the set staff member is null. If so, it returns null Otherwise it returns the 
        //lowest case load (either the set staff at zero from the beginning, or the staff found in the for loop above)
        if (lowestCaseLoad == null) {
            throw new NoSuchElementException("No staff members are qualified and available for this task.");
        }
        changeLoad(lowestCaseLoad, lowestCaseLoad.getCaseLoad() + 1);
        return lowestCaseLoad;
    }

    //Method to check if a staff member is a better choice for taking an assignment
    private boolean isBetterAssignment(StaffMember currentStaff, StaffMember lowestCaseLoad) {
        if (lowestCaseLoad == null) {
            return true;
        }

        int loadPriority = Integer.compare(currentStaff.getCaseLoad(), lowestCaseLoad.getCaseLoad());
        if (loadPriority != 0) {
            return loadPriority < 0;
        }

        int seniorityPriority = currentStaff.getSeniority().compareTo(lowestCaseLoad.getSeniority());
        if (seniorityPriority != 0) {
            return seniorityPriority < 0;
        }

        return currentStaff.getStaffId().compareTo(lowestCaseLoad.getStaffId()) < 0;
    }

    // TODO: Lower the staff member's load by 1 (never below 0) and keep the tree ordered.
    public void release(StaffMember staff) {
        if (staff == null) {
            throw new IllegalArgumentException("Cannot release a null staff member.");
        }
        //Throws an error if the staff's case load drops below zero when completing this method
        if (staff.getCaseLoad() - 1 < 0) {
            return;
        }
        //Otherwise changes the staff case load by -1
        changeLoad(staff, staff.getCaseLoad() - 1);
    }

    // TODO: Return how many staff members are in the roster.
    public int size() {
        return tiers.size() + 1;
    }

    // TODO: Print each tier, most senior first, listing its staff in order.
    public void displayRoster() {
        printIterator(attendingTree);
        printIterator(residentTree);
        printIterator(nurseTree);
        printIterator(techTree);
    }

    private void printIterator(TreeSet staffTree) {
        staffIterator = staffTree.iterator();

        while (staffIterator.hasNext()) {
            System.out.println(staffIterator.next());
        }
    }

    // TODO: Remove the member, set the new load, then add them back (the only safe way to change a load).
    private void changeLoad(StaffMember member, int newLoad) {
        if (member == null) {
            throw new IllegalArgumentException("Staff cannot be null.");
        }

        if (member.getSeniority().compareTo(Seniority.TECH) == 0) {
            tiers.remove(Seniority.TECH, member);
            techTree.remove(member);
            member.setCaseLoad(newLoad);
            techTree.add(member);
            tiers.put(Seniority.TECH, techTree);
        }
        else if (member.getSeniority().compareTo(Seniority.NURSE) == 0) {
            tiers.remove(Seniority.NURSE, member);
            nurseTree.remove(member);
            member.setCaseLoad(newLoad);
            nurseTree.add(member);
            tiers.put(Seniority.NURSE, nurseTree);
        }
        else if (member.getSeniority().compareTo(Seniority.RESIDENT) == 0) {
            tiers.remove(Seniority.RESIDENT, member);
            residentTree.remove(member);
            member.setCaseLoad(newLoad);
            residentTree.add(member);
            tiers.put(Seniority.RESIDENT, residentTree);
        }
        else if (member.getSeniority().compareTo(Seniority.ATTENDING) == 0) {
            tiers.remove(Seniority.ATTENDING, member);
            attendingTree.remove(member);
            member.setCaseLoad(newLoad);
            attendingTree.add(member);
            tiers.put(Seniority.ATTENDING, attendingTree);
        }
    }

    // TODO: Call assign(), record the treatment in history with the chosen staffId, and return that staff member.
    public StaffMember assignTreatment(String patientID, String treatment, String timestamp, Seniority required) {
        if (required == null || patientID == null || treatment == null || timestamp == null) {
            throw new IllegalArgumentException("Cannot have null values assigned.");
        }

        StaffMember assignedStaff = assign(required);
        String assignedStaffId = assignedStaff.getStaffId();
        history.addTreatment(patientID, treatment, timestamp, assignedStaffId);
        return assignedStaff;
    }

    // TODO: Pop the globally most recent treatment with your Week 3 undo, release whoever performed it, and return them (null if none).
    public StaffMember undoLastAssignment() {
        TreatmentRecord record = history.undoLastAssignment();
        if (record == null) {
            return null;
        }
        StaffMember toReturn = findById(record.getPerformedBy());
        if (toReturn == null) {
            return null;
        }
        else {
            release(toReturn);
            return toReturn;
        }
    }

    // TODO: Return the staff member with this staffId, or null if they are not in the roster.
    private StaffMember findById(String staffId) {
        if (staffId == null) {
            return null;
        }
        staffIterator = techTree.iterator();

        while (staffIterator.hasNext()) {
            StaffMember currentStaff = staffIterator.next();
            if (currentStaff.getStaffId().equals(staffId)) {
                return currentStaff;
            }
        }

        staffIterator = nurseTree.iterator();

        while (staffIterator.hasNext()) {
            StaffMember currentStaff = staffIterator.next();
            if (currentStaff.getStaffId().equals(staffId)) {
                return currentStaff;
            }
        }

        staffIterator = residentTree.iterator();

        while (staffIterator.hasNext()) {
            StaffMember currentStaff = staffIterator.next();
            if (currentStaff.getStaffId().equals(staffId)) {
                return currentStaff;
            }
        }

        staffIterator = attendingTree.iterator();

        while (staffIterator.hasNext()) {
            StaffMember currentStaff = staffIterator.next();
            if (currentStaff.getStaffId().equals(staffId)) {
                return currentStaff;
            }
        }

        return null;
    }
}
