import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by manib on 10/4/17.
 *
 * 3 Two Sum III Data structure design
 Design and implement a TwoSum class. It should support the following operations:
 add and find.
 add - Add the number to an internal data structure. find - Find if there exists any
 pair of numbers which sum is equal to the value.
 For example,
 add(1);
 add(3);
 add(5);
 find(4) -> true
 find(7) -> false
 */

public class TwoSum {

    private Map<Integer, Integer> hmap ;

    public TwoSum() {
        hmap = new HashMap<Integer, Integer>();
    }

    public void add(int value) {
        int count = 0;
        if (hmap.containsKey(value)) {
            count = hmap.get(value);
        }
        ++count;
        hmap.put(value, count);
    }

    public boolean find(int target) {

        Set<Integer> keySet = hmap.keySet();

        for (Integer key: keySet) {
            int diff = target - key;
            if (diff == key) {
                if (hmap.containsKey(diff) && (hmap.get(diff) > 1)) {
                    return true;
                }
            } else {
                if (hmap.containsKey(diff)) {
                    return true;
                }
            }

        }
        return false;
    }
}
