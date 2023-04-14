package DSU;

//implementation:
//quick find: union O(n) and find O(1) in every case
//quick union: union O(n) and find O(n) but it is O(n) in worst case when every node in a  line
//union by rank: to avoid case which every node in a  line, when union we choose node has heigher rank to root
//Path Compression Optimization: to optimize find operation when find the same element found, in first same every element, we can update the root of every element in travesal

//optimized implementation with union by rank and path compression, union O(α(N)), find O(α(N)) mean that linear constant
public class UnionFind {
    private int[] root;
    private int[] rank;

    public UnionFind(int size) {
        root = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i++) {
            root[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int x) {
        if (root[x] == x) {
            return x;
        }

        root[x] = find(root[x]); // update root of every node in travesal
        return root[x];
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
        }
    }

    public boolean connected(int x, int y){
        return find(x)==find(y);
    }
}
