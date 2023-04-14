package Queue;

import java.util.*;

public class QueueProb {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] result = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    result[i][j] = findNearestZero(mat, i, j);
                    System.out.println("\n");
                }
            }
        }

        for (int[] r : result) {
            for (int e : r) {
                System.out.println(e);
            }
        }

        return result;
    }

    public int findNearestZero(int[][] mat, int i, int j) {
        int min = 0;
        Queue<List<Integer>> q = new LinkedList<>();
        int totalcurrentNeighbors = 0;
        q.add(new ArrayList<>(List.of(i, j)));

        while (!q.isEmpty()) {
            if (totalcurrentNeighbors == 0) {
                min++;
            }
            totalcurrentNeighbors--;

            List<Integer> top = q.poll();
            int x = top.get(0);
            int y = top.get(1);

            if (mat[x][y] == 0) {
                System.out.println(min);
                return min;
            }

            int count = 0;
            if (x > 0) {
                q.add(new ArrayList<>(List.of(x - 1, y)));
                count++;
            }
            if (x < mat.length - 1) {
                q.add(new ArrayList<>(List.of(x + 1, y)));
                count++;
            }
            if (y > 0) {
                q.add(new ArrayList<>(List.of(x, y - 1)));
                count++;
            }
            if (y < mat[0].length - 1) {
                q.add(new ArrayList<>(List.of(x, y + 1)));
                count++;
            }

            if (totalcurrentNeighbors < 0) {
                totalcurrentNeighbors = count;
            }
        }

        return -1;
    }

    //    Keys and Rooms, time: O(n), space: O(n)
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        int totalVisited = 0;
        Queue<Integer> q = new LinkedList<>();

        visited[0] = true;
        totalVisited++;
        for (int room : rooms.get(0)) {
            q.add(room);
            visited[room] = true;
            totalVisited++;
        }

        while (!q.isEmpty()) {
            int top = q.poll();
            List<Integer> currentRoom = rooms.get(top);

            for (int room : currentRoom) {
                if (!visited[room]) {
                    q.add(room);
                    visited[room] = true;
                    totalVisited++;
                }
            }
        }

        return totalVisited == n;
    }

    public static void main(String[] args) {
        QueueProb qp = new QueueProb();
        int[][] nums = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        List<List<Integer>> rooms = new ArrayList<>();
        rooms.add(new ArrayList<>(List.of(1, 3)));
        rooms.add(new ArrayList<>(List.of(3, 0, 1)));
        rooms.add(new ArrayList<>(List.of(2)));
        rooms.add(new ArrayList<>(List.of(0)));

        System.out.println();
    }
}
