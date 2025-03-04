import java.util.Scanner;
public class Combination {
    static Node head = null;
    static Node tail = null;
    int size, i, j, temp;

    static class Node {
        int data;
        Node next;
        public Node(int data){
            this.data = data;
            this.next = null;
        }
    }

    public static void bubbleSortIncremental(){
        Node current = head, index=null;
        int temp;
        if (head == null) {
            return;
        }else{
            while (current != null) {
                index = current.next;
                while (index != null) {
                    if (current.data > index.data) {
                        temp = current.data;
                        current.data = index.data;
                        index.data = temp;
                    }
                    index = index.next;
                }
                current = current.next;
            }
        }
    }

    public static void bubbleSortDecremental(){
        Node current = head, index = null;
        int temp;
        if (head == null) {
            return;
        }else{
            while (current != null) {
                index = current.next;
                while (index != null) {
                    if (current.data < index.data) {
                        temp = current.data;
                        current.data = index.data;
                        index.data = temp;
                    }
                    index = index.next;
                }
                current = current.next;
            }
        }
    }

    void insert(int data){
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        }else{
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void display(){
        Node current = head;
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        i = 0;
        while (current != null) {
            System.out.println("arr["+i+"] = "+current.data + " ");
            current = current.next;
            i++;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int size, i;
        Combination list = new Combination();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("");
            System.out.println("1. Insert Record");
            System.out.println("2. Show Record");
            System.out.println("3. Descending Order");
            System.out.println("4. Ascending Order");
            System.out.println();
            System.out.print("Enter your choice: ");
            int choice  = sc.nextInt();
            System.out.println();
            switch (choice) {
                case 1:
                    System.out.println("Insert Record");
                    System.out.print("Array size: ");
                    size = sc.nextInt();
                    System.out.println();
                    System.out.println("Enter Elements----");
                    for (i = 0; i < size; i++) {
                        System.out.print("arr["+i+"] = ");
                        list.insert(sc.nextInt());
                    }
                    break;
                case 2:
                    System.out.println("Display Data");
                    list.display();
                    break;
                case 3:
                    System.out.println("Descending Order");
                    bubbleSortDecremental();
                    list.display();
                    break;
                case 4:
                    System.out.println("Descending Order");
                    bubbleSortIncremental();
                    list.display();
                    break;
                default:
                    System.exit(0);
                    System.out.println("Please select a valid choice");
                    break;
            }
        }
        
    }
}
