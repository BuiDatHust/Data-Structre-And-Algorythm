package BitManupalation;

import java.util.ArrayList;
import java.util.List;

public class BitProb {
    //    Find the Duplicate Number, time: O(n), space:O(1)
    public int findDuplicate(int[] nums) {
        int n = nums.length;

        for (int num : nums) {
            nums[num % n] += n;
        }

        for (int i = 0; i < n + 1; i++) {
            if (nums[i] >= 2 * n) {
                return i;
            }
        }

        return 0;
    }

    public String addBinary(String a, String b) {
        String result = "";
        a = a.length() > b.length() ? a : b;
        b = a.length() < b.length() ? a : b;
        int memo = 0;

        int i = b.length() - 1;
        int j = a.length() - 1;
        while (i >= 0) {
            char c1 = a.charAt(j);
            char c2 = b.charAt(i);

            if (memo == 0) {
                if (c1 == '1' && c2 == '1') {
                    result = "0" + result;
                    memo++;
                } else {
                    if (c1 == '1' || c2 == '1') {
                        result = "1" + result;
                    } else {
                        result = "0" + result;
                    }
                }
            } else {
                if (c1 == '1' && c2 == '1') {
                    result = "0" + result;
                } else {
                    if (c1 == '1' || c2 == '1') {
                        result = "1" + result;
                    } else {
                        result = "0" + result;
                        memo--;
                    }
                }
            }

            i--;
            j--;
        }
        while (j >= 0) {
            char c = a.charAt(j);
            if (memo > 0) {
                if (c == '0') {
                    result = "1" + result;
                    memo--;
                } else {
                    result = "0" + result;
                }
            } else {
                result = String.valueOf(c) + result;
            }
            j--;
        }
        System.out.println(memo);
        while(memo>0){
            result = '1'+ result;
            memo--;
        }

        return result;
    }

    public boolean isPowerOfFour(int n) {
        int temp=n;
        int x=0;

        while(temp>0){
            temp = temp/4;
            x++;
        }

        return n==Math.pow(4,x-1);
    }

    public static void main(String[] args) {
        BitProb bp = new BitProb();
        int[] nums = {1, 1};
        System.out.println(bp.isPowerOfFour(1));
    }
}
