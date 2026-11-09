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
    private StaffMember lowestCaseLoad = null;

    // TODO: Add the staff member to the correct tier, and reject a duplicate staffId.
    public void addStaff(StaffMember staff) {
        if (tiers.containsValue(staff)) {
            throw new IllegalArgumentException();
        }
        if (staff.getSeniority().compareTo(Seniority.TECH) == 0) {
            techTree.add(staff);
            tiers.put(Seniority.TECH, techTree);
        }
        else if (staff.getSeniority().compareTo(Seniority.NURSE) == 0) {
            nurseTree.add(staff);
            tiers.put(Seniority.NURSE, nurseTree);
        }
        else if (staff.getSeniority().compareTo(Seniority.RESIDENT) == 0) {
            residentTree.add(staff);
            tiers.put(Seniority.RESIDENT, residentTree);
        }
        else if (staff.getSeniority().compareTo(Seniority.ATTENDING) == 0) {
            attendingTree.add(staff);
            tiers.put(Seniority.ATTENDING, attendingTree);
        }
    }

    // TODO: Pick the least-loaded staff member at or above "required", raise their load by 1, and return them.
    public StaffMember assign(Seniority required) {  
        if (Seniority.TECH.compareTo(required) >= 0) {
            if (lowestCaseLoad == null) {
                lowestCaseLoad = techTree.getFirst();
            }
            staffIterator = techTree.iterator();
            while (staffIterator.hasNext()) {
                if (staffIterator.next().getCaseLoad() < lowestCaseLoad.getCaseLoad()) {
                    lowestCaseLoad = staffIterator.next();
                }
            }
        }
        else if (Seniority.NURSE.compareTo(required) >= 0) {
            if (lowestCaseLoad == null) {
                lowestCaseLoad = nurseTree.getFirst();
            }
            staffIterator = nurseTree.iterator();
            while (staffIterator.hasNext()) {
                if (staffIterator.next().getCaseLoad() < lowestCaseLoad.getCaseLoad()) {
                    lowestCaseLoad = staffIterator.next();
                }
            }
        }
        else if (Seniority.RESIDENT.compareTo(required) >= 0) {
            if (lowestCaseLoad == null) {
                lowestCaseLoad = residentTree.getFirst();
            }
            staffIterator = residentTree.iterator();
            while (staffIterator.hasNext()) {
                if (staffIterator.next().getCaseLoad() < lowestCaseLoad.getCaseLoad()) {
                    lowestCaseLoad = staffIterator.next();
                }
            }
        }
        else if (Seniority.ATTENDING.compareTo(required) == 0) {
            if (lowestCaseLoad == null) {
                lowestCaseLoad = attendingTree.getFirst();
            }
            staffIterator = attendingTree.iterator();
            while (staffIterator.hasNext()) {
                if (staffIterator.next().getCaseLoad() < lowestCaseLoad.getCaseLoad()) {
                    lowestCaseLoad = staffIterator.next();
                }
            }
        }
        //If the while loops through and no staff are found with the right requirements, this
        //checks if the set staff member is null. If so, it returns null Otherwise it returns the 
        //lowest case load (either the set staff at zero from the beginning, or the staff found in the for loop above)
        if (lowestCaseLoad == null) {
            return null;
        }
        else {
            changeLoad(lowestCaseLoad, lowestCaseLoad.getCaseLoad() + 1);
            return lowestCaseLoad;
        }
    }

    // TODO: Lower the staff member's load by 1 (never below 0) and keep the tree ordered.
    public void release(StaffMember staff) {
        //Throws an error if the staff's case load drops below zero when completing this method
        if (staff.getCaseLoad() - 1 < 0) {
            return;
        }
        //Otherwise changes the staff case load by -1
        changeLoad(staff, staff.getCaseLoad() - 1);
    }

    // TODO: Return how many staff members are in the roster.
    public int size() {
        return tiers.size();
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
        else {
            throw new IllegalArgumentException();
        }
    }

    // TODO: Call assign(), record the treatment in history with the chosen staffId, and return that staff member.
    public StaffMember assignTreatment(String patientID, String treatment, String timestamp, Seniority required) {
        StaffMember assignedStaff = assign(required);
        String assignedStaffId = assignedStaff.getStaffId();
        history.addTreatment(patientID, treatment, timestamp, assignedStaffId);
        return assignedStaff;
    }

    // TODO: Pop the globally most recent treatment with your Week 3 undo, release whoever performed it, and return them (null if none).
    public StaffMember undoLastAssignment() {
        if (history.peekTreatmentRecord() == null) {
            return null;
        }
        StaffMember toReturn = findById(history.peekTreatmentRecord().getPatientID());
        history.undoLastAssignment();
        return toReturn;
    }

    // TODO: Return the staff member with this staffId, or null if they are not in the roster.
    private StaffMember findById(String staffId) {
        boolean foundId = false;
        StaffMember toReturn = null;
        staffIterator = techTree.iterator();

        while (staffIterator.hasNext() || foundId == true) {
            if (staffIterator.next().getStaffId().compareTo(staffId) == 0) {
                toReturn = staffIterator.next();
                foundId = true;
            }
        }

        staffIterator = nurseTree.iterator();

        while (staffIterator.hasNext() || foundId == true) {
            if (staffIterator.next().getStaffId().compareTo(staffId) == 0) {
                toReturn = staffIterator.next();
                foundId = true;
            }
        }

        staffIterator = residentTree.iterator();

        while (staffIterator.hasNext() || foundId == true) {
            if (staffIterator.next().getStaffId().compareTo(staffId) == 0) {
                toReturn = staffIterator.next();
                foundId = true;
            }
        }

        staffIterator = attendingTree.iterator();

        while (staffIterator.hasNext() || foundId == true) {
            if (staffIterator.next().getStaffId().compareTo(staffId) == 0) {
                toReturn = staffIterator.next();
                foundId = true;
            }
        }

        return toReturn;
    }
}
