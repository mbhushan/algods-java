import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 Given a list of airline tickets represented by pairs of departure and arrival airports [from, to],
 reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK.
 Thus, the itinerary must begin with JFK.

 Note:

 If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical
 order when read as a single string. For example, the itinerary ["JFK", "LGA"]
 has a smaller lexical order than ["JFK", "LGB"].

 All airports are represented by three capital letters (IATA code).
 You may assume all tickets form at least one valid itinerary.

 Example 1:
 Input: tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]

 Example 2:
 Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]

 Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
 But it is larger in lexical order.

 =====================
 INPUT / OUTPUT
 =====================
 reconstructing tickets: {LHR=SFO, MUC=LHR, SFO=SJC, JFK=MUC}
 constructed itinerary: [JFK, MUC, LHR, SFO, SJC]

 reconstructing tickets: {ATL=SFO, SFO=ATL, JFK=SFO}
 constructed itinerary: [ATL, SFO, ATL, SFO]
 */

public class ReconstructItinerary {

    public static void main(String[] args) {
        ReconstructItinerary ri = new ReconstructItinerary();

        String [][] tickets = {{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}};

        String [][] tickets2 = {{"JFK","SFO"}, {"SFO","ATL"}, {"ATL","JFK"}, {"ATL","SFO"}};


        ri.reconstructTravelPlan(tickets);
        System.out.println();

        ri.reconstructTravelPlan(tickets2);
    }

    public void reconstructTravelPlan(String [][] tickets) {
        Map<String, String> hmap = new HashMap<>();

        for (int i=0; i<tickets.length; i++) {
            String from = tickets[i][0];
            String to = tickets[i][1];

            hmap.put(from, to);
        }

        System.out.print("reconstructing tickets: ");
        System.out.println(hmap);

        Set<String> keys = hmap.keySet();
        int targetLen = hmap.size() + 1;

        System.out.print("constructed itinerary: ");
        for (String k:  keys) {

            boolean flag = reconstructTravelPlan(hmap, k, targetLen, new ArrayList<>());
            if (flag) {
                break;
            }
        }
    }

    private boolean reconstructTravelPlan(Map<String, String> hmap, String src, int targetLen, List<String> result) {

        result.add(src);

        if (result.size() == targetLen) {
            System.out.println(result);
            return true;
        }

        if (hmap.containsKey(src)) {
            String dest = hmap.get(src);

            boolean flag = reconstructTravelPlan(hmap, dest, targetLen, result);
            if (flag) {
                return true;
            }
        }

        return false;
    }

}
