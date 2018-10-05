import java.util.Arrays;

/**
 242. Valid Anagram
 https://leetcode.com/problems/valid-anagram/description/

 Given two strings s and t , write a function to determine if t is an anagram of s.

 Example 1:

 Input: s = "anagram", t = "nagaram"
 Output: true
 Example 2:

 Input: s = "rat", t = "car"
 Output: false

 */
public class ValidAnagram {

    public static void main(String[] args) {
        ValidAnagram va = new ValidAnagram();
    }

    public boolean isAnagram(String s, String t) {
        if (s == null || t == null) {
            return false;
        }

        char [] sArr = s.toCharArray();
        char [] tArr = t.toCharArray();

        Arrays.sort(sArr);
        Arrays.sort(tArr);

        if (String.valueOf(sArr).equals(String.valueOf(tArr))) {
            return true;
        }

        return false;

    }
}
