package Greedy;

import java.util.Arrays;
import java.util.Comparator;

public class Compare {
    static void compare(Activity arr[]){
        Arrays.sort(arr, new Comparator<Activity>() {
            @Override
            public int compare(Activity o1, Activity o2) {
                return o1.finish - o2.finish;
            }
        });
    }
}
