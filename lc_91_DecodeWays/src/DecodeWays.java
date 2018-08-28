import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**

 91. Decode Ways
 https://leetcode.com/problems/decode-ways/description/

 A message containing letters from A-Z is being encoded to numbers using the following mapping:

 'A' -> 1
 'B' -> 2
 ...
 'Z' -> 26
 Given a non-empty string containing only digits, determine the total number of ways to decode it.

 Example 1:

 Input: "12"
 Output: 2
 Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 Example 2:

 Input: "226"
 Output: 3
 Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).


 ===============
 INPUT / OUTPUT
 ===============
 */
public class DecodeWays {

    private static String S = "0ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String [] args) {
        DecodeWays dw = new DecodeWays();

        dw.numDecodings("226");

    }

    public int numDecodings(String s) {



        if (s == null || s.length() < 1) {
            return 0;
        }

        int [] A = new int[s.length()];

        for (int i=0; i<s.length(); i++) {
            A[i] = Character.getNumericValue(s.charAt(i));
        }

        System.out.println(Arrays.toString(A));

        List<String> results = new ArrayList<>();
        StringBuffer sb = new StringBuffer();

        decodeWays(A, 0, sb, results);

        return results.size();
    }

    private void decodeWays(int [] A, int index, StringBuffer sb, List<String> results) {
        if (index == A.length) {
            System.out.println(sb);
            results.add(sb.toString());
            return;
        }

        if (index > A.length) {
            return;
        }

        int i = A[index];
        char ch = S.charAt(i);
        sb.append(ch);
        decodeWays(A, index+1, sb, results);
        sb.deleteCharAt(sb.length()-1);

        if (index+1 < A.length) {
            int num = 10 * A[index] + A[index+1];
            if (num <= 26) {
                ch = S.charAt(num);
                sb.append(ch);
                decodeWays(A, index+2, sb, results);
                sb.deleteCharAt(sb.length() - 1);
            }
        }

    }


}
