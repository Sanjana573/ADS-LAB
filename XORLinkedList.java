import java.util.Scanner;

class Node {
    int data;
    Node npx;

    public Node(int data) {
        this.data = data;
        this.npx = null;
    }
}

public class Main {
    static Node XOR(Node a, Node b) {
        return new Node(System.identityHashCode(a) ^ System.identityHashCode(b));
    }

    static Node insert(Node head_ref, int data) {
        Node new_node = new Node(data);
        new_node.npx = head_ref;

        if (head_ref != null) {
            new_node.npx = XOR(new_node, head_ref.npx);
        }

        head_ref = new_node;
        return head_ref;
    }

    static void printList(Node head) {
        System.out.println("Following are the nodes of Linked List:");

        Node curr = head;
        Node prev = null;
        Node next;

        while (curr != null) {
            System.out.println(curr.data);
            next = XOR(prev, curr.npx);
            prev = curr;
            curr = next;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Node head = null;

        System.out.print("Enter the number of elements to be inserted: ");
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter element " + (i + 1) + ": ");
            int num = scanner.nextInt();
            head = insert(head, num);
        }

        printList(head);
        scanner.close();
    }
}
