package CodingInterviewLeetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MathProb {

    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                result.add("FizzBuzz");
            } else if (i % 3 == 0) {
                result.add("Fizz");
            } else if (i % 5 == 0) {
                result.add("Buzz");
            } else {
                result.add(i + "");
            }
        }

        return result;
    }

    //    Count Primes, time: O(n*can(n)), space: O(1)
    public static int countPrimes(int n) {
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (isPrime(i) == true) {
                count++;
            }
        }

        return count;
    }

    //    time: O(n*log(log(n))), space: O(n)
    public static int countPrimeErotathes(int n) {
        int count = 0;
        if (n == 0 || n == 1) {
            return 0;
        }

        boolean[] isPrime = new boolean[n + 1];
        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }
        isPrime[1] = false;

        for (int i = 2; i * i < n; i++) {
            if (isPrime[i] == true) {
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int i = 2; i < n; i++) {
            if (isPrime[i] == true) {
                count++;
            }
        }

        return count;
    }

    public static boolean isPrime(int n) {
        if (n == 0 || n == 1) {
            return false;
        }
        for (int i = 2; i * i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    //    Power of Three, time: O(n), space: O(1)
    public static boolean isPowerOfThree(int n) {
        if (n < 1) {
            return false;
        }

        while (n % 3 == 0) {
            n /= 3;
        }
        if (n == 1) {
            return true;
        }

        return false;
    }

    //    Roman to Integer, time: O(n), space:O(1)
    public static int romanToInt(String s) {
        int result = 0;
        int n = s.length();
        Map<String, Integer> romanDict = new HashMap<String, Integer>() {{
            put("I", 1);
            put("V", 5);
            put("L", 50);
            put("X", 10);
            put("C", 100);
            put("D", 500);
            put("M", 1000);
        }};
        Map<String, Integer> subtractRomanDict = new HashMap<String, Integer>() {{
            put("IV", 4);
            put("IX", 9);
            put("XL", 40);
            put("XC", 90);
            put("CD", 400);
            put("CM", 900);
        }};

        int i = 0;
        while (i < n - 1) {
            String roman = String.valueOf(s.charAt(i));
            String substractRoman = s.substring(i, i + 2);
            if (subtractRomanDict.containsKey(substractRoman) == true) {
                result += subtractRomanDict.get(substractRoman);
                i += 2;
            } else {
                result += romanDict.get(roman);
                i++;
            }
        }
        if (i == n - 1) {
            result += romanDict.get(String.valueOf(s.charAt(n - 1)));
        }

        return result;
    }
    // time and space is little same, but little optimize by decrease create dictinary and operation with map.
    // idea is iteration reverse and check when has subtraction roman by math
    public static int optimalRomanToInt(String s) {
        int result = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            String str = String.valueOf(s.charAt(i));
            int ans = 0;
            switch (str) {
                case "I":
                    ans = 1;
                    break;
                case "V":
                    ans = 5;
                    break;
                case "X":
                    ans = 10;
                    break;
                case "L":
                    ans = 50;
                    break;
                case "C":
                    ans = 100;
                    break;
                case "M":
                    ans = 1000;
                    break;
                case "D":
                    ans = 500;
                    break;
            }
            if (4 * ans <= result) {
                result -= ans;
            } else {
                result += ans;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {0};
        System.out.println(optimalRomanToInt("MCMXCIV"));
    }
}
