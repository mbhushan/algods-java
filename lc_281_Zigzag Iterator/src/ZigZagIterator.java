import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 281. Zigzag Iterator
 https://leetcode.com/problems/zigzag-iterator/description/

 Given two 1d vectors, implement an iterator to return their elements alternately.

 Example:

 Input:
 v1 = [1,2]
 v2 = [3,4,5,6]

 Output: [1,3,2,4,5,6]

 Explanation: By calling next repeatedly until hasNext returns false,
 the order of elements returned by next should be: [1,3,2,4,5,6].
 Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

 Clarification for the follow up question:
 The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases.
 If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example:

 Input:
 [1,2,3]
 [4,5,6,7]
 [8,9]

 Output: [1,4,8,2,5,9,3,6,7].
 */
public class ZigZagIterator {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i=1; i<=10; i++) {
            list.add(i);
        }

        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            Integer val = it.next();
            System.out.println(val);
        }
    }
}

class ZigzagIterator {

    boolean toggle;
    Iterator itr1;
    Iterator itr2;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        toggle = true;
        itr1 = v1.iterator();
        itr2 = v2.iterator();
    }

    public int next() {
        Integer val = null;
        if (toggle && itr1.hasNext()) {
            val = (Integer)itr1.next();
            toggle = false;
        } else if (!toggle && itr2.hasNext()) {
            val = (Integer)itr2.next();
            toggle = true;
        }
        if (val == null) {
            val = itr1.hasNext() ? (Integer)itr1.next() : (Integer)itr2.next();
        }

        return val;
    }

    public boolean hasNext() {
        return (itr1.hasNext() || itr2.hasNext());
    }
}

class ZigzagIterator1 {
    LinkedList<Iterator> list;
    public ZigzagIterator1(List<Integer> v1, List<Integer> v2) {
        list = new LinkedList<Iterator>();
        if(!v1.isEmpty()) list.add(v1.iterator());
        if(!v2.isEmpty()) list.add(v2.iterator());
    }

    public int next() {
        Iterator poll = list.remove();
        int result = (Integer)poll.next();
        if(poll.hasNext()) list.add(poll);
        return result;
    }

    public boolean hasNext() {
        return !list.isEmpty();
    }
}
