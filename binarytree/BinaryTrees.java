package binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTrees {

   static class Node {
       int data;
       Node left;
       Node right;

       Node(int data) {
           this.data = data;
           this.left = null;
           this.right = null;
       }
   }

   static class BinaryTree {
       static int idx = -1;
       public static Node buildTree(int nodes[]) {
           idx++;
           if (nodes[idx] == -1) {
               return null;
           }
           Node newNode = new Node(nodes[idx]); 
           newNode.left = buildTree(nodes);
           newNode.right = buildTree(nodes);
           return newNode;
       }
   }

   // Traversals
   public static void preorder(Node root) {
       if (root == null) return;
       System.out.print(root.data+" ");
       preorder(root.left);
       preorder(root.right);
   }

   public static void inorder(Node root) {
       if (root == null) return;
       inorder(root.left);
       System.out.print(root.data+" ");
       inorder(root.right);
   }

   public static void postorder(Node root) {
       if (root == null) return;
       postorder(root.left);
       postorder(root.right);
       System.out.print(root.data+" ");
   }

   public static void levelOrder(Node root) {
       if (root == null) return;
       Queue<Node> q = new LinkedList<>();
       q.add(root);
       q.add(null);
       while (!q.isEmpty()) {
           Node curr = q.remove();
           if (curr == null) {
               System.out.println();
               if (q.isEmpty()) break;
               else q.add(null);
           } else {
               System.out.print(curr.data+" ");
               if (curr.left != null) q.add(curr.left);
               if (curr.right != null) q.add(curr.right);
           }
       }
   }

   public static int countOfNodes(Node root) {
       if (root == null) return 0;
       return countOfNodes(root.left) + countOfNodes(root.right) + 1;
   }

   public static int sumOfNodes(Node root) {
       if (root == null) return 0;
       return sumOfNodes(root.left) + sumOfNodes(root.right) + root.data;
   }

   public static int height(Node root) {
       if (root == null) return 0;
       return Math.max(height(root.left), height(root.right)) + 1;
   }

   public static int search(Node root, int key) {
       if (root == null) return 0;
       if (root.data == key) return key;
       int leftSearch = search(root.left, key);
       if (leftSearch != 0) return leftSearch;
       return search(root.right, key);
   }

   public static void printLeaves(Node root) {
       if (root == null) return;
       if (root.left == null && root.right == null) {
           System.out.print(root.data + " ");
           return;
       }
       printLeaves(root.left);
       printLeaves(root.right);
   }

   public static Node insert(Node root, int val) {
       if (root == null) {
           return new Node(val);
       }
       if (val < root.data) {
           root.left = insert(root.left, val);
       } else {
           root.right = insert(root.right, val);
       }
       return root;
   }

  
   public static Node delete(Node root, int val) {
       if (root == null) return null;

       if (val < root.data) {
           root.left = delete(root.left, val);
       } else if (val > root.data) {
           root.right = delete(root.right, val);
       } else {
        
           if (root.left == null && root.right == null) {
               return null;
           }
    
           else if (root.left == null) {
               return root.right;
           } else if (root.right == null) {
               return root.left;
           }
          
           Node inData = findMin(root.right);
           root.data = inData.data;
           root.right = delete(root.right, inData.data);
       }
       return root;
   }

    public static Node findMin(Node root) {
         while (root.left != null) {
              root = root.left;
         }
         return root;
    }
  

   public static void main(String args[]) {
       int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1}; 
       BinaryTree tree = new BinaryTree();
      
       Node root = tree.buildTree(nodes);

       System.out.println("Inorder before insert:");
       inorder(root); 
       System.out.println();
       
   }
}
