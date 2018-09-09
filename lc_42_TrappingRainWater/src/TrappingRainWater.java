import java.util.Arrays;

public class TrappingRainWater {

    public static void main(String[] args) {
        TrappingRainWater tr  = new TrappingRainWater();

        int [][] A = {
                {0,1,0,2,1,0,1,3,2,1,2,1},
                {3, 0, 0, 2, 0, 4},
                {2, 0, 2}
        };

        for (int i=0; i<A.length; i++) {
            System.out.println("input: " + Arrays.toString(A[i]));
            int water = tr.trap(A[i]);
            System.out.println("total water: " + water);
            System.out.println();
        }

        //tr.trap(A[0]);

    }

    public int trap(int[] height) {

        if (height == null || height.length <= 2) {
            return 0;
        }

        int len = height.length;

        int [] left = new int[len];
        int [] right = new int[len];

        left[0] = height[0];
        for (int i=1; i<len; i++) {
            left[i] = Math.max(left[i-1], height[i]);
        }

        right[len-1] = height[len-1];
        for (int i=len-2; i>=0; --i) {
            right[i] = Math.max(right[i+1], height[i]);
        }

        System.out.println("left: " + Arrays.toString(left));
        System.out.println("right: " + Arrays.toString(right));

        int totalWater = 0;

        for (int i=0; i<len; i++) {
            totalWater += Math.min(left[i], right[i]) - height[i];
        }

        return totalWater;

    }
}
