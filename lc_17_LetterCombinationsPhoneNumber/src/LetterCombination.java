import java.util.ArrayList;
import java.util.List;

/**
 17. Letter Combinations of a Phone Number
 Given a string containing digits from 2-9 inclusive, return all possible letter combinations
 that the number could represent.
 A mapping of digit to letters (just like on the telephone buttons) is given below.
 Note that 1 does not map to any letters.

 Example:
 Input: "23"
 Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 Note:
 Although the above answer is in lexicographical order, your answer could be in any order you want.
 */

public class LetterCombination {

    private static String [] nmap = {
      "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public static void main(String[] args) {
        LetterCombination lc = new LetterCombination();

        String [] D = {"23", "123"};

        for (int i=0; i<D.length-1; i++) {
            List<String> res = lc.letterCombinations(D[i]);
            System.out.println(res);
        }

    }

    public List<String> letterCombinations(String digits) {

        List<String> result = new ArrayList<>();

        if (digits == null || digits.length() < 1) {
            return result;
        }

        StringBuffer sb = new StringBuffer();

        int [] D = new int[digits.length()];

        int i=0;
       for (char ch: digits.toCharArray()) {
           D[i++] = Character.getNumericValue(ch);
       }

       // System.out.println("D:" + Arrays.toString(D));
        findCombo(D, 0, sb, result);

       return result;

    }

    private void findCombo(int [] D, int index, StringBuffer sb, List<String> result) {
        if (sb.length() == D.length) {
            result.add(sb.toString());
            return;
        }

        for (int i=index; i<D.length; i++) {

            String letters = nmap[D[i]];
            //System.out.println("letters: " + letters);

            if (letters.length() < 1) {
                continue;
            }

            for (int j=0; j<letters.length(); j++) {
                sb.append(letters.charAt(j));
                findCombo(D, i+1, sb, result);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }
}
