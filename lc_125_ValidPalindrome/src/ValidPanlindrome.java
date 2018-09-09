/**
 125. Valid Palindrome
 https://leetcode.com/problems/valid-palindrome/description/

 Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

 Note: For the purpose of this problem, we define empty string as valid palindrome.

 Example 1:

 Input: "A man, a plan, a canal: Panama"
 Output: true
 Example 2:

 Input: "race a car"
 Output: false

 */
public class ValidPanlindrome {

    public static void main(String[] args) {
        ValidPanlindrome vp = new ValidPanlindrome();

        String [] inputs = {"A man, a plan, a canal: Panama", "race a car"};

        for (String s: inputs) {
            System.out.println("input: " + s);
            System.out.println("is palindrome: " + vp.isPalindrome(s));
            System.out.println();
        }
    }

    public boolean isPalindrome(String s) {

        String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String rev = new StringBuffer(actual).reverse().toString();
        return actual.equals(rev);

    }
}
