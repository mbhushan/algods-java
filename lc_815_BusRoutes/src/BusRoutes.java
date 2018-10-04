import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 815. Bus Routes
 We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever.
 For example if routes[0] = [1, 5, 7], this means that the first bus (0-th indexed)
 travels in the sequence 1->5->7->1->5->7->1->... forever.

 We start at bus stop S (initially not on a bus), and we want to go to bus stop T. Travelling by buses only,
 what is the least number of buses we must take to reach our destination? Return -1 if it is not possible.

 Example:
 Input:
 routes = [[1, 2, 7], [3, 6, 7]]
 S = 1
 T = 6
 Output: 2
 Explanation:
 The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.

 */
public class BusRoutes {

    public static void main(String[] args) {
        BusRoutes br = new BusRoutes();

        int [][] routes1 = {
                {1,2,7},
                {3,6,7}
        };
        int [][] routes = {
            {1,7},
            {3,5}
        };

        System.out.println(br.numBusesToDestination(routes, 5, 5));

    }

    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (S == T) return 0;
        Set<Integer>[] bus = new Set[routes.length];
        for (int i = 0; i < routes.length; i++) {
            bus[i] = new HashSet<>();
            for (int s : routes[i]) bus[i].add(s);
        }
        Queue<Set<Integer>> q = new LinkedList<>();
        boolean[] visited = new boolean[bus.length];
        /* first stop */
        for (int i = 0; i < bus.length; i++) {
            if (bus[i].contains(S)) {
                q.add(bus[i]);
                visited[i] = true;
            }
        }
        int res = 1;
        while(!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Set<Integer> cur = q.poll();
                for (int s : cur) {
                    if (s == T) return res;
                    for (int j = 0; j < bus.length; j++) {
                        /* visit this bus */
                        if (!visited[j] && bus[j].contains(s)) {
                            q.add(bus[j]);
                            visited[j] = true;
                        }
                    }
                }
            }
            res++;
        }
        return -1;
    }

    public int numBusesToDestinationMani(int[][] routes, int src, int dst) {
        List<Set<Integer>> list = new ArrayList<>();
        Set<Integer> srcSet = new HashSet<>();
        if (src == dst) {
            return 0;
        }

        for (int i=0; i<routes.length; i++) {
            int [] arr = routes[i];
            Set<Integer> set = Arrays.stream(arr).boxed().collect(Collectors.toSet());

            list.add(set);
            if (set.contains(src)) {
                srcSet = set;
                if (set.contains(dst)) {
                    return 1;
                }
            }
        }


        Set<String> visited = new HashSet<>();
        String str = srcSet.stream().map(String::valueOf).collect(Collectors.joining());
        Queue<Set<Integer>> queue = new LinkedList<>();
        queue.add(srcSet);
        visited.add(str);
        queue.add(null);
        int level = 1;

        while (!queue.isEmpty()) {
            Set<Integer> set = queue.remove();
            if (set!= null && set.contains(dst)) {
                return level;
            }
            if (set == null) {
                ++level;
                if (!queue.isEmpty()) {
                    queue.add(null);
                }
            } else {
                for (Integer i: set) {
                    for (Set<Integer> s: list) {
                        str = s.stream().map(String::valueOf).collect(Collectors.joining());
                        if (s.contains(i) && !visited.contains(str)) {
                            queue.add(s);
                            visited.add(str);
                        }
                    }
                }
            }
        }

        return -1;
    }
}
