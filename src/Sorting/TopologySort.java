package Sorting;

import java.util.*;

public class TopologySort {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> m = new HashMap<>();
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            int value = findHeightTree(graph, i, visited);
            m.put(i, value);
            min = value < min ? value : min;
            System.out.println(value);
        }

        for (Map.Entry<Integer, Integer> entry : m.entrySet()) {
            if (entry.getValue() == min) {
                res.add(entry.getKey());
            }
        }

        return res;
    }

    public int findHeightTree(List<List<Integer>> graph, int rootEdge, boolean[] visited) {
        if (visited[rootEdge]) {
            return 0;
        }

        int height = 0;
        visited[rootEdge] = true;
        for (int edge : graph.get(rootEdge)) {
            int h = findHeightTree(graph, edge, visited);
            if (h > height) {
                height = h;
            }
        }

        return 1 + height;
    }

    //    approach with thinking problem like a 1-d array, the nodes in mid will has min height tree
    public List<Integer> optimizeFindMinHeightTrees(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        int[] degree = new int[n];
        int V = n;

        if (n == 1) {
            return new ArrayList<>(List.of(0));
        }
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int first = edge[0], second = edge[1];
            adj.get(first).add(second);
            adj.get(second).add(first);
            degree[first]++;
            degree[second]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                q.add(i);
            }
        }
        while (V > 2) {
            int totalPop = q.size();
            V -= totalPop;

            for (int i = 0; i < totalPop; i++) {
                int vertex = q.poll();
                for (int v : adj.get(vertex)) {
                    degree[v]--;
                    if (degree[v] == 1) {
                        q.add(v);
                    }
                }
            }
        }
        while (!q.isEmpty()) {
            res.add(q.poll());
        }

        return res;
    }

    static int count=0;
//    Find Eventual Safe States, time: O(V+E), space: O(V)
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        List<Integer> result = new ArrayList<>();
        Map<Integer,Boolean> memo = new HashMap<>();

        for (int i = 0; i < graph.length; i++) {
            boolean isSafeNode = findSafeNode(graph,i,memo,new boolean[n]);
            if(isSafeNode){
                result.add(i);
            }
        }

        return result;
    }
    public boolean findSafeNode(int[][] graph, int i,Map<Integer,Boolean> memo, boolean[] visited){
        count++;
        if(visited[i]){
            return false;
        }
        if(graph[i].length==0){
            return true;
        }
        if(memo.containsKey(i)){
            return memo.get(i);
        }

        visited[i] = true;
        for(int vertex: graph[i]){
            boolean isSafeNode = findSafeNode(graph,vertex,memo,visited);
            memo.put(vertex,isSafeNode);
            if(!isSafeNode){
                return false;
            }
        }
        visited[i] = false;

        return true;
    }

    public static void main(String[] args) {
        TopologySort ts = new TopologySort();
        int[][] edges = {{},{0,2,3,4},{3},{4},{}};
        System.out.println(ts.eventualSafeNodes(edges));
        System.out.println(count);
    }
}
