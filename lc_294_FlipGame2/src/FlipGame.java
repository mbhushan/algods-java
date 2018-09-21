import java.util.HashMap;
import java.util.Map;

/**
 294. Flip Game II
 https://leetcode.com/problems/flip-game-ii/description/
 You are playing the following Flip Game with your friend: Given a string that contains only these
 two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--".
 The game ends when a person can no longer make a move and therefore the other person will be the winner.

 Write a function to determine if the starting player can guarantee a win.

 Example:

 Input: s = "++++"
 Output: true
 Explanation: The starting player can guarantee a win by flipping the middle "++" to become "+--+".
 Follow up:
 Derive your algorithm's runtime complexity.

 */
public class FlipGame {

    public static void main(String[] args) {
        FlipGame fg = new FlipGame();

        System.out.println("output: " + fg.canWinHelper("++++"));
        System.out.println("output DP: " + fg.canWinDP("++++"));
    }

    public boolean canWin(String s) {
        return canWinHelper(s);
    }

    public boolean canWinDP(String s) {
        Map<String, Boolean> map = new HashMap<>();
        return winHelperDP(s, map);
    }

    private boolean winHelperDP(String s,  Map<String, Boolean> map ) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        if (s == null || s.length() < 2) {
            return false;
        }

        for (int i=0; i<s.length()-1; i++) {
            if (s.startsWith("++", i)) {
                String tmp = s.substring(0, i) + "--" + s.substring(i+2);
                if (!winHelperDP(tmp, map)) {
                    map.put(s, true);
                    return true;
                }
            }
        }

        map.put(s, false);
        return false;
    }

    private boolean canWinHelper(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }

        for (int i=0; i<s.length()-1; i++) {
            if (s.startsWith("++", i)) {
                String tmp = s.substring(0, i) + "--" + s.substring(i+2);
                if (!canWinHelper(tmp)) {
                    return true;
                }
            }
        }

        return false;
    }
}
