package Backtracking;

import java.util.*;

public class BacktrackingProb {
    static int count = 0;

    //    N-Queens II, time: O(n^2*n!), space: O(n)
    public int totalNQueens(int n) {
        List<List<Integer>> visited = new ArrayList<>();
        int total = 0;

        for (int i = 0; i < n; i++) {
            int value = findPlace(0, i, n, visited);
            total += value;
        }

        return total;
    }

    public int findPlace(int i, int j, int n, List<List<Integer>> visited) {
        count++;
        if (i == n - 1 && !isAttack(visited, i, j)) {
            return 1;
        }
        if (isAttack(visited, i, j)) {
            return 0;
        }

        int total = 0;
        visited.add(new ArrayList<>(List.of(i, j)));
        for (int k = 0; k < n; k++) {
            int result = findPlace(i + 1, k, n, visited);
            total += result;
        }
        visited.remove(visited.size() - 1);

        return total;
    }

    public boolean isAttack(List<List<Integer>> visited, int i, int j) {
        for (List<Integer> place : visited) {
            count++;
            if (place.get(0) == i || place.get(1) == j) {
                return true;
            }
            if (Math.abs(place.get(0) - i) == Math.abs(place.get(1) - j)) {
                return true;
            }
        }

        return false;
    }

    public void solveSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][10];
        boolean[][] cols = new boolean[9][10];
        Map<String, Boolean[]> boxs = new HashMap<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boxs.put(i + "," + j, new Boolean[10]);
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char ch = board[i][j];
                if (ch == '.') {
                    continue;
                }
                int c = Integer.parseInt(String.valueOf(ch));
                if (c != '.') {
                    rows[i][c] = true;
                    cols[j][c] = true;
                    String key = i / 3 + "," + j / 3;
                    Boolean[] mark = boxs.get(key);
                    mark[c] = true;
                    boxs.put(key, mark);
                }
            }
        }

        solveSudokuBacktrack(board, rows, cols, boxs);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.println(board[i][j]);
            }
        }
    }

    public boolean solveSudokuBacktrack(char[][] board, boolean[][] rows, boolean[][] cols, Map<String, Boolean[]> boxs) {
        boolean isFilled = true;
        int r = 0, c = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    isFilled = false;
                    r = i;
                    c = j;
                    break;
                }
            }

            if (!isFilled) {
                break;
            }
        }

        if (isFilled) {
            return true;
        }

        String key = r / 3 + "," + c / 3;
        for (int i = 1; i <= 9; i++) {
            boolean isValid = !rows[r][i] && !cols[c][i] && !boxs.get(key)[i];
            if (isValid) {
                rows[r][i] = true;
                cols[c][i] = true;
                Boolean[] box = boxs.get(key);
                box[i] = true;
                boxs.put(key, box);
                board[r][c] = (char) i;
                if (solveSudokuBacktrack(board, rows, cols, boxs)) {
                    return true;
                } else {
                    rows[r][i] = false;
                    cols[c][i] = false;
                    Boolean[] boxBacktrack = boxs.get(key);
                    boxBacktrack[i] = false;
                    boxs.put(key, boxBacktrack);
                    board[r][c] = '.';
                }
            }
        }

        return false;
    }

    //    Combinations, time: O(nCk*k), space: O(nCk*k), use branch and bounch to improve performance
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            combineBacktrack(result, n, k, new ArrayList<>(List.of(i)), i);
        }

        return result;
    }

    public void combineBacktrack(List<List<Integer>> result, int n, int k, List<Integer> currentList, int count) {
        if (currentList.size() == k) {
            result.add(new ArrayList<>(currentList));
            return;
        }
        if (k - currentList.size() + count > n) {
            return;
        }

        int top = currentList.get(currentList.size() - 1);
        for (int i = top + 1; i <= n; i++) {
            currentList.add(i);
            combineBacktrack(result, n, k, currentList, i);
            currentList.remove(currentList.size() - 1);
        }
    }

    //    Letter Combinations of a Phone Number, time: O(4^n), space: O(n), n is length of digits
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        Map<String, List<String>> dict = new HashMap<>();
        dict.put("2", new ArrayList<>(List.of("a", "b", "c")));
        dict.put("3", new ArrayList<>(List.of("d", "e", "f")));
        dict.put("4", new ArrayList<>(List.of("g", "h", "i")));
        dict.put("5", new ArrayList<>(List.of("j", "k", "l")));
        dict.put("6", new ArrayList<>(List.of("m", "n", "0")));
        dict.put("7", new ArrayList<>(List.of("p", "q", "r", "s")));
        dict.put("8", new ArrayList<>(List.of("t", "u", "v")));
        dict.put("9", new ArrayList<>(List.of("w", "x", "y", "z")));

        if (digits.equals("")) {
            return result;
        }
        findLetterCombine(digits, result, 0, dict, "");

        return result;
    }

    public void findLetterCombine(String digits, List<String> result, int index, Map<String, List<String>> dict, String currentResult) {
        if (index == digits.length()) {
            result.add(currentResult);
            return;
        }

        String str = String.valueOf(digits.charAt(index));
        for (String s : dict.get(str)) {
            currentResult += s;
            findLetterCombine(digits, result, index + 1, dict, currentResult);
            currentResult = currentResult.substring(0, currentResult.length() - 1);
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        String[] bank = {"(", ")"};

        generateBacktrack(n, result, "", bank, 0, 0);

        return result;
    }

    //    Generate Parentheses, time: O(n*2^n), space: O(n), but it is not optimize because we must check parenthless
    public void generateBacktrack(int n, List<String> result, String currentResult, String[] bank, int totalOpen, int totalClose) {
        if (totalOpen > n || totalClose > n) {
            return;
        }
        if (currentResult.length() == 2 * n) {
            if (isPaentheless(currentResult)) {
                result.add(currentResult);
                return;
            }
            return;
        }

        for (String s : bank) {
            currentResult += s;
            if (s == "(") {
                totalOpen++;
            }
            if (s == ")") {
                totalClose++;
            }

            generateBacktrack(n, result, currentResult, bank, totalOpen, totalClose);
            currentResult = currentResult.substring(0, currentResult.length() - 1);
            if (s == "(") {
                totalOpen--;
            }
            if (s == ")") {
                totalClose--;
            }
        }
    }

    public boolean isPaentheless(String s) {
        Stack<String> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            String str = String.valueOf(s.charAt(i));
            if (str.equals("(")) {
                st.add(str);
            } else {
                if (st.isEmpty()) {
                    return false;
                }
                st.pop();
            }
        }

        return st.isEmpty();
    }

    //    optimize generate by branch and bound in every step with to condition:  totalOpen < n,totalClose < totalOpen
