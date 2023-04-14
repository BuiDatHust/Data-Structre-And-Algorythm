package Heap;

import java.util.*;

public class HeapProb {
    //    Last Stone Weight, time: O(n^2logn), space: O(1)
    public static int lastStoneWeight(int[] stones) {
        int l = 0;
        int n = stones.length;

        while (l < n - 1) {
            Arrays.sort(stones);
            if (stones[n - 1] == stones[n - 2]) {
                stones[n - 1] = stones[n - 2] = 0;
                l += 2;
            } else if (stones[n - 1] > stones[n - 2]) {
                stones[n - 1] -= stones[n - 2];
                stones[n - 2] = 0;
                l++;
            } else {
                stones[n - 2] -= stones[n - 1];
                stones[n - 1] = 0;
                l++;
            }
        }
        Arrays.sort(stones);

        return stones[n - 1];
    }

    //    time: O(n^2), space: O(n)
    public static int lastStoneWeight1(int[] stones) {
        int l = 0;
        int n = stones.length;

        while (l < n - 1) {
            int[] indexTwoHeaviest = findHeaviestTwoStone(stones);
            int i = indexTwoHeaviest[0];
            int j = indexTwoHeaviest[1];
            if (stones[i] == stones[j]) {
                stones[i] = stones[j] = 0;
                l += 2;
            } else if (stones[i] > stones[j]) {
                stones[i] -= stones[j];
                stones[j] = 0;
                l++;
            } else {
                stones[j] -= stones[i];
                stones[i] = 0;
                l++;
            }
        }

        for (int stone : stones) {
            if (stone > 0) {
                return stone;
            }
        }
        return 0;
    }

    public static int[] findHeaviestTwoStone(int[] stones) {
        int[] res = new int[2];
        int max = 0, minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < stones.length; i++) {
            if (stones[i] > max) {
                max = stones[i];
                res[0] = i;
            }
        }
        for (int i = 0; i < stones.length; i++) {
            if (max - stones[i] < minDistance && i != res[0]) {
                minDistance = max - stones[i];
                res[1] = i;
            }
        }
        return res;
    }

    //    optimize approach, use max heap, time: O(nlogn), space:log(n)
    public static int optimizeLastStoneWeight(int[] stones) {
        int n = stones.length;
        int lastStoneWeight = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(n, Collections.reverseOrder());

        for (int stone : stones) {
            maxHeap.add(stone);
        }

        while (!maxHeap.isEmpty()) {
            int max1 = maxHeap.poll();
            if (!maxHeap.isEmpty()) {
                int max2 = maxHeap.poll();
                if (max2 != max1) {
                    maxHeap.add(max1 - max2);
                }
            } else {
                lastStoneWeight = max1;
            }
        }

        return lastStoneWeight;
    }

    //    Top K Frequent Words, time: O(nlogk),space: O(n)
    public static List<String> topKFrequent(String[] words, int k) {
        int n = words.length;
        List<String>[] frequency = new List[n];
        Map<String, Integer> dict = new HashMap<>();
        List<String> result = new ArrayList<>();

        for (String word : words) {
            dict.putIfAbsent(word, 0);
            dict.put(word, dict.get(word) + 1);
        }
        for (Map.Entry<String, Integer> entry : dict.entrySet()) {
            List<String> wordArrs;
            String key = entry.getKey();
            Integer value = entry.getValue();

            if (frequency[value] == null) {
                wordArrs = new ArrayList<>();
            } else {
                wordArrs = frequency[value];
            }
            wordArrs.add(key);

            frequency[value] = wordArrs;
        }

        for (int i = frequency.length - 1; i >= 0; i--) {
            List<String> freq = frequency[i];
            if (k == 0) {
                break;
            }
            if (freq == null) {
                continue;
            }

            Collections.sort(freq);
            for (String s : freq) {
                if (k > 0) {
                    result.add(s);
                    k--;
                } else {
                    break;
                }
            }
        }

        return result;
    }

    public static List<String> optimizeTopKFrequent(String[] words, int k) {
        List<String> result = new ArrayList<>();
        Map<String, Integer> dict = new HashMap<>();

        for (String word : words) {
            dict.putIfAbsent(word, 0);
            dict.put(word, dict.get(word) + 1);
        }

        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue() != o2.getValue()) {
                    return o1.getValue().compareTo(o2.getValue());
                } else {
                    return o2.getKey().compareTo(o1.getKey());
                }
            }
        });
        for (Map.Entry<String, Integer> entry : dict.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll().getKey());
        }
        Collections.reverse(result);

        return result;
    }

    //    Sliding Window Maximum, use heap approach, we make a max heap store max sliding window in every step
//    when slide to new window, we add new element to max heap and pop until index of top max heap in range of current slide window
//    it is naviest approach , we also do deque, double linked list,..
//    time: O(n*logk), space: log(n)
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        PriorityQueue<ArrayList<Integer>> maxHeap = new PriorityQueue<>(k, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                return o2.get(0) - o1.get(0);
            }
        });
        int index = 0;

        for (int i = 0; i < k; i++) {
            maxHeap.add(new ArrayList<>(List.of(nums[i], i)));
        }
        res[index] = maxHeap.peek().get(0);

        for (int i = k; i < n; i++) {
            index++;

            maxHeap.add(new ArrayList<>(List.of(nums[i], i)));
            while (!maxHeap.isEmpty() && maxHeap.peek().get(1) <= i - k) {
                maxHeap.poll();
            }

            res[index] = maxHeap.peek().get(0);
        }

        return res;
    }

    //    Kth Smallest Element in a Sorted Matrix, time: O(nlogn), space: O(n), we can initialize min heap with col instead of
//    in every step we find the ith smallet element by find top of min heap
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<List<Integer>> minHeap = new PriorityQueue<>(k, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return matrix[o1.get(0)][o1.get(1)] - matrix[o2.get(0)][o2.get(1)];
            }
        });

        for (int i = 0; i < n; i++) {
            minHeap.add(new ArrayList<>(List.of(0, i)));
        }
        for (int i = 1; i < k; i++) {
            List<Integer> topIthSmallest = minHeap.peek();
            minHeap.poll();

            if (topIthSmallest.get(0) + 1 < n) {
                minHeap.add(new ArrayList<>(List.of(topIthSmallest.get(0) + 1, topIthSmallest.get(1))));
            }
        }

        return matrix[minHeap.peek().get(0)][minHeap.peek().get(1)];
    }

    //    Remove Stones to Minimize the Total, time: O(nlogn), space: log(n)
    public static int minStoneSum(int[] piles, int k) {
        int sum = 0;
        int step = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(piles.length, Collections.reverseOrder());

        for (int pile : piles) {
            sum += pile;
            maxHeap.add(pile);
        }
        while (step < k) {
            int top = maxHeap.peek();
            maxHeap.poll();
            int removedStone = top / 2;

            sum -= removedStone;
            maxHeap.add(top - removedStone);
            step++;
        }

        return sum;
    }

    //    K Closest Points to Origin, time: O(nlogn),space: O(n)
    public static int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(points.length, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int distance1 = o1[0] * o1[0] + o1[1] * o1[1];
                int distance2 = o2[0] * o2[0] + o2[1] * o2[1];

                return distance2 - distance1;
            }
        });

        for (int[] point : points) {
            int[] pair = {point[0], point[1]};
            minHeap.add(pair);

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }


        return minHeap.toArray(new int[0][0]);
    }

    public static void main(String[] args) {
        int[][] nums = {{1, 3}, {-2, 2}, {10, 10}, {9, 9}};
        System.out.println(kClosest(nums, 1));
    }
}
