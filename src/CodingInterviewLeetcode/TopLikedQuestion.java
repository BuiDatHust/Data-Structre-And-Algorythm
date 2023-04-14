package CodingInterviewLeetcode;

import java.util.*;

public class TopLikedQuestion {
    //    Next Permutation, time: O(n), space:O(1)
    public static void nextPermutation(int[] nums) {
        int n = nums.length;

        for (int i = n - 1; i >= 1; i--) {
            if (nums[i] > nums[i - 1]) {
                int index = i;
                for (int j = i + 1; j < n; j++) {
                    if (nums[j] <= nums[i] && nums[j] > nums[i - 1]) {
                        index = j;
                    }
                    if (nums[j] >= nums[i - 1]) {
                        break;
                    }
                }

                swap(nums, index, i - 1);
                reverse(nums, i, n - 1);
                return;
            }
        }

        reverse(nums, 0, n - 1);
    }

    public static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void reverse(int[] nums, int a, int b) {
        while (a < b) {
            swap(nums, a, b);
            a++;
            b--;
        }
    }

    //    Rotting Oranges, time: O(m*n), space: O(m*n), use bfs to cover all fresh orange and count total level bfs tree which is min value
    public static int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int min = 0;
        int totalFresh = 0;
        boolean[][] visited = new boolean[m][n];
        Queue<List<Integer>> q = new LinkedList<>();
        int currentLevel = 0;
        int nextLevel = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    totalFresh++;
                }
                if (grid[i][j] == 2) {
                    q.add(new ArrayList<>(List.of(i, j)));
                    visited[i][j] = true;
                    currentLevel++;
                }
            }
        }
        if (totalFresh == 0) {
            return 0;
        }

        int[] diArr = {-1, 1, 0, 0};
        int[] djArr = {0, 0, -1, 1};
        while (!q.isEmpty()) {
            List<Integer> top = q.poll();
            int i = top.get(0);
            int j = top.get(1);

            if (currentLevel == 0) {
                min++;
                currentLevel = nextLevel;
                nextLevel = 0;
            }
            currentLevel--;

            for (int k = 0; k < diArr.length; k++) {
                int di = diArr[k];
                int dj = djArr[k];

                if (i + di >= 0 && i + di < m && j + dj >= 0 && j + dj < n) {
                    if (grid[i + di][j + dj] == 1 && !visited[i + di][j + dj]) {
                        nextLevel++;
                        q.add(new ArrayList<>(List.of(i + di, j + dj)));
                        visited[i + di][j + dj] = true;
                        totalFresh--;
                    }
                }
            }
        }

        if (totalFresh > 0) {
            return -1;
        }

        return min;
    }

    //    Binary Tree Right Side View, time: O(n), space: O(n), n is total number of node
