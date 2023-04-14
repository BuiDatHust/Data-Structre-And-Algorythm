package DSU;

import java.util.*;

public class DSUProb {
    //    Number of Provinces, time: O(n^2), space: O(n);
//    public int findCircleNum(int[][] isConnected) {
//        int n = isConnected.length;
//        UnionFind1 uf = new UnionFind1(n);
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                if (isConnected[i][j] == 1) {
//                    uf.union(i, j);
//                }
//            }
//        }
//
//        return uf.count;
//    }

    //    time: O(n), space: O(n)
    public int optimizeFindCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] isVisited = new boolean[n];
        int sum = 0;

        for (int i = 0; i < n; i++) {
            if (isVisited[i] == false) {
                dfs(isConnected, i, isVisited);
                sum++;
            }
        }

        return sum;
    }

    public void dfs(int[][] isConnected, int city, boolean[] isVisited) {
        isVisited[city] = true;
        for (int j = 0; j < isConnected.length; j++) {
            if (isVisited[j] == false && isConnected[city][j] == 1) {
                dfs(isConnected, j, isVisited);
            }
        }
    }

    //    Smallest String With Swaps, time: O(nlogn), space:O(n)
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        UnionFind1 uf = new UnionFind1(s.length());
        Map<Integer, List<Integer>> components = new HashMap<>();

        String[] split = s.split("");
        for (List<Integer> pair : pairs) {
            uf.union(pair.get(0), pair.get(1));
        }
        for (int i = 0; i < uf.root.length; i++) {
            int rootI = uf.find(i);
            if (components.containsKey(rootI)) {
                List<Integer> value = components.get(rootI);
                value.add(i);
                components.put(rootI, value);
            } else {
                components.put(rootI, new ArrayList<>(List.of(i)));
            }
        }

        for (List<Integer> component : components.values()) { // note that component is auto not descending beacause we create it by increase
            List<String> subStr = new ArrayList<>();
            for (int i : component) {
                subStr.add(split[i]);
            }
            Collections.sort(subStr);
            for (int i = 0; i < component.size(); i++) {
                split[component.get(i)] = subStr.get(i);
            }
        }

        return String.join("", split);
    }

    //    optimize while sort component by radix sort, time: O(n*26)
    public String optimizeSmallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        UnionFind1 uf = new UnionFind1(s.length());
        Map<Integer, List<Integer>> components = new HashMap<>();

        String[] split = s.split("");
        for (List<Integer> pair : pairs) {
            uf.union(pair.get(0), pair.get(1));
        }
        for (int i = 0; i < uf.root.length; i++) {
            int rootI = uf.find(i);
            if (components.containsKey(rootI)) {
                List<Integer> value = components.get(rootI);
                value.add(i);
                components.put(rootI, value);
            } else {
                components.put(rootI, new ArrayList<>(List.of(i)));
            }
        }

        for (List<Integer> component : components.values()) { // note that component is auto not descending beacause we create it by increase
            int[] count = new int[26];

            for (int val : component) {
                char ch = s.charAt(val);
                count[ch - 'a']++;
            }

            int c = 0;
            for (int i = 0; i < component.size(); i++) {
                while (count[c] == 0) {
                    c++;
                }
                count[c]--;
                split[component.get(i)] = String.valueOf((char) (c + 'a'));
            }
        }

        return String.join("", split);
    }

    public class UnionFind1 {
        public int[] root;
        public int[] rank;
        public int count;

        public UnionFind1(int size) {
            root = new int[size];
            rank = new int[size];
            count = size;

            for (int i = 0; i < size; i++) {
                root[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x) {
            if (root[x] == x) {
                return x;
            }

            int value = find(root[x]); // update root of every node in travesal
            root[x] = value;
            return value;
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    root[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    root[rootX] = rootY;
                } else {
                    root[rootX] = rootY;
                    rank[rootY]++;
                }
                count--;
            }
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }
    }


}
