package studentskills.mytree;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import studentskills.operation.Operation;

public class Node implements SubjectI, ObserverI, Cloneable {

  private Node left;
  private Node right;
  Set<String> skills = new HashSet<>();
  public List<ObserverI> observers = new ArrayList<>();
  private StudentRecord st;

  public Node(StudentRecord inSt) {
    st = inSt;
    this.left = this.right = null;
  }

  /**
   * @return
   */
  public int getBNumKey() {
    return st.getBNumKey();
  }

  /**
   * @return
   */
  public String getFirstName() {
    return st.getFirstName();
  }

  /**
   * @return
   */
  public String getLastName() {
    return st.getLastName();
  }

  /**
   * @return
   */
  public double getGpa() {
    return st.getGpa();
  }

  /**
   * @return
   */
  public String getMajor() {
    return st.getMajor();
  }

  /**
   * @return
   */
  public Set<String> getSkills() {
    return st.getSkills();
  }

  /**
   * @return
   */
  public Node getLeftNode() {
    return left;
  }

  /**
   * sets left node
   * 
   * @param left
   * @return
   */
  public void setLeft(Node left) {
    this.left = left;
  }

  /**
   * @return
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

  public StudentRecord gStudentRecord() {
    return st;
  }

  public void updateNode(StudentRecord inSt) {
    skills = st.getSkills();
    st = inSt;
    st.getSkills().addAll(skills);
  }

  public void replaceValue(StudentRecord inSt, String inOgValue, String inNewValue) {
    if (inSt.getFirstName().matches(inOgValue)) {
      inSt.setFirstName(inNewValue);

    } else if (inSt.getLastName().matches(inOgValue)) {
      inSt.setLastName(inNewValue);

    } else if (st.getMajor().matches(inOgValue)) {
      inSt.setMajor(inNewValue);

    } else if (inSt.getSkills().contains(inOgValue)) {
      inSt.getSkills().add(inNewValue);

    }

  }

  @Override
  public void update(StudentRecord inSt, Operation inOP) {
    // TODO Auto-generated method stub
    if (inOP == Operation.INSERT) {
      updateNode(inSt);

    } else if (inOP == Operation.MODIFY) {
      updateNode(inSt);
    }

  }

  @Override
  public void registerObserver(ObserverI observerI) {
    // TODO Auto-generated method stub
    observers.add(observerI);
  }

  @Override
  public void notifyAll(Operation op) {
    // TODO Auto-generated method stub
    for (ObserverI observerI : observers) {
      observerI.update(st, op);
    }
  }

  @Override
  public Node clone() {
    Node oldNode = new Node(st);
    return oldNode;
  }

}