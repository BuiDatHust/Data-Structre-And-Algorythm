package Sorting;

import java.util.List;

public class Main {
    public static void displayArr(int[] a) {
        for (int element : a) {
            System.out.println(element);
        }
    }

    public static void displayArr(List<Integer> a) {
        for (int element : a) {
            System.out.println(element);
        }
    }

    public static void main(String[] args) {
        int a[] = {1, 2, 2, 40, 8, 1, 19};
        a = new MergeSort().mergeSort(a);
        displayArr(a);
    }
}
