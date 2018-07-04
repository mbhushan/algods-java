/**
 LeetCode – Unique Binary Search Trees (Java)

 Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

 For example, Given n = 3, there are a total of 5 unique BST's.

  1         3     3      2      1
  \       /     /      / \      \
  3      2     1      1   3      2
  /     /       \                 \
 2     1         2                 3
 */

public class BSTUnqiueTrees {

    public static void main(String[] args) {

        BSTUnqiueTrees bt = new BSTUnqiueTrees();

        int total = 5;


        for (int i=1; i<=total; i++) {
            int result = bt.calcUniqueBST(i);
            System.out.println("n = " + i + ", total trees: " + result);
        }

    }

    public int calcUniqueBST(int n) {

        int [] T = new int[n+1];
        T[0] = 1;
        T[1] = 1;

        for (int i=2; i<=n; i++) {
            for (int j = 0; j<i; j++) {
                T[i] += T[j] * T[i-j-1];
            }
        }

        return T[n];
    }
}
