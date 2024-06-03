
import java.util.Scanner;

class Node {
    int key;
    Node forward[];

    Node(int key, int level) {
        this.key = key;
        forward = new Node[level + 1];
    }
}

class SkipList {
    int MAXLVL;
    float P;
    int level;
    Node header;

    SkipList(int MAXLVL, float P) {
        this.MAXLVL = MAXLVL;
        this.P = P;
        level = 0;
        header = new Node(-1, MAXLVL);
    }

    int randomLevel() {
        float r = (float)Math.random();
        int lvl = 0;
        while (r < P && lvl < MAXLVL) {
            lvl++;
            r = (float)Math.random();
        }
        return lvl;
    }

    Node createNode(int key, int level) {
        Node n = new Node(key, level);
        return n;
    }

    void insertElement(int key) {
        Node current = header;
        Node update[] = new Node[MAXLVL + 1];
        for (int i = level; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].key < key)
                current = current.forward[i];
            update[i] = current;
        }
        current = current.forward[0];
        if (current == null || current.key != key) {
            int rlevel = randomLevel();
            if (rlevel > level) {
                for (int i = level + 1; i < rlevel + 1; i++)
                    update[i] = header;
                level = rlevel;
            }
            Node n = createNode(key, rlevel);
            for (int i = 0; i <= rlevel; i++) {
                n.forward[i] = update[i].forward[i];
                update[i].forward[i] = n;
            }
            System.out.println("Successfully Inserted key " + key);
        }
    }

    void displayList() {
        System.out.println("\n*****Skip List*****");
        for (int i = 0; i <= level; i++) {
            Node node = header.forward[i];
            System.out.print("Level " + i + ": ");
            while (node != null) {
                System.out.print(node.key + " ");
                node = node.forward[i];
            }
            System.out.println();
        }
    }

    void deleteElement(int key) {
        Node current = header;
        Node[] update = new Node[MAXLVL + 1];
        for (int i = level; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].key < key)
                current = current.forward[i];
            update[i] = current;
        }
        current = current.forward[0];
        if (current != null && current.key == key) {
            for (int i = 0; i <= level; i++) {
                if (update[i].forward[i] != current)
                    break;
                update[i].forward[i] = current.forward[i];
            }
            while (level > 0 && header.forward[level] == null) {
                level--;
            }
            System.out.println("Successfully deleted key " + key);
        }
    }

    void searchElement(int key) {
        Node current = header;
        for (int i = level; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].key < key) {
                current = current.forward[i];
            }
        }
        current = current.forward[0];
        if (current != null && current.key == key) {
            System.out.println("Key found");
        } else {
            System.out.println("Key not found");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SkipList lst = new SkipList(3, 0.5f);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMENU:");
            System.out.println("1. Insert an element");
            System.out.println("2. Delete an element");
            System.out.println("3. Search for an element");
            System.out.println("4. Display list");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the element to be inserted: ");
                    int insertKey = scanner.nextInt();
                    lst.insertElement(insertKey);
                    break;
                case 2:
                    System.out.print("Enter the element to be deleted: ");
                    int deleteKey = scanner.nextInt();
                    lst.deleteElement(deleteKey);
                    break;
                case 3:
                    System.out.print("Enter the element to be searched: ");
                    int searchKey = scanner.nextInt();
                    lst.searchElement(searchKey);
                    break;
                case 4:
                    lst.displayList();
                    break;
                case 5:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }
}
