package studentskills.mytree;

import studentskills.util.MyLogger;
import studentskills.util.ResultsI;

/**
 * Tree class implements BST tree algorithm to create nodes, searching of nodes
 * and printing of tree.
 * 
 * Reference link
 * https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/
 * 
 */
public class Tree {
    Node root;

    // Tree constructor to set root for each tree.
    public Tree() {
        MyLogger.writeMessage("Tree constructor.", MyLogger.DebugLevel.CONSTRUCTOR);
        root = null;
    }

    /**
     * insertStudent method to insert student as node
     * 
     * @param inNode - student record node.
     */
    public void insertStudent(Node inNode) {
        MyLogger.writeMessage("Inserting student node in tree.", MyLogger.DebugLevel.TREE);
        root = insert(root, inNode);
    }

    /**
     * insert method od BST algorithm.
     * 
     * @param root   - root node.
     * @param inNode - student record node.
     * 
     * @return - root node.
     */
    public Node insert(Node root, Node inNode) {
        int bNumKey = inNode.getBNumKey();

        if (null == root) {
            root = inNode;
            return root;
        }

        if (bNumKey < root.getBNumKey()) {
            // recursive call of insert method.
            Node node = insert(root.getLeftNode(), inNode);
            root.setLeft(node);

        } else if (bNumKey > root.getBNumKey()) {
            // recursive call of insert method.
            Node node = insert(root.getRightNode(), inNode);
            root.setRight(node);

        }
        return root;
    }

    /**
     * search method of BST algorithm.
     * 
     * @param inBkey - bnumber of student record.
     * 
     * @return - searchKey node if found or Null.
     */
    public Node search(int inBkey) {
        MyLogger.writeMessage("Searching student node in tree.", MyLogger.DebugLevel.TREE);
        Node searchKey = root;
        while (searchKey != null) {
            if (searchKey.getBNumKey() == inBkey) {
                return searchKey;
            } else if (searchKey.getBNumKey() > inBkey) {
                searchKey = searchKey.getLeftNode();
            } else if (searchKey.getBNumKey() < inBkey) {
                searchKey = searchKey.getRightNode();
            }
        }
        return null;

    }

    /**
     * printNodes method that call inorder method to print nodes.
     * 
     * @param rs - ResultsI obj to store result.
     */
    public void printNodes(ResultsI rs) {
        MyLogger.writeMessage("Printing student node of tree.\n", MyLogger.DebugLevel.TREE);
        inorder(root, rs);
    }

    /**
     * inorder method of BST algorithm.
     * 
     * @param root - Starting node to print tree.
     * @param rs   - ResultsI obj to store result.
     */
    public void inorder(Node root, ResultsI rs) {

        if (root != null) {
            // recursive call of inorder method.
            inorder(root.getLeftNode(), rs);
            rs.storeResult(root.getBNumKey() + ":" + root.getFirstName() + "," + root.getLastName() + ","
                    + root.getGpa() + "," + root.getMajor() + "," + root.getSkills());
            // recursive call of inorder method.
            inorder(root.getRightNode(), rs);

        }
    }

    @Override
    public String toString() {
        return "Class: Tree, Data Members: [root=" + root.toString() + "]";

    }

}