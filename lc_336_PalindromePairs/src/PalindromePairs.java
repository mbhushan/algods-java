import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PalindromePairs {

    public static void main(String[] args) {
        String s = "mani";
        StringBuffer sb = new StringBuffer(s);
        System.out.println(sb.reverse());

        String [] words = {"abcd","dcba","lls","s","sssll"};

        PalindromePairs pp = new PalindromePairs();

        List<List<Integer>> ans = pp.palindromePairs(words);

        for (List<Integer> list: ans) {
            System.out.println(list);
        }

    }

    public List<List<Integer>> palindromePairs(String[] words) {
        //Steps:
        //a. checkR for empty string
        //b. check for reverse string
        //c. check for s1[0:i], s2[i+1] == s2 is palindrome
        //d. check for s1[0:i] == s2 and s2[i+1] is palindrome.
        List<List<Integer>> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        int index = 0;
        for (String w: words) {
            map.put(w, index);
            ++index;
        }



        index = 0;
        for (String w: words) {
            if (isPalindrome(w) && map.containsKey("")) {
                int emptyIndex = map.get("");
                result.add(Arrays.asList(emptyIndex, index));
                result.add(Arrays.asList(index, emptyIndex));
            }
            StringBuffer sb = new StringBuffer(w);
            String rev = sb.reverse().toString();
            //System.out.println();
           // System.out.println("w: " + w + " and rev: " + rev);
            if (map.containsKey(rev)) {
             //   System.out.println("rev: " + rev + ", found");
                int revIndex = map.get(rev);
                if (revIndex != index) {
                    result.add(Arrays.asList(revIndex, index));
                }

            }
            ++index;
        }

        //find the pair s1, s2 that
        //case1 : s1[0:cut] is palindrome and s1[cut+1:] = reverse(s2) => (s2, s1)
        //case2 : s1[cut+1:] is palindrome and s1[0:cut] = reverse(s2) => (s1, s2)
        for(int i = 0; i < words.length; i++){
            String cur = words[i];
            for(int cut = 1; cut < cur.length(); cut++){
                if(isPalindrome(cur.substring(0, cut))){
                    //String cut_r = reverseStr(cur.substring(cut));
                    String cut_r = new StringBuffer(cur.substring(cut)).reverse().toString();
                    if(map.containsKey(cut_r)){
                        int found = map.get(cut_r);
                        if(found == i) continue;
                        result.add(Arrays.asList(found, i));
                    }
                }
                if(isPalindrome(cur.substring(cut))){
                    //String cut_r = reverseStr(cur.substring(0, cut));
                    String cut_r = new StringBuffer(cur.substring(0, cut)).reverse().toString();
                    if(map.containsKey(cut_r)){
                        int found = map.get(cut_r);
                        if(found == i) continue;
                        result.add(Arrays.asList(i, found));
                    }
                }
            }
        }

        return result;

    }

    private boolean isPalindrome(String s) {
        if (s.isEmpty() || s.length() <= 1) {
            return true;
        }
        int i = 0;
        int j = s.length()-1;

        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            ++i;
            --j;
        }

        return true;
    }
}
