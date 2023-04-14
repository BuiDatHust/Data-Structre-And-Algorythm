package Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class main {
    public static int fourthBit(int number) {
        // Write your code here

        return (number & (1<<3));
    }

    public static long getMaxProfit(List<Integer> pnl, int k) {
        // Write your code here
        long max = 0;

        for (int i = 1; i <= k; i++) {
            max = Math.max(getMaxKProfit(pnl, i), max);
        }

        return max;
    }

    public static long getMaxKProfit(List<Integer> pnl, int k) {
        int n = pnl.size();
        long max = 0;
        long sum = 0;

        for (int i = 0; i < k; i++) {
            sum += pnl.get(i);
            max = Math.max(sum, max);
        }
        for (int i = 1; i < n - k + 1; i++) {
            sum = sum - pnl.get(i - 1) + pnl.get(i + k - 1);
            max = Math.max(sum, max);
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(fourthBit(8));
    }
}
