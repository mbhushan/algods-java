import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 249. Group Shifted Strings
 https://leetcode.com/problems/group-shifted-strings/description/

 Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd".
 We can keep "shifting" which forms the sequence:

 "abc" -> "bcd" -> ... -> "xyz"
 Given a list of strings which contains only lowercase alphabets,
 group all strings that belong to the same shifting sequence.

 Example:

 Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
 Output:
 [
 ["abc","bcd","xyz"],
 ["az","ba"],
 ["acef"],
 ["a","z"]
 ]

 */
public class GroupShiftedStrings {

    public static void main(String[] args) {

        GroupShiftedStrings gs = new GroupShiftedStrings();

        String [] S = {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};

        System.out.println(gs.groupStrings(S));

    }

    public List<List<String>> groupStrings(String[] strings) {

        Map<String, List<String>> map = new HashMap<>();
        for (String s: strings) {
            String key = getKey(s);
            List<String> values = map.getOrDefault(key, new ArrayList<>());
            values.add(s);
            map.put(key, values);
        }

        return new ArrayList<>(map.values());
    }

    public String getKey(String s) {
        if (s == null || s.length() <= 1) {
            return "";
        }

        StringBuffer sb = new StringBuffer();

        for (int i=1; i<s.length(); i++) {
            int diff = s.charAt(i) - s.charAt(i-1);
            diff = (diff < 0)? diff + 26: diff;
            sb.append(diff);
            sb.append(",");
        }

        return sb.toString();
    }
}
