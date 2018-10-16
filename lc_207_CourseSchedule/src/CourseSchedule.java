import java.util.LinkedList;
import java.util.Queue;

/**
 207. Course Schedule
 https://leetcode.com/problems/course-schedule/description/

 There are a total of n courses you have to take, labeled from 0 to n-1.

 Some courses may have prerequisites, for example to take course 0 you
 have to first take course 1, which is expressed as a pair: [0,1]

 Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

 Example 1:

 Input: 2, [[1,0]]
 Output: true
 Explanation: There are a total of 2 courses to take.
 To take course 1 you should have finished course 0. So it is possible.
 Example 2:

 Input: 2, [[1,0],[0,1]]
 Output: false
 Explanation: There are a total of 2 courses to take.
 To take course 1 you should have finished course 0, and to take course 0 you should
 also have finished course 1. So it is impossible.
 Note:

 The input prerequisites is a graph represented by a list of edges, not adjacency matrices.
 Read more about how a graph is represented.
 You may assume that there are no duplicate edges in the input prerequisites.
 */
public class CourseSchedule {

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0)
            return false;
        Queue<Integer> queue = new LinkedList<>();
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            inDegree[prerequisites[i][1]]++;
        }
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0)
                queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int x = queue.poll();
            for (int i = 0; i < prerequisites.length; i++) {
                if (x == prerequisites[i][0]) {
                    inDegree[prerequisites[i][1]]--;
                    if (inDegree[prerequisites[i][1]] == 0)
                        queue.offer(prerequisites[i][1]);
                }
            }
        }
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] != 0)
                return false;
        }
        return true;
    }
}
