import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.DoubleStream;

/**
 Total price = base price + service fee + cleaning fee + ...

 input : array of decimals ~ X
 output : array of int ~ Y

 But they need to satisfy the condition:

 sum(Y) = round(sum(x))
 minmize (|y1-x1| + |y2-x2| + ... + |yn-xn|)
 Example1:
 input = 30.3, 2.4, 3.5
 output = 30 2 4

 Example2:
 input = 30.9, 2.4, 3.9
 output = 31 2 4
 */
public class RoundNumbers {

    public static void main(String[] args) {
        double [] A = new double[]{2.9, 2.3, 1.4, 3, 6};

        RoundNumbers rn = new RoundNumbers();

        A = new double[]{1.2, 2.5, 3.6, 4.0};
        A = new double[]{2.5, 2.3, 3.1, 6.5};
        A = new double[]{2.9, 2.3, 1.4, 3, 6};
        rn.roundNumbers(A);


    }

    public int [] roundNumbers(double [] A) {

        int [] res = new int[A.length];

        double totalSum = DoubleStream.of(A).sum();

        List<Node> nodeList = new ArrayList<>();

        int floorSum = 0;
        int idx = 0;
        for (double d: A) {
            double diff = d - Math.floor(d);
            Node node = new Node(diff, idx++);
            nodeList.add(node);

            floorSum += Math.floor(d);
        }

        int targetNum = (int)Math.round(totalSum) - floorSum;
        Collections.sort(nodeList, (a, b) -> Double.compare(b.diff, a.diff));

        idx = 0;
        for (int i=0; i<targetNum; i++) {
            int num = (int)Math.ceil(A[nodeList.get(i).idx]);
            res[idx++] = num;
        }
        for (int i=targetNum; i<A.length; i++) {
            int num = (int)Math.floor(A[nodeList.get(i).idx]);
            res[idx++] = num;
        }

        System.out.println(Arrays.toString(res));
        return res;

    }
}

class Node {
    double diff;
    int idx;

    Node (double diff, int idx) {
        this.diff = diff;
        this.idx = idx;
    }
}
