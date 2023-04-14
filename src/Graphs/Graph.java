package Graphs;

import java.util.*;

public class Graph<T> {
    private Map<T, List<T>> map = new HashMap<>();
    private int totalVertex;

    public Graph() {
        this.totalVertex = 0;
    }

    public void addVertex(T s) {
        map.put(s, new LinkedList<>());
        this.totalVertex += 1;
    }

    public void addEdge(T source, T destination, Boolean bidirection) {
        if (!map.containsKey(source)) {
            addVertex(source);
        }
        if (!map.containsKey(destination)) {
            addVertex(destination);
        }
        map.get(source).add(destination);
        if (bidirection == true) {
            map.get(destination).add(source);
        }
    }

    public void depthFirstTravesal(T startVertex) {
        Stack<T> st = new Stack<T>();
        String result = "";
        st.push(startVertex);
        while (!st.isEmpty()) {
            T currentVertex = st.pop();
            result += currentVertex;
            for (T v : map.get(currentVertex)) {
                st.push(v);
            }
        }
        System.out.println(result);
    }

    public Boolean isHasPath(T source, T destination) {
        Stack<T> st = new Stack<T>();
        st.push(source);
        while (!st.isEmpty()) {
            T currentVertex = st.pop();
            if (currentVertex == destination) {
                return true;
            }
            for (T v : map.get(currentVertex)) {
                st.push(v);
            }
        }
        return false;
    }

    public int countConnectedComponent() {
        int count = 0;
        ArrayList<T> visited = new ArrayList<T>();

        for (T v : map.keySet()) {
            if (explore(v, visited) == true) {
                count += 1;
            }
        }
        return count;
    }

    public int countLargestComponent() {
        ArrayList<T> visited = new ArrayList<T>();
        int longest = 0;

        for (T v : map.keySet()) {
            int size = exploreSize(v, visited);
            if (size > longest) {
                longest = size;
            }
        }
        return longest;
    }

    public Boolean explore(T current, ArrayList<T> visited) {
        if (visited.contains(current)) return false;
        visited.add(current);

        for (T k : map.get(current)) {
            explore(k, visited);
        }
        return true;
    }

    public int exploreSize(T current, ArrayList<T> visited) {
        if (visited.contains(current)) {
            return 0;
        }
        visited.add(current);
        int size = 1;
        for (T k : map.get(current)) {
            size += exploreSize(k, visited);
        }

        return size;
    }

