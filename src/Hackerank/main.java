package Hackerank;

import java.util.*;

public class main {
    //    Gridland Metro, time: O(klogk), space: O(n)
    public static int gridlandMetro(int n, int m, int k, List<List<Integer>> track) {
        // Write your code here
        boolean[][] isTrainTracked = new boolean[n][m];
        int sum = m * n;
        int[] trackLast = new int[n];

        Collections.sort(track, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                if (o1.get(0) == o2.get(0)) {
                    return o1.get(1) - o2.get(1);
                }
                return o1.get(0) - o2.get(0);
            }
        });

        for (int i = 0; i < n; i++) {
            trackLast[i] = -1;
        }
        for (List<Integer> t : track) {
            int r = t.get(0) - 1;
            int c1 = t.get(1) - 1;
            int c2 = t.get(2) - 1;

            if (c1 > trackLast[r]) {
                sum -= c2 - c1 + 1;
                trackLast[r] = c2;
            } else {
                if (c2 > trackLast[r]) {
                    sum -= c2 - trackLast[r];
                    trackLast[r] = c2;
                }
            }
        }

        return sum;
    }

    //    Hackerland Radio Transmitters, time: O(nlogn), space: O(1), use greedy
    public static int hackerlandRadioTransmitters(List<Integer> x, int k) {
        // Write your code here
        int i = 0;
        int count = 0;

        Collections.sort(x);

        while (i < x.size()) {
            int max = x.get(i) + k;

            while (i < x.size() && x.get(i) <= max) {
                i++;
            }
            i--;

            max = x.get(i) + k;
            while (i < x.size() && x.get(i) <= max) {
                i++;
            }

            count++;
        }

        return count;
    }

    //    Organizing Containers of Balls, time: O(n^2), space: O(n)
//    idea is we calculate array has sum ball each type and calculate array has result in every case before swap of each row, then sort them
//    if two array is same then possible else impossible
    public static String organizingContainers(List<List<Integer>> container) {
        int n = container.size();
        int[] ballArr = new int[n];
        int[] row = new int[n];
        Map<Integer, Integer> dictBall = new HashMap<>();
        Map<Integer, Integer> dictRow = new HashMap<>();

        for (int i = 0; i < n; i++) {
            List<Integer> r = container.get(i);
            for (int j = 0; j < r.size(); j++) {
                row[i] += r.get(j);
                ballArr[j] += r.get(j);
            }
        }

        for (int i = 0; i < n; i++) {
            dictBall.putIfAbsent(ballArr[i], 0);
            dictRow.putIfAbsent(row[i], 0);
            dictRow.put(row[i], dictRow.get(row[i]) + 1);
            dictBall.put(ballArr[i], dictBall.get(ballArr[i]) + 1);
        }

        for (int i = 0; i < n; i++) {
            if (!dictRow.containsKey(ballArr[i]) || dictRow.get(ballArr[i]) != dictBall.get(ballArr[i])) {
                return "Impossible";
            }
        }

        return "Possible";
    }

    //    3D Surface Area, time: O(h*w), space: O(1);
    public static int surfaceArea(List<List<Integer>> A) {
        int h = A.size();
        int w = A.get(0).size();
        int area = 0;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                int currentCube = A.get(i).get(j);

                if (currentCube == 0) {
                    continue;
                }
                area += 2;

                area = calculateSurroundSide(A, i, j, -1, 0, h, w, currentCube, area);
                area = calculateSurroundSide(A, i, j, 1, 0, h, w, currentCube, area);
                area = calculateSurroundSide(A, i, j, 0, 1, h, w, currentCube, area);
                area = calculateSurroundSide(A, i, j, 0, -1, h, w, currentCube, area);
            }
        }

        return area;
    }

    public static int calculateSurroundSide(List<List<Integer>> A, int i, int j, int di, int dj, int h, int w, int currentCube, int area) {
        if (i + di >= 0 && i + di < h && j + dj >= 0 && j + dj < w) {
            int nextCube = A.get(i + di).get(j + dj);
            if (currentCube > nextCube) {
                area += currentCube - nextCube;
            }
        } else {
            area += currentCube;
        }

        return area;
    }

    //    Fraudulent Activity Notifications, time: O(n*dlogd), space: O(d)
    public static int activityNotifications(List<Integer> expenditure, int d) {
        // Write your code here
        int total = 0;
        List<Integer> prevTrailling = new ArrayList<>();
        boolean isEven = d % 2 == 1 ? true : false;

        for (int i = 0; i < d; i++) {
            prevTrailling.add(expenditure.get(i));
        }

        for (int i = d; i < expenditure.size(); i++) {
            List<Integer> tempTrailling = new ArrayList<>(prevTrailling);
            Collections.sort(tempTrailling);

            int doubleMedian = isEven ? 2 * tempTrailling.get(d / 2) : (tempTrailling.get(d / 2 - 1) + tempTrailling.get(d / 2));
            if (doubleMedian <= expenditure.get(i)) {
                total++;
            }

            prevTrailling.remove(0);
            prevTrailling.add(expenditure.get(i));
        }

        return total;
    }

    public static long roadsAndLibraries(int n, int c_lib, int c_road, List<List<Integer>> cities) {
        // Write your code here
        int total = 0;
        boolean[] visited = new boolean[n];
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (List<Integer> pair : cities) {
            int start = pair.get(0);
            int end = pair.get(1);

            graph.putIfAbsent(start, new ArrayList<>());
            graph.putIfAbsent(end, new ArrayList<>());

            List<Integer> vertexStart = graph.get(start);
            vertexStart.add(end);
            graph.put(start, vertexStart);

            List<Integer> vertexEnd = graph.get(start);
            vertexEnd.add(start);
            graph.put(end, vertexEnd);
        }

        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            if (!visited[entry.getKey()]) {
                long totalVertex = findOptimalCost(graph, entry.getKey(), 1, visited);
                long roadWay = c_lib + c_road * (totalVertex - 1);
                long libraryWay = c_lib * (totalVertex - 1);

                if (roadWay > libraryWay) {
                    total += libraryWay;
                } else {
                    total += roadWay;
                }
            }
        }

        return total;
    }

    public static long findOptimalCost(Map<Integer, List<Integer>> graph, int i, int count, boolean[] visited) {
        if (visited[i]) {
            return count - 1;
        }
        if (graph.get(i).isEmpty()) {
            return count;
        }

        long max = 0;
        visited[i] = true;
        for (int j : graph.get(i)) {
            long value = findOptimalCost(graph, j, count + 1, visited);
            max = value > max ? value : max;
        }

        return max;
    }

    public static void main(String[] args) {
        List<List<Integer>> la = new ArrayList<>(List.of(
                new ArrayList<>(List.of(1, 1, 2)),
                new ArrayList<>(List.of(1, 2, 4)),
                new ArrayList<>(List.of(1, 3, 5))
        ));
        List<List<Integer>> container = new ArrayList<>(List.of(
                new ArrayList<>(List.of(1))
//                new ArrayList<>(List.of(2,2,3)),
//                new ArrayList<>(List.of(1,2,4))
        ));
        System.out.println(activityNotifications(new ArrayList<>(List.of(2, 3, 4, 2, 3, 6, 8, 4, 5)), 5));
    }
}
