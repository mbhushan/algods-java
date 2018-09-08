import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 785. Is Graph Bipartite?
 https://leetcode.com/problems/is-graph-bipartite/description/

 Given an undirected graph, return true if and only if it is bipartite.

 Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.

 The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.

 Example 1:
 Input: [[1,3], [0,2], [1,3], [0,2]]
 Output: true
 Explanation:
 The graph looks like this:
 0----1
 |    |
 |    |
 3----2
 We can divide the vertices into two groups: {0, 2} and {1, 3}.
 Example 2:
 Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
 Output: false
 Explanation:
 The graph looks like this:
 0----1
 | \  |
 |  \ |
 3----2
 We cannot find a way to divide the set of nodes into two independent subsets.

 */
public class GraphBipartite {

    public static void main(String[] args) {
        GraphBipartite gb = new GraphBipartite();

        int [][] A = {{1,3}, {0,2}, {1,3}, {0,2}};
        int [][] B = {{1,2,3}, {0,2}, {0,1,3}, {0,2}};

       System.out.println("A: " + gb.isBipartite(A));
        System.out.println("B: " + gb.isBipartite(B));
    }

    public boolean isBipartite(int[][] graph) {

        if (graph == null) {
            return false;
        }

        int glen = graph.length;
        Queue<Integer> queue = new LinkedList<Integer>();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        //Set<Integer> green = new HashSet<Integer>();

        queue.add(0);
        map.put(0, 1);
        //int color = 1;
        boolean [] visited = new boolean[glen];
       // visited[0] = true;

        while (!queue.isEmpty()) {

            int u = queue.remove();
            int uColor = map.get(u);
            int vColor = (uColor == 1) ? 2: 1;

            int [] v = graph[u];

            for (int i: v) {
                if (map.containsKey(i) && !visited[i]) {
                    int color = map.get(i);
                    if (color == uColor) {
                        return false;
                    }
                }

                if (!visited[i]) {
                    queue.add(i);
                    //visited[i] = true;
                    map.put(i, vColor);
                }
            }

            visited[u] = true;
        }

        return true;

    }
}
