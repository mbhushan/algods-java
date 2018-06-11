/**
 * For the key values 1...numKeys, how many structurally unique
 * binary search trees are possible that store those keys?
 * Strategy: consider that each value could be the root.
 * Recursively find the size of the left and right subtrees.


 ============================================================
 Input / Output
 ============================================================
 number of BSTs with key = 0 : 1
 number of BSTs with key = 1 : 1
 number of BSTs with key = 2 : 2
 number of BSTs with key = 3 : 5
 number of BSTs with key = 4 : 14
 number of BSTs with key = 5 : 42
 number of BSTs with key = 6 : 132
 number of BSTs with key = 7 : 429
 number of BSTs with key = 8 : 1430
 number of BSTs with key = 9 : 4862
 */

public class BST {

    public static void main(String[] args) {
        BST bt = new BST();

        int n = 10;
        for (int i=0 ;i <n; i++) {
            System.out.println("number of BSTs with key = " + i + " : " + bt.countBST(i));
        }
    }

    public int countBST(int n) {
        if (n <= 1) {
            return 1;
        }
        int sum = 0 ;
        int left, right;
        for (int root=1; root<= n; root++) {

            left = countBST(root - 1);
            right = countBST(n - root);

            sum += left*right;
        }
        return sum;
    }
}
