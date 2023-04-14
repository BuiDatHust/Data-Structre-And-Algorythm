package DSU;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DSUProb prob = new DSUProb();
        List<List<Integer>> pairs = new ArrayList<>();
        pairs.add(new ArrayList<>(List.of(1,2)));
        pairs.add(new ArrayList<>(List.of(0,3)));
        pairs.add(new ArrayList<>(List.of(0,2)));

        System.out.println(prob.optimizeSmallestStringWithSwaps("dcab", pairs));
    }
}
