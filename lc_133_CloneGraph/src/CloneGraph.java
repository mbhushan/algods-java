import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 133. Clone Graph
 https://leetcode.com/problems/clone-graph/description/

 Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


 OJ's undirected graph serialization:
 Nodes are labeled uniquely.

 We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
 As an example, consider the serialized graph {0,1,2#1,2#2,2}.

 The graph has a total of three nodes, and therefore contains three parts as separated by #.

 First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 Second node is labeled as 1. Connect node 1 to node 2.
 Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 Visually, the graph looks like the following:

      1
    /  \
   /   \
 0 --- 2
      / \
      \_/

 */
public class CloneGraph {

    private UndirectedGraphNode startNode;
    private UndirectedGraphNode clonedStartNode;
    private int numVertices;

    public static void main(String[] args) {
        CloneGraph cg = new CloneGraph();

        String [] inputs = {
                "0,1,2#1,2#2,2"
        };

        cg.buildGraph(inputs[0], 3);

        cg.printGraph();

        cg.cloneGraph();


    }

    public void cloneGraph() {

        System.out.println("cloning graph: ");
        cloneGraph(startNode);
        System.out.println("printing cloned graph: ");
        printGraph(clonedStartNode);

    }

    public void printGraph() {
        printGraph(startNode);
        System.out.println();


    }

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {

        if (node == null) {
            return node;
        }

        clonedStartNode = new UndirectedGraphNode(node.label);

        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        HashMap<UndirectedGraphNode, UndirectedGraphNode> hmap = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        hmap.put(node, clonedStartNode);

//        for (UndirectedGraphNode neighbor: node.neighbors) {
//            UndirectedGraphNode clonedNode = null;
//            if (hmap.containsKey(neighbor)) {
//                clonedNode = hmap.get(neighbor);
//            } else {
//                clonedNode = new UndirectedGraphNode(neighbor.label);
//                hmap.put(neighbor, clonedNode);
//            }
//
//            clonedStartNode.neighbors.add(clonedNode);
//        }
        visited.add(clonedStartNode.label);
        queue.add(node);

        while (!queue.isEmpty()) {

            UndirectedGraphNode newNode = queue.remove();

            UndirectedGraphNode cloneNode = hmap.getOrDefault(newNode, new UndirectedGraphNode(newNode.label));

            for (UndirectedGraphNode neighbor: newNode.neighbors) {
                UndirectedGraphNode cNode = null;
                if (hmap.containsKey(neighbor)) {
                    cNode = hmap.get(neighbor);
                } else {
                    cNode = new UndirectedGraphNode(neighbor.label);
                    hmap.put(neighbor, cNode);
                }

                cloneNode.neighbors.add(cNode);

                if (!visited.contains(neighbor.label)) {
                    queue.add(neighbor);
                    visited.add(neighbor.label);
                }
            }

        }

        return clonedStartNode;
    }

    public void buildGraph(String input, int nVertices) {
        String [] nodes = input.split("#");

        System.out.println("nodes: " + Arrays.toString(nodes));

        UndirectedGraphNode [] gnodes = new UndirectedGraphNode[nVertices];

        for (int i=0; i<nVertices; i++) {
            gnodes[i] = new UndirectedGraphNode(i);
        }

        startNode = gnodes[0];
        numVertices = nVertices;

        for (int i=0; i<nodes.length; i++) {
            int [] vertices = Arrays.stream(nodes[i].split(",")).mapToInt(Integer::parseInt).toArray();

            for (int j=1; j<vertices.length; j++) {
                gnodes[vertices[0]].neighbors.add(gnodes[vertices[j]]);
            }
        }
    }

    private void printGraph(UndirectedGraphNode sNode) {
        if (startNode == null) {
            return;
        }

        boolean [] visited = new boolean[numVertices];
        Queue<UndirectedGraphNode> queue = new LinkedList<>();

        queue.add(sNode);
        visited[sNode.label] = true;

        while (!queue.isEmpty()) {
            UndirectedGraphNode node = queue.remove();
            System.out.println(node);

            for (UndirectedGraphNode neighbors: node.neighbors) {
                if (!visited[neighbors.label]) {
                    queue.add(neighbors);
                    visited[neighbors.label] = true;
                }
            }
        }

    }
}

  class UndirectedGraphNode {
      int label;
      List<UndirectedGraphNode> neighbors;
      UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }

      @Override
      public String toString() {
          StringBuffer sb = new StringBuffer();
          sb.append("[" + this.label + " -> ");
          if (this.neighbors.size() > 0) {
              List<Integer> nList = new ArrayList<>();
              for (UndirectedGraphNode n: neighbors) {
                  nList.add(n.label);
              }
              sb.append(nList);
          } else {
              sb.append("null");
          }
          sb.append("]");

          return sb.toString();
      }
  };
