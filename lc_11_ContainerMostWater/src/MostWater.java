/**
 11. Container With Most Water
 https://leetcode.com/problems/container-with-most-water/description/

 Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
 n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines,
 which together with x-axis forms a container, such that the container contains the most water.

 Note: You may not slant the container and n is at least 2.





 The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case,
 the max area of water (blue section) the container can contain is 49.



 Example:

 Input: [1,8,6,2,5,4,8,3,7]
 Output: 49
 */
public class MostWater {

    public static void main(String[] args) {
        int [] A = {1,8,6,2,5,4,8,3,7};

        MostWater mw = new MostWater();
        System.out.println(mw.maxArea(A));
    }

    public int maxArea(int[] height) {
        int low = 0;
        int high = height.length-1;
        int maxWater = 0;
        while (low < high) {
            int min = Math.min(height[low], height[high]);
            int water = (high-low)*min;
            maxWater = Math.max(maxWater, water);

            while((low < high) && (height[low] <= min)) {
                ++low;
            }

            while((low < high) && (height[high] <= min)) {
                --high;
            }
        }

        return maxWater;
    }
}
