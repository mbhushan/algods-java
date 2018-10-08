import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 The numbers on a telephone keypad are arranged thus:


 1 2 3
 4 5 6
 7 8 9
 0
 Starting from any digit, and choosing successive digits as a knight moves in chess,
 determine how many different paths can be formed of length n. There is no need to make a list of the paths,
 only to count them.

 A knight moves two steps either horizontally or vertically followed by one step in the perpendicular direction;
 thus, from the digit 1 on the keypad a knight can move to digits 6 or 8, and from the digit 4 on the keypad a
 knight can move to digits 3, 9 or 0. A path may visit the same digit more than once.

 Your task is to write a function that determines the number of paths of length n that a knight can trace on a
 keyboard starting from any digit .

 public int findNumberOfPaths(int digit, int step){
 }
 */

public class KnightPhoneNum {

    Map<Integer, List<Integer>> map;

    KnightPhoneNum() {
        map = new HashMap<>();
    }

    public static void main(String[] args) {



        KnightPhoneNum sol = new KnightPhoneNum();
        sol.demo();


    }

    public void demo() {
        map.put(0, new ArrayList<>(Arrays.asList(4, 6)));
        map.put(1, new ArrayList<>(Arrays.asList(6, 8)));
        map.put(2, new ArrayList<>(Arrays.asList(7, 9)));
        map.put(3, new ArrayList<>(Arrays.asList(4, 8)));
        map.put(4, new ArrayList<>(Arrays.asList(3, 9, 0)));
        map.put(5, new ArrayList<>(Arrays.asList()));
        map.put(6, new ArrayList<>(Arrays.asList(1, 7, 0)));
        map.put(7, new ArrayList<>(Arrays.asList(2, 6)));
        map.put(8, new ArrayList<>(Arrays.asList(1, 3)));
        map.put(9, new ArrayList<>(Arrays.asList(2, 4)));
        System.out.println(map);

        int ans = generatePhoneNums(map, 1, 1);
        System.out.println("ans: " + ans);
    }

    public int generatePhoneNums(Map<Integer, List<Integer>> map, int start, int size) {

        List<Integer> buff = new ArrayList<>();
        Set<String> set = new HashSet<>();

        buff.add(start); //1
        generatePhoneNumsHelper(map, start, size, buff, set);


        return set.size();

    }

    private void generatePhoneNumsHelper(Map<Integer, List<Integer>> map, int key, int size , List<Integer> buff, Set<String> set) {

        if (size == 7) {
            //write the result to set
            String phoneNum = buff.stream().map(n -> n.toString()).collect(Collectors.joining(","));
            System.out.println(phoneNum);
            set.add(phoneNum);

        }
        if (size > 7) {
            return;
        }
        //Steps:
        //key = 1
        List<Integer> list =  map.get(key); //list of moves 6 {1, 7,  0}
        // 1: [6, 8]

        for (int n: list) {
            buff.add(n); //buff => 1, 6, 8
            generatePhoneNumsHelper(map, n, size+1, buff, set); //size=3,
            buff.remove(buff.size()-1);
        }

        //map.put(key//, <partial solution>)

    }
}

