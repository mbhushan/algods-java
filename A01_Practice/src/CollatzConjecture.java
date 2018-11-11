import java.util.HashMap;
import java.util.Map;

/**
 If a number is odd, the next transform is 3*n+1
 If a number is even, the next transform is n/2
 The number is finally transformed into 1.
 The step is how many transforms needed for a number turned into 1.
 Given an integer n, output the max steps of transform number in [1, n] into 1.



 recursive: 1: 1
 recursive: 10: 20
 recursive: 37: 112
 recursive: 101: 119

 */
public class  CollatzConjecture {

    public static void main(String[] args) {
        CollatzConjecture cc = new CollatzConjecture();

        int [] A = {1, 10, 37, 101};
//        for (int i=1; i<=10; i++) {
//            System.out.println("recursive: " + i + ": " + cc.collatz(i));
//        }

        for (int n: A) {
            int max = Integer.MIN_VALUE;
            Map<Integer, Integer> map = new HashMap<>();
            for (int i=1; i<=n; i++) {
               // int res = cc.collatz(i);
                int res = cc.collatzMemo(i, map);
                max = Math.max(res, max);

            }
            System.out.println("recursive: " + n + ": " + max);

            //System.out.println("rec find steps: " + n + ": " + cc.findSteps(n));
        }
    }

    public int collatzMemo(int n, Map<Integer, Integer> map) {
        if (n <=1 ) {
            return 1;
        }

        if (map.containsKey(n)) {
            return map.get(n);
        }

        if (n%2 == 1) {
            n = 3*n+1;
        } else {
            n = n/2;
        }
        int res = collatzMemo(n, map);

        map.put(n, res);

        return res+1;

    }

    public int collatz(int n) {
        if (n <= 1) {
            return 1;
        }

        if (n%2 == 1) {
            return collatz(3*n+1) + 1;
        } else {
            return collatz(n/2) + 1;
        }
    }

    private int findSteps(int num) {
        if (num <= 1) return 1;
        if (num % 2 == 0) return 1 + findSteps(num / 2);
        return 1 + findSteps(3 * num + 1);
    }
}
