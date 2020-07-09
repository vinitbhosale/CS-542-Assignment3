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
            Set<String> inSkills) {

        bNumber = inBNumber;
        firstName = inFirstName;
        lastName = inLastName;
        gpa = inGpa;
        major = inMajor;
        skills = inSkills;
       
    }
    /**
	 * @return 
	 */
	public int getBNumKey() {
		return bNumber;
    }
    /**
	 * @return 
	 */
	public String getFirstName() {
		return firstName;
    }
   
    /**
	 * @return 
	 */
	public String getLastName() {
		return lastName;
    }
    /**
	 * @return 
	 */
	public double getGpa() {
		return gpa;
    }
    /**
	 * @return 
	 */
	public String getMajor() {
		return major;
    }
    /**
	 * @return 
	 */
	public Set<String> getSkills() {
		return skills;
    }

    
    
}