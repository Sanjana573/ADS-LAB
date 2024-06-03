import java.util.Scanner;

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class Dictionary {
    private static final int MAX = 10;
    Node[] root;
    Node[] temp;

    public Dictionary() {
        root = new Node[MAX];
        temp = new Node[MAX];
        for (int i = 0; i < MAX; i++) {
            root[i] = null;
            temp[i] = null;
        }
    }

    public void insert(int key) {
        int index = key % MAX;
        Node newNode = new Node(key);

        if (root[index] == null) {
            root[index] = newNode;
        } else {
            Node tempNode = root[index];
            while (tempNode.next != null) {
                tempNode = tempNode.next;
            }
            tempNode.next = newNode;
        }
    }

    public void search(int key) {
        int index = key % MAX;
        Node tempNode = root[index];
        boolean flag = false;

        while (tempNode != null) {
            if (tempNode.data == key) {
                System.out.println("\nSearch key is found!!");
                flag = true;
                break;
            } else {
                tempNode = tempNode.next;
            }
        }

        if (!flag) {
            System.out.println("\nSearch key not found.......");
        }
    }

    public void delete(int key) {
        int index = key % MAX;
        Node tempNode = root[index];
        Node prevNode = null;

        while (tempNode != null && tempNode.data != key) {
            prevNode = tempNode;
            tempNode = tempNode.next;
        }

        if (tempNode == null) {
            System.out.println("\nKey not found.");
            return;
        }

        if (prevNode == null) {
            root[index] = tempNode.next;
        } else {
            prevNode.next = tempNode.next;
        }

        System.out.println("\n" + key + " has been deleted.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Dictionary dictionary = new Dictionary();
        char c;

        do {
            System.out.println("\nMENU:\n1. Create");
            System.out.println("2. Search for a value\n3. Delete a value");
            System.out.print("Enter your choice: ");
            int ch = scanner.nextInt();

            switch (ch) {
                case 1:
                    System.out.print("\nEnter the number of elements to be inserted: ");
                    int n = scanner.nextInt();
                    System.out.println("Enter the elements to be inserted:");
                    for (int i = 0; i < n; i++) {
                        int num = scanner.nextInt();
                        dictionary.insert(num);
                    }
                    break;
                case 2:
                    System.out.print("\nEnter the element to be searched: ");
                    int searchKey = scanner.nextInt();
                    dictionary.search(searchKey);
                    break;
                case 3:
                    System.out.print("\nEnter the element to be deleted: ");
                    int deleteKey = scanner.nextInt();
                    dictionary.delete(deleteKey);
                    break;
                default:
                    System.out.println("\nInvalid Choice.");
                    break;
            }

            System.out.print("\nEnter y to continue: ");
            c = scanner.next().charAt(0);
        } while (c == 'y' || c == 'Y');
    }
}
