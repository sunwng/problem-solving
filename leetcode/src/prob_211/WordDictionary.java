package prob_211;

import java.util.*;

class WordDictionary {

    class Trie {

        Map<Character, Trie> childs;

        boolean isEnd;

        public Trie() {
            childs = new HashMap<>();
            isEnd = false;
        }
    }

    Trie root;

    public WordDictionary() {
        root = new Trie();
    }

    public void addWord(String word) {
        adder(root, word, 0);
    }

    void adder(Trie current, String word, int idx) {
        if (word.length() == idx) {
            current.isEnd = true;
            return;
        }
        if (!current.childs.containsKey(word.charAt(idx))) {
            current.childs.put(word.charAt(idx), new Trie());
        }
        adder(current.childs.get(word.charAt(idx)), word, idx + 1);
    }

    public boolean search(String word) {
        return searcher(root, word, 0);
    }

    public boolean searcher(Trie current, String word, int idx) {
        if (word.length() == idx) {
            return current.isEnd;
        }

        if (word.charAt(idx) == '.') {
            for (char charI : current.childs.keySet()) {
                if (searcher(current.childs.get(charI), word, idx + 1)) return true;
            }
        }

        if (!current.childs.containsKey(word.charAt(idx))) return false;

        return searcher(current.childs.get(word.charAt(idx)), word, idx + 1);
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
