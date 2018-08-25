import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * Example:
 *
 * Given nums = [2, 7, 11, 15], target = 9,
 *
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].

 =========================
 INPUT / OUTPUT
 =========================
 */
public class TwoSum {

    public static void main(String[] args) {
        TwoSum ts = new TwoSum();

        int [][] nums = {
                {2, 7, 11, 15},
                {3, 2, 4},
                {3, 3}
        };
        int [] target = {9, 6, 6};

        for (int i=0; i<nums.length; i++) {
            int[] result = ts.twoSum(nums[i], target[i]);
            System.out.println(Arrays.toString(result));
            System.out.println(nums[i][result[0]] + ", " + nums[i][result[1]]);
            System.out.println();
        }

    }

    public int [] twoSum(int []nums, int target) {
        int [] result = new int[2];

        if (nums == null || nums.length < 2) {
            return null;
        }

       Map<Integer, List<Integer>> hmap = new HashMap<>();

       for (int i=0; i< nums.length; i++) {
           List<Integer> list = new ArrayList<>();
           if (hmap.containsKey(nums[i])) {
               list = hmap.get(nums[i]);
           }
           list.add(i);
           hmap.put(nums[i], list);
       }

        //System.out.println(hmap);

        for (int i=0; i<nums.length; i++) {
            int diff = target - nums[i];

            if (hmap.containsKey(diff)) {
                List<Integer> list = hmap.get(diff);

                if (diff == nums[i] && list.size() > 1) { //both are equal
                    result[0] = list.get(0);
                    result[1] = list.get(1);
                    break;
                } else if (diff != nums[i]){
                    result[0] = i;
                    result[1] = list.get(0);
                    break;
                }
            }
        }

        return result;
    }
}
