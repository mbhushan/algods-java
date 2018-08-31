import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 Given a collection of intervals, merge all overlapping intervals.

 Example 1:
 Input: [[1,3],[2,6],[8,10],[15,18]]
 Output: [[1,6],[8,10],[15,18]]
 Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

 Example 2:
 Input: [[1,4],[4,5]]
 Output: [[1,5]]
 Explanation: Intervals [1,4] and [4,5] are considerred overlapping.


 ======================================
 INPUT / OUTPUT
 ======================================
 input intervals: [[1, 3], [2, 6], [8, 10], [15, 18]]
 merged: [[1, 6], [8, 10], [15, 18]]

*/

public class MergeIntervals {

    public static void main(String [] args) {
        MergeIntervals mi = new MergeIntervals();

        Integer [] starts = {1, 2, 8, 15};
        //Integer [] starts = {1, 0};
        Integer [] ends = {3, 6, 10, 18};
        //Integer [] ends = {4, 4};
        ArrayList<Interval> intervals = new ArrayList<Interval>();
        for (int i=0; i<starts.length; i++) {
            intervals.add(new Interval(starts[i], ends[i]));
        }
        System.out.println();

        System.out.println("input intervals: " + intervals);
        mi.merge(intervals);



    }

    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<>();

        if (intervals == null || intervals.size() < 1) {
            return result;
        }

        if (intervals.size() == 1) {
            result.addAll(intervals);
            return result;
        }

        int len = intervals.size();

        Collections.sort(intervals, new IntervalCompare());
        Interval prev = intervals.get(0);

        for (int i=1; i<len; i++) {
            Interval curr = intervals.get(i);
            if (isOverlap(prev, curr)) {
                prev.start = Math.min(prev.start, curr.start);
                prev.end = Math.max(prev.end, curr.end);
            } else {
                result.add(prev);
                prev = curr;
            }
        }
        result.add(prev);

        System.out.println("merged: " + result);

        return result;

    }

    private boolean isOverlap(Interval i, Interval j) {
        int maxStart = Math.max(i.start, j.start);
        int minEnd = Math.min(i.end, j.end) ;

        return maxStart <= minEnd;
    }
}

class Interval {
    int start;
    int end;

    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }

    @Override
    public String toString() {
        return "[" + start + ", " + end + "]";
    }
}

class IntervalCompare implements Comparator<Interval> {

    @Override
    public int compare(Interval o1, Interval o2) {
        return o1.start - o2.start;
    }
}
