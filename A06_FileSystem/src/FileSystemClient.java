import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 Write a file system class, which has two functions: create, and get

 create("/a",1)
 get("/a") //get 1
 create("/a/b",2)
 get("/a/b") //get 2
 create("/c/d",1) //Error, because "/c" is not existed
 get("/c") //Error, because "/c" is not existed

 */

public class FileSystemClient {

    public static void main(String[] args) {
        String in = "/a/b/c";
        System.out.println(Arrays.toString(in.split("/")));

        FileSystemClient fsc = new FileSystemClient();
        fsc.demo();
    }

    public void demo() {
        FileSystem fs = new FileSystem();
        boolean ans  = fs.create("/a", 1);
        System.out.println(ans);
        ans = fs.create("/a/b", 2);
        System.out.println(ans);
        ans = fs.create("/c/d", 1);
        System.out.println(ans);


        //read examples
        System.out.println("get /a => " + fs.get("/a"));
        System.out.println("get /a/b => " + fs.get("/a/b"));
        System.out.println("get /c => " + fs.get("/c"));
    }

}

class FileSystem {

    private FileNode root;

    public FileSystem() {
        root = new FileNode("/", 0);
    }

    public boolean create(String input, int value) {
        String [] files = input.split("/");
        return createHelper(files, value);
    }

    private boolean createHelper(String [] files, int value) {
        FileNode node = this.root;
        int len = files.length;
        String name = "";
        for (int i=1; i<files.length-1; i++) {
            name = files[i];
            if (!node.children.containsKey(name)) {
                return false;
            }
            node = node.children.get(name);
        }
        name = files[len-1];
        FileNode fnode = new FileNode(name, value);
        node.children.put(name, fnode);

        return true;
    }

    public Integer get(String input) {
        String [] files = input.split("/");

        FileNode node = this.root;
        String name = "";
        for (int i=1; i<files.length; i++) {
            name = files[i];
            if (!node.children.containsKey(name)) {
                return null;
            }
            node = node.children.get(name);
        }

        return node.value;
    }

}

class FileNode {
    String name;
    int value;
    Map<String, FileNode> children;

    FileNode (String name, int value) {
        this.name = name;
        this.value = value;
        this.children = new HashMap<>();
    }
}
