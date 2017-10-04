/**
 * Created by manib on 10/4/17.
 */

public class TwoSumClient {

    public static void main(String [] args) {
        TwoSum ts = new TwoSum();

        ts.add(1);
        ts.add(3);
        ts.add(5);
        ts.add(16);
        ts.add(8);
        ts.add(8);


        System.out.println(ts.find(4));
        System.out.println(ts.find(7));
        System.out.println(ts.find(16));
    }
}
