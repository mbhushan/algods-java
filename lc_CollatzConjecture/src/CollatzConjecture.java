import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 If a number is odd, the next transform is 3*n+1
 If a number is even, the next transform is n/2
 The number is finally transformed into 1.
 The step is how many transforms needed for a number turned into 1.
 Given an integer n, output the max steps of transform number in [1, n] into 1.

 */
public class CollatzConjecture {

    public static void main(String[] args) {
        CollatzConjecture cc = new CollatzConjecture();

        System.out.println(cc.maxSteps(100));

        String elements[] = { "A", "B", "C", "D", "E" };
        Set<String> set = new HashSet<String>(Arrays.asList(elements));

        elements = new String[] { "B", "D", "F", "G", "1", "2", "3", "4" };
        Set<String> set2 = new HashSet<String>(Arrays.asList(elements));

        set.removeAll(set2);
        System.out.println(set);
    }

    public int maxSteps(int n) {
        Map<Integer, Integer> map = new HashMap<>();

        int max = 0;
        int max1 = 0;
        for (int i=1; i<=n; i++) {
            int x = calcSteps(i);
            max = Math.max(x, max);
            System.out.println("i: " + i + "; max: " + max);
            x = calcStepsMemo(i, map);
            max1 = Math.max(x, max1);
            System.out.println("i: " + i + "; max memo: " + max1);
            System.out.println();
        }

        return max;
    }

    private int calcStepsMemo(int n, Map<Integer, Integer> map) {
       if (n <= 1) {
           return 1;
       }

       if (map.containsKey(n)) {
           System.out.println("returning from map: " + n);
           return map.get(n);
       }
       if (n%2 == 0) {
           n = n / 2;
       } else {
           n = 3*n+1;
       }
//       if (map.containsKey(n)) {
//           return map.get(n)+1;
//       }
        int t = calcStepsMemo(n, map);
       map.put(n, t);

       return t+1;

    }

    public int calcSteps(int n) {
        if (n <= 1) {
            return 1;
        }

        if (n%2 == 0) {
            return 1 + calcSteps(n/2);
        } else {
            return 1 + calcSteps(3*n+1);
        }
    }
}
