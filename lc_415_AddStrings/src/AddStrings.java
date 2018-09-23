/**
 415. Add Strings
 https://leetcode.com/problems/add-strings/description/

 Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

 Note:

 The length of both num1 and num2 is < 5100.
 Both num1 and num2 contains only digits 0-9.
 Both num1 and num2 does not contain any leading zero.
 You must not use any built-in BigInteger library or convert the inputs to integer directly.

 ================= input / output ===========
 input: [1234, 9876]
 sum: 11110

 */

public class AddStrings {
    public static void main(String[] args) {

        AddStrings as = new AddStrings();

        String s1 = "1234";
        String s2 = "9876";

        System.out.println("input: [" + s1 + ", " + s2 + "]");

        System.out.println("sum: " + as.addStrings(s1, s2));

    }

    public String addStrings(String num1, String num2) {
        StringBuffer sb = new StringBuffer();

        if (num1 == null || num2 == null) {
            return sb.toString();
        }

        //Steps
        //a. start at the end of the strings.
        //b. add it char by char and maintain the carry

        int len1 = num1.length()-1;
        int len2 = num2.length()-1;

        int i = len1,  j=len2;
        int carry = 0;

        while (i >= 0 && j >= 0 ) {
            int x = Character.getNumericValue(num1.charAt(i));
            int y = Character.getNumericValue(num2.charAt(j));

            int res = x + y + carry;
            carry = res / 10;
            res = res % 10;

            sb.append(res);
            --i;
            --j;
        }

        while ( i >= 0) {
            int x = Character.getNumericValue(num1.charAt(i));
            int res = x + carry;
            carry = res / 10;
            res = res % 10;
            sb.append(res);
            --i;
        }

        while ( j >= 0) {
            int y = Character.getNumericValue(num2.charAt(j));
            int res = y + carry;
            carry = res / 10;
            res = res % 10;
            sb.append(res);
            --j;

        }

        if (carry > 0) {
            sb.append(carry);
        }

        return sb.reverse().toString();

    }
}
