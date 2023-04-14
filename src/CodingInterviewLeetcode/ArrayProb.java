package CodingInterviewLeetcode;

import java.util.*;

public class ArrayProb {
    public static void display(int[] nums) {
        for (int num : nums) {
            System.out.println(num);
        }
    }

    //    Remove Duplicates from Sorted Array, time: O(n), space: O(1)
    public static int removeDuplicates(int[] nums) {
        int currentIndex = 0;
        int i = 0;

        while (i < nums.length - 1) {
            int currentValue = nums[i];
            while (i < nums.length - 1 && nums[i + 1] == currentValue) {
                i++;
            }

            if (i < nums.length - 1) {
                currentIndex++;
                i++;
                nums[currentIndex] = nums[i];
            }
        }

        for (int j = currentIndex + 1; j < nums.length; j++) {
            nums[j] = 0;
        }

        return currentIndex + 1;
    }

    //  Best Time to Buy and Sell Stock II,  time: O(n), space: O(n)
    public static int maxProfit(int[] prices) {
        List<Integer> statisticArr = new ArrayList<>();
        int maxProfit = 0;
        int i = 1;

        statisticArr.add(prices[0]);
        while (i < prices.length) {
            if (prices[i] > prices[i - 1]) {
                while (i < prices.length && prices[i] > prices[i - 1]) {
                    i++;
                }
                statisticArr.add(prices[i - 1]);
                continue;
            }
            if (prices[i] < prices[i - 1]) {
                while (i < prices.length && prices[i] < prices[i - 1]) {
                    i++;
                }
                statisticArr.add(prices[i - 1]);
                continue;
            }
            i++;
        }

        for (int j = 0; j < statisticArr.size() - 1; j++) {
            if (statisticArr.get(j) < statisticArr.get(j + 1)) {
                maxProfit += statisticArr.get(j + 1) - statisticArr.get(j);
            }
        }

        return maxProfit;
    }

