import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 349. Intersection of Two Arrays
 https://leetcode.com/problems/intersection-of-two-arrays/description/

 Given two arrays, write a function to compute their intersection.

 Example 1:

 Input: nums1 = [1,2,2,1], nums2 = [2,2]
 Output: [2]
 Example 2:

 Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 Output: [9,4]
 */

public class IntersectionArrays {

    public static void main(String[] args) {
        IntersectionArrays ia = new IntersectionArrays();

        int [][] A = {
                {1, 2, 2, 1},
                {4,9,5}
        };

        int [][] B = {
                {2, 2},
                {9,4,9,8,4}
        };

        for (int i=0; i<A.length; i++) {
            System.out.println("A: " + Arrays.toString(A[i]));
            System.out.println("B: " + Arrays.toString(B[i]));
            int [] result = ia.intersection(A[i], B[i]);
            System.out.println("intersection: " + Arrays.toString(result));
        }

    }

    public int[] intersection(int[] nums1, int[] nums2) {

        //List<Integer> result = new ArrayList<>();
        Set<Integer> result = new HashSet<>();

        if (nums1 == null || nums2 == null || nums1.length < 1 || nums2.length < 1) {
            return result.stream().mapToInt(i -> i).toArray();
        }

        Set<Integer> hset = new HashSet<Integer>();


        for (int i: nums1) {
            hset.add(i);
        }

        for (int i: nums2) {
            if (hset.contains(i) && !result.contains(i)) {
                result.add(i);
            }
        }

        return result.stream().mapToInt( i -> i).toArray();
    }
}
