import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 Problem:

 Given a bag with weight W and a list of items having a certain
 weight and value, how would you fill the bag so that you have
 maximum value?
 Another variation of this problem is unbounded knapsack problem
 where an item can be repeated which is much simpler in the sense
 just take the value/weight ratio for each item then sort them in
 decreasing order and keep adding the item until you have no
 capacity left.

 =========================
 INPUT / OUTPUT
 =========================
 weights: [1, 3, 4, 5]
 values: [1, 4, 5, 7]
 max value bag can have? 9
 0 0 0 0 0 0 0 0
 0 1 1 1 1 1 1 1
 0 1 1 4 5 5 5 5
 0 1 1 4 5 6 6 9
 0 1 1 4 5 7 8 9
 ans: [2, 1]
 weights : values
 4 : 5
 3 : 4


 */

public class Knapsack {

    public static void main(String[] args) {

        Knapsack kObj = new Knapsack();
        int [] weights = {1, 3, 4, 5};
        int [] values = {1, 4, 5, 7};
        int W = 7;

        kObj.knapsackValue(weights, values, W);

    }

    public void knapsackValue(int [] weights, int [] values, int bagWeight) {

        System.out.println("weights: " + Arrays.toString(weights));
        System.out.println("values: " + Arrays.toString(values));
        int row = weights.length+1;
        int col = bagWeight+1;
        int [][] DP = new int [row][col];
        int maxValue = Integer.MIN_VALUE;

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                if (i == 0 ||  j == 0) {
                    DP[i][j] = 0;
                    continue;
                }
                if (weights[i-1] > j) {
                    DP[i][j] = DP[i-1][j];
                } else {
                    DP[i][j] = Math.max(DP[i-1][j - weights[i-1]] + values[i-1], DP[i-1][j]);
                    if (DP[i][j] > maxValue) {
                        maxValue = DP[i][j];
                    }
                }
            }
        }

        System.out.println("max value bag can have? " + maxValue);
        printMatrix(DP);
        findItems(DP, weights, values);
    }

    public void findItems(int [][] M, int [] weights, int [] values) {
        int row = M.length-1;
        int col = M[0].length - 1;
        List<Integer> indices = new ArrayList<>();
        while (row >=1 && col >= 1) {
            if (M[row][col] == M[row-1][col]) {
                --row;
            } else {
                indices.add(row-1);
                col = col - weights[row-1];
            }
        }
        System.out.println("ans: " + indices.toString());
        System.out.println("weights : values");
        for (int index: indices) {
            System.out.println(weights[index] + " : " + values[index]);
        }

    }

    public void printMatrix(int [][] M) {
        if (M == null) {
            return;
        }
        for (int i=0; i<M.length; i++) {
            for (int j=0; j<M[0].length; j++) {
                System.out.print(M[i][j] + " ");
            }
            System.out.println();
        }
    }


}