    //   time: O(n), space: O(1)
    public static int optimizeMaxProfit(int[] prices) {
        int maxProfit = 0;
        int sellingDate = 0;
        int buyingDate = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] >= prices[i - 1]) {
                sellingDate++;
            } else {
                maxProfit += prices[sellingDate] - prices[buyingDate];
                buyingDate = sellingDate = i;
            }
        }
        maxProfit += prices[sellingDate] - prices[buyingDate];

        return maxProfit;
    }

    //  Rotate Array, time: O(n), space: O(n)
    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        int realK = k % n;
        int[] subRotateArr = new int[realK];

        int j = 0;
        for (int i = n - realK; i < n; i++) {
            subRotateArr[j] = nums[i];
            j++;
        }
        for (int i = n - 1; i >= 0; i--) {
            if (i >= realK) {
                nums[i] = nums[i - realK];
            } else {
                nums[i] = subRotateArr[i];
            }
        }
    }

    // optimize space
    public static void optimizeRotate(int[] nums, int k) {
        int n = nums.length;
        int realK = k % n;

        reverse(nums, 0, n - 1);
        reverse(nums, 0, realK - 1);
        reverse(nums, realK, n - 1);
    }

    public static void reverse(int[] nums, int l, int r) {
        while (l < r) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
            l++;
            r--;
        }
    }

    //    Contains Duplicate, time:O(n), space: O(n)
    public static boolean containsDuplicate(int[] nums) {
        Map<Integer, Integer> dict = new HashMap<Integer, Integer>();

        for (int num : nums) {
            if (dict.containsKey(num) == true) {
                if (dict.get(num) == 1) {
                    return true;
                }
                dict.put(num, dict.get(num) + 1);
            } else {
                dict.put(num, 1);
            }
        }

        return false;
    }

    //    Single Number, time: O(n),space: O(n)
    public static int singleNumber(int[] nums) {
        Map<Integer, Integer> dict = new HashMap<Integer, Integer>();

        for (int num : nums) {
            if (dict.containsKey(num) == true) {
                dict.put(num, dict.get(num) + 1);
            } else {
                dict.put(num, 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : dict.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
            ;
        }

        return nums[0];
    }

    //    time: O(n),space: O(1)
    public static int optimizeSingleNumber(int[] nums) {
        int n = nums.length;
        int result = nums[0];

        for (int i = 1; i < n; i++) {
            result ^= nums[i];
        }

        return result;
    }

    //    Intersection of Two Arrays II, time: O(m+n), space: O(m+n)
    public static int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> dict1 = new HashMap<Integer, Integer>();
        Map<Integer, Integer> dict2 = new HashMap<Integer, Integer>();
        ArrayList<Integer> res = new ArrayList<>();

        for (int num : nums1) {
            if (dict1.containsKey(num) == true) {
                dict1.put(num, dict1.get(num) + 1);
            } else {
                dict1.put(num, 1);
            }
        }
        for (int num : nums2) {
            if (dict2.containsKey(num) == true) {
                dict2.put(num, dict2.get(num) + 1);
            } else {
                dict2.put(num, 1);
            }
        }

        int i = 0;
        if (nums1.length < nums2.length) {
            for (int num : nums1) {
                if (dict2.containsKey(num) == true) {
                    int val = dict2.get(num);
                    if (val > 0) {
                        res.add(num);
                        i++;
                        dict2.put(num, val - 1);
                        System.out.println(num);
                    }
                }
            }
        } else {
            for (int num : nums2) {
                if (dict1.containsKey(num) == true) {
                    int val = dict1.get(num);
                    if (val > 0) {
                        res.add(num);
                        i++;
                        dict1.put(num, val - 1);
                        System.out.println(num);
                    }
                }
            }
        }

        int[] result = new int[i];
        for (int j = 0; j < res.size(); j++) {
            result[j] = res.get(j);
        }

        return result;
    }

    //    Two Sum, time: O(n), space:O(n)
    public static int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> dict = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            int x = target - nums[i];
            if (dict.containsKey(x)) {
                int index = dict.get(x);
                res[0] = x;
                res[1] = index;
                return res;
            }
            dict.put(nums[i], i);
        }

        return res;
    }

    //    Valid Sudoku, time:O(n^2), space: O(n^2), idea is create three list map to check valid in row col and box 3*3 in each loop
    public boolean isValidSudoku(char[][] board) {
        List<Map<String, Integer>> rows = new ArrayList<>();
        List<Map<String, Integer>> cols = new ArrayList<>();
        Map<String, Map<String, Integer>> boxs = new HashMap<>();

        for (int i = 0; i < 9; i++) {
            rows.add(new HashMap<>());
            cols.add(new HashMap<>());
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String key = i + "," + j;
                boxs.put(key, new HashMap<>());
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String c = String.valueOf(board[i][j]);
                if (c == ".") {
                    continue;
                }

                int ro = Math.floorDiv(i, 3);
                int co = Math.floorDiv(j, 3);
                String keyBox = co + "," + ro;
                if (rows.get(i).containsKey(c) || cols.get(j).containsKey(c) || boxs.get(keyBox).containsKey(c)) {
                    return false;
                }
                rows.get(i).put(c, 1);
                cols.get(j).put(c, 1);
                boxs.get(keyBox).put(c, 1);
            }
        }

        return true;
    }

    //    Plus One, time: O(n), space: O(n);
    public static int[] plusOne(int[] digits) {
        int memory = 1;
        int n = digits.length;

        for (int i = n - 1; i >= 0; i--) {
            int sum = digits[i] + memory;
            digits[i] = sum;
            if (sum == 10) {
                digits[i] = 0;
                memory = 1;
            } else {
                memory = 0;
            }
            System.out.println(digits[i]);
        }

        if (digits[0] == 0) {
            int[] res = new int[n + 1];
            res[0] = 1;
            for (int i = 0; i < n; i++) {
                res[i + 1] = digits[i];
            }
            return res;
        }


        return digits;
    }

    public static int[] optimizePlusOne(int[] digits) {
        int n = digits.length;

        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        int[] res = new int[n + 1];
        res[0] = 1;
        return res;
    }

    public static void moveZeroes(int[] nums) {
        int n = nums.length;
        int j = n;
        int i = 0;

        while (i < j) {
            if (nums[i] == 0) {
                j--;
                for (int k = i; k < j; k++) {
                    nums[k] = nums[k + 1];
                }
                nums[j] = 0;
                i--;
            }
            i++;
        }
    }

    public static void optimizeMoveZeroes(int[] nums) {
        int n = nums.length;
        int numOfNonZeros = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                nums[numOfNonZeros] = nums[i];
                numOfNonZeros++;
            }
        }
        for (int i = numOfNonZeros; i < n; i++) {
            nums[i] = 0;
        }
    }

    //    Valid Anagram,time: O(nlogn), space: O(1)
    public static String sortString(String inputString) {
        // Converting input string to character array
        char tempArray[] = inputString.toCharArray();

        // Sorting temp array using
        Arrays.sort(tempArray);

        // Returning new sorted string
        return new String(tempArray);
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        String sortedS = sortString(s);
        String sortedT = sortString(t);

        return sortedT.equals(sortedS);
    }

    //    time: O(n), space: O(n)
    public static boolean optimizeIsAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] alphas = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            alphas[ch - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            alphas[ch - 'a']--;
        }

        for (int i = 0; i < alphas.length; i++) {
            if (alphas[i] != 0) {
                return false;
            }
        }

        return true;
    }

//    Product of Array Except Self, time: O(n), space:O(n)
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] res = new int[n];

        left[0] = 1;
        right[n - 1] = 1;
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < n; i++) {
            res[i] = left[i] * right[i];
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 1, 0, -3, 3};
        int[] nums1 = {1, 2};
        int[] nums2 = {1, 1};
//        optimizeMoveZeroes(nums);
//        display(nums);
        System.out.println(productExceptSelf(nums));
    }
}
