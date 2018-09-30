public class FindMinRotatedSortedArray {

    public static void main(String[] args) {
        FindMinRotatedSortedArray fs = new FindMinRotatedSortedArray();
        int [] A = {4,5,6,7,0,1,2};

        System.out.println("ans: " + fs.findMin(A));

    }

    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length-1;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < nums[high]) {
                high = mid;
            } else {
                low = mid+1;
            }
        }

        return nums[low];

    }
}
