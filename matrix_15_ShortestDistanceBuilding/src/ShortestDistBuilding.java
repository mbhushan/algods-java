import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 LeetCode – Shortest Distance from All Buildings (Java)

 You want to build a house on an empty land which reaches all buildings in the shortest amount of distance.
 You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

 Each 0 marks an empty land which you can pass by freely.
 Each 1 marks a building which you cannot pass through.
 Each 2 marks an obstacle which you cannot pass through.


 For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2).

 1 - 0 - 2 - 0 - 1
 |   |   |   |   |
 0 - 0 - 0 - 0 - 0
 |   |   |   |   |
 0 - 0 - 1 - 0 - 0

 The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal.
 So return 7.

 ===============
 INPUT / OUTPUT
 ===============
 path at cell: [2, 4]: [1, 3, 5]
 total distance: 9
 path at cell: [2, 0]: [1, 3, 5]
 total distance: 9
 path at cell: [2, 4]: [1, 3, 5]
 total distance: 9
 path at cell: [2, 4]: [2, 2, 4]
 total distance: 8
 path at cell: [2, 4]: [1, 3, 3]
 total distance: 7
 path at cell: [2, 0]: [2, 2, 4]
 total distance: 8
 path at cell: [2, 0]: [1, 3, 5]
 total distance: 9
 path at cell: [2, 4]: [2, 2, 6]
 total distance: 10
 path at cell: [2, 4]: [1, 3, 5]
 total distance: 9
 path at cell: [2, 0]: [1, 3, 5]
 total distance: 9
 path at cell: [2, 0]: [2, 2, 6]
 total distance: 10

 final distances: [1, 3, 3]
 final shortest distance: 7

 */
public class ShortestDistBuilding {

    public static void main(String[] args) {
        ShortestDistBuilding sd = new ShortestDistBuilding();

        int [][] M = {
                { 1, 0 , 2 , 0, 1},
                { 0, 0 , 0 , 0, 0},
                { 0, 0 , 1 , 0, 0},
        };

        sd.findShortestDistBFS(M);



    }

    public void findShortestDistBFS(int [][] M) {
        if (M == null) {
            return;
        }

        int row = M.length;
        int col = M[0].length;

        boolean [][] visited;

        ArrayList<Integer> path = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();
        result.add(Integer.MAX_VALUE);

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                visited = new boolean[row][col];
                path = new ArrayList<>();
                if (M[i][j] == 0) {
                    //call bfs
                    visited[i][j] = true;
                    bfs(M, visited, i, j, path);

                    if (path.stream().mapToInt(x -> x).sum() < result.stream().mapToInt(y -> y).sum()) {
                        result.clear();
                        result.addAll(path);
                    }
                }

            }
        }

        System.out.println();
        System.out.println("final distances: " + result);
        System.out.println("final shortest distance: " + result.stream().mapToInt(y -> y).sum());
    }

    public void bfs(int [][] M, boolean[][] visited, int r, int c, ArrayList<Integer> path) {
        Queue<Cell> queue = new LinkedList<>();
        Cell cell = new Cell(r, c);
        queue.add(cell);


        int [][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

        while (!queue.isEmpty()) {
            cell = queue.remove();

            for (int i=0; i<directions.length; i++) {
                int r1 = cell.row + directions[i][0];
                int c1 = cell.col + directions[i][1];

                if (r1 < 0 || r1 >= M.length || c1 < 0 || c1 >= M[0].length || visited[r1][c1] ||  M[r1][c1] == 2) {
                    continue;
                }
                visited[r1][c1] = true;
                if (M[r1][c1] == 1) {
                    path.add(cell.dist+1);
                } else if (M[r1][c1] == 0) {
                    Cell adjCell = new Cell(r1, c1);
                    adjCell.dist = cell.dist + 1;
                    queue.add(adjCell);
                }
            }
        }

        System.out.println("path at cell: " + cell + ": " + path);
        System.out.println("total distance: " + path.stream().mapToInt(i -> i).sum());

    }
}

class Cell {
    int row;
    int col;
    int dist;

    Cell (int row, int col) {
        this.row = row;
        this.col = col;
        this.dist = 0;
    }

    @Override
    public String toString() {
        return "[" + this.row + ", " + this.col + "]";
    }
}