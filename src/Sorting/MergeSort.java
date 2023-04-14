package Sorting;

import java.util.Arrays;

public class MergeSort {
    public int[] mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }

        int mid = arr.length / 2;
        int[] leftArr = mergeSort(Arrays.copyOfRange(arr, 0, mid));
        int[] rightArr = mergeSort(Arrays.copyOfRange(arr, mid, arr.length));

        return merge(leftArr, rightArr);
    }

    public int[] merge(int[] leftArr, int[] rightArr) {
        int n1 = leftArr.length;
        int n2 = rightArr.length;
        int[] result = new int[n1 + n2];

        int i = 0;
        int j = 0;
        int k = 0;
        while (i < n1 && j < n2) {
            if (leftArr[i] < rightArr[j]) {
                result[k] = leftArr[i];
                i++;
            } else {
                result[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            result[k] = leftArr[i];
            i++;
            k++;
        }
        while (j < n2) {
            result[k] = rightArr[j];
            j++;
            k++;
        }

        return result;
    }
}
