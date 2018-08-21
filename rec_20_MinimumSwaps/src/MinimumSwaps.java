import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 Minimum number of swaps required for arranging pairs adjacent to each other
 There are n-pairs and therefore 2n people. everyone has one unique number ranging from 1 to 2n.
 All these 2n persons are arranged in random fashion in an Array of size 2n. We are also given who is partner of whom.
 Find the minimum number of swaps required to arrange these pairs such that all pairs become adjacent to each other.

 Example:

 Input:
 n = 3
 pairs[] = {1->3, 2->6, 4->5}  // 1 is partner of 3 and so on
 arr[] = {3, 5, 6, 4, 1, 2}

 Output: 2
 We can get {3, 1, 5, 4, 6, 2} by swapping 5 & 6, and 6 & 1
 We strongly recommend you to minimize your browser and try this yourself first.

 The idea is to start from first and second elements and recur for remaining elements. Below are detailed steps/

 1) If first and second elements are pair, then simply recur
 for remaining n-1 pairs and return the value returned by
 recursive call.

 2) If first and second are NOT pair, then there are two ways to
 arrange. So try both of them return the minimum of two.
 a) Swap second with pair of first and recur for n-1 elements.
 Let the value returned by recursive call be 'a'.
 b) Revert the changes made by previous step.
 c) Swap first with pair of second and recur for n-1 elements.
 Let the value returned by recursive call be 'b'.
 d) Revert the changes made by previous step before returning
 control to parent call.
 e) Return 1 + min(a, b)

 =================
 INPUT / OUTPUT
 =================
 min swap: 2
 Array: [4, 5, 6, 2, 3, 1]
 index map: {1=5, 2=3, 3=1, 4=0, 5=2, 6=1}
 IndexMap Array: [4, 6, 5, 2, 3, 1]

 */

public class MinimumSwaps {

    public static void main(String[] args) {
        MinimumSwaps ms = new MinimumSwaps();
        int [] A = {1, 5, 6, 4, 3, 2};

        Map<Integer, Integer> hmap = new HashMap<>();

        //create pairs -  pairs[] = {1->3, 2->6, 4->5}
        hmap.put(1, 3);
        hmap.put(3, 1);
        hmap.put(2, 6);
        hmap.put(6, 2);
        hmap.put(4, 5);
        hmap.put(5, 4);

        ms.minSwaps(A, hmap);

    }

    public void minSwaps(int [] A, Map<Integer, Integer> hmap) {

        Map<Integer, Integer> indexMap = new HashMap<>();

        for (int i=0; i<A.length; i++) {
            indexMap.put(A[i], i);
        }

        int minSwap = minSwaps(A, hmap, indexMap, 0);

        System.out.println("min swap: " + minSwap);
        System.out.println("Array: " + Arrays.toString(A) );
        System.out.println("index map: " + indexMap);

        for (Map.Entry<Integer, Integer> pair: indexMap.entrySet()) {
            int k = pair.getKey();
            int v = pair.getValue();
            A[v] = k;
        }
        System.out.println("IndexMap Array: " + Arrays.toString(A) );}

    private int minSwaps(int [] A, Map<Integer, Integer> pairs, Map<Integer, Integer> indexMap, int index) {

        if (index >= A.length) {
            return 0;
        }

        int v1 = A[index];
        int v2 = A[index+1];

        if (v1 == pairs.get(v2)) {
            return minSwaps(A, pairs, indexMap, index+2);
        } else {
            int idx1 = indexMap.get(v1);
            int idx2 = indexMap.get(v2);

            //get the index of the pairs of v1 and v2
            int idx3 = indexMap.get(pairs.get(v1));
            int idx4 = indexMap.get(pairs.get(v2));

            //swap idx2 and idx3
            swap(A, indexMap, idx2, idx3, v2, pairs.get(v1));
            int first = minSwaps(A, pairs, indexMap, index+2);
            //swap(A, indexMap, idx3, idx2, v2, pairs.get(v1));

            //swap idx1 and idx4
            swap(A, indexMap, idx1, idx4, v1, pairs.get(v2));
            int second = minSwaps(A, pairs, indexMap, index+2);
            //swap(A, indexMap, idx4, idx1, v1, pairs.get(v2));

            return 1 + Math.min(first, second);
        }
    }

    private void swap(int [] A, Map<Integer, Integer> indexMap, int i, int j, int x, int y) {
        indexMap.put(x, j);
        indexMap.put(y, i);

        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
