import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 269. Alien Dictionary
 https://leetcode.com/problems/alien-dictionary/description/

 There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you.
 You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of
 this new language. Derive the order of letters in this language.

 Example 1:

 Input:
 [
 "wrt",
 "wrf",
 "er",
 "ett",
 "rftt"
 ]

 Output: "wertf"
 Example 2:

 Input:
 [
 "z",
 "x"
 ]

 Output: "zx"
 Example 3:

 Input:
 [
 "z",
 "x",
 "z"
 ]

 Output: ""

 Explanation: The order is invalid, so return "".
 Note:

 You may assume all letters are in lowercase.
 You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
 If the order is invalid, return an empty string.
 There may be multiple valid order of letters, return any one of them is fine.

 */
public class AlienDictionary {

    public static void main(String[] args) {
        AlienDictionary ad = new AlienDictionary();

        String [] words = {"wrt",
                "wrf",
                "er",
                "ett",
                "rftt"};

        String ans = ad.alienOrderBFS(words);
        System.out.println("ans: " + ans);


    }

    public String alienOrderBFS(String[] words) {
        Map<Character, Set<Character>> map=new HashMap<Character, Set<Character>>();
        Map<Character, Integer> degree=new HashMap<Character, Integer>();
        String result="";
        if(words==null || words.length==0) return result;
        for(String s: words){
            for(char c: s.toCharArray()){
                degree.put(c,0);
            }
        }
        for(int i=0; i<words.length-1; i++){
            String cur=words[i];
            String next=words[i+1];
            int length=Math.min(cur.length(), next.length());
            for(int j=0; j<length; j++){
                char c1=cur.charAt(j);
                char c2=next.charAt(j);
                if(c1!=c2){
                    Set<Character> set=new HashSet<Character>();
                    if(map.containsKey(c1)) set=map.get(c1);
                    if(!set.contains(c2)){
                        set.add(c2);
                        map.put(c1, set);
                        degree.put(c2, degree.get(c2)+1);
                    }
                    break;
                }
            }
        }
        System.out.println("map: " + map);
        System.out.println("degree: " + degree);

        Queue<Character> q=new LinkedList<Character>();
        for(char c: degree.keySet()){
            if(degree.get(c)==0) q.add(c);
        }
        while(!q.isEmpty()){
            char c=q.remove();
            result+=c;
            if(map.containsKey(c)){
                for(char c2: map.get(c)){
                    degree.put(c2,degree.get(c2)-1);
                    if(degree.get(c2)==0) q.add(c2);
                }
            }
        }
        if(result.length()!=degree.size()) return "";
        return result;
    }

    public String alienOrder(String[] words) {

        boolean [][] matrix = new boolean[26][26];
        int [] nodes = new int[26];
        Arrays.fill(nodes, -1);

        buildGraph(words, matrix, nodes);

        System.out.println("nodes: " + Arrays.toString(nodes));

        StringBuffer sb = new StringBuffer();

        for (int i=0; i<26; i++) {
            if (nodes[i] == 0) {
                boolean flag = dfs(matrix, nodes, sb, i);
                if (!flag) {
                    return "";
                }
            }
        }

        System.out.println("sb: " + sb.toString());

        return sb.reverse().toString();

    }

    public boolean dfs(boolean [][] matrix, int [] nodes, StringBuffer sb, int index) {

        nodes[index] = 1; //visiting
        for (int i=0; i<26; i++) {
            if (matrix[index][i]) {
                if (nodes[i] == 1) {
                    return false; //cycle.
                }
                if (nodes[i] == 0) {
                    boolean flag = dfs(matrix, nodes, sb, i);
                    if (!flag) {
                        return false;
                    }
                }
            }
        }
        nodes[index] = 2;
        sb.append((char)('a' + index));
        return true;
    }

    public void buildGraph(String [] words, boolean [][] matrix, int [] nodes) {

        for (char ch: words[0].toCharArray()) {
            nodes[ch - 'a'] = 0;
        }

        for (int i=1; i<words.length; i++) {
            String prev = words[i-1];
            String curr = words[i];

            for (char ch: curr.toCharArray()) {
                nodes[ch - 'a'] = 0;
            }

            int len = Math.min(prev.length(), curr.length());
            for (int j=0; j<len; j++) {
                char c1 = prev.charAt(j);
                char c2 = curr.charAt(j);

                if (c1 != c2) {
                    matrix[c1 - 'a'][c2 - 'a'] = true;
                }
            }
        }

        printMatrix(matrix, nodes);

    }

    public void printMatrix(boolean [][] M, int [] nodes) {
        System.out.print("  ");
        for (int i=0; i< nodes.length; i++) {
            System.out.print((char)('a' + i) + " ");
        }
        System.out.println();
        for (int i=0; i<M.length; i++) {
            System.out.print((char)('a' + i) + " ");
            for (int j=0; j<M[0].length; j++) {
                if (M[i][j]) {
                    System.out.print(1 + " ");
                } else {
                    System.out.print(0 + " ");
                }
            }
            System.out.println();
        }
    }

    public String alienOrderWrong(String[] words) {

        //go vertically and collect the chars in result buffer.
        int index = 0 ;
        boolean found = false;
        Set<Character> set = new HashSet<Character>();
        StringBuffer sb = new StringBuffer();

        while (!found) {
            found = true;
            boolean firstChar = true;
            char prev = ' ';
            for (String w: words) {
                if (index < w.length()) {
                    char ch = w.charAt(index);
                    if (firstChar) {
                        if (!set.contains(ch)) {
                            set.add(ch);
                            sb.append(ch);
                            found = false;
                        }
                        firstChar = false;
                        // prev = ch;
                    } else {
                        if (set.contains(ch) && ch != prev) {
                            System.out.println("ch: " + ch + ", prev: " + prev);
                            System.out.println("ret: " + sb.toString());
                            return "";
                        }
                        if (!set.contains(ch)) {
                            set.add(ch);
                            sb.append(ch);
                            found = false;
                        }


                    }
                    prev = ch;
                }
            }
            ++index;
        }

        return sb.toString();

    }
}
