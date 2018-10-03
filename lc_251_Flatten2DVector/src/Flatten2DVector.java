import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 251. Flatten 2D Vector
 https://leetcode.com/problems/flatten-2d-vector/description/
 Implement an iterator to flatten a 2d vector.

 Example:

 Input: 2d vector =
 [
 [1,2],
 [3],
 [4,5,6]
 ]
 Output: [1,2,3,4,5,6]
 Explanation: By calling next repeatedly until hasNext returns false,
 the order of elements returned by next should be: [1,2,3,4,5,6].
 Follow up:
 As an added challenge, try to code it using only iterators in C++ or iterators in Java.
 */
public class Flatten2DVector {
}

class Vector2D implements Iterator<Integer> {
    Queue<Integer> queue = new LinkedList<>();

    public Vector2D(List<List<Integer>> vec2d) {
        for (List<Integer> list: vec2d) {
            queue.addAll(list);
        }
    }

    @Override
    public Integer next() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.remove();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */
