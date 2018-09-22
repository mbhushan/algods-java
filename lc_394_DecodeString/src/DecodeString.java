import java.util.Stack;

/**
 394. Decode String
 https://leetcode.com/problems/decode-string/description/
 Given an encoded string, return it's decoded string.

 The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is
 being repeated exactly k times. Note that k is guaranteed to be a positive integer.

 You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

 Furthermore, you may assume that the original data does not contain any digits and that digits are only for
 those repeat numbers, k. For example, there won't be input like 3a or 2[4].

 Examples:

 s = "3[a]2[bc]", return "aaabcbc".
 s = "3[a2[c]]", return "accaccacc".
 s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

 =========input / output ===========
 input: 3[a]2[bc]
 aaabcbc

 input: 3[a2[c]]
 accaccacc

 input: 2[abc]3[cd]ef
 abcabccdcdcdef


 */
public class DecodeString {

    public static void main(String[] args) {
        DecodeString ds = new DecodeString();

        String [] str = {
                "3[a]2[bc]",
                "3[a2[c]]",
                "2[abc]3[cd]ef"
        };

//        for (String s: str) {
//            System.out.println("input: " + s);
//            System.out.println(ds.decodeString(s));
//            System.out.println();
//        }

//        System.out.println("decoding: " + "2[a10[c]]");
//        System.out.println(ds.decodeString("2[a10[c]]"));

        String st = "3[z]2[2[y]pq4[2[jk]e1[f]]]ef";
       // st = "2[2[b]]";
        // zzzyypqjkjkefjkjkefjkjkefjkjkefyypqjkjkefjkjkefjkjkefjkjkefef"
        System.out.println("input: " + st);
        System.out.println(ds.decodeStringWorkig(st));




    }

    public String decodeStringGood(String s) {
        String res = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count = 0;
                while (Character.isDigit(s.charAt(idx))) {
                    count = 10 * count + (s.charAt(idx) - '0');
                    idx++;
                }
                countStack.push(count);
            }
            else if (s.charAt(idx) == '[') {
                resStack.push(res);
                res = "";
                idx++;
            }
            else if (s.charAt(idx) == ']') {
                StringBuilder temp = new StringBuilder (resStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(res);
                }
                res = temp.toString();
                idx++;
            }
            else {
                res += s.charAt(idx++);
            }
        }
        return res;

    }

    public String decodeStringWorkig(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        Stack<Integer> countStack = new Stack<>();
        Stack<String> resultStack = new Stack<>();
        char[] strArr = s.toCharArray();
        int count = 0;
        String curResult = "";
        for (int i = 0; i < s.length(); i++) {
            //calculate repeat number
            if (Character.isDigit(strArr[i])) {
                count = count * 10 + (strArr[i] - '0');
            }
            //push previous decoded string into stack
            else if (strArr[i] == '[') {
                countStack.push(count);
                resultStack.push(curResult);
                count = 0;
                curResult = "";
            }
            //start to decode current string
            else if (strArr[i] == ']') {
                int repeat = countStack.pop();
                StringBuilder temp = new StringBuilder(resultStack.pop());
                for (int j = 0; j < repeat; j++) {
                    temp.append(curResult);
                }
                curResult = temp.toString();
            }
            //normal character, concat to current string, preparing for decoding
            else {
                curResult += strArr[i];
            }
        }
        return curResult;
    }

    public String decodeString(String s) {
        StringBuffer sb = new StringBuffer();

        if (s == null || s.length() < 1) {
            return s;
        }

        int len = s.length();
        Stack<Integer> numStack = new Stack<Integer>();
        Stack<String> strStack = new Stack<>();
        int i = 0;

        StringBuffer buff = new StringBuffer();
        while (i < len) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                int count = 0;
                while (Character.isDigit(s.charAt(i))) {
                    count = 10*count + (s.charAt(i) - '0');
                    i++;
                }
                numStack.push(count);
                if (buff.length() > 0) {
                    strStack.push(buff.toString());
                    buff.setLength(0);
                }
            } else if (ch == '[') {
                //buff = new StringBuffer();
                buff.setLength(0);
            } else if (ch == ']') {
                if (buff.length() == 0) {
                    buff.append(strStack.pop());
                }
                String st =  buff.toString();
                int num = numStack.pop();

                while (num > 1) {
                    buff.append(st);
                    --num;
                }
                if (!numStack.isEmpty() && !strStack.isEmpty()) {
                    strStack.push(strStack.pop() + buff.toString());
                } else if (!numStack.isEmpty()) {
                    strStack.push(buff.toString());
                } else {
                    sb.append(buff.toString());
                }
                buff.setLength(0);

            } else {
                buff.append(ch);
            }
            ++i;
        }
        if (buff.length() > 0) {
            sb.append(buff.toString());
        }

        return sb.toString();
    }
}
