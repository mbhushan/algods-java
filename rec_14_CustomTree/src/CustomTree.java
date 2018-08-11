import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 Custom Tree Problem
 You are given a set of links, e.g.

 a ---> b
 b ---> c
 b ---> d
 a ---> e

 Print the tree that would form when each pair of these links that has the same
 character as start and end point is joined together. You have to maintain fidelity w.r.t.
 the height of nodes, i.e. nodes at height n from root should be printed at same row or column.
 For set of links given above, tree printed should be –

 -->a
 |-->b
 |   |-->c
 |   |-->d
 |-->e

 Note that these links need not form a single tree; they could form, ahem, a forest. Consider the following links

 a ---> b
 a ---> g
 b ---> c
 c ---> d
 d ---> e
 c ---> f
 z ---> y
 y ---> x
 x ---> w
 The output would be following forest.

 -->a
 |-->b
 |   |-->c
 |   |   |-->d
 |   |   |   |-->e
 |   |   |-->f
 |-->g

 -->z
 |-->y
 |   |-->x
 |   |   |-->w
 You can assume that given links can form a tree or forest of trees only, and there are no duplicates among links.
 */

public class CustomTree {

    public static void main(String[] args) {
        CustomTree ct = new CustomTree();

        String [] links1 = {"a b", "b c", "b d", "a e"};

        String [] links2 = {"a b", "a g", "b c", "c d", "d e", "c f",
                "z y", "y x", "x w"};

        ct.printCustomTree(links2);
    }

    public void printCustomTree(String [] S) {
        Map<String, List<String>> cmap = new HashMap<>();

        for (String str: S) {
            String [] A = str.split(" ");
            List<String> children = new ArrayList<String>();
            if (cmap.containsKey(A[0])) {
                children = cmap.get(A[0]);
            }
            children.add(A[1]);
            cmap.put(A[0], children);
        }

        System.out.println(cmap);
    }
}

class Node {
    char name;
    List<Character> children;
}
