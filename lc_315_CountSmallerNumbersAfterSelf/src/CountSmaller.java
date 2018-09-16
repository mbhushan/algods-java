import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**

 315. Count of Smaller Numbers After Self
 https://leetcode.com/problems/count-of-smaller-numbers-after-self/description/


 You are given an integer array nums and you have to return a new counts array.
 The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

 Example:

 Input: [5,2,6,1]
 Output: [2,1,1,0]
 Explanation:
 To the right of 5 there are 2 smaller elements (2 and 1).
 To the right of 2 there is only 1 smaller element (1).
 To the right of 6 there is 1 smaller element (1).
 To the right of 1 there is 0 smaller element.

    1
        6
      2
        5

 ================INPUT / OUTPUT =============
 input: [8, 6, 6, 9, 3, 7, 2, 1]
 [d: 1, c: 0] [d: 2, c: 1] [d: 3, c: 2] [d: 6, c: 3] [d: 6, c: 4] [d: 7, c: 5] [d: 8, c: 6] [d: 9, c: 5]
 count smaller:
 [6, 3, 3, 4, 2, 2, 1, 0]

 */

public class CountSmaller {


    private Node root;

    CountSmaller () {

        this.root = null;
    }

    public static void main(String[] args) {
        //int [] A = {5,2,6,1};

        int [] A = {8, 6, 6, 9, 3, 7, 2, 1};

        CountSmaller sol = new CountSmaller();

        List<Integer> list = new ArrayList<>();

        System.out.println("input: " + Arrays.toString(A));

        for (int i=A.length-1; i>=0; --i) {
            sol.insertBST(A[i], list);
        }

        sol.inorder();
        System.out.println();

        Collections.reverse(list);
        System.out.println("count smaller: ");
        System.out.println(list);
    }



    private void insertBST(int value, List<Integer> list) {
        int [] count = new int[1];
        count[0] = 0;
        this.root = insertBST(this.root, value, count);
        list.add(count[0]);

    }

    private Node insertBST(Node node, int value, int [] count) {
        if (node == null) {
            node = new Node(value);
            node.count = count[0];
            return node;
        }

        if (value <= node.data) {
            node.count += 1;
            node.left = insertBST(node.left, value, count);
        } else {
            count[0] = node.count+1;
            node.right = insertBST(node.right, value, count);
        }

        return node;
    }

    public void inorder() {
        inorder(this.root);
    }

    private void inorder(Node node) {
        if (node == null) {
            return;
        }

        if (node.left != null) {
            inorder(node.left);
        }
        System.out.print(node + " ");
        if (node.right != null) {
            inorder(node.right);
        }
    }
}


class Node {
    int data;
    int count;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.count = 0;
    }

    public String toString() {
        return "[d: " + data + ", c: " + count + "]";
    }
}