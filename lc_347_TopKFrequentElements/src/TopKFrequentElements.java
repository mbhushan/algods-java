import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements {

    public List<Integer> topKFrequent(int[] nums, int k) {

        List<Integer> result = new ArrayList<>();

        if (nums == null || nums.length < 1) {
            return result;
        }

        Map<Integer, Integer> map = new HashMap<>();

        for (int n: nums) {
            map.put(n, map.getOrDefault(n, 0)+1);
        }

        PriorityQueue<Node> pq = new PriorityQueue(map.size(), new NodeComparator());
        for (int key: map.keySet()) {
            pq.add(new Node(key, map.get(key)));
        }



        while (k > 0 && !pq.isEmpty()) {
            Node node = pq.remove();
            result.add(node.num);
            --k;
        }

        return result;

    }

}


class Node {
    int num;
    int freq;

    Node (int n, int f) {
        num = n;
        freq = f;
    }
}

class NodeComparator implements Comparator<Node> {

    public int compare(Node n1, Node n2) {
        if (n1.freq < n2.freq) {
            return 1;
        } else if (n1.freq > n2.freq) {
            return -1;
        } else {
            return 0;
        }
    }
}