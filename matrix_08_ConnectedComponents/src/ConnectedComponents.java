import java.util.Arrays;

/**
 LeetCode – Number of Connected Components in an Undirected Graph (Java)

 Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
 write a function to find the number of connected components in an undirected graph.

 Example 1:
 0 3
 | |
 1 --- 2 4
 Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

 ================
 INPUT / OUTPUT
 ================

 total connected components: 2

 */

public class ConnectedComponents {

    public static void main(String[] args) {

        ConnectedComponents cc = new ConnectedComponents();

        int n  = 5;

        int [][] edges = {
                {0, 1},
                {1, 2},
                {3, 4},
                //{4, 2}
        };

        cc.getConnectedComponents(edges, n);
    }

    public void getConnectedComponents(int [][] edges, int n) {

        int [] parents = new int[n];
        Arrays.fill(parents, -1);
        int count = 5;

        for (int i=0; i<edges.length; i++) {
            int [] col = edges[i];

            int p = col[0];
            int q = col[1];
            //++count;

            if ((parents[p] == -1 && parents[q] == -1) || (getParent(parents, p) != getParent(parents, q))) {
                parents[q] = p;
                --count;
            }
        }

        System.out.println("total connected components: " + count);
    }

    private int getParent(int [] parents, int x) {
        while (parents[x] != -1) {
            x = parents[x];
        }

        return x;
    }


}
