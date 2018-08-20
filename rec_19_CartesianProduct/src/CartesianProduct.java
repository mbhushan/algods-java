import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a list of list of Strings/Integers. Print cartesian product of lists.


 * input -> {"Hello", "World"} , {"Game"}, {"Go","Home"}
 * output ->
 * Hello Game Go
 * Hello Game Home
 * World Game Go
 * World Game Home

 Input : A = {1, 2}, B = {3, 4}
 Output : A × B = {{1, 3}, {1, 4}, {2, 3}, {2, 4}}

 Input  : A = {1, 2, 3} B = {4, 5, 6}
 Output : A × B = {{1, 4}, {1, 5}, {1, 6}, {2, 4},
 {2, 5}, {2, 6}, {3, 4}, {3, 5}, {3, 6}}

 =================
 INPUT / OUTPUT
 =================
 input: [[Hello, World], [Game], [Go, Home]]
 cartesian product output:
 Hello Game Go
 Hello Game Home
 World Game Go
 World Game Home

 */

public class CartesianProduct {

    public static void main(String[] args) {
        CartesianProduct cp = new CartesianProduct();

        List<List<String>> slist = new ArrayList<>();

        List<String> A = new ArrayList<String>(Arrays.asList("Hello", "World"));
        List<String> B = new ArrayList<String>(Arrays.asList("Game"));
        List<String> C = new ArrayList<String>(Arrays.asList("Go", "Home"));

        slist.add(A);
        slist.add(B);
        slist.add(C);

        cp.cartesianProduct(slist);

    }

    public void cartesianProduct(List<List<String>> inputs) {
        System.out.println("input: " + inputs);
        System.out.println("cartesian product output: ");
        cartesianProduct(inputs, 0, new ArrayList<>());
        System.out.println();

    }

    public void cartesianProduct(List<List<String>> inputs, int index, List<Integer> result) {

        if (index == inputs.size()) {
            int j = 0;
           for (List<String> S: inputs) {
               int r = result.get(j++);
               System.out.print(S.get(r) + " ");
           }
            System.out.println();
           return;
        }

        if (index > inputs.size()) {
            return;
        }

        for (int i=0; i<inputs.get(index).size(); i++) {
            result.add(i);
            cartesianProduct(inputs, index+1, result);
            result.remove(result.size()-1);
        }
    }
}
