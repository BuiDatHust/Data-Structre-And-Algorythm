package Stack;

import Queue.MonotonicQueue;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MonotonicProb {
    //    Remove Duplicate Letters, time: O(n), space: O(n), maintain a monotoic increase stack to have a min lexical order
    public String removeDuplicateLetters(String s) {
        Stack<String> st = new Stack<>();
        int[] dict = new int[26];
        String res = "";
        boolean[] seen = new boolean[26];

        for (int i = 0; i < s.length(); i++) {
            dict[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (seen[c - 'a']) {
                dict[c - 'a']--;
                continue;
            }
            while (!st.isEmpty() && st.peek().compareTo(String.valueOf(c)) > 1 && dict[st.peek().charAt(0) - 'a'] > 0) {
                seen[st.pop().charAt(0) - 'a'] = false;
            }

            st.push(String.valueOf(c));
            seen[c - 'a'] = true;
            dict[c - 'a']--;
        }

        while (!st.isEmpty()) {
            res += st.pop();
        }
        String result = "";
        for (int i = res.length() - 1; i >= 0; i--) {
            result += String.valueOf(res.charAt(i));
        }

        return result;
    }

    //    Next Greater Element I, time: O(m+ n), space: O(n)
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> memo = new HashMap<>();
        Stack<Integer> st = new Stack<>();
        int[] res = new int[nums1.length];

        for (int i = 0; i < nums2.length; i++) {
            while (!st.isEmpty() && nums2[st.peek()] < nums2[i]) {
                memo.put(nums2[st.pop()], nums2[i]);
            }
            st.push(i);
        }
        while (!st.isEmpty()) {
            memo.put(nums2[st.pop()], -1);
        }

        for (int i = 0; i < nums1.length; i++) {
            res[i] = memo.get(nums1[i]);
            System.out.println(res[i]);
        }

        return res;
    }

    public static void main(String[] args) {
        MonotonicProb mq = new MonotonicProb();
        int[] nums1 = {2,4};
        int[] nums2 = {1, 2, 3, 4};
        System.out.println();
    }
}