    public int shortestPath(T source, T target) {
        if (!map.containsKey(source) || !map.containsKey(target)) {
            return -1;
        }
        Queue<Object[]> q = new LinkedList<Object[]>();
        ArrayList<T> visited = new ArrayList<T>();
        q.add(new Object[]{source, 1});

        while (!q.isEmpty()) {
            Object[] current = q.remove();
            T currentVertex = (T) current[0];
            int distance = (int) current[1];
            if (currentVertex == target) {
                return distance;
            }

            for (T k : map.get(currentVertex)) {
                if (!visited.contains(k)) {
                    visited.add(k);
                    q.add(new Object[]{k, distance + 1});
                }
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (T v : map.keySet()) {
            builder.append(v.toString() + ": ");
            for (T k : map.keySet()) {
                builder.append(k.toString() + " ");
            }
            builder.append("\n");
        }
        System.out.println(builder.toString());
        return builder.toString();
    }

    //    Flood Fill, time: O(n^2), space: O(n)
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int m = image.length;
        int n = image[0].length;
        boolean[][] isVisited = new boolean[m][n];
        int oldColor = image[sr][sc];

        if (oldColor == color) {
            return image;
        }
        dfs(image, oldColor, color, sr, sc, isVisited);

        return image;
    }

    public static void dfs(int[][] image, int start, int color, int i, int j, boolean[][] isVisited) {
        int m = image.length;
        int n = image[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || image[i][j] != start || isVisited[i][j]) {
            return;
        }

        image[i][j] = color;
        isVisited[i][j] = true;

        dfs(image, start, color, i, j + 1, isVisited);
        dfs(image, start, color, i, j - 1, isVisited);
        dfs(image, start, color, i + 1, j, isVisited);
        dfs(image, start, color, i - 1, j, isVisited);
    }

    //    Number of Islands, time: O(n^2), space: O(n^2)
    public int numIslands(char[][] grid) {
        int sum = 0;
        int m = grid.length, n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    findAIsland(grid, i, j);
                    sum++;
                }
            }
        }

        return sum;
    }

    public void findAIsland(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';
        findAIsland(grid, i + 1, j);
        findAIsland(grid, i - 1, j);
        findAIsland(grid, i, j + 1);
        findAIsland(grid, i, j - 1);
    }

    //    Max Area of Island, time: O(n^2), space: O(n), beat time 100% and beat space 50%
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        int m = grid.length, n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int area = findAreaIsland(grid, i, j);
                    if (area > max) {
                        max = area;
                    }
                }
            }
        }

        return max;
    }

    public int findAreaIsland(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }

        grid[i][j] = 0;
        int area1 = findAreaIsland(grid, i + 1, j);
        int area2 = findAreaIsland(grid, i - 1, j);
        int area3 = findAreaIsland(grid, i, j + 1);
        int area4 = findAreaIsland(grid, i, j - 1);

        return 1 + area1 + area2 + area3 + area4;
    }

    //    Surrounded Regions, time: O(n^2), space: O(n^2)
    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] == false && board[i][j] == 'O') {
                    List<List<Integer>> vertexs = new ArrayList<>();
                    boolean isAbleColor = colorRegion(board, i, j, visited, vertexs);
                    if (isAbleColor) {
                        for (List<Integer> vertex : vertexs) {
                            board[vertex.get(0)][vertex.get(1)] = 'X';
                        }
                    }
                }
            }
        }
    }

    public boolean colorRegion(char[][] grid, int i, int j, boolean[][] visited, List<List<Integer>> vertexs) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return false;
        }
        if (visited[i][j] || grid[i][j] == 'X') {
            return true;
        }

        visited[i][j] = true;
        vertexs.add(new ArrayList<>(List.of(i, j)));

        boolean isColor1 = colorRegion(grid, i + 1, j, visited, vertexs);
        boolean isColor2 = colorRegion(grid, i - 1, j, visited, vertexs);
        boolean isColor3 = colorRegion(grid, i, j + 1, visited, vertexs);
        boolean isColor4 = colorRegion(grid, i, j - 1, visited, vertexs);

        boolean canColor = isColor1 && isColor2 && isColor3 && isColor4;
        return canColor;
    }

    //    Course Schedule,bfs, time: O(V+E), space O(V+E), V la tong so dinh E la tong so canh
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int count = 0;
        int[] inCommingEdges = new int[numCourses]; // luu tong so canh di vao 1 dinh
        List<Integer>[] outCommingEdges = new LinkedList[numCourses]; // luu cac canh di ra tu 1 dinh
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            outCommingEdges[i] = new LinkedList<>();
        }
        for (int[] pair : prerequisites) {
            inCommingEdges[pair[1]]++;
            outCommingEdges[pair[0]].add(pair[1]);
        }
        for (int i = 0; i < numCourses; i++) {
            if (inCommingEdges[i] == 0) {
                q.add(i); // them cac dinh xuat phat khi chay bfs vi chung ko co canh di ra, chac chan phai la cac dinh xuat phat
            }
        }

        while (!q.isEmpty()) {
            int currentVertex = q.poll();
            count++;

            for (int i : outCommingEdges[currentVertex]) {
                // moi lan duyet qua tru 1 tong so canh di vao 1 dinh, den luc bang 0 moi them vao queue duyet de tranh bi lap duyet dinh
                if (--inCommingEdges[i] == 0) {
                    q.add(i);
                }
            }
        }
        // neu xuat hien cycle trong do thi thi khong the nao duyet het cac dinh duoc (inCommingEdges contraint da lam dieu nay)
        return count == numCourses;
    }

    //    Optimal solution, use dfs to detect a cycle in graph, if has cycle then return false, time: O(E)
    public boolean dfsCanFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] vertexs = new LinkedList[numCourses];
        boolean[] visited = new boolean[numCourses];
        boolean[] marked = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            vertexs[i] = new LinkedList<>();
        }
        for (int[] pair : prerequisites) {
            vertexs[pair[0]].add(pair[1]);
        }

        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                if (isCyclic(vertexs, i, visited, marked)) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isCyclic(List<Integer>[] vertexs, int i, boolean[] visited, boolean[] marked) {
        visited[i] = true;
        for (int j : vertexs[i]) {
            if (!visited[j]) {
                if (isCyclic(vertexs, j, visited, marked)) {
                    return true;
                }
            } else if (!marked[j]) {
                return true;
            }
        }
        marked[i] = true;

        return false;
    }

    //    Course Schedule II, time: O(V+E), space: O(V+E)
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int count = 0;
        int[] inCommingEdges = new int[numCourses]; // luu tong so canh di vao 1 dinh
        List<Integer>[] outCommingEdges = new LinkedList[numCourses]; // luu cac canh di ra tu 1 dinh
        Queue<Integer> q = new LinkedList<>();
        int[] result = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            outCommingEdges[i] = new LinkedList<>();
        }
        for (int[] pair : prerequisites) {
            inCommingEdges[pair[0]]++;
            outCommingEdges[pair[1]].add(pair[0]);
        }
        for (int i = 0; i < numCourses; i++) {
            if (inCommingEdges[i] == 0) {
                q.add(i); // them cac dinh xuat phat khi chay bfs vi chung ko co canh di ra, chac chan phai la cac dinh xuat phat
            }
        }

        while (!q.isEmpty()) {
            int currentVertex = q.poll();
            result[count] = currentVertex;
            count++;

            for (int i : outCommingEdges[currentVertex]) {
                // moi lan duyet qua tru 1 tong so canh di vao 1 dinh, den luc bang 0 moi them vao queue duyet de tranh bi lap duyet dinh
                if (--inCommingEdges[i] == 0) {
                    q.add(i);
                }
            }
        }

        if (count == numCourses) {
            return result;
        }
        return new int[0];
    }

