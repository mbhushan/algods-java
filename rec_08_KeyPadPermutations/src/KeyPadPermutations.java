import java.util.Arrays;

/**
 Print all possible words from phone digits
 Before advent of QWERTY keyboards, texts and numbers were placed on the same key.
 For example 2 has “ABC” if we wanted to write anything starting with ‘A’ we need to type key 2 once.
 If we wanted to type ‘B’, press key 2 twice and thrice for typing ‘C’. below is picture of such keypad.

 Mobile-keypad

 Given a keypad as shown in diagram, and a n digit number, list all words which are possible by pressing these numbers.
 For example if input number is 234, possible words which can be formed are (Alphabetical order):

 adg adh adi aeg aeh aei afg afh afi bdg bdh bdi beg beh bei bfg bfh bfi cdg cdh cdi ceg ceh cei cfg cfh cfi

 ====================
 INPUT / OUTPUT
 ====================
 input keys: [2, 3, 4]
 keypad permutations are:
 [ adg; adh; adi; aeg; aeh; aei; afg; afh; afi; bdg; bdh; bdi; beg; beh; bei; bfg; bfh; bfi; cdg;
 cdh; cdi; ceg; ceh; cei; cfg; cfh; cfi;  ]
 */

public class KeyPadPermutations {

    private String [] pad = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public static void main(String[] args) {
        KeyPadPermutations kp = new KeyPadPermutations();

        int [] A = {2, 3, 4};

        System.out.println("input keys: " + Arrays.toString(A));
        kp.permute(A);


    }

    public void permute(int [] A) {
        StringBuffer sb = new StringBuffer();

        System.out.println("keypad permutations are: ");
        System.out.print("[ ");
        permute(A, 0, sb);
        System.out.println(" ]");
    }

    private void permute(int [] A, int index, StringBuffer sb) {

        if (sb.length() >= A.length) {
            System.out.print(sb + "; ");
            return;
        }

        for (int i=index; i<A.length; i++) {
            int num = A[i];
            char [] C = this.pad[num].toCharArray();
            for (int j=0; j<C.length; j++) {
                sb.append(C[j]);
                permute(A, i+1, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
