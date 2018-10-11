import java.util.Stack;

/**
 445. Add Two Numbers II
 https://leetcode.com/problems/add-two-numbers-ii/description/

 You are given two non-empty linked lists representing two non-negative integers.
 The most significant digit comes first and each of their nodes contain a single digit.
 Add the two numbers and return it as a linked list.

 You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 Follow up:
 What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

 Example:

 Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 8 -> 0 -> 7

 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }

 ========
 Steps:
 Explain the algorithm.
 Write down the steps
 During if/else or conditional.
 Write the logic in english then translate
 Put todo or edge cases if not handled.
 Return relevant data
 Test it.
 Trace with few use cases.

 */
class AddNumbers2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {


        Stack<ListNode> st1 = new Stack<>();
        Stack<ListNode> st2 = new Stack<>();

        ListNode node = l1;
        while (node != null) {
            st1.push(node);
            node = node.next;
        }
        node = l2;
        while (node != null) {
            st2.push(node);
            node = node.next;
        }

        ListNode res = new ListNode(0);
        int carry = 0, sum=0;

        while (!st1.isEmpty() || !st2.isEmpty()) {
            sum = 0;
            if (!st1.isEmpty()) {
                sum += st1.pop().val;
            }
            if (!st2.isEmpty()) {
                sum += st2.pop().val;
            }
            sum += carry;
            carry = sum / 10;
            sum = sum % 10;
            node = new ListNode(sum);
            node.next = res.next;
            res.next = node;
        }

        if (carry > 0) {
            node = new ListNode(carry);
            node.next = res.next;
            res.next = node;
        }

        return res.next;
    }
}
class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
