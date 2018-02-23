/**
 * Created by manib on 2/22/18.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * You are playing the following Flip Game with your friend: Given a string that contains only these
 * two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--".
 * The game ends when a person can no longer make a move and therefore the other person will be the winner.
 * Write a function to compute all possible states of the string after one valid move.
 */

public class FlipGame {

    public static void main(String [] args) {
        FlipGame fg = new FlipGame();
        String str = "++++";
        List<String> result = fg.generatePossibleMoves(str);
        for (String s: result) {
            System.out.println(s);
        }

    }

    public List<String> generatePossibleMoves(String str) {
        List<String> result = new ArrayList<String>();

        if (str == null || str.isEmpty()) {
            return result;
        }

        char [] arr = str.toCharArray();
        for (int i=0; i<arr.length-1; i++) {
            if (arr[i] == arr[i+1] && arr[i] == '+') {
                arr[i] = '-';
                arr[i+1] = '-';
                result.add(new String(arr));
                arr[i] = '+';
                arr[i+1] = '+';
            }
        }

        return result;
    }
}
