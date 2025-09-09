import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;

public class Tree {
    Node root;
    private PrintWriter logWriter;

    public Tree (String logFile) throws IOException {
        root = null;
        logWriter = new PrintWriter(new FileWriter(logFile, true));
    }
    private void log(String message) {
        logWriter.println(message + " " + LocalDate.now() + " " + LocalTime.now());
        logWriter.flush();
    }

    public void insert(int Data) {
        root = insertRec(root, Data);
        log("Insertando valor: " + Data);
    }
    private Node insertRec(Node root, int Data){
        if (root == null){
            root = new Node(Data);
            return root;
        }

        if (Data < root.Data){
            root.Left = insertRec(root.Left, Data);
        } else if (Data > root.Data){
            root.Right = insertRec(root.Right, Data);
        }
        return root;
    }
     public void inorder(){
        inorderRec(root);
     }

     private void inorderRec(Node root){
        if (root != null ){
            inorderRec(root.Left);
            System.out.print(root.Data + " ");
            inorderRec(root.Right);
        }
     }

     public boolean search(int data){
        boolean found = searchRec(root, data);
         log("Búsqueda de " + data + ": " + (found ? "ENCONTRADO" : "NO ENCONTRADO"));
         return found;
     }

    private boolean searchRec(Node root, int data){
        if (root == null){
            return false;
        }

        if (root.Data == data){
            return true;
        }

        if (data < root.Data){
            return searchRec(root.Left, data);
        } else {
            return searchRec(root.Right, data);
        }
    }

    public int findMin(){
        return findMinRec(root);
    }

    private int findMinRec(Node root) {
        if (root == null){
            throw new IllegalStateException("Tree is empty");
        }

        if (root.Left == null){
            return root.Data;
        }

        return findMinRec(root.Left);
    }

    public int findMax(){
        return findMaxRec(root);
    }

    private int findMaxRec(Node root){
        if (root == null)
            throw new IllegalStateException("Tree is empty");

        if (root.Right == null)
            return root.Data;

        return findMaxRec(root.Right);
    }

    // Cierra el Log
    public void closeLog(){
        if (logWriter != null){
            logWriter.close();
        }
    }
}
