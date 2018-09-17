import java.util.Arrays;

/**
 717. 1-bit and 2-bit Characters

 https://leetcode.com/problems/1-bit-and-2-bit-characters/description/

 We have two special characters. The first character can be represented by one bit 0.
 The second character can be represented by two bits (10 or 11).

 Now given a string represented by several bits. Return whether the last character must be a one-bit
 character or not. The given string will always end with a zero.

 Example 1:
 Input:
 bits = [1, 0, 0]
 Output: True
 Explanation:
 The only way to decode it is two-bit character and one-bit character. So the last character is one-bit character.
 Example 2:
 Input:
 bits = [1, 1, 1, 0]
 Output: False
 Explanation:
 The only way to decode it is two-bit character and two-bit character. So the last character is NOT one-bit character.
 Note:

 1 <= len(bits) <= 1000.
 bits[i] is always 0 or 1.

 ==============
 input / output
 ==============
 input: [1, 1, 1, 0]
 ans: false
 input: [1, 1, 1, 1, 0]
 ans: true
 */
public class BitCharacters {

    public static void main(String[] args) {
        BitCharacters bc = new BitCharacters();

        int [] bits = {1, 1, 1, 0};

        System.out.println("input: " + Arrays.toString(bits));

        boolean ans = bc.isOneBitCharacter(bits);
        System.out.println("ans: " + ans);

        int [] bits1 = {1, 1, 1, 1, 0};

        System.out.println("input: " + Arrays.toString(bits1));

        boolean ans1 = bc.isOneBitCharacter(bits1);
        System.out.println("ans: " + ans1);


    }

    public boolean isOneBitCharacter(int[] bits) {
        if (bits == null) { return false; }

        int len = bits.length - 1;
        int index = 0;

        while (index < len) {
            index += bits[index] + 1;
        }

        return index == len;
    }
}
