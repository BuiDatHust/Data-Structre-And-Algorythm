package Sorting;

public class QuickSort {
    public static void solve(int[] a, int l, int r) {
        if (l < r) {
            int pivotIndex = partition(a, l, r);

            solve(a, pivotIndex + 1, r);
            solve(a, l, pivotIndex - 1);
        }
    }

    public static int partition(int[] a, int l, int r) {
        int pivotIndex = r;
        int pivotValue = a[pivotIndex];
        int i = l - 1;

        for (int j = l; j < r; j++) {
            if (a[j] < pivotValue) {
                i++;
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }

        i++;
        int temp = a[i];
        a[i] = pivotValue;
        a[pivotIndex] = temp;
        return i;
    }
}
