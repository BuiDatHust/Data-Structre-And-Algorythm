package CodingInterviewLeetcode;

import java.util.Arrays;
import java.util.HashMap;

public class StringProb {
    public static char[] reverseString(char[] s) {
        int l = 0;
        int r = s.length - 1;

        while (l < r) {
            char temp = s[l];
            s[l] = s[r];
            s[r] = temp;
            l++;
            r--;
        }

        return s;
    }

    //    Reverse Integer, time: O(n), space: O(1)
    public static int reverse(int x) {
        int isNegative = x < 0 ? -1 : 1;
        long absX = isNegative * (long) x;
        String s = Long.toString(absX);
        char[] reversedS = reverseString(s.toCharArray());

        long reversedX = Long.parseLong(String.valueOf(reversedS));
        if (reversedX > Integer.MAX_VALUE) {
            return 0;
        }

        return isNegative * Integer.parseInt(String.valueOf(reversedS));
    }

    public static int optimizeReverse(int x) {
        int result = 0;

        while (x != 0) {
            int digit = x % 10;
            int newResult = result * 10 + digit;
            if ((newResult - digit) / 10 != result) {
                return 0;
            }
            result = newResult;

            x /= 10;
        }

        return result;
    }

    //    First Unique Character in a String, time: 0(n) ,space: O(n)
    public static int firstUniqChar(String s) {
        HashMap<String, Integer> dict = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            String element = String.valueOf(s.charAt(i));
            if (dict.containsKey(element) == true) { // need optimize
                dict.put(element, dict.get(element) + 1);
            } else {
                dict.put(element, 1);
            }
        }
        for (int i = 0; i < s.length(); i++) {
            String element = String.valueOf(s.charAt(i));
            if (dict.containsKey(element) == true) {  // need optimize
                if (dict.get(element) == 1) {
                    return i;
                }
            }

        }

        return -1;
    }

    public static int optimizeFirstUniqChar(String s) {
        int[] dict = new int[256];

        for (int i = 0; i < s.length(); i++) {
            dict[s.charAt(i)]++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (dict[s.charAt(i)] == 1) {
                return i;
            }
        }

        return -1;
    }

    //    Longest Common Prefix, time: O(n*k), k la phan tu co do dai nho nhat cua day, space: O(1)
    public static String longestCommonPrefix(String[] strs) {
        int minLen = Integer.MAX_VALUE;

        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() < minLen) {
                minLen = strs[i].length();
            }
        }

        int i = 1;
        boolean isStop = false;
        while (i < minLen+1 && !isStop) {
            String prefix = strs[0].substring(0, i);
            for (int j = 0; j < strs.length; j++) {
                if (!strs[j].substring(0, i).equals(prefix)) {
                    isStop = true;
                    i--;
                    break;
                }
            }

            i++;
        }

        if (i > 1) {
            return strs[0].substring(0, i-1);
        }else{
            return "";
        }
    }
    //    time: O(n*log(n) + k), k la phan tu co do dai nho nhat cua day, space: O(1)
    public static String optimizeLongestCommonPrefix(String[] str){
        Arrays.sort(str);

        String first = str[0];
        String last = str[str.length - 1];

        int i = 0 , c = 0;
        while(i < first.length()){
            if(first.charAt(i) == last.charAt(i)) c++;
            else break;
            i++;
        }
        return first.substring(0 , c);
    }


    public static void main(String[] args) {
        String[] s = {"flower","abd","mnjmj","flight"};
        System.out.println((optimizeLongestCommonPrefix(s)));
    }
}
