
import java.util.Arrays;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

/*
Build a queue class with the enqueue and dequeue methods. The queue can store an *UNLIMITED* number of elements but you are limited to using arrays that can store up to 5 elements max.
*/

//A[] array = new A[5];
/**
 enque
 deque

 storage primitive-> array of size 5

 A[0][] => 1, 1, 1, 1, 1
 A[1][] => 1,1, 1, 1, 1
 A[2][] = 1,1, 1, 1, 1

 (Array of size 5) -> ()





 */



public class QueueClass {



    static class Queue {

        private ListNode head;
        private ListNode tail;
        private int enqueIndex = 0;
        private int dequeIndex = 0;

        //initialize head and taill to null.
        Queue() {
            head = null;
            tail = null;
        }

        public void enque(int value) {
            // steps:
            // check if there is space in tail node.
            // if yes then enqueu it
            //if no get a new ListNode and move the tail ptr and put the value.
            if (head == null) {
                head = new ListNode();
                tail = head;
            }
            int [] arr = tail.arr;
            arr[enqueIndex] = value; //TODO: init index and keep track of it.
            ++enqueIndex;
            System.out.println(Arrays.toString(arr));
            if (enqueIndex > 4) {
                //get a new tail node and reset index.
                tail.next = new ListNode();
                tail = tail.next;
                enqueIndex = 0;
            }
        }

        public Integer deque() throws Exception {
            //Steps:
            //a. check for the dequeIndex;
            //b. get the value from head node for dequeIndex
            //c. increment dequeIndex
            //d. if dequeuIndex > 4 then move head to head.next (check for head.next exists).
            //e. check for empty Queue
            if (head == null) {
                throw new Exception();
            }
            int [] arr = head.arr;
            int value = arr[dequeIndex];
            ++dequeIndex;
            if (dequeIndex > 4) {
                head = head.next; //handle edge cases like head == null;
                if (head == null) {
                    tail = null;
                }
                dequeIndex = 0;
            }

            return value;
        }

    }

    static class ListNode {
        int [] arr = new int[5];
        ListNode next = null;

        ListNode() {
        }
    }

    public static void main(String[] args) throws Exception {
        QueueClass sol = new QueueClass();
        sol.demo();
    }

    public void demo() throws Exception {
        Queue queue = new Queue();

        queue.enque(1);
        queue.enque(2);
        queue.enque(3);
        queue.enque(4);
        queue.enque(5);
        queue.enque(6);
        queue.enque(7);
        System.out.println(queue.deque());
        System.out.println(queue.deque());
        System.out.println(queue.deque());
        System.out.println(queue.deque());
        System.out.println(queue.deque());
    }
}

