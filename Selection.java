import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Selection {
    public static void main(String[] args) throws FileNotFoundException {
        // Import scanned value into array
        int[] array = scanner();
        ask(array);
    }

    public static int[] scanner() throws FileNotFoundException {
        File file = new File("numbers.txt");
        Scanner scan = new Scanner(file);

        // Count the number of line
        int count = 0;
        while (scan.hasNextInt()) {
            scan.nextInt();
            count++;
        }
        // Close scanner
        scan.close();

        // Import number into array
        scan = new Scanner(file);
        int[] Number = new int[count];

        for (int i = 0; i < count; i++) {
            Number[i] = scan.nextInt();
        }
        scan.close();
        return Number;
    }
    public static void ask(int[] array) {
        Scanner user = new Scanner(System.in);
        System.out.println("Choose an option: ");
        System.out.println("1. Selection Sort");
        System.out.println("2. Insetion Sort");
        String choose = user.nextLine();
        switch (choose) {
            case "1":
                int[] NewArray = selectionSort(array);
                //System.out.println(Arrays.toString(NewArray));   Testing
                int result = BinarySearch(NewArray, 7586);
                System.out.println("Value in position 7586 is " + result);
                break;
            case "2":
                int[] NewArray2 = insertionSort(array);
                //System.out.println(Arrays.toString(NewArray2));   Testing
                int result2 = BinarySearch(NewArray2, 7586);
                System.out.println("Value in position 7586 is " + result2);
                break;
            default:
                System.out.println("Invalid input");
                break;
        }

    }

    private static int[] selectionSort(int[] array) {
        // Get the length of an array
        int length = array.length;

        // Going through the array
        for (int i = 0; i < length - 1; i++) {
            // Assume the current value as the minimum
            int min = array[i];
            int minIndex = i;

            // Find the minimum value in the unsorted part of the array
            for (int j = i + 1; j < length; j++) {
                if (array[j] < min) {
                    // Update the minimum and its index if a smaller value is found
                    min = array[j];
                    minIndex = j;
                }
            }
            // Swap the current value with the minimum element found
            swap(array, i, minIndex);
        }
        // Return the sorted array
        return array;
    }
    private static void swap(int[] array, int a, int b) {
        // Swap the values at positions a and b in the array
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    private static int[] insertionSort(int[] array) {
        // Get the length of the array
        int length = array.length;

        // Iterate through the array starting from the second value
        for (int i = 1; i < length; i++) {
            // Store the current value to be inserted
            int current = array[i];

            // Initialize the pointer to the value just before the current value
            int j = i - 1;

            // Shift values greater than current to the right until finding the correct position for current value
            while (j >= 0 && array[j] > current) {
                array[j + 1] = array[j];
                j--; // Move the pointer to the left
            }
            // Insert current value into its correct position
            array[j + 1] = current;
        }
        // Return the sorted array
        return array;
    }

    private static int BinarySearch(int[] NewArray, int TargetPosition) {
        // Initialize the lowest and highest point
        int low = 0;
        int high = NewArray.length - 1;

        while (low <= high) {
            // Calculate the middle point
            int midPos = (low + high) / 2;

            // Check if the middle point is the target
            if (midPos == TargetPosition) {
                return NewArray[midPos];
            }

            // If the target is smaller than the middle point then search in the left half
            if (TargetPosition < midPos) {
                high = midPos - 1;
            } else { // If the target is larger than the middle point then search in the right half
                low = midPos + 1;
            }
        }
        // If not found, return -1
        return -1;
    }
}