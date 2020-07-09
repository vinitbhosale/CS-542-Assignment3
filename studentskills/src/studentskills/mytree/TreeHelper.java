package studentskills.mytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import studentskills.operation.Operation;

public class TreeHelper {

    Node replica_Node_0;
    Node replica_Node_1;
    Node replica_Node_2;
    Node findNode;

    private List<Tree> treeList = new ArrayList<Tree>();
    private Tree tObj;

    public TreeHelper(Tree... inTreeArr) {
        treeList = Arrays.asList(inTreeArr);
        tObj = new Tree();
    }

    public void builTree(StudentRecord st) {

        Node findNode = treeList.get(0).search(st.getBNumKey());

        if (findNode == null) {
            replica_Node_0 = new Node(st);
            replica_Node_1 = replica_Node_0.clone();
            replica_Node_2 = replica_Node_0.clone();

            replica_Node_0.registerObserver(replica_Node_1);
            replica_Node_0.registerObserver(replica_Node_2);

            replica_Node_1.registerObserver(replica_Node_0);
            replica_Node_1.registerObserver(replica_Node_2);
           
            replica_Node_2.registerObserver(replica_Node_1);
            replica_Node_2.registerObserver(replica_Node_0);

            treeList.get(0).insertStudent(replica_Node_0);
            treeList.get(1).insertStudent(replica_Node_1);
            treeList.get(2).insertStudent(replica_Node_2);

        } else {
            findNode.updateNode(st);
            findNode.notifyAll(Operation.INSERT);

        }

    }

    public void printTree() {
        treeList.get(0).print();
        System.out.println("------");
        treeList.get(1).print();
        System.out.println("------");
        treeList.get(1).print();
    }

}