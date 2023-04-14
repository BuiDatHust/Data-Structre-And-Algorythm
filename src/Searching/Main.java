package Searching;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static int binarySearch(ArrayList<Integer> arr, int target) {
        int low = 0;
        int high = arr.size() - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr.get(mid) == target) {
                return mid;
            }
            if (arr.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 8, 10));
        System.out.println(Main.binarySearch(arr, 5));
    }
}
