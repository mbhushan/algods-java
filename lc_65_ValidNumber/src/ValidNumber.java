/**
 65. Valid Number
 https://leetcode.com/problems/valid-number/description/

 Validate if a given string is numeric.

 Some examples:
 "0" => true
 " 0.1 " => true
 "abc" => false
 "1 a" => false
 "2e10" => true

 Note: It is intended for the problem statement to be ambiguous.
 You should gather all requirements up front before implementing one.

 */

public class ValidNumber {

    public static void main(String[] args) {
        ValidNumber vn = new ValidNumber();

        String [] inputs = {
                "0", "0.1", "abc", "1 a", "2e10"
        };

        for (String s: inputs) {
            System.out.println("input: " + s);
            System.out.println(vn.isNumber(s));
            System.out.println();
        }

    }

    public boolean isNumber(String s) {
        if (s == null) {
            return false;
        }

        s = s.trim();

        boolean numberSeen = false;
        boolean pointSeen = false;
        boolean eSeen = false;

        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= '0' && ch <= '9') {
                numberSeen = true;

            } else if (ch == '.') {
                if (eSeen || pointSeen) {
                    return false;
                }
                pointSeen = true;

            } else if (ch == 'e') {
                if (eSeen || !numberSeen) {
                    return false;
                }
                eSeen = true;
                numberSeen = false;

            } else if (ch == '+' || ch == '-') {
                if (i != 0 && (s.charAt(i-1) != 'e')) {
                    return false;
                }

            } else {
                return false;
            }
        }

        return numberSeen;
    }
}
