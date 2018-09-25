import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 354. Russian Doll Envelopes
 https://leetcode.com/problems/russian-doll-envelopes/description/

 You have a number of envelopes with widths and heights given as a pair of integers (w, h).
 One envelope can fit into another if and only if both the width and height of one envelope
 is greater than the width and height of the other envelope.

 What is the maximum number of envelopes can you Russian doll? (put one inside other)

 Note:
 Rotation is not allowed.

 Example:

 Input: [[5,4],[6,4],[6,7],[2,3]]
 Output: 3
 Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).

 */


class RussianDollEnvelopes {

    public static void main(String[] args) {
        RussianDollEnvelopes rd = new RussianDollEnvelopes();

        int [][] M = {
                {5, 4},
                {6, 4},
                {6, 7},
                {2, 3}
        };
        int ans = rd.maxEnvelopes(M);
        System.out.println("ans: " + ans);
    }

    public int maxEnvelopesNLogN(int[][] envelopes) {
        if(envelopes == null || envelopes.length == 0
                || envelopes[0] == null || envelopes[0].length != 2)
            return 0;
        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] arr1, int[] arr2){
                if(arr1[0] == arr2[0])
                    return arr2[1] - arr1[1];
                else
                    return arr1[0] - arr2[0];
            }
        });
        int dp[] = new int[envelopes.length];
        int len = 0;
        for(int[] envelope : envelopes){
            int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
            if(index < 0)
                index = -(index + 1);
            dp[index] = envelope[1];
            if(index == len)
                len++;
        }
        return len;
    }

    public int maxEnvelopes(int[][] envelopes) {
        if (   envelopes           == null
                || envelopes.length    == 0
                || envelopes[0]        == null
                || envelopes[0].length == 0){
            return 0;
        }

        Arrays.sort(envelopes, new Comparator<int[]>(){
            @Override
            public int compare(int[] e1, int[] e2){
                return Integer.compare(e1[0], e2[0]);
            }
        });

        int   n  = envelopes.length;
        int[] dp = new int[n];

        int ret = 0;
        for (int i = 0; i < n; i++){
            dp[i] = 1;

            for (int j = 0; j < i; j++){
                if (   envelopes[i][0] > envelopes[j][0]
                        && envelopes[i][1] > envelopes[j][1]){
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }

            ret = Math.max(ret, dp[i]);
        }
        return ret;
    }

    public int maxEnvelopesDPMine(int[][] envelopes) {
        //handle edge cases.
        if (envelopes == null) {
            return 0;
        }
        if (envelopes.length < 1) {
            return 0;
        }

        if (envelopes.length == 1) {
            return 1;
        }

        //Steps.
        //a. Sort the envelopes by area -> there is a chance that the bigger area can come on the top - not necessarily true.
        //b. go through the envelopes left to right and check which one can come at the top
        //c. there would be 2 ptrs i, and j. for each j check 0 to j-1 if j can be on the top of them.
        //d. do another pass on the aux array to find the max value -> thats the ans.
        int [] aux = new int[envelopes.length];
        List<Node> list = new ArrayList<Node>();
        for (int i=0; i<envelopes.length; i++) {
            list.add(new Node(envelopes[i][0], envelopes[i][1]));
        }

        Collections.sort(list, (a, b) -> a.area - b.area);
        Arrays.fill(aux, 1);
        int max = 1;

        for (int i=1; i<list.size(); i++) {
            int j = 0;
            while (j < i) {
                if (list.get(i).x > list.get(j).x && list.get(i).y > list.get(j).y) {
                    aux[i] = Math.max(aux[i], aux[j] + 1);
                    if (aux[i] > max) {
                        max = aux[i];
                    }
                }
                ++j;
            }
        }

        return max;
    }
}

class Node {
    int x;
    int y;
    int area;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.area = x*y;
    }
}
