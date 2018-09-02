import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 621. Task Scheduler
 https://leetcode.com/problems/task-scheduler/description/
 Given a char array representing tasks CPU need to do. It contains capital letters A to Z
 where different letters represent different tasks.Tasks could be done without original order.
 Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

 However, there is a non-negative cooling interval n that means between two same tasks,
 there must be at least n intervals that CPU are doing different tasks or just be idle.

 You need to return the least number of intervals the CPU will take to finish all the given tasks.

 Example 1:
 Input: tasks = ["A","A","A","B","B","B"], n = 2
 Output: 8
 Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.

 ==============
 INPUT / OUTPUT
 ==============
 array: [A, A, A, A, A, B, B, B]
 [[A, 5], [B, 3]]
 ABIABIABIAIIA
 time: 13

 array: [A, A, A, B, B, B]
 [[A, 3], [B, 3]]
 ABIABIAB
 time: 8
 */

public class TaskScheduler {

    public static void main(String[] args) {
        TaskScheduler ts = new TaskScheduler();

        int n = 2;
        char [][] tasks = {
                {'A','A','A','A','A','B','B','B'},
                {'A','A','A','B','B','B'},
        };

        for (int i=0; i<tasks.length; i++) {
            System.out.println("array: " + Arrays.toString(tasks[i]));
            int ans = ts.leastInterval(tasks[i], n);
            System.out.println("time: " + ans);
            System.out.println();
        }

    }

    public int leastInterval(char[] tasks, int n) {

        PriorityQueue<Node> pq = new PriorityQueue<Node>(10, new TaskComparator());
        Map<Character, Integer> hmap = new HashMap<>();

        for (char ch: tasks) {
            hmap.put(ch, hmap.getOrDefault(ch, 0)+1);
        }

        for (char ch: hmap.keySet()) {
            pq.add(new Node(ch, hmap.get(ch)));
        }

        System.out.println(pq);
        StringBuffer sb = new StringBuffer();

        int wt = 0;
        int time = 0;
        while(!pq.isEmpty()) {
            wt = n;
            List<Node> tmpList = new ArrayList<>();
            while (wt > 0 && !pq.isEmpty()) {
                Node node = pq.poll();
                sb.append(node.ch);
                if (node.count > 1) {
                    --node.count;
                    tmpList.add(node);
                }
                ++time;
                --wt;
            }
            while (wt >= 0 && !tmpList.isEmpty()) {
                sb.append("I");
                ++time;
                --wt;
            }

            //heapify
            for (Node node: tmpList) {
                pq.add(node);
            }
        }
        System.out.println(sb);

        return time;
    }
}

class TaskComparator implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {
        if ((o1.count - o2.count) < 0) {
            return 1;
        } else if ((o1.count - o2.count) > 0) {
            return -1;
        } else {
            return 0;
        }
    }
}

class Node {
    char ch;
    int count;

    Node (char ch, int count) {
        this.ch = ch;
        this.count = count;
    }

    @Override
    public String toString() {
        return "[" + this.ch + ", " + this.count + "]";
    }
}
