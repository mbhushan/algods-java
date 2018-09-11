/**
 362. Design Hit Counter
 https://leetcode.com/problems/design-hit-counter/description/

 Design a hit counter which counts the number of hits received in the past 5 minutes.

 Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.

 It is possible that several hits arrive roughly at the same time.

 Example:

 HitCounter counter = new HitCounter();

 // hit at timestamp 1.
 counter.hit(1);

 // hit at timestamp 2.
 counter.hit(2);

 // hit at timestamp 3.
 counter.hit(3);

 // get hits at timestamp 4, should return 3.
 counter.getHits(4);

 // hit at timestamp 300.
 counter.hit(300);

 // get hits at timestamp 300, should return 4.
 counter.getHits(300);

 // get hits at timestamp 301, should return 3.
 counter.getHits(301);
 Follow up:
 What if the number of hits per second could be very large? Does your design scale?

 ==============
 INPUT / OUTPUT
 ==============
 hit: 3
 hit: 4
 hit: 3

 One more potential solution:
 https://github.com/mbhushan/codique/blob/master/QPSCounter/src/QPSCounter.java


 */
public class HitCounter {

    private static int [] hits;
    private static long [] times;
    private int size;

    HitCounter(int size) {
        this.size = size;
        hits = new int[size];
        times = new long[size];

    }

    public static void main(String [] args) {
        HitCounter counter = new HitCounter(300);

        counter.hit(1);
        counter.hit(2);
        counter.hit(3);
        System.out.println("hit: " + counter.getHits(4));

        counter.hit(300);
        System.out.println("hit: " + counter.getHits(300));
        System.out.println("hit: " + counter.getHits(301));

    }

    public void hit(int timestamp) {
        int index = timestamp % 300;
        if (times[index] != timestamp) {
            times[index] = timestamp;
            hits[index] = 0;
        }
        ++hits[index];
    }

    public int getHits(int timestamp) {
        int total = 0;

        for (int i=0; i<times.length; i++) {
            if (timestamp - times[i] < 300) {
                total += hits[i];
            } else {
                hits[i] = 0;
            }
        }
        return total;
    }
}
