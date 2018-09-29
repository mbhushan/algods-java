/**
 234. Palindrome Linked List
 https://leetcode.com/problems/palindrome-linked-list/description/

 Given a singly linked list, determine if it is a palindrome.

 Example 1:

 Input: 1->2
 Output: false
 Example 2:

 Input: 1->2->2->1
 Output: true
 Follow up:
 Could you do it in O(n) time and O(1) space?

 */
public class PalindromeList {

    public static void main(String[] args) {

    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        if (fast != null) { //odd nodes
            slow = slow.next;
        }

        slow = reverseList(slow);
        fast = head;

        while (slow != null) {
            if (fast.val != slow.val) {
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }

        return true;

    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;

        while (head != null) {
            ListNode node = head.next;
            head.next = prev;
            prev = head;
            head = node;
        }

        return prev;
    }
}

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
