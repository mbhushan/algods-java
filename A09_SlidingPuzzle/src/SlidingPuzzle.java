import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class SlidingPuzzle {

    public static void main(String[] args) {
        SlidingPuzzle sp = new SlidingPuzzle();

        int [][] M = {{4,1,2},{5,0,3}};

        System.out.println("moves: " + sp.moves(M));
    }

    public int moves(int [][] M) {
        String target = "123450";
        int size = M.length * M[0].length;

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        int [] directions = {-1, 1, -3, 3};

        String marker = "-1";

        StringBuffer sb = new StringBuffer();
        for (int i=0; i<M.length; i++) {
            for (int j=0; j<M[0].length; j++) {
                sb.append(M[i][j]);
            }
        }
        queue.add(sb.toString());
        queue.add(marker);
        int step = 0;

        while (!queue.isEmpty()) {
            String curr = queue.remove();

            if (curr.equals(target)) {
                return step;
            }
            if (curr.equals(marker)) {
                ++step;
                if (!queue.isEmpty()) {
                    queue.add(marker);
                }
                continue;
            }

            int zeroIdx = curr.indexOf('0');
            for (int i=0; i<directions.length; i++) {
                char [] arr = curr.toCharArray();
                System.out.println("curr: " + curr + " |  zero idx: " + zeroIdx);
                int swapIdx = zeroIdx + directions[i];
                if (swapIdx >= 0 && swapIdx < size) {
                    char tmp = arr[zeroIdx];
                    arr[zeroIdx] = arr[swapIdx];
                    arr[swapIdx] = tmp;
                }
                String next = String.valueOf(arr);
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.add(next);
                }

            }
        }

        return -1;
    }
}
