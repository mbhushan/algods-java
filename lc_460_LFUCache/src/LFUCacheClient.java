import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 460. LFU Cache
 https://leetcode.com/problems/lfu-cache/description/

 Design and implement a data structure for Least Frequently Used (LFU) cache.
 It should support the following operations: get and put.

 get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity,
 it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem,
 when there is a tie (i.e., two or more keys that have the same frequency),
 the least recently used key would be evicted.

 Follow up:
 Could you do both operations in O(1) time complexity?

 Example:

 LFUCache cache = new LFUCache( 2 /* capacity  );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.get(3);       // returns 3.
        cache.put(4, 4);    // evicts key 1.
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4

 */

public class LFUCacheClient {

    public static void main(String[] args) {

        LFUCacheClient lfu = new LFUCacheClient();

        LFUCache cache = new LFUCache( 2 /* capacity */ );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.get(3);       // returns 3.
        cache.put(4, 4);    // evicts key 1.
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4



    }


}

class LFUCache {

    Map<Integer, Node> cmap = new HashMap<>();
    PriorityQueue<Node> pq = new PriorityQueue<>();
    int capacity;

    public LFUCache(int capacity) {
        cmap = new HashMap<>();
        pq = new PriorityQueue<>(capacity, new NodeComparator());
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!cmap.containsKey(key)) {
            return -1;
        }

        Node node = cmap.get(key);
        int res = node.value;

        pq.remove(node);
        ++node.freq;
        node.timestamp = System.currentTimeMillis();
        pq.add(node);

        System.out.println("get: " + res);
        return res;
    }

    public void put(int key, int value) {
        Node node = new Node(key, value);
        if (cmap.size() == capacity) {
            Node toRem = pq.remove();
            cmap.remove(toRem.key);
        }
        cmap.put(key, node);
        pq.add(node);

        System.out.println("put: " + node);

    }
}

class Node {
    int key;
    int value;
    int freq;
    long timestamp;

    Node (int key, int value) {
        this.key = key;
        this.value = value;
        this.timestamp = System.currentTimeMillis();
        this.freq = 1;

    }

    @Override
    public String toString() {
        return "[k: " + key + ", v:" + value + ", f: " + freq + ", t: " + timestamp + "]";
    }
}

class NodeComparator implements Comparator<Node> {

    public int compare(Node n1, Node n2) {
        if (n1.freq < n2.freq) {
            return -1;
        } else if (n1.freq > n2.freq) {
            return 1;
        } else {
            if (n1.timestamp < n2.timestamp) {
                return -1;
            } else {
               return 1;
            }
        }
    }
}