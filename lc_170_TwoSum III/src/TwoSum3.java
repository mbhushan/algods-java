import java.util.HashMap;
import java.util.Map;

/**
 170. Two Sum III - Data structure design
 https://leetcode.com/problems/two-sum-iii-data-structure-design/description/

 Design and implement a TwoSum class. It should support the following operations: add and find.

 add - Add the number to an internal data structure.
 find - Find if there exists any pair of numbers which sum is equal to the value.

 Example 1:

 add(1); add(3); add(5);
 find(4) -> true
 find(7) -> false
 Example 2:

 add(3); add(1); add(2);
 find(3) -> true
 find(6) -> false

 */

public class TwoSum3 {

    Map<Integer, Integer> map ;
    /** Initialize your data structure here. */
    public TwoSum3() {
        map = new HashMap<>();
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        map.put(number, map.getOrDefault(number,0)+1);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for(Integer k : map.keySet()){
            int t = value - k;
            if((t == k && map.get(k) > 1) || (t != k && map.containsKey(t))) return true;
        }
        return false;
    }
}
