/**
 https://leetcode.com/problems/add-two-numbers/description/
 2. Add Two Numbers

 You are given two non-empty linked lists representing two non-negative integers.
 The digits are stored in reverse order and each of their nodes contain a single digit.
 Add the two numbers and return it as a linked list.

 You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 Example:

 Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 0 -> 8
 Explanation: 342 + 465 = 807.

 */
public class AddNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //handle edge cases
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        //Steps.
        //a. iterate from start for l1 and l2 and add the values.
        //b. keep track of carry.
        //c. once we run of out either list - make sure we finish the longer list along with carry.
        //d. in the end if carry is non-zero, add it to the resultant list.
        //e. return the head of the result list.

        ListNode n1 = new ListNode(-1);
        ListNode curr = n1;
        int carry = 0;

        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carry;
            int x = sum % 10;
            carry = sum / 10;

            //update n1
            curr.next = new ListNode(x);
            curr = curr.next;

            l1 = l1.next;
            l2 = l2.next;

        }

        while (l1 != null) {
            int sum = l1.val + carry;
            int x = sum % 10;
            carry = sum / 10;

            //update n1
            curr.next = new ListNode(x);
            curr = curr.next;

            l1 = l1.next;
        }

        while (l2 != null) {
            int sum = l2.val + carry;
            int x = sum % 10;
            carry = sum / 10;

            //update n1
            curr.next = new ListNode(x);
            curr = curr.next;


            l2 = l2.next;

        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }

        return n1.next;

    }
}

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
 }