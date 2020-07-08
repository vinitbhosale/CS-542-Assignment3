package studentskills.mytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeHelper {

    private List<Tree> treeList = new ArrayList<Tree>();

    public TreeHelper(Tree... inTreeArr) {
        treeList = Arrays.asList(inTreeArr);
    }

    public void builTree(StudentRecord st) {

        Node replica_Node_0 = new Node(st);
        Node replica_Node_1 = replica_Node_0.clone();
        Node replica_Node_2 = replica_Node_0.clone();

        replica_Node_0.registerObserver(replica_Node_1);
        replica_Node_0.registerObserver(replica_Node_2);

        replica_Node_1.registerObserver(replica_Node_0);
        replica_Node_1.registerObserver(replica_Node_2);

        replica_Node_2.registerObserver(replica_Node_1);
        replica_Node_2.registerObserver(replica_Node_0);

        treeList.get(0).insertStudent(replica_Node_0);
        treeList.get(1).insertStudent(replica_Node_1);
        treeList.get(2).insertStudent(replica_Node_2);

    }

    public void printTree() {
        treeList.get(0).print();
        System.out.println("------");
        treeList.get(1).print();
        System.out.println("------");
        treeList.get(1).print();
    }

}