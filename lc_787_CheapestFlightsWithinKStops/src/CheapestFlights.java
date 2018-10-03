import javafx.util.Pair;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 787. Cheapest Flights Within K Stops
 https://leetcode.com/problems/cheapest-flights-within-k-stops/description/

 There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.

 Now given all the cities and fights, together with starting city src and the destination dst,
 your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

 Example 1:
 Input:
 n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 src = 0, dst = 2, k = 1
 Output: 200
 Explanation:
 The graph looks like this:


 The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
 Example 2:
 Input:
 n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 src = 0, dst = 2, k = 0
 Output: 500
 Explanation:
 The graph looks like this:


 The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
 Note:

 The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
 The size of flights will be in range [0, n * (n - 1) / 2].
 The format of each flight will be (src, dst, price).
 The price of each flight will be in the range [1, 10000].
 k is in the range of [0, n - 1].
 There will not be any duplicated flights or self cycles.

 */
public class CheapestFlights {

    public static void main(String[] args) {
        CheapestFlights cf = new CheapestFlights();

        int[][] flights = {{0,1,100},{2,1,100},{0,2,500}};

        int ans = cf.findCheapestPrice(3, flights, 0, 1, 1);
        System.out.println("ans: "  + ans);


    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<Pair<Integer, Integer>>> map = new HashMap<>();
        for (int i=0; i<n; i++) {
            map.put(i, new LinkedList<>());
        }
        for (int [] flight: flights) {
            map.get(flight[0]).add(new Pair(flight[1], flight[2]));
        }
        PriorityQueue<int []> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.add(new int[]{0, src, 0});

        while (!pq.isEmpty()) {
            int [] E = pq.remove();
            int cost = E[0];
            int V = E[1];
            int stops = E[2];

            if (V == dst) {
                return cost;
            } else if (stops <= K) {
                for (Pair<Integer, Integer> pair: map.get(V)) {
                    pq.add(new int[] {cost + pair.getValue(), pair.getKey(), stops+1});
                }
            }
        }

        return -1;

    }
}
