package Stack;

import java.util.Stack;

public class StackProb {

    //    Valid Parentheless, time: O(n), space: O(n), with n is length of input string
    public boolean isValid(String s) {
        Stack<String> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(' || c == '{' || c == '[') {
                st.push(String.valueOf(c));
            } else {
                if (st.isEmpty()) {
                    return false;
                }

                String top = st.peek();
                if (isParentheses(top.charAt(0), c)) {
                    st.pop();
                } else {
                    return false;
                }
            }
        }

        return st.isEmpty();
    }

    public boolean isParentheses(char c, char t) {
        if (c == '(' && t == ')' || c == '{' && t == '}' || c == '[' && t == ']') {
            return true;
        }

        return false;
    }

    //    Daily Temperatures, time: O(n), space: O(n)
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> st = new Stack<>();
        int[] result = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            if (!st.isEmpty()) {
                int top = st.peek();
                while (!st.isEmpty() && temperatures[top] < temperatures[i]) {
                    result[top] = i - top;
                    st.pop();
                    if (!st.isEmpty()) {
                        top = st.peek();
                    }
                }
            }
            st.push(i);
        }

        return result;
    }

    //    use dp to optimize by use equation dp[i] = i+1 if temp[i]<temp[i+1] or dp[i] = 1+ dp[i+1] if temp[i]>temp[i+1], time: O(n)
    public int[] optimizeDailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        int hottest = 0;

        for (int i = n - 1; i >= 0; i--) {
            int temparature = temperatures[i];
            if (temparature >= hottest) {
                hottest = temparature;
                continue;
            }

            int days = 1;
            while (i + days < n && temperatures[i + days] <= temparature) {
                days += result[i + days];
            }
            result[i] = days;
        }

        return result;
    }

//    Evaluate Reverse Polish Notation, time: O(n), space:O(n)
    public int evalRPN(String[] tokens) {
        int sum = 0;
        Stack<Integer> st = new Stack<>();

        for (String token : tokens) {
            if (token.equals("+") || token.equals("*") || token.equals("-") || token.equals("/")) {
                int operator2 = st.pop();
                int operator1 = st.pop();

                switch (token) {
                    case "+":
                        sum = operator1 + operator2;
                        break;
                    case "-":
                        sum = operator1 - operator2;
                        break;
                    case "*":
                        sum = operator1 * operator2;
                        break;
                    case "/":
                        if (operator2 == 0) {
                            sum = 0;
                            break;
                        }

                        sum = operator1 / operator2;
                        break;
                    default:
                        break;
                }
                st.push(sum);
            } else {
                st.push(Integer.parseInt(token));
            }
        }

        return st.peek();
    }

    public static void main(String[] args) {
        StackProb sp = new StackProb();
        String[] nums = {"2", "1", "+", "3", "*"};
        System.out.println(sp.evalRPN(nums));
    }
}
