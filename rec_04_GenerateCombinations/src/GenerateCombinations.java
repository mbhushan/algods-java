import java.util.ArrayList;
import java.util.List;

/**
 Print all possible combinations of r elements in a given array of size n
 Given an array of size n, generate and print all possible combinations of r elements in array.
 For example, if input array is {1, 2, 3, 4} and r is 2,
 then output should be {1, 2}, {1, 3}, {1, 4}, {2, 3}, {2, 4} and {3, 4}.

 ==============
 INPUT / OUTPUT
 ==============
 printing all combinations:
 [1, 2]
 [1, 3]
 [1, 4]
 [2, 3]
 [2, 4]
 [3, 4]

 */

public class GenerateCombinations {

    public static void main(String[] args) {
        GenerateCombinations gc = new GenerateCombinations();

        int [] A = {1, 2, 3, 4};
        int r = 2;

        gc.genCombinations(A, r);

    }

    public void genCombinations(int [] A, int r) {

        List<Integer> result = new ArrayList<>();
        int index = 0;

        System.out.println("printing all combinations: ");
        generateCombinations(A, r, 0, result);

    }

    private void generateCombinations(int [] A, int r, int index, List<Integer> result) {
        if (result.size() == r) {
            System.out.println(result);
            return;
        }

        if (index >= A.length) {
            return;
        }


        for (int i=index; i<A.length; i++) {
                result.add(A[i]);
            generateCombinations(A, r, i+1, result);
            result.remove(result.size()-1);
        }

    }


}
