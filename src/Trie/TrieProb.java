package Trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrieProb {

    //    Word Break, time: O(m*n), space: O(n*n)
    public boolean wordBreak(String s, List<String> wordDict) {
        TrieBase trie = new TrieBase();
        Map<String, Boolean> memo = new HashMap<>();
        for (String word : wordDict) {
            trie.insert(word);
        }

        return wordBreakRecur(s, wordDict, trie, memo);
    }

    public boolean wordBreakRecur(String s, List<String> wordDict, TrieBase trie, Map<String, Boolean> memo) {
        int n = s.length();
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        if (n == 0) {
            return true;
        }

        for (int i = 1; i <= n; i++) {
            boolean isWordBreak = wordBreakRecur(s.substring(i, n), wordDict, trie, memo);
            memo.put(s.substring(i, n), isWordBreak);
            if (trie.search(s.substring(0, i)) && isWordBreak) {
                return true;
            }
        }

        return false;
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            String wordStart = words[i];
            for (int j = 0; j < words.length; j++) {
                String wordEnd = words[j];
                if (j != i) {
                    String s;
                    if (wordStart.equals("") || wordEnd.equals("") || wordStart.charAt(0) == wordEnd.charAt(wordEnd.length() - 1)) {
                        s = wordStart + wordEnd;
                        if (isPalindrome(s, 0, s.length() - 1)) {
                            result.add(new ArrayList<>(List.of(i, j)));
                        }
                    }
                }
            }
        }

        return result;
    }

    public boolean isPalindrome(String s, int l, int r) {
        boolean isPalindrome = true;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                isPalindrome = false;
                break;
            }
            l++;
            r--;
        }

        return isPalindrome;
    }

    public static void main(String[] args) {
        TrieProb trieProb = new TrieProb();
        String[] words = {"abcd","dcba","lls","s","sssll"};
    }

    public class TrieBase {
        public TreeNode root;

        public TrieBase() {
            this.root = new TreeNode();
        }

        // time: O(s), space: O(1), s is len of word
        public void insert(String word) {
            TreeNode p = root;
            if (search(word)) {
                return;
            }

            for (int i = 0; i < word.length(); i++) {
                int c = word.charAt(i) - 'a';

                if (p.child[c] == null) {
                    p.child[c] = new TreeNode();
                }
                p = p.child[c];
                p.count++;
            }

            p.isEndOfWord = true;
        }

        // time: O(s), space: O(1), s is len of word
        public boolean search(String word) {
            TreeNode p = root;
            for (int i = 0; i < word.length(); i++) {
                int c = word.charAt(i) - 'a';

                if (p.child[c] == null) {
                    return false;
                }
                p = p.child[c];
            }

            return p.isEndOfWord;
        }


        // time: O(s), space: O(1), s is len of word
        public boolean startsWith(String prefix) {
            TreeNode p = root;
            for (int i = 0; i < prefix.length(); i++) {
                int c = prefix.charAt(i) - 'a';

                if (p.child[c] == null) {
                    return false;
                }
                p = p.child[c];
            }

            return true;
        }

        public class TreeNode {
            public int count;
            public TreeNode[] child = new TreeNode[26];
            public boolean isEndOfWord = false;

            public TreeNode() {
                count = 0;
                for (int i = 0; i < 26; i++) {
                    child[i] = null;
                }
            }
        }
    }
}
