package DynamicPrograming;

import java.util.*;

public class DPProblem {
    public static int count=0;
    public static int gridTraveler(int m, int n, HashMap<String, Integer> memo){
        String key = Integer.toString(m) + ','+ n;
        if(memo.containsKey(key)) return  memo.get(key);
        if(m==1 && n==1) return 1;
        if(m==0 || n==0) return 0;

        int value = gridTraveler(m-1,n, memo) + gridTraveler(m,n-1, memo);
        memo.put(key, value);
        return value;
    }

    public static Boolean canSum(int sum, ArrayList<Integer> numbers, HashMap<Integer, Boolean> memo){
        if(memo.containsKey(sum)){
            return memo.get(sum);
        }
        if(sum==0) return true;
        if(sum<0) return false;
        DPProblem.count+=1;

        for(int number : numbers){
            DPProblem.count+=1;
            int remainder = sum - number;
            Boolean value = canSum(remainder,numbers,memo);
            memo.put(remainder, value);
            if(value==true){
                return true;
            }
        }

        return false;
    }

    public static int countFibTabulation(int n){
        if(n<2){
            return n;
        }
        ArrayList<Integer> list = new ArrayList<>(List.of(0,1));
        for(int i=2; i<=n; i++){
            int value = list.get(i-1) + list.get(i-2);
            list.add(value);
        }

        return list.get(list.size()-1);
    }

    public static int gridTravelerTabulation(int m , int n){
        int[][] result = new int[m+1][n+1];
        result[1][1] = 1;
        for(int i=0; i<=m;i++){
            for(int j=0;j<=n;j++){
                int current = result[i][j];
                if(i+1<=m) result[i+1][j] += current;
                if(j+1<=n) result[i][j+1] += current;
            }
        }

        return result[m][n];
    }

    public static Boolean canConstruct(String target, String[] wordBank, HashMap<String, Boolean> memo){
        if(memo.containsKey(target)){
            return memo.get(target);
        }
        if(target==""){
            return true;
        }
        DPProblem.count+=1;

        for(String word: wordBank){
            DPProblem.count+=1;
            if(target.indexOf(word)==0){
                String remainder = target.substring(word.length());
                Boolean value = canConstruct(remainder, wordBank, memo);
                memo.put(remainder, value);
                if(value==true){
                    return true;
                }
            }
        }

        return false;
    }

    public static Integer countConstruct(String target, String[] wordBank, HashMap<String, Integer> memo){
        if(memo.containsKey(target)){
            return memo.get(target);
        }
        if(target==""){
            return 1;
        }
        int totalCount =0;

        for(String word: wordBank){
            if(target.indexOf(word)==0){
                String remainder = target.substring(word.length());
                Integer numWaysForRest = countConstruct(remainder, wordBank, memo);
                memo.put(remainder, numWaysForRest);
                totalCount+=numWaysForRest;
            }
        }

        return totalCount;
    }

    public static List<List<String>> allConstruct(String target, String[] wordBank){
        if(target==""){
            return new ArrayList<List<String>>();
        }
        List<List<String>> concatResult = new ArrayList<List<String>>(0);

        for(String word: wordBank){
            if(target.indexOf(word)==0){
                String remainder = target.substring(word.length());
                List<List<String>> constructs = allConstruct(remainder, wordBank);
                if(constructs.isEmpty()) {
                    constructs.add(new ArrayList<>(List.of(word)));
                }

                for(List<String> construct: constructs){
                    Collections.reverse(construct);
                    construct.add(word);
                    Collections.reverse(construct);
                }

                concatResult.addAll(constructs);
            }
        }

        return concatResult;
    }

    public static int min(int x,int y, int z){
        int minXy = x<y ? x : y;
        return minXy<z ? minXy : z;
    }
    public static int minEditDistance(String str1, String str2,int m, int n, HashMap<String, Integer> memo){
        if(m==0){
            return n;
        }
        if(n==0){
            return m;
        }
        if(memo.containsKey(Integer.toString(m)+ n)){
            return memo.get(Integer.toString(m)+ n);
        }

        String key = Integer.toString(m-1,n-1);
        if(str1.charAt(m-1) == str2.charAt(n-1)){
            Integer minDist = minEditDistance(str1,str2,m-1,n-1, memo);
            memo.put(key, minDist);
            return minDist;
        }
        Integer value = 1+ min(
                minEditDistance(str1,str2,m,n-1, memo), //insert
                minEditDistance(str1,str2,m-1,n-1, memo), //replace
                minEditDistance(str1,str2,m-1,n, memo) //remove
        );
        memo.put(key, value);
        return value;
    }

    public static void main(String[] args) {
//        System.out.println(gridTraveler(3,6,new HashMap<String, Integer>(){}));
//        System.out.println(canSum(7, new ArrayList<>(List.of(3,4,7)),new HashMap<Integer, Boolean>(){}));
//        System.out.println(countFibTabulation(6));
//        System.out.println(DPProblem.gridTravelerTabulation(2,3));
//        System.out.println(DPProblem.canConstruct("abcdef", new String[]{"ab","cd","e","ef"}, new HashMap<>()));
////        System.out.println(DPProblem.countConstruct("abcdef", new String[]{"ab","cd","e","ef","abcdef", "cdef"}, new HashMap<>()));
//        System.out.println(DPProblem.allConstruct("abcdef", new String[]{"ab","cd","e","ef","abcdef", "cdef"}));
        System.out.println(DPProblem.minEditDistance("sunday", "saturday",6,8, new HashMap<>()));
        System.out.println(DPProblem.count);
    }
}
