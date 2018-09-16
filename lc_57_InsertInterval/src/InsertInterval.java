import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 57. Insert Interval
 https://leetcode.com/problems/insert-interval/description/


 Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

 You may assume that the intervals were initially sorted according to their start times.

 Example 1:

 Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 Output: [[1,5],[6,9]]
 Example 2:

 Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 Output: [[1,2],[3,10],[12,16]]
 Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

 */

  class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
  }

class InsertInterval {

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {

        List<Interval> result = new ArrayList<>();

        if (intervals == null) {
            return result;
        }



        int len = intervals.size();
        int index = 0;

        while (index < len) {
            if (newInterval != null && newInterval.end < intervals.get(index).start) {
                result.add(newInterval);
                newInterval = null;
            } else if (newInterval != null && newInterval.start > intervals.get(index).end) {
                result.add(intervals.get(index));
                ++index;
            } else if (newInterval != null && isOverlap(intervals.get(index), newInterval)) {
                newInterval.start = Math.min(intervals.get(index).start, newInterval.start);
                newInterval.end = Math.max(intervals.get(index).end, newInterval.end);
                ++index;
            } else {
                result.add(intervals.get(index));
                ++index;
            }
        }

        if (newInterval != null) {
            result.add(newInterval);
        }


        return result;


    }

    public List<Interval> insertGood(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new LinkedList<>();
        int i = 0;
        // add all the intervals ending before newInterval starts
        while (i < intervals.size() && intervals.get(i).end < newInterval.start)
            result.add(intervals.get(i++));
        // merge all overlapping intervals to one considering newInterval
        while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
            newInterval = new Interval( // we could mutate newInterval here also
                    Math.min(newInterval.start, intervals.get(i).start),
                    Math.max(newInterval.end, intervals.get(i).end));
            i++;
        }
        result.add(newInterval); // add the union of intervals we got
        // add all the rest
        while (i < intervals.size()) result.add(intervals.get(i++));
        return result;
    }

    public List<Interval> insertNW(List<Interval> intervals, Interval newInterval) {

        List<Interval> result = new ArrayList<>();

        if (intervals == null) {
            return result;
        }



        int len = intervals.size();
        int index = 0;

        if (intervals.size() > 0 && newInterval.end < intervals.get(0).start) {
            result.add(newInterval);
            result.addAll(intervals);
            return result;
        }

        while ((index < len) && !isOverlap(intervals.get(index), newInterval)) {
            result.add(intervals.get(index));
            ++index;
        }

        while ((index < len) && isOverlap(intervals.get(index), newInterval)) {
            newInterval.start = Math.min(intervals.get(index).start, newInterval.start);
            newInterval.end = Math.max(intervals.get(index).end, newInterval.end);
            ++index;

        }
        result.add(newInterval);

        while (index < len) {
            result.add(intervals.get(index));
            ++index;
        }


        return result;

    }

    private boolean isOverlap(Interval i1, Interval i2) {
        int max = Math.max(i1.start, i2.start);
        int min = Math.min(i1.end, i2.end);

        return max <= min;
    }
}
