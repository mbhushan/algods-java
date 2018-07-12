import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 LeetCode – Number of Islands II (Java)

 A 2d grid map of m rows and n columns is initially filled with water.
 We may perform an addLand operation which turns the water at position (row, col) into a land.
 Given a list of positions to operate, count the number of islands after each addLand operation.
 An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 You may assume all four edges of the grid are all surrounded by water.

 */
public class IslandCount2 {

    public static void main(String [] args) {

        IslandCount2 ic = new IslandCount2();

        int [][][] islands = {
                {       {1, 1, 1, 1, 0},
                        {1, 1, 0, 1, 0},
                        {1, 1, 0, 0, 0},
                        {0, 0, 0, 0, 0}
                },
                {
                        {1, 0, 1, 0, 0},
                        {1, 0, 0, 1, 0},
                        {1, 1, 0, 0, 0},
                        {0, 0, 0, 0, 0}
                },
                {
                        {1, 1, 0, 1, 0},
                        {0, 1, 0, 1, 0},
                        {0, 0, 0, 0, 0},
                        {1, 0, 1, 0, 1}
                }
        };

        for (int i=0; i<islands.length; i++) {

            System.out.println("Started island counting for island: " + (i+1));

            int islandCount = 0;
            List<Node> positions = ic.getPositions(islands[i]);
            int row = islands[i].length;
            int col = islands[i][0].length;
            int [][] M = new int[row][col];
            int [] parent = new int[row * col];
            Arrays.fill(parent, -1);
            System.out.println("Add land positions: ");
            System.out.println(positions);

            for (Node node : positions) {
                int r = node.r;
                int c = node.c;
                islandCount = ic.addLand(M, r, c, parent, islandCount);
                System.out.println("island count: " + islandCount);
                ic.printMatrix(M);
                System.out.println();
            }

            System.out.println("FINISHED island counting for island: " + (i+1));
            System.out.println();

        }



    }

    public int addLand(int [][] M, int r, int c, int [] parent, int islandCount) {
        if (M == null || r < 0 || r > M.length || c < 0 || c > M[0].length) {
            return 0;
        }

        int row = M.length;
        int col = M[0].length;

        M[r][c] = 1;
        int p = r*col + c;


        int [][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        ++islandCount;

        for (int i=0; i<directions.length; i++) {
            r = r + directions[i][0];
            c = c + directions[i][1];
            if ((r >= 0 && r < M.length) && (c >= 0 && c < M.length) && M[r][c] == 1) {
                int q = (r)*col + c;

                if (getParent(parent, p) != getParent(parent, q)) {
                   --islandCount;
                   union(parent, p, q);
                }
            }
        }

        return islandCount;
    }

    public int getParent(int [] parent, int p) {
        while (parent[p] != -1) {
            p = parent[p];
        }

        return p;
    }

    public void union(int [] parent, int p, int q) {
        int k = getParent(parent, p);
        parent[q] = k;
    }

    public List<Node>  getPositions(int [][] M) {
        if (M == null) {
            return null;
        }

        int row = M.length;
        int col = M[0].length;

        List<Node> positions = new ArrayList<>();

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                if (M[i][j] == 1) {
                    Node node = new Node(i, j);
                    positions.add(node);
                }
            }
        }
        return positions;
    }

    public void printMatrix(int [][] M) {
        if (M == null) {
            return;
        }
        int row = M.length;
        int col = M[0].length;

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                System.out.print(M[i][j] + " ");
            }
            System.out.println();
        }
    }

}

class Node {
    int r;
    int c;

    Node (int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public String toString() {
        return "[" + this.r + ", " + this.c + "]";
    }
}