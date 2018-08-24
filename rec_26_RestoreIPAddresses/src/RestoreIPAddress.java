import java.util.ArrayList;
import java.util.List;

/**
 Restore IP Addresses
 Given a string containing only digits, restore it by returning all possible valid IP address combinations.

 Example:

 Input: "25525511135"
 Output: ["255.255.11.135", "255.255.111.35"]
 ==================================================
 Alternate Problem definition:
 Program to generate all possible valid IP addresses from given string
 Given a string containing only digits, restore it by returning all possible valid IP address combinations.

 A valid IP address must be in the form of A.B.C.D, where A, B, C, and D are numbers from 0-255.
 The numbers cannot be 0 prefixed unless they are 0.

 Examples :

 Input : 25525511135
 Output : [“255.255.11.135”, “255.255.111.35”]
 Explanation:
 These are the only valid possible
 IP addresses.

 Input : "25505011535"
 Output : []
 Explanation :
 We cannot generate a valid IP
 address with this string.
 First, we will place 3 dots in the given string and then Try out all the possible combinations for the 3 dots.
 Corner case for validity :

 For string "25011255255"

 25.011.255.255 is not valid as 011 is not valid.
 25.11.255.255 is not valid either as you are not
 allowed to change the string.
 250.11.255.255 is valid.
 =================================================================================================

 ===============
 INPUT / OUTPUT
 ===============
 input string: 25525511135
 possible ip addresses:
 [255, 255, 11, 135]
 [255, 255, 111, 35]

 */


public class RestoreIPAddress {

    public static void main(String[] args) {
        RestoreIPAddress rip = new RestoreIPAddress();

        String [] input = {
                "25525511135",
        };

        for (String s: input) {
            System.out.println("input string: " + s);
            System.out.println("possible ip addresses: ");
            rip.restoreIP(input[0]);
        }
    }

    public void restoreIP(String input) {
        if (input == null || input.length() < 1) {
            return;
        }
        int [] A = new int[input.length()];
        for (int i=0; i<A.length; i++) {
            A[i] = input.charAt(i) - '0';
        }

        //System.out.println(Arrays.toString(A));
        restoreIP(A, 0, 0, new ArrayList<>());
    }

    private void restoreIP(int[] A, int index, int points, List<Integer> result) {
        if (points == 4) {
            if (index < A.length) {
                return;
            }
            if (index >= A.length) {
                System.out.println(result);
                return;
            }
        }

        for (int i = index; i < A.length && (i - index < 3); i++) {
            int num = getNumber(A, index, i);
            if (num <= 255) {
                result.add(num);
                restoreIP(A, i + 1, points + 1, result);
                result.remove(result.size() - 1);
            }
        }
    }

    private int getNumber(int [] A, int start, int end) {
        int x = 0;
        for (int i=start; i<=end; i++) {
            x = x*10 + A[i];
        }
        return x;
    }
}
