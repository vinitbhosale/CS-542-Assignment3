package studentskills.mytree;

import java.util.LinkedList;
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
    public List<ObserverI> observers = new LinkedList<>();

    public Node(int inBNumber, String inFirstName, String inLastName, double inGpa, String inMajor, Set<String> inSkills){
        bNumber = inBNumber; 
        firstName = inFirstName; 
        lastName = inLastName; 
        gpa = inGpa; 
        major = inMajor; 
        skills = inSkills;
        left = null;
        right = null;
    }

    public Node(Node inNode){

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
    /**
	 * @return 
	 */
	public Node getLeftNode() {
		return left;
    }
    /**
	 * sets left node
	 * @param left
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
	public Node clone()
    {
		Node oldNode = new Node(bNumber, firstName, lastName, gpa, major, skills);
        return oldNode;
    }
    
}