//    by this we can not check string valid (O(n)) and decrease maxium invalid case in recursive
    public List<String> optimizeGenerateParentheless(int n) {
        List<String> result = new ArrayList<>();

        optimizeGenerate(n, result, "", 0, 0);

        return result;
    }

    public void optimizeGenerate(int n, List<String> result, String currentResult, int totalOpen, int totalClose) {
        if (currentResult.length() == 2 * n) {
            result.add(currentResult);
            return;
        }

        if (totalOpen < n) {
            optimizeGenerate(n, result, currentResult + "(", totalOpen + 1, totalClose);
        }
        if (totalClose < totalOpen) {
            optimizeGenerate(n, result, currentResult + ")", totalOpen, totalClose + 1);
        }
    }

    //    Permutations, time: O(n!), space: O(n!)
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        permuteBacktrack(nums, result, new boolean[nums.length], new ArrayList<>());

        return result;
    }

    public void permuteBacktrack(int[] nums, List<List<Integer>> result, boolean[] visited, List<Integer> currentResult) {
        if (currentResult.size() == nums.length) {
            result.add(new ArrayList<>(currentResult));
            return;
        }

        for (int j = 0; j < nums.length; j++) {
            if (visited[j]) {
                continue;
            }
            visited[j] = true;
            currentResult.add(nums[j]);
            permuteBacktrack(nums, result, visited, currentResult);

            visited[j] = false;
            currentResult.remove(currentResult.size() - 1);
        }
    }

    //    Word Search, time: O(x*k), space: O(m*n), x is frequency of first or last character in word
//    little optimize by reverse string if last character has frequency lower than first so that we can have less recurseive
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        int[] ch = new int[26];

        for (char[] row : board) {
            for (char c : row) {
                ch[c - 'A']++;
            }
        }
        if (ch[word.charAt(0) - 'A'] > ch[word.charAt(word.length() - 1) - 'A']) {
            StringBuilder sb = new StringBuilder(word);
            word = sb.reverse().toString();
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0) && existDfs(board, word, i, j, 0, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean existDfs(char[][] board, String word, int r, int c, int index, boolean[][] visited) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || visited[r][c]) {
            return false;
        }
        if (index == word.length() - 1 && board[r][c] == word.charAt(index)) {
            return true;
        }
        if (board[r][c] != word.charAt(index)) {
            return false;
        }

        visited[r][c] = true;
        boolean isExist1 = existDfs(board, word, r + 1, c, index + 1, visited);
        boolean isExist2 = existDfs(board, word, r - 1, c, index + 1, visited);
        boolean isExist3 = existDfs(board, word, r, c + 1, index + 1, visited);
        boolean isExist4 = existDfs(board, word, r, c - 1, index + 1, visited);
        visited[r][c] = false;

        return isExist1 || isExist2 || isExist3 || isExist4;
    }

    public static void main(String[] args) {
        BacktrackingProb bp = new BacktrackingProb();
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        int[] nums = {0, 1};
        System.out.println(bp.exist(board, "ABCCED"));
    }
}