//    use bfs to loop level tree from right to left and the first element in every level is rightside node
    public List<Integer> rightSideView(TreeNode root) {
        TreeNode temp = root;
        List<Integer> result = new ArrayList<>();
        if (temp == null) {
            return result;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(temp);
        int currentLevel = 1;
        int nextLevel = 0;
        boolean isChangeLevel = true;

        while (!q.isEmpty()) {
            TreeNode top = q.poll();

            if (currentLevel == 0) {
                currentLevel = nextLevel;
                nextLevel = 0;
                isChangeLevel = true;
            }
            currentLevel--;
            if (nextLevel == 0 && isChangeLevel) {
                result.add(top.val);
                isChangeLevel = false;
            }

            if (top.right != null) {
                q.add(top.right);
                nextLevel++;
            }
            if (top.left != null) {
                q.add(top.left);
                nextLevel++;
            }
        }

        return result;
    }

    //    Subarray Sum Equals K, time: O(n), space:O(1).
//    use hash map with idea is sum(i+1,j)= sum(0,j)-sum(0,i)
//    maintain currSum in every index (sum from 0->j) and if exist currSum-k mean that exist i to sum(0,j)-sum(0,i) =k
    public static int subarraySum(int[] nums, int k) {
        int currSum = 0;
        Map<Integer, Integer> dict = new HashMap<>();
        dict.put(0, 1);
        int total = 0;

        for (int i = 0; i < nums.length; i++) {
            currSum += nums[i];
            int firstSum = currSum - k;

            if (dict.containsKey(firstSum)) {
                total += dict.get(firstSum);
            }
            dict.putIfAbsent(currSum, 0);
            dict.put(currSum, dict.get(currSum) + 1);
        }

        return total;
    }

    //    Sort List, time: O(nlogn), space: O(n)
    public ListNode sortList(ListNode head) {
        ListNode temp = head;
        ListNode result = new ListNode(0);
        List<ListNode> arr = new ArrayList<>();

        while (temp != null) {
            arr.add(temp);
            temp = temp.next;
        }
        Collections.sort(arr, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        ListNode l = result;
        for (ListNode element : arr) {
            l.next = element;
            element.next = null;
            l = l.next;
        }

        return result.next;
    }

    //    Search a 2D Matrix, time: O(log(m*n)), space: O(1)
//    beacause first element each row always greater than last element previous row, then we make array to a 1D array with i = k/n and j=k/%n, k is index in new array
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (target < matrix[0][0] || target > matrix[m - 1][n - 1]) {
            return false;
        }

        int l = 0, r = m * n - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            int i = mid / n;
            int j = mid % n;

            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return false;
    }

    //    Merge k Sorted Lists, time: n*klog(k), space: O(1), use merge sort idea to optimize solution
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }

        return mergeDivideAndConquer(lists, 0, lists.length - 1);
    }

    public ListNode mergeDivideAndConquer(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }

        int mid = (l + r) / 2;
        ListNode leftList = mergeDivideAndConquer(lists, l, mid);
        ListNode rightList = mergeDivideAndConquer(lists, mid + 1, r);
        return mergeTwoList(leftList, rightList);
    }

    public ListNode mergeTwoList(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode l = result;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                l.next = l1;
                l1 = l1.next;
            } else {
                l.next = l2;
                l2 = l2.next;
            }

            l = l.next;
        }

        if (l1 != null) {
            l.next = l1;
        }
        if (l2 != null) {
            l.next = l2;
        }

        return result.next;
    }

    //    Combination Sum, time: O(n*n!) ,space: O(n), use backtrack to find all case, only maintain a not decrease int in currentList to not checking same result
//    litel optimization for better result
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new LinkedList<>();
        for (int i = 0; i < candidates.length; i++) {
            combinationSumRecur(candidates, target, candidates[i], new ArrayList<>(List.of(candidates[i])), result, i);
        }

        return result;
    }

    public void combinationSumRecur(int[] candidates, int target, int sum, List<Integer> currentResult, List<List<Integer>> result, int i) {
        if (sum == target) {
            result.add(new ArrayList<>(currentResult));
            return;
        }

        for (int j = i; j < candidates.length; j++) {
            if (sum + candidates[j] <= target) {
                currentResult.add(candidates[j]);
                combinationSumRecur(candidates, target, sum + candidates[j], currentResult, result, j);
                currentResult.remove(currentResult.size() - 1);
            }
        }
    }


    //    Search in Rotated Sorted Array, time: O(nlogn), space: O(1)
//    idea is we use binary search twice, first time we search for index rotation, if k=0 then return len of nums
//    second we use bsearch to find index of target in one of two half array rotated by k
    public int search(int[] nums, int target) {
        int n = nums.length;

        int k = searchRotatedIndex(nums, n);
        if (target >= nums[0]) {
            return search(nums, 0, k, target);
        }
        return search(nums, k + 1, n - 1, target);
    }

    public int searchRotatedIndex(int[] nums, int n) {
        int l = 0, r = n - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (mid < n - 1 && nums[mid] > nums[mid + 1]) {
                return mid;
            }
            if (mid > 0 && nums[mid] < nums[mid - 1]) {
                return mid - 1;
            }

            if (mid > 0 && nums[mid] > nums[mid - 1]) {
                if (nums[mid] > nums[0]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            } else {
                l = mid + 1;
            }
        }

        return r;
    }

    public int search(int[] nums, int l, int r, int target) {
        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1};

        int[][] grid = new int[2][2];
        grid[0] = new int[]{1, 10};
        grid[1] = new int[]{2, 3};
        grid[1] = new int[]{4, 5};
        grid[1] = new int[]{8, 9};

        System.out.println(new TopLikedQuestion().search(nums, 1));
    }
}
