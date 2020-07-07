package studentskills.mytree;

import java.util.HashSet;
import java.util.Set;

public class StudentRecord {
    private int bNumber;
    private String firstName;
    private String lastName;
    private double gpa;
    private String major;
    private Set<String> skills = new HashSet<String>();
    private String operation;

    /**
     * StudentRecord Constructor
     * 
     * @param inBNumber
     * @param inFirstName
     * @param inLlastName
     * @param inGpa
     * @param inMajor
     * @param inSkills
     */
    public StudentRecord(int inBNumber, String inFirstName, String inLastName, double inGpa, String inMajor,
            Set<String> inSkills, String inOperation) {

        bNumber = inBNumber;
        firstName = inFirstName;
        lastName = inLastName;
        gpa = inGpa;
        major = inMajor;
        skills = inSkills;
        operation = inOperation;
    }

    
}