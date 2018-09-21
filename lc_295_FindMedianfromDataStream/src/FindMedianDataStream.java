import java.util.PriorityQueue;

/**

 295. Find Median from Data Stream
 https://leetcode.com/problems/find-median-from-data-stream/description/

 Median is the middle value in an ordered integer list. If the size of the list is even,
 there is no middle value. So the median is the mean of the two middle value.

 For example,
 [2,3,4], the median is 3

 [2,3], the median is (2 + 3) / 2 = 2.5

 Design a data structure that supports the following two operations:

 void addNum(int num) - Add a integer number from the data stream to the data structure.
 double findMedian() - Return the median of all elements so far.


 Example:

 addNum(1)
 addNum(2)
 findMedian() -> 1.5
 addNum(3)
 findMedian() -> 2


 Follow up:

 If all integer numbers from the stream are between 0 and 100, how would you optimize it?
 If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?

 */
public class FindMedianDataStream {

    public static void main(String[] args) {
        FindMedianDataStream fd = new FindMedianDataStream();

    }
}

class MedianFinder {

    /** initialize your data structure here. */
    PriorityQueue<Integer> minPQ = new PriorityQueue<>(10, (a, b) -> a - b);
    PriorityQueue<Integer> maxPQ = new PriorityQueue<>(10, (a, b) -> b - a);

    public MedianFinder() {

    }

    public void addNum(int num) {
        minPQ.add(num);

        maxPQ.add(minPQ.peek());
        minPQ.remove();

        if (minPQ.size() < maxPQ.size()) {
            minPQ.add(maxPQ.peek());
            maxPQ.remove();
        }

    }

    public double findMedian() {
        if (minPQ.size() > maxPQ.size()) {
            return minPQ.peek();
        }
        double ans = (minPQ.peek() + maxPQ.peek()) * 0.5;

        return ans;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

class MedianFinderOld {

    /** initialize your data structure here. */
    PriorityQueue<Integer> minPQ = new PriorityQueue<>(10, (a, b) -> a - b);
    PriorityQueue<Integer> maxPQ = new PriorityQueue<>(10, (a, b) -> b - a);

    public MedianFinderOld() {

    }

    public void addNum(int num) {
        if (minPQ.size() < maxPQ.size()) {
            minPQ.add(num);
        } else if (minPQ.size() > maxPQ.size()) {
            maxPQ.add(num);
        } else {
            minPQ.add(num);
        }

    }

    public double findMedian() {
        Integer x = !minPQ.isEmpty() ? minPQ.peek(): null;
        Integer y = !maxPQ.isEmpty() ? maxPQ.peek(): null;

        if (x != null && y != null) {
            if (minPQ.size() > maxPQ.size()) {
                return maxPQ.peek();
            } else {
                return (x+y)*1.0 / 2.0;
            }
        } else {
            return (x == null) ? y : x;
        }
    }
}
