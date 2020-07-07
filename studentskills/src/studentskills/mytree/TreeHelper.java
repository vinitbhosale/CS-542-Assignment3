package studentskills.mytree;

public class TreeHelper {

    Node root;

    public TreeHelper() {
        root = null;
    }

    public void insertStudent(Node inNode) {
        root = insert(root, inNode);
    }

    public Node insert(Node root, Node inNode) {
        int bNumKey = inNode.getBNumKey();

        if (null == root) {
            root = new Node(inNode);

            return root;
        }

        if (bNumKey < root.getBNumKey()) {
            root.setLeft(insert(root.getLeftNode(), inNode));
        } else if (bNumKey > root.getBNumKey()) {
            root.setRight(insert(root.getRightNode(), inNode));
        }
        return root;
    }

    public Node search(int inBkey) {
        Node searchKey = root;
        while(searchKey != null){
            if(searchKey.getBNumKey() == inBkey){
                return searchKey;
            }else if(searchKey.getBNumKey() > inBkey){
                searchKey.getLeftNode();
                return searchKey;
            }else if(searchKey.getBNumKey() < inBkey){
                searchKey.getRightNode();
                return searchKey;
            }
        }
        return null;
        
    }

}