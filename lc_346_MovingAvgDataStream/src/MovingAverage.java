import java.util.LinkedList;
import java.util.Queue;

/**
 346. Moving Average from Data Stream

 Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

 Example:

 MovingAverage m = new MovingAverage(3);
 m.next(1) = 1
 m.next(10) = (1 + 10) / 2
 m.next(3) = (1 + 10 + 3) / 3
 m.next(5) = (10 + 3 + 5) / 3
 */

class MovingAverage {

    Queue<Integer> queue;
    int capacity;
    int total;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.queue = new LinkedList<>();
        this.capacity = size;
        this.total = 0;
    }

    public double next(int val) {
        if (queue.size() < capacity) {
            queue.add(val);
            total += val;

            return (total*1.0) / queue.size();
        }

        queue.add(val);
        total += val;

        if (queue.size() > capacity) {
            int rm = queue.remove();
            total -= rm;
        }

        return (total*1.0) / capacity;
    }
}

class MovingAverage1 {
    private double previousSum = 0.0;
    private int maxSize;
    private Queue<Integer> currentWindow;

    public MovingAverage1(int size) {
        currentWindow = new LinkedList<Integer>();
        maxSize = size;
    }

    public double next(int val) {
        if (currentWindow.size() == maxSize)
        {
            previousSum -= currentWindow.remove();
        }

        previousSum += val;
        currentWindow.add(val);
        return previousSum / currentWindow.size();
    }}