//    Longest Increasing Path in a Matrix, bad approach, time: O(m*n), space O(m*n)
    public int longestIncreasingPath(int[][] matrix) {
        int maxPath = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        Map<String, List<String>> vertexs = new HashMap<>();
        Map<String,Boolean> inCommingEdges = new HashMap<>();
        Map<String,Integer> memo = new HashMap<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                List<String> vertex = new ArrayList<>();
                if (i > 0 && matrix[i][j] < matrix[i - 1][j]) {
                    vertex.add((i - 1) + "," + j);
                    inCommingEdges.put((i - 1) + "," + j, true);
                }
                if (i < m - 1 && matrix[i][j] < matrix[i + 1][j]) {
                    vertex.add((i + 1) + "," + j);
                    inCommingEdges.put((i + 1) + "," + j, true);
                }
                if (j > 0 && matrix[i][j] < matrix[i][j - 1]) {
                    vertex.add(i + "," + (j - 1));
                    inCommingEdges.put(i + "," + (j - 1), true);
                }
                if (j < n - 1 && matrix[i][j] < matrix[i][j + 1]) {
                    vertex.add(i + "," + (j + 1));
                    inCommingEdges.put(i + "," + (j + 1), true);
                }

                vertexs.put(i + "," + j, vertex);
            }
        }

        for(String vertex : vertexs.keySet()){
            if(!inCommingEdges.containsKey(vertex)){
                int maxPathLen = dfsIncreasePath(vertexs, vertex,memo);
                if(maxPathLen>maxPath){
                    maxPath = maxPathLen;
                }
            }
        }

        return maxPath;
    }
    public int dfsIncreasePath(Map<String, List<String>> vertexs, String vertex,Map<String,Integer> memo){
        if(vertexs.get(vertex).size()==0){
            return 1;
        }
        if(memo.containsKey(vertex)){
            return  memo.get(vertex);
        }

        int max = 0;
        for(String i: vertexs.get(vertex)){
            int maxPath = 1+ dfsIncreasePath(vertexs, i,memo);
            max = maxPath>max ? maxPath: max;
        }
        memo.put(vertex, max);

        return max;
    }
    // good approach, use dfs and memolize, time: O(m*n), space: O(1)
    public int optimizeLongestIncreasingPath1(int[][] matrix) {
        int maxPath = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        Map<String,Integer> dp = new HashMap<>();

        for(int i=0;i<m;i++){
            for(int j=0; j<n;j++){
                int max = optimizeDfsIncreasePath1(matrix,i,j,dp, -1);
                maxPath = max>maxPath? max: maxPath;
            }
        }

        return maxPath;
    }
    public int optimizeDfsIncreasePath1(int[][] matrix, int i, int j, Map<String,Integer> dp, int pastValue){
        if(i<0 || i>=matrix.length || j<0 || j>=matrix[0].length || matrix[i][j]<=pastValue){
            return 0;
        }
        String key = i+ ","+ j;
        if(dp.containsKey(key)){
            return  dp.get(key);
        }

        int max =0;
        max = Math.max(1+ optimizeDfsIncreasePath1(matrix,i+1,j,dp,matrix[i][j]), max);
        max = Math.max(1+ optimizeDfsIncreasePath1(matrix,i-1,j,dp,matrix[i][j]), max);
        max = Math.max(1+ optimizeDfsIncreasePath1(matrix,i,j+1,dp,matrix[i][j]), max);
        max = Math.max(1+ optimizeDfsIncreasePath1(matrix,i,j-1,dp,matrix[i][j]), max);
        dp.put(key, max);

        return max;
    }

}
