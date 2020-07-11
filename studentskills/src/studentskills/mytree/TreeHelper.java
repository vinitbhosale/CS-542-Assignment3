package studentskills.mytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import studentskills.operation.Operation;
import studentskills.replica.Replica;
import studentskills.userException.ErroFileException;
import studentskills.util.ResultsI;

/**
 * TreeHelper class calls tree build method and update method to update the
 * nodes of respective trees. Also call print method to print respective tree.
 */
public class TreeHelper {

    // Node object for 3 trees
    Node replica_Node_0;
    Node replica_Node_1;
    Node replica_Node_2;
    Node findNode;

    // Array list to store 3 instance of tree.
    private List<Tree> treeList = new ArrayList<Tree>();
    ResultsI rs0;
    ResultsI rs1;
    ResultsI rs2;

    /**
     * TreeHelper constructor
     * 
     * @param inRS0     - ResultsI obj for replica_0 tree.
     * @param inRS1     - ResultsI obj for replica_1 tree.
     * @param inRS2     - ResultsI obj for replica_2 tree.
     * @param inTreeArr - Tree obj datatype to store all 3 trees.
     */
    public TreeHelper(ResultsI inRS0, ResultsI inRS1, ResultsI inRS2, Tree... inTreeArr) {
        rs0 = inRS0;
        rs1 = inRS1;
        rs2 = inRS2;
        treeList = Arrays.asList(inTreeArr);

    }

    /**
     * builTree method that creates new node and clone it twice and insert node in
     * respective 3 trees.
     * 
     * @param st - StudentRecord obj for node creation.
     */
    public void builTree(StudentRecord st) {
        // Intialize repeated node.
        Node findNode = treeList.get(0).search(st.getBNumKey());

        // Condition to check node is present in tree.
        if (findNode == null) {
            /**
             * create new node for replica_0 tree and clone it twice for replica_1 and
             * reclica_2 tree.
             */
            replica_Node_0 = new Node(st);
            replica_Node_1 = replica_Node_0.clone();
            replica_Node_2 = replica_Node_0.clone();

            // Regestering observers of replica_0_node.
            replica_Node_0.registerObserver(replica_Node_1);
            replica_Node_0.registerObserver(replica_Node_2);

            // Regestering observers of replica_1_node.
            replica_Node_1.registerObserver(replica_Node_0);
            replica_Node_1.registerObserver(replica_Node_2);

            // Regestering observers of replica_2_node.
            replica_Node_2.registerObserver(replica_Node_1);
            replica_Node_2.registerObserver(replica_Node_0);

            // insert created nodes in respective trees.
            treeList.get(0).insertStudent(replica_Node_0);
            treeList.get(1).insertStudent(replica_Node_1);
            treeList.get(2).insertStudent(replica_Node_2);

        } else {
            /**
             * Updated the repeated node and notifly respected node observers.
             */
            findNode.updateNode(st);
            findNode.notifyAll(Operation.INSERT);

        }

    }

    /**
     * updateNode method that updates node of respective tree with the new values
     * and notify all the observers of the respected updated node.
     * 
     * @param replicaNum - tree number.
     * @param ModBNumber - Student Bnumber which record need to update.
     * @param origValue  - Original value of the student.
     * @param newValue   - New value of the student.
     * @throws ErroFileException
     */
    public void updateNode(String replicaNum, int ModBNumber, String origValue, String newValue)
            throws ErroFileException {
        // Intializing StudentRecord obj.
        StudentRecord st;
        // Condition to check in which tree node need to update.
        if (replicaNum.equals(Replica.ZERO.getreplicaNumString())) {
            // Condition to check node is present in tree.
            Node findNode = treeList.get(0).search(ModBNumber);
            // Fetching that student node record.
            st = findNode.getStudentRecord();
            // Condition to check node is present in replica_0 tree.
            if (findNode != null) {
                // Update node and notify its observer.
                findNode.replaceValue(st, origValue, newValue);
                findNode.notifyAll(Operation.MODIFY);
            }
            throw new ErroFileException(ModBNumber+" No such student record in replica_0 tree");

        } else if (replicaNum.equals(Replica.ONE.getreplicaNumString())) {
            Node findNode = treeList.get(1).search(ModBNumber);
            st = findNode.getStudentRecord();
            // Condition to check node is present in replica_1 tree.
            if (findNode != null) {
                // Update node and notify its observer.
                findNode.replaceValue(st, origValue, newValue);
                findNode.notifyAll(Operation.MODIFY);

            }
            throw new ErroFileException(ModBNumber+" No such student record in replica_1 tree");

        } else if (replicaNum.equals(Replica.TWO.getreplicaNumString())) {
            Node findNode = treeList.get(2).search(ModBNumber);
            st = findNode.getStudentRecord();
            // Condition to check node is present in replica_2 tree.
            if (findNode != null) {
                // Update node and notify its observer.
                findNode.replaceValue(st, origValue, newValue);
                findNode.notifyAll(Operation.MODIFY);

            }
            throw new ErroFileException(ModBNumber+" No such student record in replica_2 tree");

        }

    }

    /**
     * storeTreeResult method to print nodes of the created tree.
     */
    public void storeTreeResult() {
        treeList.get(0).printNodes(rs0);

        treeList.get(1).printNodes(rs1);

        treeList.get(1).printNodes(rs2);
    }

    @Override
    public String toString() {
        return "Class: TreeHelper, Data Members: [replica_Node_0=" + replica_Node_0.toString() + ", replica_Node_1="
                + replica_Node_1.toString() + ", replica_Node_2=" + replica_Node_2.toString() + ", findNode="
                + findNode.toString() + ", treeList=" + treeList.toString() + ", rs0=" + rs0.toString() + ", rs1="
                + rs1.toString() + ", rs2=" + rs2.toString() + "]";

    }

}