import java.util.Arrays;

/**
 14. Longest Common Prefix
 https://leetcode.com/problems/longest-common-prefix/description/

 Write a function to find the longest common prefix string amongst an array of strings.

 If there is no common prefix, return an empty string "".

 Example 1:

 Input: ["flower","flow","flight"]
 Output: "fl"
 Example 2:

 Input: ["dog","racecar","car"]
 Output: ""
 Explanation: There is no common prefix among the input strings.
 Note:

 All given inputs are in lowercase letters a-z.

 =================input / output ================
 input: [flower, flow, flight]
 longest common prefix: fl

 */
public class LongestCommonPrefix {
    public static void main(String[] args) {

        LongestCommonPrefix lcp = new LongestCommonPrefix();

        String [] S1 = {"dog","racecar","car"};
        String [] S = {"flower","flow","flight"};
        System.out.println("input: " + Arrays.toString(S));
        String res = lcp.longestCommonPrefix(S);
        System.out.println("longest common prefix: " + res);

    }



    public String longestCommonPrefix(String[] strs) {
        if (strs == null) {
            return null;
        }
        if (strs.length < 1) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }

        //steps.
        //sort the string -> [ab, abc, abcd] and then compare the first and the last strings.
        Arrays.sort(strs);

        String first = strs[0];
        String second = strs[strs.length-1];
        StringBuffer sb = new StringBuffer();

        for (int i=0; i<first.length(); i++) {
            if (i <= second.length() && first.charAt(i) == second.charAt(i)) {
                sb.append(first.charAt(i));
            } else  {
                break;
            }
        }

        return sb.toString();
    }

    public String longestCommonPrefixLinear(String[] strs) {
        if(strs == null || strs.length == 0)    return "";
        String pre = strs[0];
        int i = 1;
        while(i < strs.length){
            while(strs[i].indexOf(pre) != 0)
                pre = pre.substring(0,pre.length()-1);
            i++;
        }
        return pre;
    }
}
