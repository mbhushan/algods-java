import java.util.HashSet;
import java.util.Set;

/**
 266. Palindrome Permutation
 https://leetcode.com/problems/palindrome-permutation/description/

 Given a string, determine if a permutation of the string could form a palindrome.

 Example 1:

 Input: "code"
 Output: false
 Example 2:

 Input: "aab"
 Output: true
 Example 3:

 Input: "carerac"
 Output: true
 */
public class PalindromePartition {

    public static void main(String[] args) {

    }

    public boolean canPermutePalindrome(String s) {
        if (s == null || s.length() < 1) {
            return false;
        }
        Set<Character> set = new HashSet<>();
        for (char ch: s.toCharArray()) {
            if (set.contains(ch)) {
                set.remove(ch);
            } else {
                set.add(ch);
            }

        }

        return set.size() == 1 || set.isEmpty();
    }
}
