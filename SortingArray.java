import java.util.Scanner;

public class SortingArray {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        boolean status = true;
        do {
            System.out.println("Choose your sorting method: ");
            System.out.println("1. Bubble Sort");
            System.out.println("2. Selection Sort");
            System.out.println("3. Insertion Sort");
            System.out.println("4. Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt();
            System.out.println();
            System.out.print("Elements: ");
            int n = sc.nextInt();
            int[] array = new int[n];
            System.out.println();
            System.out.println("Insert Elements Value-----");
            for (int i = 0; i < n; i++) {
                System.out.print("Array['" + i + "'] = ");
                array[i] = sc.nextInt();
            }

            switch (choice) {
                case 1:
                    bubbleSort(array);
                    displayArray(array);
                    break;
                case 2:
                    selectionSort(array);
                    displayArray(array);
                    break;
                case 3:
                    insertionSort(array);
                    displayArray(array);
                    break;
                case 4:
                    status = false;
                    System.out.println("Exiting Program");
                    break;
                default:
                    System.out.println("Please select a valid choice");
            }
        } while (status);
        sc.close();
    }

    static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    static void displayArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
