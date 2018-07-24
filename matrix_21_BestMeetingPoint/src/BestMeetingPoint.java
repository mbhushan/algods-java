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
 */

public class BestMeetingPoint {

    public static void main(String[] args) {
        BestMeetingPoint bmp = new BestMeetingPoint();

        int [][] M = {
                {1, 0, 0, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0}
        };

        System.out.println("input matrix: ");
        bmp.printMatrix(M);
    }

    public void find

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
