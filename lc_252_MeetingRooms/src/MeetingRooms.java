import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 252. Meeting Rooms
 https://leetcode.com/problems/meeting-rooms/description/

 Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 determine if a person could attend all meetings.

 Example 1:

 Input: [[0,30],[5,10],[15,20]]
 Output: false
 Example 2:

 Input: [[7,10],[2,4]]
 Output: true

 */
public class MeetingRooms {

    public boolean canAttendMeetings(Interval[] intervals) {

        List<Interval> list = new ArrayList<>();

        for (Interval i: intervals) {
            list.add(i);
        }

        Collections.sort(list, (a, b) -> a.start - b.start);
        //System.out.println(list);
        for (int i=0; i<list.size()-1; i++) {
            if (list.get(i).end > list.get(i+1).start) {
                return false;
            }
        }
        return true;
    }
}

class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
  }
