import java.util.ArrayList;
import java.util.List;

/**
 * Created by manib on 2/10/18.
 *
 * Given a sorted integer array without duplicates, return the summary of its ranges for consecutive numbers.

 For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 */
public class SummrayRanges {

    public static void main(String [] args) {
        SummrayRanges sr = new SummrayRanges();
        sr.calcSummaryRanges();

    }

    public void calcSummaryRanges() {
        int  [][] A = {
                        {0, 1, 2, 4, 5, 7},
                        {0, 1, 2, 4, 5, 6},
                        {0, 1, 2, 3, 4, 5, 6}
                        };
        for (int i=0; i<A.length; i++) {
            List<String> result = summarize(A[i]);
            System.out.println(result);
        }
//        for (String s: result) {
//            System.out.print(s + " ");
//        }
//        System.out.println();
    }

    public List<String> summarize(int [] A) {
        List<String> result = new ArrayList<String>();
        int len = A.length;
        int index = 0;
        int start = index;

        while (index < len-1) {
           if ((A[index] - A[index+1]) != -1) {
               if (index == start) {
                   result.add(Integer.toString(A[start]));
               } else {
                   result.add(A[start] + "->" + A[index]);
               }
               start = index+1;
           }
           ++index;
        }

        if (index != start) {
            result.add(A[start] + "->" + A[index]);
        } else {
            result.add(Integer.toString(A[len-1]));
        }

        return result;
    }
}
