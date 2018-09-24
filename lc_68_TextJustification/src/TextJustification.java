import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 68. Text Justification
 https://leetcode.com/problems/text-justification/description/
 Given an array of words and a width maxWidth, format the text such that each line has
 exactly maxWidth characters and is fully (left and right) justified.

 You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
 Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

 Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a
 line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

 For the last line of text, it should be left justified and no extra space is inserted between words.

 Note:

 A word is defined as a character sequence consisting of non-space characters only.
 Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 The input array words contains at least one word.
 Example 1:

 Input:
 words = ["This", "is", "an", "example", "of", "text", "justification."]
 maxWidth = 16
 Output:
 [
 "This    is    an",
 "example  of text",
 "justification.  "
 ]
 Example 2:

 Input:
 words = ["What","must","be","acknowledgment","shall","be"]
 maxWidth = 16
 Output:
 [
 "What   must   be",
 "acknowledgment  ",
 "shall be        "
 ]
 Explanation: Note that the last line is "shall be    " instead of "shall     be",
 because the last line must be left-justified instead of fully-justified.
 Note that the second line is also left-justified becase it contains only one word.
 Example 3:

 Input:
 words = ["Science","is","what","we","understand","well","enough","to","explain",
 "to","a","computer.","Art","is","everything","else","we","do"]
 maxWidth = 20
 Output:
 [
 "Science  is  what we",
 "understand      well",
 "enough to explain to",
 "a  computer.  Art is",
 "everything  else  we",
 "do                  "
 ]

 */
public class TextJustification {

    public static void main(String[] args) {

        TextJustification tj = new TextJustification();

      //  String [] words = {"This", "is", "an", "example", "of", "text", "justification."};
        int width = 16;

        //["What","must","be","acknowledgment","shall","be"]
        //16

        String [] words = {"What","must","be","acknowledgment","shall","be"};

        System.out.println();
        List<String> ans = tj.fullJustify(words, width);
        for (String s: ans) {
            System.out.println("|" + s + "|");
        }

    }

    public List<String> fullJustify(String[] words, int maxWidth) {

        List<String> result = new ArrayList<>();
        // words = ["This", "is", "an", "example", "of", "text", "justification."]
        //maxWidth = 16

        //Steps:
        //a. accumulate words with single space in each line subject to maxWidth.
        //b. In each line check total Free space and allocate them from left to right.
        //c. If word is greater than maxWidth -> take substring.
        List<Node> nList = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        List<String> collectWord = new ArrayList<>();
        int currLength = 0;
        int totalCharLen = 0;

        for (int i = 0; i < words.length; i++) {
            currLength += (words[i].length() + 1);

            if (currLength > maxWidth+1) {
                Node node = new Node(collectWord, totalCharLen);
                nList.add(node);
                sb.setLength(0);
                totalCharLen = 0;
                currLength = 0;
                collectWord = new ArrayList<>();
                --i;
            } else {
                sb.append(words[i] + " ");
                totalCharLen += words[i].length();
                collectWord.add(words[i]);

            }
        }

        if (sb.length() > 0) {
            Node node = new Node(collectWord, totalCharLen);
            nList.add(node);
        }
        // buff = {"this is an ", "example of " ...}
        //visit each string in buff -> take the total strings and free space and allocate.
        sb.setLength(0);
        for (int i=0; i<nList.size(); i++){
            Node node = nList.get(i);
            int spaceToAllocate = maxWidth - node.totalCharLen;
            int slots = node.wordList.size() - 1;
            int [] arr = getSlotSpaces(spaceToAllocate, slots);
            if (i == nList.size()-1 && node.wordList.size() > 1) {
                Arrays.fill(arr, 1);
            }

            int index = 0;
            for (String s: node.wordList) {
                sb.append(s);
                while (arr[index] > 0) {
                    sb.append(' ');
                    --arr[index];
                }
                ++index;
            }
            result.add(sb.toString());
            sb.setLength(0);
        }

        return result;
    }

    private int [] getSlotSpaces(int spaceToAllocate, int slots) {
        int [] arr = new int[slots+1];
        if (slots == 0) {
            arr[0] = spaceToAllocate;
        } else {
            for (int i=0; i<arr.length-1; i++) {
                arr[i] = spaceToAllocate % slots == 0 ? (spaceToAllocate / slots) : (spaceToAllocate / slots) + 1;
                spaceToAllocate = spaceToAllocate - arr[i];
                --slots;
            }
        }
        System.out.println("space array: " + Arrays.toString(arr));
        return arr;

    }
}

class Node {
    List<String> wordList;
    int totalCharLen;


    Node(List<String> wordList, int totalCharLen) {
        this.wordList = new ArrayList<>(wordList);
        this.totalCharLen = totalCharLen;
    }
}
