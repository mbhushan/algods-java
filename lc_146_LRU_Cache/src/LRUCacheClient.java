import java.util.HashMap;

/**
 146. LRU Cache
 https://leetcode.com/problems/lru-cache/description/

 Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

 get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

 Follow up:
 Could you do both operations in O(1) time complexity?

 Example:

 LRUCache cache = new LRUCache(2); // capacity

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4
*/

public class LRUCacheClient {

    public static void main(String[] args) {
        LRUCacheClient lru = new LRUCacheClient();

        lru.demoClient();

    }

    public void demoClient() {

        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4


    }

}

class LRUCache {

    private Node head;
    private Node tail;
    private HashMap<Integer, Node> hmap;
    private int capacity;

    public LRUCache(int capacity) {
        this.head = null;
        this.hmap = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        int value = -1;

        if (!hmap.containsKey(key)) {
            System.out.println("val: " + value);
            return value;

        } else {
            Node node = hmap.get(key);
            value = node.data;

            //move node to front of linked list;
            if (hmap.size() > 1) {
                //splice node
               //check if its head - if yes do nothing.
               if (node.prev == null) {
                   //do nothing.
               } else if (node.next == null) { //check if its tail - easy update
                   node.prev.next = null;
                   node.prev = null;

                   node.next = head;
                   head.prev = node;
                   head = node;

                } else {   // its middle node - update accordingly.
                   node.prev.next = node.next;
                   node.next.prev = node.prev;
                   //update head
                   node.next = head;
                   head.prev = node;
                   head = node;
               }

            }
        }

        System.out.println("val: " + value);
        return value;

    }

    public void put(int key, int value) {
        Node node = new Node(key, value);

        //check if put is within size
        if (hmap.size() < capacity) {
            //if first node
            if (hmap.isEmpty()) {
                head = node;
                tail = node;
                //hmap.put(key, node);
            } else {
                tail.next = node;
                node.prev = tail;
                tail = tail.next;
            }
            hmap.put(key, node);
            System.out.println("map: " + hmap);
            printList();
        } else if (capacity == 1) {
            hmap.remove(head.key);
            System.out.println("evict: " + head.key);
            System.out.println("map: " + hmap);

            head = node;
            tail = node;
            hmap.put(key, node);
            System.out.println("map: " + hmap);
            printList();
        } else {
            int keyToRemove = tail.key;
            node.next = head;
            head.prev = node;

            head = node;

            tail = tail.prev;
            tail.next.prev = null;
            tail.next = null;

            hmap.put(key, node);
            hmap.remove(keyToRemove);
            System.out.println("evict: " + keyToRemove);
            System.out.println("map: " + hmap);
            printList();
        }

//        System.out.println("k-v" + node);
//        System.out.println("map: " + hmap);
//        printList();
//        System.out.println();
    }

    private void printList() {
        Node node = head;

        while (node != null) {
            System.out.print(node.data + " -> ");
            node = node.next;
        }
        System.out.println("null");
    }
}

class Node {
    int data;
    int key;
    Node next;
    Node prev;

    Node (int key, int value) {
        this.data = value;
        this.key = key;
        this.next = null;
        this.prev = null;
    }

    @Override
    public String toString() {
        return "[" + this.key + ", " + this.data + "]";
    }
}
