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

 ======================
 INPUT / OUTPUT
 =====================
 inserted words:
 shreya
 shoe
 shona
 hack
 hackerrank
 mani
 manib

 search key: hack
 found slow: true
 found fast: true

 search key: manibh
 found slow: false
 found fast: false

 search key: munib
 found slow: false
 found fast: false

 search key: manib
 found slow: true
 found fast: true

 search key: shrey
 found slow: false
 found fast: false

 search key: shona
 found slow: true
 found fast: true

 search key: shreya
 found slow: true
 found fast: true

 search key: shreyas
 found slow: false
 found fast: false

 regex search operations:
 search key: .anib
 regex search: true

 search key: .ack
 regex search: true

 search key: a.ck
 regex search: false

 search key: mani.
 regex search: true

 search key: sh.na
 regex search: true

 search key: shre..
 regex search: true

 search key: shreya.
 regex search: false

 search key: .manib
 regex search: false

 search key: ...ib
 regex search: true

 search key: .a.k
 regex search: true

 */
public class AddSearchWordTrie {

    private TrieNode root;

    public static void main(String[] args) {

        AddSearchWordTrie as = new AddSearchWordTrie();

        as.initTrie();

        String [] words = {"hack", "hackerrank", "mani", "manib", "shona", "shreya", "shoe"};

        for (String word: words) {
            as.insert(word);
        }

        System.out.println("inserted words: ");
        as.printTrie();

        System.out.println();

        String [] keys = {"hack", "manibh", "munib", "manib", "shrey", "shona", "shreya", "shreyas"};

        for (String k: keys) {
            System.out.println("search key: " + k);
            System.out.println("found slow: " + as.search(k));
            System.out.println("found fast: " + as.searchFast(k));
            System.out.println();
        }

        String [] regexKeys = {".anib", ".ack", "a.ck", "mani.", "sh.na", "shre..", "shreya.", ".manib", "...ib",
                ".a.k"};

        System.out.println("regex search operations: ");
        for (String key: regexKeys) {
            System.out.println("search key: " + key);
            System.out.println("regex search: " + as.regexSearch(key));
            System.out.println();
        }

        //as.insert("manib");

    }

    public void initTrie() {
        this.root = new TrieNode('0');
    }

    public void insert(String word) {
        insertWord(word.toCharArray(), this.root, 0);
    }

    private void insertWord(char [] A, TrieNode node, int index) {
        if (index >= A.length) {
            return;
        }

        char ch = A[index];
        //check if ch exists in trie-node children, if not create one.

        if(node.children.containsKey(ch)) {
           node = node.children.get(ch);
        } else {
            TrieNode tnode = new TrieNode(ch);
            node.children.put(ch, tnode);
            node = tnode;
        }

        if (index == A.length-1) {
            node.isLeaf = true;
        }

        insertWord(A, node, index+1);

    }


    public boolean search(String word) {

        return search(word.toCharArray(), this.root, new StringBuffer());
    }

    public boolean searchFast(String word) {

        return searchFast(word.toCharArray(), this.root, 0);
    }

    private boolean searchFast(char [] A, TrieNode node, int index) {

        if (index == A.length) {
            if (node.children.isEmpty() || node.isLeaf) {
                return true;
            }

            return false;
        }

        boolean flag = false;

        if (node.children.containsKey(A[index])) {
            flag = searchFast(A, node.children.get(A[index]), index+1);
        }

        return flag;
    }

    private boolean search(char [] A, TrieNode node, StringBuffer sb) {
        if (node == null || node.children.size() == 0) {
            return sb.toString().equals(String.valueOf(A));
        }

        boolean flag = false;

        if (node.isLeaf && sb.toString().equals(String.valueOf(A))) {
            return true;
        }


        for (char ch: node.children.keySet()) {
            sb.append(ch);
            flag = search(A, node.children.get(ch), sb);
            if (flag) {
                break;
            }
            sb.deleteCharAt(sb.length()-1);
        }

        return flag;
    }

    public boolean regexSearch(String word) {
        return regexSearch(word.toCharArray(), 0, this.root);
    }

    private boolean regexSearch(char [] A, int index, TrieNode node) {
        //terminating condition
        if (index == A.length) {
            if (node.children.isEmpty() || node.isLeaf) {
                return true;
            }
            return false;
        }

        //core logic.
        boolean flag = false;

        if (A[index] == '.') {
            for (char ch: node.children.keySet()) {
                flag = regexSearch(A, index+1, node.children.get(ch));
                if (flag) {
                    return true;
                }
            }
        } else if (node.children.containsKey(A[index])) {
            flag = regexSearch(A, index+1, node.children.get(A[index]));
            if (flag) {
                return true;
            }
        }

        return flag;
    }

    public void printTrie() {

        printTrie(this.root, new StringBuffer());

    }

    private void printTrie(TrieNode node, StringBuffer sb) {
        if (node == null || node.children.size() == 0) {
            System.out.println(sb);
            return;
        }

        if (node.isLeaf) {
            System.out.println(sb);
        }

        for (char ch: node.children.keySet()) {
            sb.append(ch);
            printTrie(node.children.get(ch), sb);
            sb.deleteCharAt(sb.length()-1);
        }
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
