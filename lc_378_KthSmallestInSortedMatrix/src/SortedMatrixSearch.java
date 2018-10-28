/**
 378. Kth Smallest Element in a Sorted Matrix
 Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

 Note that it is the kth smallest element in the sorted order, not the kth distinct element.

 Example:

 matrix = [
 [ 1,  5,  9],
 [10, 11, 13],
 [12, 13, 15]
 ],
 k = 8,

 return 13.
 */
public class SortedMatrixSearch {

    public static void main(String[] args) {
        SortedMatrixSearch sm = new SortedMatrixSearch();
        int [][] M = {
                {1,  5,  9},
                {10, 11, 14},
                {12, 13, 15}
        };

        int k = 8;
        int ans = sm.kthSmallest(M, k);
        System.out.println("ans: " + ans);
    }

    public int kthSmallest(int[][] matrix, int k) {
        int lo = matrix[0][0], hi = matrix[matrix.length - 1][matrix[0].length - 1] + 1;//[lo, hi)
        while(lo < hi) {
            int mid = lo + (hi - lo) / 2;
            System.out.println("mid: " + mid);
            int count = 0;
            int j = matrix[0].length - 1; //col
            for(int i = 0; i < matrix.length; i++) {
                while(j >= 0 && matrix[i][j] > mid) j--;

                count += (j + 1);
                System.out.println("count: " + count);
            }
            if(count < k) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}
