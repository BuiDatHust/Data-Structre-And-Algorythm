package Trie;

public class Trie {
    public Node root;

    public Trie() {
        this.root = new Node();
    }

    // time: O(s), space: O(1), s is len of word
    public void insert(String word) {
        Node p = root;
        if (search(word)) {
            return;
        }

        for (int i = 0; i < word.length(); i++) {
            int c = word.charAt(i) - 'a';

            if (p.child[c] == null) {
                p.child[c] = new Node();
            }
            p = p.child[c];
            p.count++;
        }

        p.isEndOfWord = true;
    }

    // time: O(s), space: O(1), s is len of word
    public boolean search(String word) {
        Node p = root;
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
        Node p = root;
        for (int i = 0; i < prefix.length(); i++) {
            int c = prefix.charAt(i) - 'a';

            if (p.child[c] == null) {
                return false;
            }
            p = p.child[c];
        }

        return true;
    }

    public class Node {
        public int count;
        public Node[] child = new Node[26];
        public boolean isEndOfWord = false;

        public Node() {
            count = 0;
            for (int i = 0; i < 26; i++) {
                child[i] = null;
            }
        }
    }
}
