import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 LeetCode – Best Meeting Point (Java)

 A group of two or more people wants to meet and minimize the total travel distance.
 You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group.
 The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

 For example, given three people living at (0,0), (0,4), and (2,2):


 1 - 0 - 0 - 0 - 1
 |   |   |   |   |
 0 - 0 - 0 - 0 - 0
 |   |   |   |   |
 0 - 0 - 1 - 0 - 0

 The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.
 =================
 INPUT / OUTPUT
 =================
 input matrix:
 1 0 0 0 1
 0 0 0 0 0
 0 0 1 0 0

 best meeting point: [0, 2]
 min total distance: 6
 input matrix:
 1 0 1 0 1
 0 1 0 0 0
 0 1 1 0 0

 best meeting point: [1, 2]
 min total distance: 11
 */

public class BestMeetingPoint {

    public static void main(String[] args) {
        BestMeetingPoint bmp = new BestMeetingPoint();

        int [][] M = {
                {1, 0, 0, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0}
        };

        int [][] N =  {
                {1, 0, 1, 0, 1},
                {0, 1, 0, 0, 0},
                {0, 1, 1, 0, 0}
        };

        System.out.println("input matrix: ");
        bmp.printMatrix(M);
        System.out.println();
        bmp.findBestMeetingPoint(M);

        System.out.println("input matrix: ");
        bmp.printMatrix(N);
        System.out.println();
        bmp.findBestMeetingPoint(N);
    }

    public void findBestMeetingPoint(int [][] M) {
        if (M == null) {
            return;
        }
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        int row = M.length;
        int col = M[0].length;

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                if (M[i][j] == 1) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        int totalDistance = 0;

        Collections.sort(cols);

        int midRow = rows.get(rows.size() / 2);
        int midCol = cols.get(cols.size() / 2);

        for (int r: rows) {
            totalDistance += Math.abs(r - midRow);
        }

        for (int c : cols) {
            totalDistance += Math.abs(c - midCol);
        }

        System.out.println("best meeting point: [" + midRow + ", " + midCol + "]");
        System.out.println("min total distance: " + totalDistance);


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
