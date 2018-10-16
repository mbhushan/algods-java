import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 210. Course Schedule II

 There are a total of n courses you have to take, labeled from 0 to n-1.

 Some courses may have prerequisites, for example to take course 0 you have to
 first take course 1, which is expressed as a pair: [0,1]

 Given the total number of courses and a list of prerequisite pairs, return
 the ordering of courses you should take to finish all courses.

 There may be multiple correct orders, you just need to return one of them.
 If it is impossible to finish all courses, return an empty array.

 Example 1:

 Input: 2, [[1,0]]
 Output: [0,1]
 Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
 course 0. So the correct course order is [0,1] .
 Example 2:

 Input: 4, [[1,0],[2,0],[3,1],[3,2]]
 Output: [0,1,2,3] or [0,2,1,3]
 Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
 courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
 Note:

 The input prerequisites is a graph represented by a list of edges, not adjacency matrices.
 Read more about how a graph is represented.
 You may assume that there are no duplicate edges in the input prerequisites.
 */
public class CourseSchedule2 {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer> order = new ArrayList<>();

        boolean flag = canFinish(numCourses, prerequisites, order);
        if (!flag) {
            order.clear();

        }

        Collections.reverse(order);
        return order.stream().mapToInt(i->i).toArray();


    }

    public static boolean canFinish(int numCourses, int[][] prerequisites, List<Integer> order) {
        if (numCourses <= 0)
            return false;
        Queue<Integer> queue = new LinkedList<>();
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            inDegree[prerequisites[i][1]]++;
        }
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
                // order.add(i);
            }

        }
        while (!queue.isEmpty()) {
            int x = queue.poll();
            order.add(x);
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
