package studentskills.mytree;

public class Tree {
    Node root;

    public Tree() {
        root = null;
    }

    public void insertStudent(Node inNode) {
        root = insert(root, inNode);
    }

    public Node insert(Node root, Node inNode) {
        int bNumKey = inNode.getBNumKey();

        if (null == root) {
            root = inNode;
            return root;
        }

        if (bNumKey < root.getBNumKey()) {
            Node node = insert(root.getLeftNode(), inNode);
            root.setLeft(node);

        } else if (bNumKey > root.getBNumKey()) {
            Node node = insert(root.getRightNode(), inNode);
            root.setRight(node);

        }
        return root;
    }

    public Node search(int inBkey) {

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

    public void print() {
        inorder(root);
    }

    /**
     * 
     * 
     * @param root
     */
    public void inorder(Node root) {
        if (root != null) {

            inorder(root.getLeftNode());
            System.out.println(root.getBNumKey()+":"+root.getFirstName()+","+root.getLastName()+","+root.getGpa()+","+root.getMajor()+","+root.getSkills());
            inorder(root.getRightNode());

        }
    }

}