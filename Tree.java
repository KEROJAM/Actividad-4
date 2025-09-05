public class Tree {
    Node root;

    public Tree (){
        root = null;
    }

    public void insert(int Data) {
        root = insertRec(root, Data);
    }
    private Node insertRec(Node root, int Data){
        if (root == null){
            root = new Node(Data);
            return root;
        }

        if (Data < root.Data){
            root.last = insertRec(root.last, Data);
        } else if (Data > root.Data){
            root.next = insertRec(root.next, Data);
        }
        return root;
    }
     public void inorder(){
        inorderRec(root);
     }

     private void inorderRec(Node root){
        if (root != null ){
            inorderRec(root.last);
            System.out.print(root.Data + " ");
            inorderRec(root.next);
        }
     }

     public boolean search(int data){
        return searchRec(root, data);
     }

    private boolean searchRec(Node root, int data){
        if (root == null){
            return false;
        }

        if (root.Data == data){
            return true;
        }

        if (data < root.Data){
            return searchRec(root.last, data);
        } else {
            return searchRec(root.next, data);
        }
    }

    public int findMind(){
        return findMinRec(root);
    }

    private int findMinRec(Node root) {
        if (root == null){
            throw new IllegalStateException("Tree is empty");
        }

        if (root.last == null){
            return root.Data;
        }

        return findMinRec(root.last);
    }

    public int findMax(){
        return findMaxRec(root);
    }

    private int findMaxRec(Node root){
        if (root == null)
            throw new IllegalStateException("Tree is empty");

        if (root.next == null)
            return root.Data;

        return findMaxRec(root.next);
    }
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        // Insert some nodes
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        // Print inorder traversal of the tree
        System.out.println("Inorder traversal:");
        tree.inorder();
        // Output: 20 30 40 50 60 70 80

        // Search for a key
        int searchKey = 40;
        if (tree.search(searchKey))
            System.out.println("\nKey " + searchKey + " found in the tree.");
        else
            System.out.println("\nKey " + searchKey + " not found in the tree.");

        // Find minimum and maximum values
        System.out.println("Minimum value in the tree: " + tree.findMin());
        System.out.println("Maximum value in the tree: " + tree.findMax());
    }
}
