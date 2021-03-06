import java.util.HashMap;
import java.util.Map;

/**
 246. Strobogrammatic Number
 https://leetcode.com/problems/strobogrammatic-number/description/

 A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

 Write a function to determine if a number is strobogrammatic. The number is represented as a string.

 Example 1:

 Input:  "69"
 Output: true
 Example 2:

 Input:  "88"
 Output: true
 Example 3:

 Input:  "962"
 Output: false
 */
public class StrobogrammaticNumber {

    public boolean isStrobogrammatic(String num) {
        Map<Character, Character> map = new HashMap<Character, Character>();
        map.put('6', '9');
        map.put('9', '6');
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');

        int l = 0, r = num.length() - 1;
        while (l <= r) {
            if (!map.containsKey(num.charAt(l))) return false;
            if (map.get(num.charAt(l)) != num.charAt(r))
                return false;
            l++;
            r--;
        }

        return true;
    }

    public boolean isStrobogrammatic1(String num) {
        int left, right;
        left = 0;
        right = num.length()-1;

        while(left<=right) {
            if(!isGood(num.charAt(left), num.charAt(right)))
                return false;
            left++;
            right--;
        }
        return true;
    }

    public boolean isGood(char a, char b) {
        if( (a=='1'&&b=='1') || (a=='6'&&b=='9') || (a=='9'&&b=='6') || (a=='8'&&b=='8')  || (a=='0'&&b=='0') )
            return true;
        return false;
    }
}
