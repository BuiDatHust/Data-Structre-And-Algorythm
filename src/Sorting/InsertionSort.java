package Sorting;

public class InsertionSort {
    public static void solve(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int j = i;
            int newMember = a[i];
            while (newMember < a[j - 1] & i > 0) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = newMember;
        }
    }
}
