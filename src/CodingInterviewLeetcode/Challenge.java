package CodingInterviewLeetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Challenge {
    //    Isomorphic Strings, time: O(n), space: O(1)
    public static boolean isIsomorphic(String s, String t) {
        Map<String, String> mapS = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            String strS = String.valueOf(s.charAt(i));
            String strT = String.valueOf(t.charAt(i));

            if (mapS.containsKey(strS)) {
                String str = mapS.get(strS);
                if (!str.equals(strT)) {
                    return false;
                }
            } else if (!mapS.containsValue(strT)) {
                mapS.put(strS, strT);
            } else {
                return false;
            }
        }

        return true;
    }

    //    Is Subsequence, time: O(n), space: O(1), beat time 84, beat space 41
    public static boolean isSubsequence(String s, String t) {
        int l = 0, r = 0;

        while (l < s.length() && r < t.length()) {
            while (r < t.length() && s.charAt(l) != t.charAt(r)) {
                r++;
            }
            if (r < t.length()) {
                l++;
                r++;
            } else {
                return false;
            }
        }

        if (l == s.length()) {
            return true;
        }
        return false;
    }

    //    Spiral Matrix, time: O(n*m),space: O(n*m)
//    with idea that we store 4 move direction  in every step in dy and dx
//    every move is same with (dy[i], dx[i]) , when the index is out bound, we must change direction follow clockwise by change i : 0,1,2,3,0,1,2... by divide to 4
//    we also use dfs to solve the problem with correspond performance but this is not clear but more navie
    public static List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        List<Integer> result = new ArrayList<>();
        if (m * n == 0) {
            return result;
        }
        boolean[][] visited = new boolean[m][n];
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};
        int x = 0, y = 0, di = 0;

        for (int i = 0; i < m * n; i++) {
            result.add(matrix[y][x]);
            visited[y][x] = true;
            int nextX = x + dx[di];
            int nextY = y + dy[di];

            if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < m && !visited[nextY][nextX]) {
                x = nextX;
                y = nextY;
            } else {
                di = (di + 1) % 4;
                x += dx[di];
                y += dy[di];
            }
        }

        return result;
    }

    static int count = 0;

    //    Where Will the Ball Fall, time: O(m*n), space: O(m*n)
    public static int[] findBall(int[][] grid) {
        int n = grid[0].length;
        int[] res = new int[n];
        Map<String, Integer> memo = new HashMap<>();

        for (int i = 0; i < n; i++) {
            res[i] = findDropPath(grid, 0, i, memo);
        }

        return res;
    }

    public static int findDropPath(int[][] grid, int i, int j, Map<String, Integer> memo) {
        String key = i + "," + j;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int m = grid.length;
        int n = grid[0].length;
        boolean isStuckInWall = (j == 0 && grid[i][j] == -1) || (j == n - 1 && grid[i][j] == 1);
        boolean isStuckInV = (j > 0 && grid[i][j] == -1 && grid[i][j - 1] == 1) || (j < n - 1 && grid[i][j] == 1 && grid[i][j + 1] == -1);
        if (isStuckInWall || isStuckInV) {
            return -1;
        }
        if (i == m - 1) {
            if (grid[i][j] == -1) {
                return j - 1;
            }
            return j + 1;
        }

        int result;
        if (grid[i][j] == 1) {
            result = findDropPath(grid, i + 1, j + 1, memo);
        } else {

            result = findDropPath(grid, i + 1, j - 1, memo);
        }
        memo.put(key, result);
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 1, 1, -1, -1, 1}};
        System.out.println(count);
    }
}
