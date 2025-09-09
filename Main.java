import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Tree tree = new Tree("");

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
