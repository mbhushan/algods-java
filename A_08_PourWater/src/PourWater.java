import java.util.Arrays;

public class PourWater {

    public static void main(String[] args) {
        PourWater pw = new PourWater();

        int [][] A = {
                {2,1,1,2,1,2,2},
                {1,2,3,4},
                {3,1,3}
        };
        int [] V = {4, 2, 5};
        int [] K = {3, 2, 1};

        for (int i=0; i<A.length; i++) {
            System.out.println("input: " + Arrays.toString(A[i]));
            pw.pourWater(A[i], V[i], K[i]);

            System.out.println("output: " + Arrays.toString(A[i]));
            System.out.println();

        }


    }

    public int[] pourWater(int[] heights, int V, int K) {

        for (int i=1; i<=V; i++) {
            int minIdx = getMinLeftIdx(heights, K);
            if (minIdx == K) {
                minIdx = getMinRightIdx(heights, K);
            }
            ++heights[minIdx];
            System.out.println("step: " + Arrays.toString(heights));
        }
        return heights;
    }

    public int getMinLeftIdx(int [] A, int K) {

        int i = K-1;
        int idx = K;

        while (i >= 0) {
            if (A[i] > A[idx]) {
                break;
            }

            if (A[i] < A[idx]) {
                idx = i;
            }
            --i;
        }
        return idx;
    }

    public int getMinRightIdx(int [] A, int K) {
        int i = K+1;
        int idx = K;

        while (i < A.length) {
            if (A[i] > A[idx]) {
                break;
            }
            if (A[i] < A[idx]) {
                idx = i;
            }
            ++i;
        }
        return idx;
    }

}
