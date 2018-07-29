import java.util.ArrayList;
import java.util.List;

/**
 /**
 * Generate all combination of size k and less of adjacent numbers
 * e.g 1,2,3,4 k = 2
 * 1 2 3 4
 * 12 3 4
 * 1 23 4
 * 1 2 3 34
 * 12 34

 =====================
 INPUT / OUTPUT
 =====================
 [1, 2, 3, 4]
 [1, 2, 34]
 [1, 23, 4]
 [12, 3, 4]
 [12, 34]
 */

public class AllAdjCombination {

    public static void main(String[] args) {
        AllAdjCombination ac = new AllAdjCombination();

        int [] A = {1, 2, 3, 4};
        int k = 2;

        ac.generateCombinations(A, k);

    }

    public void generateCombinations(int [] A, int k) {
        StringBuffer sb = new StringBuffer();
        List<Integer> result = new ArrayList<>();
        generateCombinations(A, k, 0, result);
    }

    public void generateCombinations(int [] A, int k, int index, List<Integer> result) {
        if (index >= A.length) {
            System.out.println(result);
            return;
        }

        for (int j = 1; j <= k; j++) {
            if (index+j <= A.length) {
                int num = getNum(A, index, index + j);
                result.add(num);
                generateCombinations(A, k, index + j, result);
                result.remove(result.size() - 1);
            }
        }
    }

    private int getNum(int [] A, int start, int end) {
        int num = 0;
        for (int i=start; i<end; i++) {
            num = (10*num + A[i]);
        }
        return num;
    }
}
