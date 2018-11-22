import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class PageListings {

    PriorityQueue<PQNode> pqNodes;


    public static void main(String[] args) {
        String [] input = {
                "host_id,listing_id,score,city",
                "1,28,300.1,San Francisco",
                "4,5,209.1,San Francisco",
                "20,7,208.1,San Francisco",
                "23,8,207.1,San Francisco",
                "16,10,206.1,Oakland",
                "1,16,205.1,San Francisco",
                "1,31,204.6,San Francisco",
                "6,29,204.1,San Francisco",
                "7,20,203.1,San Francisco",
                "8,21,202.1,San Francisco",
                "2,18,201.1,San Francisco",
                "2,30,200.1,San Francisco",
                "15,27,109.1,Oakland",
                "10,13,108.1,Oakland",
                "11,26,107.1,Oakland",
                "12,9,106.1,Oakland",
                "13,1,105.1,Oakland",
                "22,17,104.1,Oakland",
                "1,2,103.1,Oakland",
                "28,24,102.1,Oakland",
                "18,14,11.1,San Jose",
                "6,25,10.1,Oakland",
                "19,15,9.1,San Jose",
                "3,19,8.1,San Jose",
                "3,11,7.1,Oakland",
                "27,12,6.1,Oakland",
                "1,3,5.1,Oakland",
                "25,4,4.1,San Jose",
                "5,6,3.1,San Jose",
                "29,22,2.1,San Jose",
                "30,23,1.1,San Jose"
        };

        String[] strs = new String[]{
                //"host_id,listing_id,score,city",
                "1,28,300.1,SanFrancisco",
                "4,5,209.1,SanFrancisco",
                "20,7,208.1,SanFrancisco",
                "23,8,207.1,SanFrancisco",
                "16,10,206.1,Oakland",
                "1,16,205.1,SanFrancisco",
                "6,29,204.1,SanFrancisco",
                "7,20,203.1,SanFrancisco",
                "8,21,202.1,SanFrancisco",
                "2,18,201.1,SanFrancisco",
                "2,30,200.1,SanFrancisco",
                "15,27,109.1,Oakland",
                "10,13,108.1,Oakland",
                "11,26,107.1,Oakland",
                "12,9,106.1,Oakland",
                "13,1,105.1,Oakland",
                "22,17,104.1,Oakland",
                "1,2,103.1,Oakland",
                "28,24,102.1,Oakland",
                "18,14,11.1,SanJose",
                "6,25,10.1,Oakland",
                "19,15,9.1,SanJose",
                "3,19,8.1,SanJose",
                "3,11,7.1,Oakland",
                "27,12,6.1,Oakland",
                "1,3,5.1,Oakland",
                "25,4,4.1,SanJose",
                "5,6,3.1,SanJose",
                "29,22,2.1,SanJose",
                "30,23,1.1,SanJose"
        };

        PageListings pl = new PageListings();
       // pl.process(strs);

        pl.displayPages(strs, 12);
    }


    public void displayPages(String [] input, int pageSize) {
        List<String> inList = new ArrayList<>();
        for (String st: input) {
            inList.add(st);
        }
        System.out.println("input size: " + input.length);
        System.out.println("list size: " + inList.size());

        //steps
        //input iterator for iterating thru the list.
        //set to keep track of host_ids
        //reached flag to keep track of buffer.
        //counter for pageSize

        Iterator<String> iter = inList.iterator();
        List<String> result = new ArrayList<>();
        Set<String> hostSet = new HashSet<>();
        int counter = 0;
        boolean isEnd = false;

        while (iter.hasNext()) {
            String curr = iter.next();
            String host_id = curr.split(",")[0];
            if (!hostSet.contains(host_id) || isEnd) {
                result.add(curr);
                hostSet.add(host_id);
                iter.remove();
                ++counter;
            }
            if (counter == pageSize) {
                if (!inList.isEmpty()) {
                    result.add(" ");
                    hostSet.clear();
                    counter = 0;
                    isEnd = false;
                    iter = inList.iterator();
                }
//                else {
//                    isEnd = true;
//                }

            }
            if (!iter.hasNext()) {
                isEnd = true;
                iter = inList.iterator();
            }
        }
        int count = 1;
        for (String r: result){
            if (r.length() < 3) {
                System.out.println();
                continue;
            }
            System.out.println(count++ + " " + r);
        }
        //System.out.println(result);
    }

    public void process(String [] input) {
        pqNodes = new PriorityQueue<>((a, b) -> Double.compare(b.score, a.score));
        Map<Integer, List<String>> map = new HashMap<>();
        boolean firstRow = true;
        for (String in: input) {
            if (firstRow) {
                firstRow = false;
                continue;
            }
            int hostId = Integer.valueOf(in.split(",")[0]);
            List<String> list = map.getOrDefault(hostId, new ArrayList<>());
            list.add(in);
            map.put(hostId, list);
        }

        for (Map.Entry<Integer, List<String>> entry: map.entrySet()) {
            PQNode node = new PQNode(entry.getValue());
            pqNodes.add(node);
        }

        int count = 0;
        List<PQNode> buff  = new ArrayList<>();
        PriorityQueue<ResultNode> resultPQ = new PriorityQueue<>((a, b) -> Double.compare(b.score, a.score));

        while (!pqNodes.isEmpty() || !buff.isEmpty()) {
            PQNode node = pqNodes.poll();
            resultPQ.add(new ResultNode(node.score, node.listing));
            ++count;
            if (node.iter.hasNext()) {
                node.updateNode();
                buff.add(node);
            }
            if ((pqNodes.isEmpty() && !buff.isEmpty()) || count == 12) {
                for (PQNode buffNode: buff) {
                    pqNodes.add(buffNode);
                }
                buff.clear();
                if (count == 12) {
                    while (!resultPQ.isEmpty()) {
                        System.out.println(resultPQ.remove().row);
                    }
                    System.out.println("=================================================");
                    count = 0;
                }
            }
        }

        while (!resultPQ.isEmpty()) {
            System.out.println(resultPQ.remove().row);
        }
        System.out.println("=================================================");


    }
}

class PQNode {
    double score;
    String listing;
    Iterator<String> iter;

    PQNode(List<String> listings) {
        this.iter = listings.iterator();
        if (this.iter.hasNext()) {
            this.listing = this.iter.next();
            this.score = Double.valueOf(listing.split(",")[2]);
        }
    }

    public void updateNode() {
        if (this.iter.hasNext()) {
            this.listing = this.iter.next();
            this.score = Double.valueOf(listing.split(",")[2]);
        }
    }
}

class ResultNode {
    double score;
    String row;

    public ResultNode(double score, String row) {
        this.score = score;
        this.row = row;
    }
}
