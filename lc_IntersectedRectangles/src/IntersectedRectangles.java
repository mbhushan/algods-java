import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 30 Number of Intersected Rectangles
 Given lots of rectangles, output how many of them intersect.

 */
public class IntersectedRectangles {

    public static void main(String[] args) {
        IntersectedRectangles sol = new IntersectedRectangles();
        int[][][] rectangles = {
                {{-3, -2}, {2, 1}},
                {{10, 8}, {15, 10}},
                {{1, 0}, {7, 4}},
                {{12, 9}, {16, 12}},
                {{-2, -1}, {5, 3}}
        };
        int ans = sol.countIntersection(rectangles);
        System.out.println("ans: " + ans);
    }

    private int find(int val, int[] parents) {
        while (parents[val] != val) {
            val = parents[val];
        }
        return val;
    }

//    // Returns true if two rectangles (l1, r1) and (l2, r2) overlap
//    public boolean intersect(l1, Point r1, Point l2, Point r2)
//    {
//        // If one rectangle is on left side of other
//        if (l1.x > r2.x || l2.x > r1.x)
//            return false;
//
//        // If one rectangle is above other
//        if (l1.y < r2.y || l2.y < r1.y)
//            return false;
//
//        return true;
//    }

    private boolean intersect(int[][] r1, int[][] r2) {
        return r1[0][0] < r2[0][0] && r1[0][1] < r2[0][1] && r2[0][0] < r1[1][0] && r2[0][1] < r1[1][1] ||
                r1[0][0] < r2[1][0] && r1[0][1] < r2[1][1] && r2[1][0] < r1[1][0] && r2[1][1] < r1[1][1];
    }

    public int countIntersection(int[][][] rectangles) {
        if (rectangles == null || rectangles.length == 0) return 0;
        int n = rectangles.length;
        int[] parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        System.out.println("parents: " + Arrays.toString(parents));

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (intersect(rectangles[i], rectangles[j])) {
                    System.out.println("intersecting: " + i + ", " + j);
                    int root1 = find(i, parents);
                    int root2 = find(j, parents);

                    if (root1 != root2) {
                        parents[root1] = root2;
                    }
                }
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(find(i, parents));
        }

        System.out.println("parents: " + Arrays.toString(parents));
        System.out.println("set: " + set);
        return set.size();
    }

}
