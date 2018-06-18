import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 Combination of Characters in String (All subsets of characters)

 =================================
 Input/Output
 =================================
 unique characters: abc
 counts: [2, 1, 1]

 a
 a a
 a a b
 a a b c
 a a c
 a b
 a b c
 a c
 b
 b c
 c
 method 2 to generate combinations:
 []
 [a]
 [a, b]
 [a, b, c]
 [a, c]
 [b]
 [b, c]
 [c]

 */

public class CharCombination {

    public static void main(String [] args) {
        CharCombination cc = new CharCombination();
        String [] inputs = {"abca"};
        for (int i=0; i<inputs.length; i++) {
            cc.generateCombinations(inputs[i]);
        }
    }

    public void generateCombinations(String str) {
        if (str == null) {
            return;
        }

        char [] input = str.toCharArray();
        int len = input.length;

        Arrays.sort(input);

        StringBuffer sb = new StringBuffer();
        List<Integer> countList = new ArrayList<Integer>();
        char prev = input[0];
        int count = 1;
        for (int i=1; i < input.length; i++) {
            if (input[i] == prev) {
                ++count;
            } else {
                countList.add(count);
                sb.append(prev);
                prev = input[i];
                count = 1;
            }
        }

        sb.append(input[len-1]);
        countList.add(count);
        System.out.println("unique characters: " + sb.toString());
        System.out.println("counts: " + countList.toString());

        char [] result = new char[input.length];
        int [] countArr = countList.stream().mapToInt(i -> i).toArray();
        combinationsUtil(sb.toString().toCharArray(), countArr, result, 0, 0);
        System.out.println("method 2 to generate combinations: ");
        //List<Character> buff = new ArrayList<Character>();
        combinationUtil2(sb.toString().toCharArray(), 0, new ArrayList<Character>()) ;
    }

    public void combinationsUtil(char [] A, int [] count, char [] result, int index, int level) {
        printResult(result, index);

        for (int i=level; i<A.length; i++) {
            if (count[i] == 0) {
                continue;
            }
            result[index] = A[i];
            --count[i];
            combinationsUtil(A, count, result, index+1, i);
            ++count[i];

        }
    }

    public void combinationUtil2(char [] A, int index, List<Character> result) {
        System.out.println(result);

        for (int i=index; i<A.length; i++) {
//            if (i != index && A[i] == A[i-1]) {
//                continue;
//            }
            result.add(A[i]);
            combinationUtil2(A, i+1, result);
            result.remove(result.size()-1);
        }
    }

    private void printResult(char [] result, int index) {
        for (int i=0; i<index; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println();
    }
}
