import java.util.HashSet;
import java.util.Set;

/**

 824. Goat Latin
 https://leetcode.com/problems/goat-latin/description/

 A sentence S is given, composed of words separated by spaces. Each word consists of lowercase and uppercase letters only.

 We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.)

 The rules of Goat Latin are as follows:

 If a word begins with a vowel (a, e, i, o, or u), append "ma" to the end of the word.
 For example, the word 'apple' becomes 'applema'.

 If a word begins with a consonant (i.e. not a vowel), remove the first letter and append it to the end, then add "ma".
 For example, the word "goat" becomes "oatgma".

 Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
 For example, the first word gets "a" added to the end, the second word gets "aa" added to the end and so on.
 Return the final sentence representing the conversion from S to Goat Latin.


 */
public class GoatLatin {

    public static void main(String[] args) {

    }

    public String toGoatLatin(String S) {

        String vowels = "aeiouAEIOU";
        Set<Character> set = new HashSet<>();

        for (char ch: vowels.toCharArray()) {
            set.add(ch);
        }


        String [] words = S.split(" ");
        StringBuffer sb = new StringBuffer();
        StringBuffer asb = new StringBuffer();
        asb.append("a");

        for (String w: words) {
            char ch = w.charAt(0);
            if (set.contains(ch)) {
                w = w + "ma";
            } else {
                w = w.substring(1) + String.valueOf(ch) + "ma";
            }
            w += asb.toString();
            asb.append("a");

            sb.append(w + " ");
        }
        sb.deleteCharAt(sb.length()-1);


        return sb.toString();
    }
}

