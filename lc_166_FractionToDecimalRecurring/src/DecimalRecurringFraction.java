import java.util.HashMap;
import java.util.Map;

/**
 166. Fraction to Recurring Decimal
 https://leetcode.com/problems/fraction-to-recurring-decimal/description/

 Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

 If the fractional part is repeating, enclose the repeating part in parentheses.

 Example 1:

 Input: numerator = 1, denominator = 2
 Output: "0.5"
 Example 2:

 Input: numerator = 2, denominator = 1
 Output: "2"
 Example 3:

 Input: numerator = 2, denominator = 3
 Output: "0.(6)"

 */
public class DecimalRecurringFraction {

    public static void main(String[] args) {
        DecimalRecurringFraction dr = new DecimalRecurringFraction();

        System.out.println("ans: " + dr.fractionToDecimal(4, 333));
    }

    public String fractionToDecimal(int numerator, int denominator) {

        StringBuffer result = new StringBuffer();
        String sign = (numerator < 0 == denominator < 0 || numerator == 0 ) ? "" : "-";

        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);

        result.append(sign);
        result.append(num / den);
        long remainder = num % den;
        if (remainder == 0) {
            return result.toString();
        }

        result.append('.');
        Map<Long, Integer> map = new HashMap<>();

        while(!map.containsKey(remainder)) {
            map.put(remainder, result.length());
            result.append(10*remainder / den);
            remainder = 10*remainder % den;
        }
        int index = map.get(remainder);
        result.insert(index, "(");
        result.append(")");

        return result.toString().replace("(0)", "");
    }
}
