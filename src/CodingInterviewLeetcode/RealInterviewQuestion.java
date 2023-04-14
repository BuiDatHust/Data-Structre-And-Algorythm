package CodingInterviewLeetcode;

import java.text.DecimalFormat;
import java.util.*;

public class RealInterviewQuestion {
    public static int count = 0;

    public static int fib(int n) {
        int prev1 = 0;
        int prev2 = 1;
        int curr = 0;

        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        for (int i = 2; i <= n; i++) {
            curr = prev1 + prev2;
            prev1 = prev2;
            prev2 = curr;
        }

        return curr;
    }

    public static int distributeCandies(int[] candyType) {
        int n = candyType.length;
        int maxCandy = n / 2;
        int totalType = 0;
        HashMap<Integer, Boolean> dict = new HashMap<>();

        for (int type : candyType) {
            if (!dict.containsKey(type)) {
                totalType++;
                if (totalType > maxCandy) {
                    return maxCandy;
                }
                dict.put(type, true);
            }
        }

        return totalType;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderTraversalRecur(root, result);

        return result;
    }

    public void inorderTraversalRecur(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inorderTraversalRecur(root.left, result);
        result.add(root.val);
        inorderTraversalRecur(root.right, result);
    }

    //    Sqrt(x), time: O(logn), space: O(1)
    public static int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }
        int l = 0;
        int r = x;

        while (l < r) {
            long mid = (r - l) / 2 + l;
            long powMid = mid * mid;

            if (powMid == x || (powMid < x && (mid + 1) * (mid + 1) > x)) {
                return (int) mid;
            }
            if (powMid > x && (mid - 1) * (mid - 1) <= x) {
                return (int) (mid - 1);
            }

            if (powMid > x) {
                r = (int) (mid - 1);
            } else {
                l = (int) (mid + 1);
            }
        }

        return 0;
    }

    public static boolean isHappy(int n) {
        int start = n;
        HashMap<Integer, Boolean> dict = new HashMap<>();

        while (true) {
            int sum = 0;
            while (start != 0) {
                int x = start % 10;
                sum += Math.pow(x, 2);
                start /= 10;
            }
            System.out.println(sum);
            if (sum == 1) {
                return true;
            }
            if (dict.containsKey(sum)) {
                return false;
            }
            start = sum;
            dict.put(start, true);
        }
    }

    //    use two pointer to decrease space ,idea is has cycle when slow=fast
    public static boolean optimizeIsHappy(int n) {
        int slow = n;
        int fast = getNext(n);
        while (fast != 1 && slow != fast) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
            System.out.println(slow + " " + fast);
        }
        return fast == 1;
    }

    public static int getNext(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }

    //    Jump Game, recursion with memolize, time: O(n^2), space: O(n)
    public static boolean canJump(int[] nums) {
        Map<Integer, Boolean> memo = new HashMap<>();
        return jumpRecur(nums, 0, memo);
    }

    public static boolean jumpRecur(int[] nums, int index, Map<Integer, Boolean> memo) {
        int n = nums.length;
        if (index == n - 1) {
            return true;
        }
        if (nums[index] == 0 || index >= n) {
            return false;
        }
        if (memo.containsKey(index)) {
            return memo.get(index);
        }

        for (int i = 1; i <= nums[index]; i++) {
            if (index + i >= n - 1) {
                return true;
            }
            boolean canJump = jumpRecur(nums, index + i, memo);
            memo.put(index + i, canJump);
            if (canJump) {
                return true;
            }
        }

        return false;
    }

    //    use greedy to optimize, each step choose index nearest can reach before index, time: O(n), space: O(1)
    public static boolean canJump1(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return true;
        }
        int goal = n - 1;

        for (int i = n - 2; i >= 0; i--) {
            if (i + nums[i] >= goal) {
                goal = i;
            }
        }

        return goal == 0 ? true : false;
    }

    //    Perfect Squares, time:O(n*can(n)), space: O(n)
    public static int numSquares(int n) {
        int max = (int) Math.floor(Math.sqrt(n));
        if (max * max == n) {
            return 1;
        }
        Map<Integer, Integer> memo = new HashMap<>();

        return numSquareRecur(n, max, 0, memo);
    }

    public static int numSquareRecur(int sum, int n, int step, Map<Integer, Integer> memo) {
        if (sum < 0) {
            return Integer.MAX_VALUE;
        }
        if (sum == 0) {
            return step;
        }
        if (memo.containsKey(sum)) {
            return memo.get(sum);
        }

        int min = Integer.MAX_VALUE;
        for (int i = n; i >= 1; i--) {
            count++;
            int val = numSquareRecur(sum - i * i, n, step + 1, memo);
            memo.put(sum - i * i, val);
            if (min > val) {
                min = val;
            }
        }

        return min;
    }

    //    use dp with equation from prediction, time:O(n*can(n)), space: O(n)
    public static int optimizeNumSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i < n + 1; i++) {
            int min = Integer.MAX_VALUE;

            for (int j = 1; j * j <= i; j++) {
                int rem = i - j * j;
                if (dp[rem] < min) {
                    min = dp[rem];
                }
            }

            dp[i] = min + 1;
        }

        return dp[n];
    }

    public static int missingNumber(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] > 1) {
                return nums[i] - 1;
            }
        }

        return nums[0] == 0 ? n : 0;
    }

    public static int optimizeMissingNumber(int[] nums) {
        int n = nums.length;
        int sum = n * (n + 1) / 2;

        for (int num : nums) {
            sum -= num;
        }

        return sum == 0 ? 0 : sum;
    }

    public static double myPow(double x, int n) {
        boolean isNegative = n < 0 ? true : false;
        double value = myPowRecur(x, Math.abs(n));
        double res = isNegative ? 1 / value : value;

        return Double.parseDouble(String.format("%,.5f", res));
    }

    public static double myPowRecur(double x, int n) {
        if (n == 0) {
            return 1;
        }

        double temp = myPowRecur(x, n / 2);
        if (n % 2 == 0) {
            return temp * temp;
        } else {
            return temp * temp * x;
        }
    }

    //    3Sum, time: O(n^2), space: O(n)
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Map<String, Boolean> isExist = new HashMap<>();
        int n = nums.length;
        Arrays.sort(nums);
        for (int num : nums) {
            System.out.println(num);
        }

        for (int i = 0; i < n - 2; i++) {
            Map<Integer, Boolean> s = new HashMap<>(); // in every loop from i we have a set save value from i->j and k is searched in this range so i!=j!=k
            for (int j = i + 1; j < n; j++) {
                int rest = -nums[i] - nums[j];
                if (s.containsKey(rest) && !isExist.containsKey(nums[i] + " " + nums[j])) {
                    isExist.put(nums[i] + " " + nums[j], true);
                    result.add(new ArrayList<>(List.of(nums[i], nums[j], rest)));
                }
                s.put(nums[j], true);
            }
        }

        return result;
    }

    //    use radix sort, time: O(3*n), space: O(n)
    public static void sortColors(int[] nums) {
        int[] bucket = new int[3];

        for (int num : nums) {
            bucket[num]++;
        }
        int i = 0, j = 0;
        while (i < 3) {
            while (bucket[i] > 0) {
                nums[j] = i;
                j++;
                bucket[i]--;
            }
            i++;
        }
    }

    //    space: O(1)
    public static void optimizeSortColors(int[] nums) {
        int zero = 0, two = 0, one = 0;
        int i = 0;

        for (int num : nums) {
            if (num == 0) {
                zero++;
            }
            if (num == 1) {
                one++;
            }
            if (num == 2) {
                two++;
            }
        }

        while (zero > 0) {
            nums[i] = 0;
            i++;
            zero--;
        }
        while (one > 0) {
            nums[i] = 1;
            i++;
            one--;
        }
        while (two > 0) {
            nums[i] = 2;
            i++;
            two--;
        }
    }

    //    Top K Frequent Elements, time: O(n), space: O(n)
    public static int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        Map<Integer, Integer> dict = new HashMap<>();

        for (int num : nums) {
            dict.putIfAbsent(num, 0);
            int frequency = dict.get(num);
            dict.put(num, frequency + 1);
        }

        List<Integer>[] sortFrequency = new List[nums.length + 1];
        for (Map.Entry<Integer, Integer> entry : dict.entrySet()) {
            int value = entry.getValue();
            int key = entry.getKey();

            if (sortFrequency[value] != null) {
                List<Integer> l = sortFrequency[value];
                l.add(key);
                sortFrequency[value] = l;
            } else {
                sortFrequency[value] = new ArrayList<>(List.of(key));
            }
        }

        int j = 0;
        for (int i = sortFrequency.length - 1; i >= 0; i--) {
            List<Integer> l = sortFrequency[i];
            if (l != null && j < k) {
                for (Integer str : l) {
                    if (j < k) {
                        result[j] = str;
                        j++;
                    } else {
                        break;
                    }
                }
            }
        }

        return result;
    }

    //    Kth Largest Element in an Array, time: O(n), space: O(n), we can use quick sort to optimize space but trade off to time is not efficient
    public static int findKthLargest(int[] nums, int k) {
        int sum = k;
        int countPositive = 0;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(max, Math.abs(num));
        }
        int bucketPositive[] = new int[max + 1];
        int bucketNegative[] = new int[max + 1];

        for (int num : nums) {
            if (num >= 0) {
                bucketPositive[num]++;
                countPositive++;
            } else {
                bucketNegative[Math.abs(num)]++;
            }
        }

        if (countPositive >= k) {
            for (int i = bucketPositive.length - 1; i >= 0; i--) {
                if (bucketPositive[i] != 0) {
                    sum -= bucketPositive[i];
                    if (sum <= 0) {
                        return i;
                    }
                }
            }
        } else {
            sum -= countPositive;
            for (int i = 0; i < bucketNegative.length; i++) {
                if (bucketNegative[i] != 0) {
                    sum -= bucketNegative[i];
                    if (sum <= 0) {
                        return -i;
                    }
                }
            }
        }

        return -1;
    }

    //    Find Peak Element, time: O(logn), space: O(1)
    public static int findPeakElement(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        int res = -1;

        if (n == 1) {
            return 0;
        }
        while (l <= r) {
            int mid = (r - l) / 2 + l;

            if ((mid == 0 && nums[mid] > nums[mid + 1]) || (mid == n - 1 && nums[mid] > nums[mid - 1]) || (mid > 0 && mid < n - 1 && nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1])) {
                return mid;
            }
            if (mid == 0 || (nums[mid] > nums[mid - 1] && nums[mid] < nums[mid + 1])) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        System.gc();
        return res;
    }

    // a more clear solution
    public static int optimizeFindPeakElement(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;

        while (l < r) {
            int mid = (r - l) / 2 + l;

            if (nums[l] > nums[l + 1]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

    //    Unique Paths, time: O(m*n), space: O(m*n)
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    //    Coin Change, time: O(n*k), space: O(k) , n is length of coins and k is amount,
//    dp equation is dp[i] = 1+ min(dp[i-coins[j]) with dp[i] is min combination to create i from coins
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];

        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        for (int coin : coins) {
            if (coin <= amount) {
                dp[coin] = 1;
            }
        }

        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i - coin >= 0) {
                    min = Math.min(min, dp[i - coin]);
                }
            }
            if (min == Integer.MAX_VALUE) {
                dp[i] = Integer.MAX_VALUE;
            } else {
                dp[i] = 1 + min;
            }
        }

        if (dp[amount] == Integer.MAX_VALUE) {
            return -1;
        }
        return dp[amount];
    }

    //    Longest Increasing Subsequence, time: O(n^2), space: O(n)
