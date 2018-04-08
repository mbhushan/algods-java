import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by manib on 2/18/18.
 */
public class TestGround {

    public static void main(String [] args) {
        Queue<String> queue = new LinkedList<String>();

        queue.add("name: ");
        List<String> listEx = new LinkedList<String>();
        listEx.add("mani");
        listEx.add("bhushan");
        listEx.add("sinha");

        queue.addAll(listEx);

        System.out.println(queue.toString());

        TestGround tg = new TestGround();
        tg.testListRm();
    }

    public void testListRm() {
        List<Node> mylist = new ArrayList<>();
        mylist.add(new Node("k1", "u1"));
        mylist.add(new Node("k2", "u2"));
        mylist.add(new Node("k3", "u3"));
        mylist.add(new Node("k4", "u4"));

        Node node = new Node("k2", "u2");

//        if (mylist.contains(node)) {
//            mylist.remove(node);
//        }

        for (Node n: mylist) {
           if (n.uuid.equals(node.uuid)) {
               mylist.remove(n);
               break;
           }
        }

        System.out.println(mylist.toString());

    }
}


class Node {
    String keyspace;
    String uuid;

    Node (String keyspace, String uuid) {
        this.keyspace = keyspace;
        this.uuid = uuid;
    }

    public String toString() {
        return "[" + this.keyspace + ", " + this.uuid + "]";
    }
}
