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
    }
}
