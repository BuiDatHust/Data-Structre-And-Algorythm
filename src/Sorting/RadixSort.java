package Sorting;

public class RadixSort {
    public static void solve(int[] a) {
        int n = a.length;
        int[] bucket = new int[10];
        int max = Integer.MIN_VALUE;
        int[] output = new int[n+10];
        int exp = 1;

        for (int i = 0; i < n; i++) {
            if (max < a[i]) {
                max = a[i];
            }
        }

        while (max / exp > 0) {
            for (int i = 0; i < n; i++) {
                bucket[a[i] / exp % 10]++;
            }
            for (int i = 1; i < 10; i++) {
                bucket[i] += bucket[i - 1];
            }
            for (int i = n-1; i >=0; i--) {
                output[--bucket[a[i] / exp % 10]] = a[i];
            }
            for (int i = 0; i < n; i++) {
                a[i] = output[i];
            }

            exp *= 10;
        }
    }
}
