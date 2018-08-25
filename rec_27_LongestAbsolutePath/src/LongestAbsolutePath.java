import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 Suppose we abstract our file system by a string in the following manner:

 The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:

 dir
    subdir1
    subdir2
        file.ext
 The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.

 The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:

 dir
    subdir1
        file1.ext
       subsubdir1
    subdir2
        subsubdir2
            file2.ext

 The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a
 file file1.ext and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level
 sub-directory subsubdir2 containing a file file2.ext.

 We are interested in finding the longest (number of characters) absolute path to a file within our
 file system. For example, in the second example above, the longest absolute path
 is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).

 Given a string representing the file system in the above format, return the length of the longest
 absolute path to file in the abstracted file system. If there is no file in the system, return 0.

 Note:
 The name of a file contains at least a . and an extension.
 The name of a directory or sub-directory will not contain a ..
 Time complexity required: O(n) where n is the size of the input string.

 Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.

 =========================
 INPUT / OUTPUT
 =========================
 input string: "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"
 tokens with tabs: [dir, 	subdir1, 	subdir2, 		file.ext]
 list of path nodes: [[dir, 0], [subdir1, 1], [subdir2, 1], [file.ext, 2]]
 longest path size: 3
 longest path: dir/subdir2/file.ext

 input string: "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"
 tokens with tabs: [dir, 	subdir1, 		file1.ext, 		subsubdir1, 	subdir2, 		subsubdir2, 			file2.ext]
 list of path nodes: [[dir, 0], [subdir1, 1], [file1.ext, 2], [subsubdir1, 2], [subdir2, 1], [subsubdir2, 2], [file2.ext, 3]]
 longest path size: 4
 longest path: dir/subdir2/subsubdir2/file2.ext
 */

public class LongestAbsolutePath {

    public static void main(String[] args) {
        LongestAbsolutePath lap = new LongestAbsolutePath();

        String [] inputs = {
                "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext",
                "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"
        };

        for (String in: inputs) {
            lap.longestPath(in);
        }

    }

    public void longestPath(String input) {

        System.out.println("input string: ");
        System.out.println(input);
        String [] tokens = input.split("\n");

        List<Node> list = new ArrayList<>();
        for (String tok: tokens) {
            Node node = new Node(tok);
            list.add(node);
        }

        System.out.println("tokens with tabs: " + Arrays.toString(tokens));
        System.out.println("list of path nodes: " + list);

        longestPath(list);
        System.out.println();
    }

    private String longestPath(List<Node> list){
        StringBuilder sb = new StringBuilder();

        List<String> path = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        int maxPathSize = 0;
        String longestPath = null;

        for (Node node: list) {
            if (stack.isEmpty()) {
                path.add(node.data);
                stack.push(node);
            } else {
                if (node.tabs <= stack.peek().tabs) {
                    if (path.size() > maxPathSize) {
                        maxPathSize = path.size();
                        longestPath = path.toString();
                    }
                    while (!stack.isEmpty() && stack.peek().tabs >= node.tabs) {
                        stack.pop();
                        path.remove(path.size()-1);
                    }
                }
                path.add(node.data);
                stack.push(node);
            }
        }
        if (path.size() > maxPathSize) {
            maxPathSize = path.size();
            longestPath = path.toString();
        }

        System.out.println("longest path size: " + maxPathSize);
        longestPath = String.join("/", path);
        System.out.println("longest path: " + longestPath);

        return longestPath;
    }
}

class Node {
    String data;
    int tabs;

    public Node(String token) {
        this.tabs = 0;
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<token.length(); i++) {
            if (token.charAt(i) == '\t') {
                ++this.tabs;
            } else {
                sb.append(token.charAt(i));
            }
        }
        this.data = sb.toString();
    }

    @Override
    public String toString() {
        return "[" + data + ", " + tabs + "]";
    }
}
