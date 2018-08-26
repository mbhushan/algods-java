/**

 273. Integer to English Words
 https://leetcode.com/problems/integer-to-english-words/description/

 Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

 Example 1:

 Input: 123
 Output: "One Hundred Twenty Three"
 Example 2:

 Input: 12345
 Output: "Twelve Thousand Three Hundred Forty Five"
 Example 3:

 Input: 1234567
 Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 Example 4:

 Input: 1234567891
 Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"

 ==================
 INPUT / OUTPUT
 ==================
 Input number: 123
 English: One Hundred Twenty Three

 Input number: 345
 English: Three Hundred Fourty Five

 Input number: 567
 English: Five Hundred Sixty Seven

 Input number: 789
 English: Seven Hundred Eighty Nine

 Input number: 890
 English: Eight Hundred Ninty

 Input number: 101
 English: One Hundred One

 Input number: 100
 English: One Hundred

 Input number: 500
 English: Five Hundred

 Input number: 999
 English: Nine Hundred Ninty Nine

 Input number: 12
 English: Twelve

 Input number: 123
 English: One Hundred Twenty Three

 Input number: 12345
 English: Twelve Thousand Three Hundred Fourty Five

 Input number: 1234567
 English: One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven

 Input number: 1234567891
 English: One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninty One
 */

public class IntegerToWords {

    private static String [] digit1 = {
            "",
            "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"
    };

    private static String [] digit10 = {
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };

    private static String [] digit2 = {
            "", "", "Twenty", "Thirty", "Fourty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninty"
    };

    private static String [] digit0 = {
            "", "", "Hundred", "Thousand", "", "", "Million", "", "", "Billion"
    };

    public static void main(String[] args) {
        IntegerToWords iw = new IntegerToWords();

        int [] inputs = {
                123,
                12345,
                1234567,
                1234567891
        };

        int [] digits = {
                123,
                345,
                567,
                789,
                890,
                101,
                100,
                500,
                999,
                12
        };

       // System.out.println(iw.getThreeDigitWord(12));

        for (int n: digits) {
            System.out.println("Input number: " + n);
            String word = iw.getThreeDigitWord(n);
            System.out.println("English: " + word);
            System.out.println();
        }

      // iw.intToWord(1234567891);

        for (int n: inputs) {
            System.out.println("Input number: " + n);
            String word = iw.intToWord(n);
            System.out.println("English: " + word);
            System.out.println();
        }

    }

    public String intToWord(int n) {

        StringBuffer sb = new StringBuffer();

        String str = String.valueOf(n);
        int len = str.length();

        int x = -1;

        if (len == 10) {
            x = n / (int) Math.pow(10, 9);
            n = n % (int) Math.pow(10, 9);

            sb.append(digit1[x] + " " + digit0[9] + " ");

            len = String.valueOf(n).length();
        }
        if (len > 6) {
            x = n / (int) Math.pow(10, 6);
            n = n % (int) Math.pow(10, 6);

            String word = getThreeDigitWord(x);
            sb.append(word + " " + digit0[6] + " ");

            len = String.valueOf(n).length();
        }

        if (len > 3) {
            x = n / (int) Math.pow(10, 3);
            n = n % (int) Math.pow(10, 3);

            String word = getThreeDigitWord(x);
            sb.append(word + " " + digit0[3] + " ");

            len = String.valueOf(n).length();
        }

        if (len <= 3) {
            String word = getThreeDigitWord(n);
            sb.append(word);
        }

        //System.out.println("english: " + sb.toString());

        return sb.toString();
    }

    public String getThreeDigitWord(int n) {

        String str = String.valueOf(n);
        int len = str.length();
        int x = -1;

        StringBuffer sb = new StringBuffer();

        if (len == 3) {
            x = n / 100;
            n = n % 100;
            len = String.valueOf(n).length();
            sb.append(digit1[x] + " " + digit0[2] + " ");
        }
        if (len == 2) {
            x = n / 10;
            n = n % 10;
            len = String.valueOf(n).length();
            if (x >= 2) {
                sb.append(digit2[x] + " ");
            } else if (x == 1){
                sb.append(digit10[n]);
                len = 0;
            }
        }
        if (len == 1) {
            sb.append(digit1[n]);
        }

        //System.out.println("english: " + sb.toString());

        return sb.toString();
    }
}
