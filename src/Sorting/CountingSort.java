package Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CountingSort {
    public static List<Integer> a = new ArrayList<>(Arrays.asList(1, 3, 4, 1, 7, 20, 5, 3, 7, 7));

    public static void solve() {
        int min = Collections.min(a);
        int max = Collections.max(a);
        int count[] = new int[max + a.size() + 1];
        int index = 0;

        for (int i = min; i <= max; i++) {
            count[i] = 0;
        }
        for (Integer ele : a) {
            count[ele] += 1;
        }

        for (int i = min; i <= max; i++) {
            int value = count[i];
            while (value > 0) {
                a.set(index, i);
                index++;
                value--;
            }
        }
        Main.displayArr(a);
    }
}
