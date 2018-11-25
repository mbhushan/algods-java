import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    public static void main(String[] args) {

        CombinationSum cs = new CombinationSum();

        int [] A = {2,3,5};
        int target = 8;

        System.out.println("combo sum 1:");
        cs.comboSum1(A, target);

        A = new int[]{10,1,2,7,6,1,5};
        System.out.println("combo sum 2:");
        cs.comboSum2(A, target);

        A = new int[]{10,1,2,7,6,1,5};
        System.out.println("comboSumNonRepeat2 :");
        cs.comboSumNonRepeat2(A, target);

    }

    public void comboSum2(int [] A, int target) {

        List<Integer> buff = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        comboSum2(A, 0, target, buff, result);

        System.out.println(result);
    }

    public void comboSumNonRepeat2(int [] A, int target) {

        List<Integer> buff = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(A);
        comboSumNonRepeat2(A, 0, target, buff, result);

        System.out.println(result);
    }

    private void comboSumNonRepeat2(int [] A, int index, int target, List<Integer> buff, List<List<Integer>> result) {

        if (target == 0) {
            List<Integer> tmp = new ArrayList<>(buff);
            result.add(tmp);
            return;
        }

        if (target < 0 || index >= A.length) {
            return;
        }

        for (int i=index; i<A.length; i++) {
            if (i > index && A[i] == A[i-1]) {
                continue;
            }
            buff.add(A[i]);
            comboSumNonRepeat2(A, i+1, target-A[i], buff, result);
            buff.remove(buff.size()-1);
        }
    }

    private void comboSum2(int [] A, int index, int target, List<Integer> buff, List<List<Integer>> result) {

        if (target == 0) {
            List<Integer> tmp = new ArrayList<>(buff);
            result.add(tmp);
            return;
        }

        if (target < 0 || index >= A.length) {
            return;
        }

        buff.add(A[index]);
        comboSum2(A, index+1, target-A[index], buff, result);
        buff.remove(buff.size()-1);
        comboSum2(A, index+1, target, buff, result);
    }

    public void comboSum1(int [] A, int target) {

        List<Integer> buff = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        comboSum1(A, 0, target, buff, result);

        System.out.println(result);
    }

    private void comboSum1(int [] A, int index, int target, List<Integer> buff, List<List<Integer>> result) {

        if (target == 0) {
            List<Integer> tmp = new ArrayList<>(buff);
            result.add(tmp);
            return;
        }

        if (target < 0 || index >= A.length) {
            return;
        }

        buff.add(A[index]);
        comboSum1(A, index, target - A[index], buff, result);
        buff.remove(buff.size()-1);
        comboSum1(A, index+1, target, buff, result);
    }
}
