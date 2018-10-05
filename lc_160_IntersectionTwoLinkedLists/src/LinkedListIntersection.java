/**
 160. Intersection of Two Linked Lists
 https://leetcode.com/problems/intersection-of-two-linked-lists/description/

 Write a program to find the node at which the intersection of two singly linked lists begins.


 For example, the following two linked lists:

 A:              a1 → a2
                        ↘
                         c1 → c2 → c3
                       ↗
 B:                   b1 → b2 → b3
 */
public class LinkedListIntersection {

    public static void main(String[] args) {

    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int lenA = 0;
        ListNode curr = headA;
        while (curr != null) {
            curr = curr.next;
            ++lenA;
        }

        int lenB = 0;
        curr = headB;
        while (curr != null) {
            curr = curr.next;
            ++lenB;
        }
        int diff = Math.abs(lenA - lenB);
        if (lenA > lenB) {
            while (diff > 0) {
                headA = headA.next;
                --diff;
            }
        } else if (lenB > lenA) {
            while (diff > 0) {
                headB = headB.next;
                --diff;
            }
        }

        while (headA != null && headB != null) {
            if (headA.equals(headB)) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }

        return null;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}
