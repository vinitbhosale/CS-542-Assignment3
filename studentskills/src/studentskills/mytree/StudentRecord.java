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

  public int setBNumKey(int inBNumber) {
    return bNumber = inBNumber;
  }

  /**
   * @return
   */
  public String getFirstName() {
    return firstName;
  }

  public String setFirstName(String inFirstName) {
    return firstName = inFirstName;
  }

  /**
   * @return
   */
  public String getLastName() {
    return lastName;
  }

  public String setLastName(String intLastName) {
    return lastName = intLastName;
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

  public String setMajor(String intMajor) {
    return major = intMajor;
  }


  /**
   * @return
   */
  public Set<String> getSkills() {
    return skills;
  }

  public Set<String> setSkills(Set<String> inSkills) {
    return skills = inSkills;
  }

}