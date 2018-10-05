/**
 38. Count and Say
 https://leetcode.com/problems/count-and-say/description/
 The count-and-say sequence is the sequence of integers with the first five terms as following:

 1.     1
 2.     11
 3.     21
 4.     1211
 5.     111221
 1 is read off as "one 1" or 11.
 11 is read off as "two 1s" or 21.
 21 is read off as "one 2, then one 1" or 1211.

 Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence.

 Note: Each term of the sequence of integers will be represented as a string.



 Example 1:

 Input: 1
 Output: "1"
 Example 2:

 Input: 4
 Output: "1211"

 */
public class CountSay {

    public static void main(String[] args) {
        CountSay cs = new CountSay();

        System.out.println(cs.countAndSay(10));

    }

    public String countAndSay(int n) {
        int count = 1;
        char say = '1';
        StringBuffer sb = new StringBuffer("1");
        StringBuffer prev = new StringBuffer();
        for (int i=1; i<=n; i++) {
            prev = sb;
            sb = new StringBuffer();
            count=1;
            say = prev.charAt(0);
            for (int j=1; j<prev.length(); j++) {
                if (prev.charAt(j) != say) {
                    sb.append(count);
                    sb.append(say);
                    count = 1;
                    say = prev.charAt(j);
                } else {
                    ++count;
                }
            }
            sb.append(count).append(say);
        }

        return sb.toString();
    }
}
