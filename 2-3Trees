
import java.util.*;

class Node {
    public int data1;
    public int data2;
    public Node left;
    public Node mid;
    public Node right;
    public Node parent;

    public Node(int data) {
        data1 = data;
        data2 = -1; 
        left = null;
        mid = null;
        right = null;
        parent = null;
    }
}

class TwoThreeTree {
    private Node root;

    private Node insert(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }

        if (node.data2 == -1) {
            if (data < node.data1) {
                node.data2 = node.data1;
                node.data1 = data;
            } else {
                node.data2 = data;
            }
            return node;
        }

        if (data < node.data1) {
            node.left = insert(node.left, data);
        } else if (data > node.data2) {
            node.right = insert(node.right, data);
        } else {
            node.mid = insert(node.mid, data);
        }

        return node;
    }

    private Node remove(Node node, int data) {
        if (node == null) {
            return null;
        }

        if (node.data1 == data && node.data2 == -1) {
            return null;
        }

        if (node.data1 == data && node.data2 != -1) {
            node.data1 = node.data2;
            node.data2 = -1;
            return node;
        }

        if (node.data2 == data && node.mid == null) {
            node.data2 = -1;
            return node;
        }

        if (data < node.data1) {
            node.left = remove(node.left, data);
        } else if ((data < node.data2 && data > node.data1) || (node.data1 == data && node.mid != null)) {
            node.mid = remove(node.mid, data);
        } else {
            node.right = remove(node.right, data);
        }

        return node;
    }

    private void traverse(Node node) {
        if (node != null) {
            traverse(node.left);
            System.out.print(node.data1 + " ");
            if (node.data2 != -1) {
                System.out.print(node.data2 + " ");
            }
            traverse(node.mid);
            traverse(node.right);
        }
    }

    public TwoThreeTree() {
        root = null;
    }

    public void insert(int data) {
        root = insert(root, data);
    }

    public void remove(int data) {
        root = remove(root, data);
    }

    public void display() {
        traverse(root);
    }
}

public class Main {
    public static void main(String[] args) {
        TwoThreeTree tree = new TwoThreeTree();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of elements to insert:");
        int n = sc.nextInt();

        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            tree.insert(sc.nextInt());
        }

        System.out.print("2-3 Tree elements: ");
        tree.display();
        System.out.println();

        System.out.println("Enter the number of elements to delete:");
        int m = sc.nextInt();

        System.out.println("Enter the elements to delete:");
        for (int i = 0; i < m; i++) {
            tree.remove(sc.nextInt());
            System.out.print("2-3 Tree elements after deletion: ");
            tree.display();
            System.out.println();
        }

        sc.close();
    }
}
