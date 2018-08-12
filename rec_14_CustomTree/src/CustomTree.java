import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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


 -->a   |-->b
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

 ===================
 INPUT / OUTPUT
 ===================
 {a=[b, g], b=[c], c=[d, f], d=[e], x=[w], y=[x], z=[y]}
 roots: [a, z]
 -->a
    |-->b
        |-->c
            |-->d
                 |-->e
            |-->f

        |-->g
 -->z
        |-->y
            |-->x
                |-->w

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
        HashSet<String> roots = new HashSet<>();
        HashSet<String> leafs = new HashSet<>();

        for (String str: S) {
            String [] A = str.split(" ");
            List<String> children = new ArrayList<String>();

            roots.add(A[0]);
            leafs.add(A[1]);

            if (cmap.containsKey(A[0])) {
                children = cmap.get(A[0]);
            }
            children.add(A[1]);
            cmap.put(A[0], children);
        }

        roots.removeAll(leafs);

        System.out.println(cmap);
        System.out.println("roots: " + roots);

        StringBuffer sb = new StringBuffer();
        StringBuffer arrow = new StringBuffer();
        arrow.append(" -->");
        for (String root: roots) {
            System.out.print(arrow.toString() + root);
            printTree(cmap, root, sb);
            sb = new StringBuffer();
        }
    }

    private void printTree(Map<String, List<String>> cmap, String root, StringBuffer sb) {
        if (!cmap.containsKey(root)) {
            return;
        }

        List<String> children = cmap.get(root);
        sb.append("\t");
        System.out.println();

        for (String s: children) {
            System.out.print(sb.toString() + "|-->" + s);
            printTree(cmap, s, sb);
        }
        System.out.println();
        sb.deleteCharAt(sb.length()-1);
    }
}
