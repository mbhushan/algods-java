import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 403. Frog Jump
 https://leetcode.com/problems/frog-jump/description/

 A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone.
 The frog can jump on a stone, but it must not jump into the water.
 Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the
 river by landing on the last stone. Initially, the frog is on the first stone and assume the first jump must be 1 unit.
 If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog
 can only jump in the forward direction.

 Note:
 The number of stones is ≥ 2 and is < 1,100.
 Each stone's position will be a non-negative integer < 231.
 The first stone's position is always 0.
 Example 1:
 [0,1,3,5,6,8,12,17]

 There are a total of 8 stones.
 The first stone at the 0th unit, second stone at the 1st unit,
 third stone at the 3rd unit, and so on...
 The last stone at the 17th unit.

 Return true. The frog can jump to the last stone by jumping
 1 unit to the 2nd stone, then 2 units to the 3rd stone, then
 2 units to the 4th stone, then 3 units to the 6th stone,
 4 units to the 7th stone, and 5 units to the 8th stone.
 Example 2:
 [0,1,2,3,4,8,9,11]

 Return false. There is no way to jump to the last stone as
 the gap between the 5th and 6th stone is too large.


 */
public class FrogJump {

    public static void main(String[] args) {
        FrogJump fj = new FrogJump();

        int [][] A = {
                {0,1,2,3,4,8,9,11},
                {0,1,3,5,6,8,12,17},
                {0, 2}
        };

        for (int i=0; i<A.length; i++) {
            System.out.println("input: " + Arrays.toString(A[i]));
            boolean flag = fj.canCross(A[i]);
            System.out.println("can jump: " + flag);
            System.out.println();
        }

    }

    public boolean canCross(int[] stones) {

        if (stones == null || stones.length < 1) {
            return false;
        }

        Map<Integer, Set<Integer>> map = new HashMap<>();

        for (int s: stones) {
            map.put(s, new HashSet<>());
        }

        map.put(stones[0], new HashSet<Integer>(){{add(0);}});

        for (int i=0; i<stones.length; i++) {
            Set<Integer> list = map.get(stones[i]);

            for (int j: list) {
                for (int k=-1; k<=1; k++) {
                    int jump = j + k;
                    if (jump > 0) {
                        if (map.containsKey(stones[i]+jump)) {
                            Set<Integer> jList = map.get(stones[i]+jump);
                            jList.add(jump);
                            map.put(stones[i]+jump, jList);
                        }
                    }
                }
            }
        }

        System.out.println(map);

        return !map.get(stones[stones.length-1]).isEmpty();
    }
}
