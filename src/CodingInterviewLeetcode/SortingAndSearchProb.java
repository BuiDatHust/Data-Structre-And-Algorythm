package CodingInterviewLeetcode;

import java.util.Arrays;

public class SortingAndSearchProb {
    //    First Bad Version, time: O(log(n)), space: O(1)
    public static int firstBadVersion(int n) {
        int l = 1;
        int r = n;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (isBadVersion(mid) == true && isBadVersion(mid - 1) == false) {
                return mid;
            }
            if (isBadVersion(mid) == true) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return -1;
    }

    public static boolean isBadVersion(int n) {
        if (n >= 2) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(firstBadVersion(2));
    }
}
