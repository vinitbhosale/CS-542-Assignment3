package studentskills.mytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import studentskills.operation.Operation;
import studentskills.replica.Replica;
import studentskills.userException.ErroFileException;
import studentskills.util.MyLogger;
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
    private ResultsI rs0;
    private ResultsI rs1;
    private ResultsI rs2;
    private ResultsI errRs;

    /**
     * TreeHelper constructor
     * 
     * @param inRS0     - ResultsI obj for replica_0 tree.
     * @param inRS1     - ResultsI obj for replica_1 tree.
     * @param inRS2     - ResultsI obj for replica_2 tree.
     * @param inErrRs   - ResultsI obj
     * @param inTreeArr - Tree obj datatype to store all 3 trees.
     */
    public TreeHelper(ResultsI inRS0, ResultsI inRS1, ResultsI inRS2, ResultsI inErrRs, Tree... inTreeArr) {
        MyLogger.writeMessage("TreeHelper constructor.", MyLogger.DebugLevel.CONSTRUCTOR);
        rs0 = inRS0;
        rs1 = inRS1;
        rs2 = inRS2;
        errRs = inErrRs;
        treeList = Arrays.asList(inTreeArr);

    }

    /**
     * builTree method that creates new node and clone it twice and insert node in
     * respective 3 trees.
     * 
     * @param st - StudentRecord obj for node creation.
     */
    public void builTree(StudentRecord st) {
        MyLogger.writeMessage("Creating all three tree", MyLogger.DebugLevel.CONSTRUCTOR);
        // Intialize repeated node.
        Node findNode = treeList.get(0).search(st.getBNumKey());

        // Condition to check node is present in tree.
        if (findNode == null) {
            MyLogger.writeMessage("Student record node creation and cloning the node.", MyLogger.DebugLevel.TREEHELPER);
            /**
             * create new node for replica_0 tree and clone it twice for replica_1 and
             * reclica_2 tree.
             */
            replica_Node_0 = new Node(st);
            replica_Node_1 = replica_Node_0.clone();
            replica_Node_2 = replica_Node_0.clone();

            MyLogger.writeMessage("Regestering observers of the nodes.", MyLogger.DebugLevel.TREEHELPER);
            // Regestering observers of replica_0_node.
            replica_Node_0.registerObserver(replica_Node_1);
            replica_Node_0.registerObserver(replica_Node_2);

            // Regestering observers of replica_1_node.
            replica_Node_1.registerObserver(replica_Node_0);
            replica_Node_1.registerObserver(replica_Node_2);

            // Regestering observers of replica_2_node.
            replica_Node_2.registerObserver(replica_Node_1);
            replica_Node_2.registerObserver(replica_Node_0);

            MyLogger.writeMessage("Inserting nodes in tree.", MyLogger.DebugLevel.TREEHELPER);
            // insert created nodes in respective trees.
            treeList.get(0).insertStudent(replica_Node_0);
            treeList.get(1).insertStudent(replica_Node_1);
            treeList.get(2).insertStudent(replica_Node_2);

        } else {
            MyLogger.writeMessage("Updating node from Input file and notifying its observer to update their nodes.",
                    MyLogger.DebugLevel.TREEHELPER);
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

        MyLogger.writeMessage("Updating node from modify file.", MyLogger.DebugLevel.TREEHELPER);
        // Intializing StudentRecord obj.
        StudentRecord st;
        MyLogger.writeMessage("Checking in which tree node need to be update.", MyLogger.DebugLevel.TREEHELPER);
        // Condition to check in which tree node need to update.
        if (replicaNum.equals(Replica.ZERO.getreplicaNumString())) {
            MyLogger.writeMessage("Searching node in tree to update.\n", MyLogger.DebugLevel.TREEHELPER);
            // Condition to check node is present in tree.
            Node findNode = treeList.get(0).search(ModBNumber);

            // Condition to check node is present in replica_0 tree.
            if (findNode != null) {
                MyLogger.writeMessage("Updating node from modify file notifying its observer to update their nodes.\n",
                        MyLogger.DebugLevel.TREEHELPER);
                // Fetching that student node record.
                st = findNode.getStudentRecord();
                // Update node and notify its observer.
                findNode.replaceValue(st, origValue, newValue);
                findNode.notifyAll(Operation.MODIFY);
            } else {
                throw new ErroFileException(ModBNumber + " No such student record in replica_0 tree");
            }

        } else if (replicaNum.equals(Replica.ONE.getreplicaNumString())) {
            MyLogger.writeMessage("Searching node in tree to update.\n", MyLogger.DebugLevel.TREEHELPER);
            Node findNode = treeList.get(1).search(ModBNumber);

            // Condition to check node is present in replica_1 tree.
            if (findNode != null) {
                MyLogger.writeMessage("Updating node from modify file notifying its observer to update their nodes.\n",
                        MyLogger.DebugLevel.TREEHELPER);
                // Fetching that student node record.
                st = findNode.getStudentRecord();
                // Update node and notify its observer.
                findNode.replaceValue(st, origValue, newValue);
                findNode.notifyAll(Operation.MODIFY);

            } else {
                throw new ErroFileException(ModBNumber + " No such student record in replica_1 tree");
            }

        } else if (replicaNum.equals(Replica.TWO.getreplicaNumString())) {
            MyLogger.writeMessage("Searching node in tree to update.\n", MyLogger.DebugLevel.TREEHELPER);
            Node findNode = treeList.get(2).search(ModBNumber);

            // Condition to check node is present in replica_2 tree.
            if (findNode != null) {
                MyLogger.writeMessage("Updating node from modify file notifying its observer to update their nodes.\n",
                        MyLogger.DebugLevel.TREEHELPER);
                // Fetching that student node record.
                st = findNode.getStudentRecord();
                // Update node and notify its observer.
                findNode.replaceValue(st, origValue, newValue);
                findNode.notifyAll(Operation.MODIFY);

            } else {
                throw new ErroFileException(ModBNumber + " No such student record in replica_2 tree");
            }

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