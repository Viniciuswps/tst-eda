import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class ValorMaisProximoBST {
       
    static int min_diff, min_diff_key; 
            
    /*  A binary tree node has key, pointer to left child 
    and a pointer to right child */
    static class Node 
    { 
        int key; 
        
        Node  left, right; 
    }; 
    
    /*  Utility that allocates a new node with the 
    given key and null left and right pointers.  */
    
    static Node newnode(int key) 
    { 
        
        Node node = new Node(); 
        node.key = key; 
        node.left = node.right = null; 
        return (node); 
    } 
    
    // Function to find node with minimum absolute 
    // difference with given K 
    // min_diff   -. minimum difference till now 
    // min_diff_key  -. node having minimum absolute 
    //                   difference with K 
    static void maxDiffUtil(Node  ptr, int k) 
    { 
        if (ptr == null) 
            return ; 
    
        // If k itself is present 
        if (ptr.key == k) 
        { 
            min_diff_key = k; 
            return; 
        } 
    
        // update min_diff and min_diff_key by checking 
        // current node value 
        if (min_diff > Math.abs(ptr.key - k)) 
        { 
            min_diff = Math.abs(ptr.key - k); 
            min_diff_key = ptr.key; 
        } 
    
        // if k is less than ptr.key then move in 
        // left subtree else in right subtree 
        if (k < ptr.key) 
            maxDiffUtil(ptr.left, k); 
        else
            maxDiffUtil(ptr.right, k); 
    } 
    
    // Wrapper over maxDiffUtil() 
    static int maxDiff(Node  root, int k) 
    { 
        // Initialize minimum difference 
        min_diff = 999999999; min_diff_key = -1; 
    
        // Find value of min_diff_key (Closest key 
        // in tree with k) 
        maxDiffUtil(root, k); 
    
        return min_diff_key; 
    } 

    static Node createTree(Node root, int number)
    {
        if (root == null) {
            return newnode(number);
        }
        
        if (number < root.key) {
            root.left = createTree(root.left, number);
        } else if (number > root.key) {
            root.right = createTree(root.right, number);
        } else {
            return root;
        }
        
        return root;
    }
    
    static ArrayList<Integer> printPreorder(Node node) 
    { 
        ArrayList<Integer> list = new ArrayList<>();
        if (node == null) 
            return list; 
  
        /* first print data of node */
        list.add(node.key); 
  
        /* then recur on left sutree */
        list.addAll(printPreorder(node.left)); 
  
        /* now recur on right subtree */
        list.addAll(printPreorder(node.right));
        
        return list;
    } 
    
    // Driver program to run the case 
    public static void main(String args[]) 
    { 
        Scanner scan = new Scanner(System.in);
        String[] numbers = scan.nextLine().split(" ");
        int k = Integer.parseInt(scan.nextLine());

        Node  root = newnode(Integer.parseInt(numbers[0])); 
        for (int i = 1; i < numbers.length; i++) {
            root = createTree(root, Integer.parseInt(numbers[i]));
        } 
        
        System.out.println(Arrays.toString(printPreorder(root).toArray()));
        System.out.println(maxDiff(root, k)); 
        
    } 
} 