//    equation dp: dp[i] = 1+ max(dp[j]), j in range(0,i) and nums[j]<nums[i]
//    if has not j , dp[i] =1
//    hard case: if nums[i] < min nums[j] in range(0,i) then start new sub sequence in i mean that dp[i]=1
    public static int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    max = Math.max(dp[j], max);
                }
            }

            dp[i] = 1 + max;
            maxLen = Math.max(dp[i], maxLen);
        }

        return maxLen;
    }

//  Longest Substring Palindrome, time: O(n^2), space: O(n^2)
    public static String longestPalindrome(String s) {
        int max = 1;
        String result = String.valueOf(s.charAt(0));
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                if (max < 2) {
                    max = 2;
                    result = s.substring(i, i + 2);
                }
            }
        }

        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                if (dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                    if (j - i + 1 > max) {
                        max = j - i + 1;
                        result = s.substring(i, j + 1);
                    }
                }
            }
        }
        return result;
    }

//    Add Two Numbers, time: O(m+n), space: O(1)
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode temp = result;
        int memo = 0;

        while(l1!=null && l2!=null){
            int val1 = l1.val;
            int val2 = l2.val;
            int val = val1+val2+memo;

            temp.next = new ListNode(val%10);
            temp = temp.next;
            memo= val/10;

            l1=l1.next;
            l2=l2.next;
        }

        while(l1!=null){
            int val = l1.val+memo;
            temp.next = new ListNode(val%10);
            temp = temp.next;
            memo= val/10;
            l1= l1.next;
        }
        while(l2!=null){
            int val = l2.val+memo;
            temp.next = new ListNode(val%10);
            temp = temp.next;
            memo= val/10;
            l2= l2.next;
        }
        if(memo==1){
            temp.next = new ListNode(1,null);
        }

        return result.next;
    }


    public static void main(String[] args) {
        int[] nums = {1, 3, 6, 7, 9, 4, 10, 5, 6};
        ListNode l1 = new ListNode(2,new ListNode(4,new ListNode(3)));
        ListNode l2 = new ListNode(5,new ListNode(6,new ListNode(4)));

        System.out.println();
    }
}
