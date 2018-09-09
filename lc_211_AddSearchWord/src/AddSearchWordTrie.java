import java.util.HashMap;
import java.util.Map;

/**
 211. Add and Search Word
 https://leetcode.com/problems/add-and-search-word-data-structure-design/description/

 Design a data structure that supports the following two operations:

 void addWord(word)
 bool search(word)
 search(word) can search a literal word or a regular expression string containing only letters a-z or
 .. A . means it can represent any one letter.

 Example:
 addWord("bad")
 addWord("dad")
 addWord("mad")
 search("pad") -> false
 search("bad") -> true
 search(".ad") -> true
 search("b..") -> true
 Note:
 You may assume that all words are consist of lowercase letters a-z.

 */
public class AddSearchWordTrie {

    private TrieNode root;

    public static void main(String[] args) {

    }

    public void initTrie() {
        this.root = new TrieNode('0');
    }

    public void insert(String word) {
        
    }
}

class TrieNode {
    char ch;
    boolean isLeaf;
    Map<Character, TrieNode> children;

    TrieNode(char ch) {
        this.ch = ch;
        this.isLeaf = false;
        this.children = new HashMap<>();
    }
}
