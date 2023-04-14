package Greedy;

import java.util.ArrayList;
import java.util.List;

public class GreedyProblem {
    //     time : O(nlog(n)), space: O(n)
    static Integer maxActivities(Activity arr[]) {
        List<Activity> result = new ArrayList<>(List.of(new Activity(arr[0].start, arr[0].finish)));
        System.out.println(Integer.toString(arr[0].start) + "->" + Integer.toString(arr[0].finish));
        int max = 1;
        Compare.compare(arr);

        int j = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].start >= arr[j].finish) {
                max += 1;
                result.add(arr[i]);
                System.out.println(Integer.toString(arr[i].start) + "->" + Integer.toString(arr[i].finish));
                j = i;
            }
        }

        return max;
    }

    static int findEndHouseGreedy(int start, int startVertexOfPipe[], int diameterOfPipe[], int min) {
        if (startVertexOfPipe[start] == 0) {
            return start;
        }
        if (diameterOfPipe[start] < min) {
            min = diameterOfPipe[start];
        }

        return findEndHouseGreedy(startVertexOfPipe[start], startVertexOfPipe, diameterOfPipe, min);
    }

    //    time: O(n), space: O(n)
    static Integer countEffectiveWaterConnection(int numberOfHouse, int arr[][]) {
        int min = 0;
        int count = 0;
        int startVertexOfPipe[] = new int[numberOfHouse + 1];
        int endVertexOfPipe[] = new int[numberOfHouse + 1];
        int diameterOfPipe[] = new int[numberOfHouse + 1];

        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        List<Integer> c = new ArrayList<>();

        for (int i = 0; i < numberOfHouse; i++) {
            startVertexOfPipe[i] = endVertexOfPipe[i] = diameterOfPipe[i] = 0;
        }
        for (int[] connection : arr) {
            startVertexOfPipe[connection[0]] = connection[1];
            endVertexOfPipe[connection[1]] = connection[0];
            diameterOfPipe[connection[0]] = connection[2];
        }
        for (int vertex : startVertexOfPipe) {
            if (endVertexOfPipe[vertex] == 0 && startVertexOfPipe[vertex] > 0) {
                min = 100000000;
                int w = findEndHouseGreedy(vertex, startVertexOfPipe, diameterOfPipe, min);
                a.add(vertex);
                b.add(w);
                c.add(min);
                count += 1;
                System.out.println(Integer.toString(vertex) + "->" + Integer.toString(w) + " " + Integer.toString(min));
            }
        }

        return count;
    }

    //    time: O(n), space: O(n)
    static int findMaxThiefCatchable(String arr[], int k) {
        int maxCatching = 0;
        List<Integer> polices = new ArrayList<>();
        List<Integer> theives = new ArrayList<>();
        int l = 0;
        int r = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == "P") {
                polices.add(i);
            }
            if (arr[i] == "T") {
                theives.add(i);
            }
        }

        while (l < polices.size() && r < theives.size()) {
            if (Math.abs(polices.get(l) - theives.get(r)) <= k) {
                maxCatching++;
                l++;
                r++;
            } else if (polices.get(l) < theives.get(r)) {
                l++;
            } else {
                r++;
            }
        }

        return maxCatching;
    }

    static int findMaxThiefCatchableOptimalSpace(String arr[], int k) {
        int maxCatching = 0;
        int l = -1, r = -1;
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            if (arr[i] == "P") {
                l = i;
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            if (arr[i] == "T") {
                r = i;
                break;
            }
        }
        if (l == -1 || r == -1) {
            return maxCatching;
        }

        while (l < n && r < n) {
            if (Math.abs(l - r) <= k) {
                l++;
                while (l < n && arr[l] != "P") {
                    l++;
                }

                r++;
                while (r < n && arr[r] != "T") {
                    r++;
                }
                maxCatching++;
            } else if (l < r) {
                l++;
                while (l < n && arr[l] != "P") {
                    l++;
                }
            } else {
                r++;
                while (r < n && arr[r] != "T") {
                    r++;
                }
            }
        }

        return maxCatching;
    }

//    time: O(n), space: O(1)
    static List<Integer> findBestFittingShelves(int wall, int m, int n) {
        int largerShelf = m > n ? m : n;
        int smallerShelf = m > n ? n : m;
        int p = wall / largerShelf, q = 0;
        int minEmptySpace = wall % largerShelf;
        int num_larger = p, num_smaller = q;

        while (p > 0) {
            q++;
            int remainder = wall - q * smallerShelf;
            p = remainder / largerShelf;
            int emptySpace = remainder % largerShelf;

            if (emptySpace < minEmptySpace) {
                minEmptySpace = emptySpace;
                num_larger = p;
                num_smaller = q;
            }
        }
        if (p == 0) {
            int emptySpace = wall % smallerShelf;
            if (emptySpace < minEmptySpace) {
                minEmptySpace = emptySpace;
                num_larger = 0;
                num_smaller = wall / smallerShelf;
            }
        }

        return new ArrayList<>(List.of(num_larger, num_smaller, minEmptySpace));
    }

    public static void main(String[] args) {
//        Activity activities[] = new Activity[6];
//        activities[0] = new Activity(5, 9);
//        activities[1] = new Activity(1, 2);
//        activities[2] = new Activity(3, 4);
//        activities[3] = new Activity(0, 6);
//        activities[4] = new Activity(5, 7);
//        activities[5] = new Activity(8, 9);
//        System.out.println(maxActivities(activities));

//        int arr[][] = {{6, 3, 98}, {4, 8, 72}, {3, 5, 10},
//                {1, 7, 22}, {8, 6, 17}, {2, 0, 66}};
//        System.out.println(countEffectiveWaterConnection(9, arr));

//        String arr[] = {"P", "T", "T", "P", "T"};
//        System.out.println(findMaxThiefCatchableOptimalSpace(arr, 1));

        List<Integer> result = findBestFittingShelves(24, 4, 7);
        System.out.println(result);
    }
}
