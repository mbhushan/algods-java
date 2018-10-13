/**
 387. First Unique Character in a String
 https://leetcode.com/problems/first-unique-character-in-a-string/description/

 Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

 Examples:

 s = "leetcode"
 return 0.

 s = "loveleetcode",
 return 2.
 */
public class FirstUniqueChar {

    public static void main(String[] args) {
        FirstUniqueChar fu = new FirstUniqueChar();

        int ans = fu.firstUniqChar("aaadd");
        System.out.println(ans);
    }

    public int firstUniqChar(String s) {
        int freq [] = new int[26];
        for(int i = 0; i < s.length(); i ++)
            freq [s.charAt(i) - 'a'] ++;
        for(int i = 0; i < s.length(); i ++)
            if(freq [s.charAt(i) - 'a'] == 1)
                return i;
        return -1;
    }

    public int firstUniqCharWrong(String s) {
        if (s == null || s.length() < 1) {
            return -1;
        }

        if (s.length() == 1) {
            return 0;
        }

        int start = 0, end = 1;
        int res = -1;
        while (end < s.length()) {
            while ((end < s.length()) && (s.charAt(end) != s.charAt(start))) {
                ++end;
            }
            char ch = s.charAt(start);
            while ((start < s.length()) && (s.charAt(start) == ch)) {
                ++start;
            }
            if (start == s.length()) {
                return -1;
            }
            end = start+1;

            if (end >= s.length()) {
                return start-1;
            }

        }

        return -1;
    }
}
