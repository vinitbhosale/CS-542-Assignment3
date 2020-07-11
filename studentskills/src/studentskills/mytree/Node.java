package studentskills.mytree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import studentskills.operation.Operation;
import studentskills.util.MyLogger;

/**
 * Node class that implements SubjectI, ObserverI, Cloneable interface and
 * implements methods of all interfaces.
 */
public class Node implements SubjectI, ObserverI, Cloneable {

  private Node left;
  private Node right;
  // Intializing of Set for unique skills.
  Set<String> skills = new HashSet<>();
  // Intializing of List to store Observers.
  public List<ObserverI> observers = new ArrayList<>();
  // Intializing of StudentRecord obj.
  private StudentRecord st;

  /**
   * Node methode to create student record node.
   * 
   * @param inSt - StudentRecord obj
   */
  public Node(StudentRecord inSt) {
    MyLogger.writeMessage("Node Constructor", MyLogger.DebugLevel.CONSTRUCTOR);
    st = inSt;
    this.left = this.right = null;
  }

  /**
   * getBNumKey method to get students bnumber.
   * 
   * @return - bnumber
   */
  public int getBNumKey() {
    return st.getBNumKey();
  }

  /**
   * getFirstName method to get students first name.
   * 
   * @return firstname
   */
  public String getFirstName() {
    return st.getFirstName();
  }

  /**
   * getLastName method to get student last name.
   * 
   * @return lastname
   */
  public String getLastName() {
    return st.getLastName();
  }

  /**
   * getGpa methoed to get students gpa.
   * 
   * @return - gpa
   */
  public double getGpa() {
    return st.getGpa();
  }

  /**
   * getMajor method to get students major.
   * 
   * @return - major
   */
  public String getMajor() {
    return st.getMajor();
  }

  /**
   * getSkills method to get students skill.
   * 
   * @return - skills
   */
  public Set<String> getSkills() {
    return st.getSkills();
  }

  /**
   * getLeftNode method to get left node
   * 
   * @return - left
   */
  public Node getLeftNode() {
    return left;
  }

  /**
   * sets left node
   * 
   * @param left
   */
  public void setLeft(Node left) {
    this.left = left;
  }

  /**
   * getRightNode method to get right node
   * 
   * @return - right
   */
  public Node getRightNode() {
    return right;
  }

  /**
   * sets right node
   * 
   * @param right
   */
  public void setRight(Node right) {
    this.right = right;
  }

  /**
   * getStudentRecord method to get student record.
   * 
   * @return - student record
   */
  public StudentRecord getStudentRecord() {
    return st;
  }

  /**
   * updateNode method to update student record.
   * 
   * @param inSt - student record
   */
  public void updateNode(StudentRecord inSt) {
    MyLogger.writeMessage("Node updated from Input file.\n", MyLogger.DebugLevel.NODE);
    // fetch old values of skills
    skills = st.getSkills();
    // update the student
    st = inSt;
    // add old values to new values.
    st.getSkills().addAll(skills);
  }

  /**
   * replaceValue method to replace values of student record from modify file
   * 
   * @param inSt       - student record
   * @param inOgValue  - original value in student record
   * @param inNewValue - new value
   */
  public void replaceValue(StudentRecord inSt, String inOgValue, String inNewValue) {
    MyLogger.writeMessage("Node value updated from modify file.\n", MyLogger.DebugLevel.CONSTRUCTOR);
    // Condition to check which value to change
    if (inSt.getFirstName().matches(inOgValue)) {
      inSt.setFirstName(inNewValue);

    } else if (inSt.getLastName().matches(inOgValue)) {
      inSt.setLastName(inNewValue);

    } else if (st.getMajor().matches(inOgValue)) {
      inSt.setMajor(inNewValue);

    } else if (inSt.getSkills().contains(inOgValue)) {
      inSt.getSkills().remove(inOgValue);
      inSt.getSkills().add(inNewValue);

    }

  }

  /**
   * update method to udpate all the observers node of tree.
   * 
   * @param inSt - student record
   * @param inOP - Operation INSERT/MODIFY
   */
  @Override
  public void update(StudentRecord inSt, Operation inOP) {
    MyLogger.writeMessage("Update call for all observer.", MyLogger.DebugLevel.CONSTRUCTOR);
    // TODO Auto-generated method stub
    if (inOP == Operation.INSERT) {
      updateNode(inSt);

    } else if (inOP == Operation.MODIFY) {
      updateNode(inSt);
    }

  }

  /**
   * registerObserver method that add observers of nodes in observers list.
   * 
   * @param observerI - ObserverI obj
   */
  @Override
  public void registerObserver(ObserverI observerI) {
    MyLogger.writeMessage("Observer regestering.", MyLogger.DebugLevel.CONSTRUCTOR);
    // TODO Auto-generated method stub
    observers.add(observerI);
  }

  /**
   * notifyAll method that notify all the observers in the observers list that
   * implements update method to update the respective node.
   * 
   * @param op - Operation INSERT/MODIFY
   */
  @Override
  public void notifyAll(Operation op) {
    MyLogger.writeMessage("Notifying all observer.", MyLogger.DebugLevel.CONSTRUCTOR);
    // TODO Auto-generated method stub
    for (ObserverI observerI : observers) {
      observerI.update(st, op);
    }
  }

  /**
   * clone method that clones the student record node.
   * 
   * @return cloneNode
   */
  @Override
  public Node clone() {
    Node cloneNode = new Node(st);
    return cloneNode;
  }

  @Override
  public String toString() {
    return "Class: Node, Data Members: [left=" + left.toString() + ", right=" + right.toString() + ", skills="
        + skills.toString() + ", observers=" + observers.toString() + ", st=" + st.toString() + "]";

  }

}