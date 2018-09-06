import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    }

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {


        return null;
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

    private void printGraph() {
        if (startNode == null) {
            return;
        }

        boolean [] visited = new boolean[numVertices];
        Queue<UndirectedGraphNode> queue = new LinkedList<>();

        queue.add(startNode);
        visited[startNode.label] = true;

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
