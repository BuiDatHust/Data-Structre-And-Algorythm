package Graphs;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Graph<String> graph = new Graph<String>();
        graph.addVertex("a");
        graph.addVertex("b");
        graph.addVertex("c");
        graph.addVertex("d");
        graph.addVertex("e");
        graph.addVertex("f");
        graph.addVertex("l");
        graph.addEdge("a", "b", false);
        graph.addEdge("a", "c", false);
        graph.addEdge("b", "d", false);
        graph.addEdge("c", "e", false);
        graph.addEdge("d", "f", false);

//        graph.depthFirstTravesal("a");
//        System.out.println(graph.isHasPath("e", "f"));
//        System.out.println(graph.countConnectedComponent());
//        System.out.println(graph.countLargestComponent());
//        System.out.println(graph.shortestPath("a", "d"));

        int[][] image = {
                {9,9,4},{6,6,8},{2,1,1}
        };
        int[][] prerequisites= {{1,0},{2,3}};
        System.out.println(graph.optimizeLongestIncreasingPath1(image));;
    }
}
