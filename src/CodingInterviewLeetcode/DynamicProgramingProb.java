package CodingInterviewLeetcode;

import java.util.*;

public class DynamicProgramingProb {
    //    Climbing Stairs, top-down approach, time:O(n), space: O(1)
    public static int optimizeClimbStairs(int n) {
        int curr = 0;
        int prev1 = 1;
        int prev2 = 2;

        if (n == 1) {
            return prev1;
        }
        if (n == 2) {
            return prev2;
        }
        for (int i = 3; i <= n; i++) {
            curr = prev1 + prev2;
            prev1 = prev2;
            prev2 = curr;
        }

        return curr;
    }

    //    Climbing Stairs, bottom-up approach, time:O(n), space: O(n)
    public static int climbStairs(int n, HashMap<Integer, Integer> memo) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 0;
        }

        int value = climbStairs(n - 1, memo) + climbStairs(n - 2, memo);
        memo.put(n, value);

        return value;
    }

    //    Maximum Subarray, time: O(n), space: O(1), idea: global maxium is max all local maxium
    public static int maxSubArray(int[] nums) {
        int localMaximum = nums[0];
        int globalMaximum = localMaximum;

        for (int i = 1; i < nums.length; i++) {
            localMaximum = Math.max(localMaximum + nums[i], nums[i]); // local maxium with subarray has end index is i
            if (localMaximum > globalMaximum) { // global maxium is max of all local maxium above
                globalMaximum = localMaximum;
            }
        }

        return globalMaximum;
    }

    //    House Robber, time: O(n^2), space: O(n)
    public static int rob(int[] nums) {
        int[] memo = new int[nums.length + 1];
        return robSolving(nums, nums.length - 1, memo);
    }

    public static int robSolving(int[] nums, int n, int[] memo) {
        if (n < 0) {
            return 0;
        }
        if (n == 0) {
            return nums[0];
        }
        if (memo[n] != 0) {
            return memo[n];
        }

        int pick = nums[n] + robSolving(nums, n - 2, memo);
        int notPick = robSolving(nums, n - 1, memo);
        int robAtN = Math.max(pick, notPick);
        memo[n] = robAtN;

        return robAtN;
    }

    //    time: O(n), space: O(n)
    public static int optimizeRob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[0] = nums[0];
        if (n == 1) {
            return dp[0];
        }
        dp[1] = Math.max(nums[0], nums[1]);

        if (n == 2) {
            return dp[1];
        }
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[n - 1];
    }

    //   bottom-up approach, Longest Palindromic Substring, time: O(n^2), space: O(n^2)
    public static String longestPalindrome(String s) {
        int start = 0;
        int maxPalLen = 1;
        int n = s.length();
        boolean[][] dp = new boolean[n + 1][n + 1];

        // fill base case of table, with substring has length 1 and 2
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                maxPalLen = 2;
            }
        }
        // fill table cac vi tri tuong ung substring >2, k=3 beacuse function substring get from start to end-1
        for (int k = 3; k <= n; k++) {
            // fill table tat cac vi tri tuong ung voi substring co length k-1
            for (int i = 0; i < n - k + 1; i++) {
                int j = i + k - 1;
                if (dp[i + 1][j - 1] == true && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;

                    if (k > maxPalLen) {
                        start = i;
                        maxPalLen = k;
                    }
                }
            }
        }

        return s.substring(start, start + maxPalLen);
    }

    // little optimize by reduce prepare base case dp and space store dp table, time: O(n^2), space: O(1)
    public static String optimizeLongestPalindrome(String s) {
        int maxLen = 1;
        String result = String.valueOf(s.charAt(0));

        for (int i = 0; i < s.length() - 1; i++) {
            // in each index has two case ,first case is substring palindrome with odd length, second case is even length
            result = find(s, i, i, maxLen, result);
            result = find(s, i, i + 1, result.length(), result);
        }

        return result;
    }

    public static String find(String s, int l, int r, int maxLen, String result) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            if (r - l + 1 > maxLen) {
                maxLen = r - l + 1;
                result = s.substring(l, r + 1);
            }
            l--;
            r++;
        }

        return result;
    }

    //    Generate Parentheses, time: O(2^n), space: O(n). idea is use backtrack algo to gen all case with constraint is open and close =n
    public static List<String> generateParenthesis(int n) {
        String[] str = new String[2 * n];
        List<String> result = new ArrayList<>();

        backtrack(str, result, 0, 0, n, 0);

        return result;
    }

    public static void backtrack(String[] str, List<String> result, int open, int close, int n, int pos) {
        if (open == n && close == n) {
            result.add(String.join("", str));
        }
        if (open < n) {
            str[pos] = "(";
            backtrack(str, result, open + 1, close, n, pos + 1);
        }
        if (close < open) {
            str[pos] = ")";
            backtrack(str, result, open, close + 1, n, pos + 1);
        }
    }

    //    Maximal Square, time: O(m*n), space:O(m*n), idea is equation: dp[i][j]=1+min(dp[i-1][j],dp[i][j-1],dp[i-1][j-1])
    //    dp is array store max square has vertex bottom is  (i,j)
    public static int maximalSquare(char[][] matrix) {
        int m = matrix[0].length;
        int n = matrix.length;
        int maxSquare = 0;
        int[][] dp = new int[n + 1][m + 1];

        // fill base case in first column and row of table
        for (int i = 0; i < m; i++) {
            dp[0][i] = Integer.parseInt(String.valueOf(matrix[0][i]));
            if (dp[0][i] > maxSquare) {
                maxSquare = dp[0][i];
            }
        }
        for (int i = 0; i < n; i++) {
            dp[i][0] = Integer.parseInt(String.valueOf(matrix[i][0]));
            if (dp[i][0] > maxSquare) {
                maxSquare = dp[i][0];
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                    if (dp[i][j] > maxSquare) {
                        maxSquare = dp[i][j];
                    }
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        return maxSquare * maxSquare;
    }

    public int maxProduct(int[] nums) {
        int n = nums.length;
        int[] currentMinProduct = new int[n];
        int[] currentMaxProduct = new int[n];

        currentMinProduct[0] = nums[0];
        currentMaxProduct[0] = nums[0];
        int max = nums[0];

        for (int i = 1; i < n; i++) {
            currentMinProduct[i] = Math.min(nums[i], Math.min(currentMinProduct[i - 1] * nums[i], currentMaxProduct[i - 1] * nums[i]));
            currentMaxProduct[i] = Math.max(nums[i], Math.max(currentMinProduct[i - 1] * nums[i], currentMaxProduct[i - 1] * nums[i]));
            max = Math.max(max, currentMaxProduct[i]);
        }

        return max;
    }

    //    Decode Ways, time: O(n), space: O(n)
    public int numDecodings(String s) {
        Map<Integer, Integer> memo = new HashMap<>();

        return numDecodingRecur(s, 0, memo);
    }

    public int numDecodingRecur(String s, int index, Map<Integer, Integer> memo) {
        if (index == s.length()) {
            return 1;
        }
        if (s.charAt(index) == '0') {
            return 0;
        }
        if (memo.containsKey(index)) {
            return memo.get(index);
        }

        int sum = 0;
        sum += numDecodingRecur(s, index + 1, memo);
        if (index < s.length() - 1) {
            int x = Integer.parseInt(s.substring(index, index + 2));
            if (x <= 26) {
                sum += numDecodingRecur(s, index + 2, memo);
            }
        }
        memo.put(index, sum);

        return sum;
    }

    //    optimize by dp, time: O(n)
    public int optimizeNumDecoding(String s) {
        int sum = 1;
        if (s.charAt(0) == '0') {
            return 0;
        }
        char lastDigit = s.charAt(0);

        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '0' && lastDigit > '2') {
                return 0;
            }
            if (lastDigit >= '1' && lastDigit <= '2' && (lastDigit != '2' || c <= '6')) {
                System.out.println(c);
                if (c != '0') {
                    sum++;
                }
            }
            lastDigit = s.charAt(i);
        }

        return sum;
    }


    public static void main(String[] args) {
        char[][] matrix = {{'0', '1', '1'}, {'1', '1', '1'}, {'1', '1', '1'}, {'1', '1', '1'}, {'1', '1', '1'}, {'1', '1', '1'}};
        int[] nums = {2, 3, -2, 4};
        DynamicProgramingProb dpb = new DynamicProgramingProb();
        System.out.println(dpb.maxProduct(nums));
    }
}
