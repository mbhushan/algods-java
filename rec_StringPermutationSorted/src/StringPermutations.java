import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * generate all string permutations of strings in lexicographically sorted order where repetition of characters is
 * possible.
 */

public class StringPermutations {
    public static void main(String [] args) {
        StringPermutations sp = new StringPermutations();
        String [] inputs = {"abcab","abcabcc", "cats", "doggy", "eata"};
        for (int i=0; i<inputs.length; i++) {
            sp.permute(inputs[i]);
        }
    }

    public void permute(String str) {
        if (str == null || str.length() <= 1) {
            return;
        }
        char [] input = str.toCharArray();
        int len = input.length;
        Arrays.sort(input);
        System.out.println("sorted: " + String.valueOf(input));
        Map<Character, Integer> countMap = new HashMap<Character, Integer>();
        StringBuffer sbChar = new StringBuffer();
        StringBuffer sbCount = new StringBuffer();
        char prev = input[0];
        int count = 1;

        for (int i=1; i<input.length; i++) {
            if (prev == input[i]) {
                ++count;
            } else {
                sbChar.append(prev);
                sbCount.append(count);
                count = 1;
                prev = input[i];
            }
        }
        if (prev == input[len-1]) {
            sbChar.append(input[len-1]);
            sbCount.append(count);
        }

        System.out.println("characters: " + sbChar.toString());
        System.out.println("counts: " + sbCount.toString());
    }
}
