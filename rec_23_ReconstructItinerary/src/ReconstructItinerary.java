import java.util.HashMap;
import java.util.Map;

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

 */

public class ReconstructItinerary {

    public static void main(String[] args) {
        ReconstructItinerary ri = new ReconstructItinerary();

        String [][] tickets = {{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}};

        ri.reconstructTravelPlan(tickets);
    }

    public void reconstructTravelPlan(String [][] tickets) {
        Map<String, String> hmap = new HashMap<>();

        for (int i=0; i<tickets.length; i++) {
            String from = tickets[i][0];
            String to = tickets[i][1];

            hmap.put(from, to);
        }

        System.out.println(hmap);
    }

}
