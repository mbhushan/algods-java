import java.util.Arrays;

/**
 253. Meeting Rooms II
 https://leetcode.com/problems/meeting-rooms-ii/description/

 Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 find the minimum number of conference rooms required.

 Example 1:

 Input: [[0, 30],[5, 10],[15, 20]]
 Output: 2
 Example 2:

 Input: [[7,10],[2,4]]
 Output: 1

 */

public class MeetingRooms2 {

    public static void main(String[] args) {
        MeetingRooms2 mr = new MeetingRooms2();


        int [][] M = {
                {0, 30},
                {5, 10},
                {15, 20}
        };

        int [][] M1 = {
                {13, 15},
                {1, 13}
        };

        Interval [] intervals = new Interval[M1.length];
        for (int i=0; i<M1.length; i++) {
            intervals[i] = new Interval(M1[i][0], M1[i][1]);
        }

        mr.minMeetingRooms(intervals);

    }

    public int minMeetingRooms(Interval[] intervals) {

        System.out.println("interval size: " + intervals.length);
        System.out.println("intervals: ");

        for (Interval i: intervals) {
            System.out.println(i);
        }

        int [] starts = new int[intervals.length];
        int [] ends = new int[intervals.length];

        int j = 0;
        for (Interval i: intervals) {
            starts[j] = i.start;
            ends[j] = i.end;
            ++j;
        }

        int len = 0, count = 0, i=0;
        j = 0;


        Arrays.sort(starts);
        Arrays.sort(ends);

//        for (int i=0; i<starts.length; i++) {
//            if (starts[i] < ends[j]) {
//                ++room;
//                len = Math.max(len, room);
//            } else {
//                --room;
//                ++j;
//            }
//        }

        while(i < intervals.length && j < intervals.length){
            if(starts[i] < ends[j]){
                count++;   i++;
                len = Math.max(len, count);
            }else{
                j++;  count--;
            }
        }

        System.out.println("min room: " + len);

        return len;
    }

}

class Interval {
    int start;
    int end;

    Interval() {
        this.start = 0;
        this.end = 0;
    }

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[" + this.start + ", " + this.end + "]";
    }
}
