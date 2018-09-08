import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 639. Decode Ways II
 https://leetcode.com/problems/decode-ways-ii/description/

 A message containing letters from A-Z is being encoded to numbers using the following mapping way:

 'A' -> 1
 'B' -> 2
 ...
 'Z' -> 26
 Beyond that, now the encoded string can also contain the character '*', which can be treated as one
 of the numbers from 1 to 9.

 Given the encoded message containing digits and the character '*', return the total number of ways to decode it.

 Also, since the answer may be very large, you should return the output mod 109 + 7.

 Example 1:
 Input: "*"
 Output: 9
 Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".
 Example 2:
 Input: "1*"
 Output: 9 + 9 = 18
 */

public class DecodeWays2 {

    private static String S = "0ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int M = 1000000007;

    public static void main(String[] args) {

        DecodeWays2 dw = new DecodeWays2();

        String [] inputs = {
          "*1", "1*", "22*", "123*"
        };

        for (String s: inputs) {
            System.out.println("input: " + s);
            System.out.println("DP count: " + dw.numDecodings(s));
            System.out.println("DP Optimized count: " + dw.numDecodingsDP(s));
            System.out.println();
        }

        //dw.numDecodings("1*");

    }

    public int numDecodingsDP(String s) {
        long[] dp = new long[s.length() + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '*' ? 9 : s.charAt(0) == '0' ? 0 : 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '*') {
                dp[i + 1] = 9 * dp[i];
                if (s.charAt(i - 1) == '1')
                    dp[i + 1] = (dp[i + 1] + 9 * dp[i - 1]) % M;
                else if (s.charAt(i - 1) == '2')
                    dp[i + 1] = (dp[i + 1] + 6 * dp[i - 1]) % M;
                else if (s.charAt(i - 1) == '*')
                    dp[i + 1] = (dp[i + 1] + 15 * dp[i - 1]) % M;
            } else {
                dp[i + 1] = s.charAt(i) != '0' ? dp[i] : 0;
                if (s.charAt(i - 1) == '1')
                    dp[i + 1] = (dp[i + 1] + dp[i - 1]) % M;
                else if (s.charAt(i - 1) == '2' && s.charAt(i) <= '6')
                    dp[i + 1] = (dp[i + 1] + dp[i - 1]) % M;
                else if (s.charAt(i - 1) == '*')
                    dp[i + 1] = (dp[i + 1] + (s.charAt(i) <= '6' ? 2 : 1) * dp[i - 1]) % M;
            }
        }
        return (int) dp[s.length()];
    }

    public int numDecodings(String s) {

        long first = 1, second = s.charAt(0) == '*' ? 9 : s.charAt(0) == '0' ? 0 : 1;
        for (int i = 1; i < s.length(); i++) {
            long temp = second;
            if (s.charAt(i) == '*') {
                second = 9 * second;
                if (s.charAt(i - 1) == '1')
                    second = (second + 9 * first) % M;
                else if (s.charAt(i - 1) == '2')
                    second = (second + 6 * first) % M;
                else if (s.charAt(i - 1) == '*')
                    second = (second + 15 * first) % M;
            } else {
                second = s.charAt(i) != '0' ? second : 0;
                if (s.charAt(i - 1) == '1')
                    second = (second + first) % M;
                else if (s.charAt(i - 1) == '2' && s.charAt(i) <= '6')
                    second = (second + first) % M;
                else if (s.charAt(i - 1) == '*')
                    second = (second + (s.charAt(i) <= '6' ? 2 : 1) * first) % M;
            }
            first = temp;
        }
        return (int) second;
    }

    public int numDecodings(String s, int index) {

        if (index >= s.length()) {
            return 0;
        }

        int count = 0;

        //char ch = s.charAt(index);

//        if (ch == '*') {
//            count += 9;
//        } else {
//            count += 1;
//        }

        count += numDecodings(s, index+1);

        if (index+1 < s.length()) {
            if (s.charAt(index) == '*' || s.charAt(index+1) == '*') {
                String st = s.substring(index, index + 2);
                count += getStarCombCount(st);
            } else {
                int x = Character.getNumericValue(s.charAt(index));
                int y = Character.getNumericValue(s.charAt(index+1));
                int num = 10*x + y;
                if (num <= 26) {
                    count += 1;
                    count += numDecodings(s, index+2);
                }
            }
        }


        return count;
    }

    private int getStarCombCount(String s) {
        if (s.equals("**")) {
            return 26;
        } else if (s.charAt(0) == '*') {
            Set<String> hset = new HashSet<>();
            String [] arr = {"*1", "*2", "*3", "*4", "*5", "*6", "*0"};
            for (String a: arr) {
               hset.add(a);
            }

            if (hset.contains(s)) {
                return 11;
            } else {
                return 10;
            }

        } else if (s.equals("1*")){
            return 18;
        } else if (s.equals("2*")) {
            return 15;
        } else {
            return 9;
        }
    }

    private void numDecodings(char [] A, int index, List<String> result) {

        if (index == A.length) {
            System.out.println(result);

            return;
        }

        if (index > A.length) {
            return;
        }

        char ch = A[index];

        if (ch == '*') {
            result.add("ABCDEFGHI");
        } else {
            int n = Character.getNumericValue(ch);
            result.add(String.valueOf(S.charAt(n)));
        }

        numDecodings(A, index+1, result);

        result.remove(result.size()-1);

        if (index+1 < A.length) {
            if (A[index] == '*' || A[index+1] == '*') {

            }
        }






    }
}
