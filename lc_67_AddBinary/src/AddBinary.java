/**
 67. Add Binary
 https://leetcode.com/problems/add-binary/description/

 Given two binary strings, return their sum (also a binary string).

 The input strings are both non-empty and contains only characters 1 or 0.

 Example 1:

 Input: a = "11", b = "1"
 Output: "100"
 Example 2:

 Input: a = "1010", b = "1011"
 Output: "10101"

 */

public class AddBinary {

    public static void main(String [] args) {
        AddBinary ab = new AddBinary();

        String [] a = {"11", "1010"};
        String [] b = {"1", "1011"};

        for (int i=0; i<a.length; i++) {
            System.out.println("input: " + a[i] + ", " + b[i]);
            System.out.println(ab.addBinary(a[i], b[i]));
        }

    }

    public String addBinary(String a, String b) {

        if (a == null) {
            return b;
        } else if (b == null) {
            return a;
        }

        char [] A = a.toCharArray();
        char [] B = b.toCharArray();

        StringBuffer sb = new StringBuffer();

        int i = A.length-1;
        int j = B.length-1;
        int carry = 0;

        while (i >= 0 && j >=0) {
            if (A[i] == '1' && B[j] == '1') {
                if (carry == 1) {
                    sb.append('1');
                } else {
                    sb.append('0');
                }
                carry = 1;
            } else if (A[i] == '1' || B[j] == '1') {
                if (carry == 1) {
                    sb.append('0');
                    carry = 1;
                } else {
                    sb.append('1');
                }
            } else { //both A[i] && B[j] are zero.
                sb.append(carry);
                carry = 0;
            }
            --i;
            --j;
        }
        if (i >= 0) {
            updateStringBuffer(sb, A, i, carry);
        } else if (j >= 0) {
            updateStringBuffer(sb, B, j, carry);
        } else if (carry == 1){
            sb.append(carry);
        }

        return sb.reverse().toString();
    }

    private void updateStringBuffer(StringBuffer sb, char [] X, int i, int carry) {
        while (i >= 0) {
            if (X[i] == '1') {
                if (carry == 1) {
                    sb.append(0);
                    carry = 1;
                } else {
                    sb.append(1);
                }
            } else { //X[i] == 0
                if (carry == 1) {
                    sb.append(1);
                    carry = 0;
                } else {
                    sb.append(0);
                }
            }
            --i;
        }
        if (carry == 1) {
            sb.append(carry);
        }
    }
}
