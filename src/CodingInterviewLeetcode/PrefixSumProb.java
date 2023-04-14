package CodingInterviewLeetcode;

public class PrefixSumProb {
    //    Running Sum of 1d Array, time: O(n), space:O(1), can optimize space by update nums and return result nums but this is not good
    public int[] runningSum(int[] nums) {
        int sum = 0;
        int n = nums.length;
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            sum += nums[i];
            res[i] = sum;
        }

        return res;
    }

    //    Find Pivot Index, time: O(n), space: O(1)
    public static int pivotIndex(int[] nums) {
        if(nums.length==0){
            return -1;
        }
        int sum = 0;
        int total = 0;

        for (int num : nums) {
            total += num;
        }
        for (int i = 0; i < nums.length; i++) {
            int rest = total - nums[i];

            if (rest % 2 == 0 && rest / 2 == sum) {
                return i;
            }
            sum += nums[i];
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, -1};

        System.out.println(pivotIndex(nums));
    }
}
