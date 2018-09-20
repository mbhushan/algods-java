import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {

    public static void main(String[] args) {
        GroupAnagrams ga = new GroupAnagrams();

    }

    public List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> result = new ArrayList<>();

        if (strs == null) {
            return result;
        }


        Map<String, List<String>> map = new HashMap<>();

        for (String s: strs) {
            char [] arr = s.toCharArray();
            Arrays.sort(arr);
            String key = String.valueOf(arr);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(s);
            map.put(key, list);
        }

        for (String key: map.keySet()) {
            result.add(map.get(key));
        }

        return result;
    }
}
