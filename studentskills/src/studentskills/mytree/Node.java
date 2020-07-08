package studentskills.mytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Node implements SubjectI, ObserverI, Cloneable {

  private Node left;
  private Node right;
  private int bNumber;
  private String firstName;
  private String lastName;
  private double gpa;
  private String major;
  Set<String> skills;
  public List<ObserverI> observers = new ArrayList<>();
  private StudentRecord st;

  public Node(StudentRecord inSt) {
    st = inSt;
    this.left = this.right = null;

  }

  public Node(Node inNode) throws NullPointerException {
    st = inNode.st;
    this.left = inNode.getLeftNode();
    this.right = inNode.getRightNode();
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

  @Override
  public void registerObserver(ObserverI observerI) {
    observers.add(observerI);
  }

  @Override
  public Node clone() {
    Node oldNode = new Node(st);
    return oldNode;
  }

}