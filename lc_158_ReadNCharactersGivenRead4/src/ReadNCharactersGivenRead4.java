import java.util.Random;

/**
 *
 158. Read N Characters Given Read4 II - Call multiple times
 https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/description/

 The API: int read4(char *buf) reads 4 characters at a time from a file.

 The return value is the actual number of characters read. For example,
 it returns 3 if there is only 3 characters left in the file.

 By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

 Note:
 The read function may be called multiple times.

 Example 1:

 Given buf = "abc"
 read("abc", 1) // returns "a"
 read("abc", 2); // returns "bc"
 read("abc", 1); // returns ""
 Example 2:

 Given buf = "abc"
 read("abc", 4) // returns "abc"
 read("abc", 1); // returns ""

 =======================
 Question Clarification
 =======================
 https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/discuss/49607/The-missing-clarification-you-wish-the-question-provided

 It took me many hours by try and error as well as looking at many solutions to figure out the following 3 key clarifications the question failed to provide.

 With the provided clarifications, it's not difficult to think of a solution using a char buffer[5] to store results from read4() and then read chars from it.

 read4() has its own file pointer, much like FILE *fp in C.
 // file is "abc", initially fp points to 'a'
 read(1) // returns buf = "a", now fp points to 'b'
 read(1) // returns buf = "b", now fp points to 'c'
 read(2) // returns buf = "c", now fp points to end of file
 char *buf is destination not source, similar to that of scanf("%s", buf), OJ outputs this buf value.

 Each time read() is called, we need to provide a new buf to store read characters, therefore, the return value of int read() is simply the length of buf.

 ==================
 INPUT / OUTPUT
 ==================

 */

public class ReadNCharactersGivenRead4 {

    private int buffPtr = 0;
    private int buffCount = 0;
    private char[] tmpBuff = new char[4];

    public static void main(String[] args) {
        ReadNCharactersGivenRead4 rn = new ReadNCharactersGivenRead4();

        char [] buf = new char[4];
        rn.read(buf, 2);

    }

    public int read(char[] buf, int n) {
        int ptr = 0;

        while (ptr < n) {
            //tmp buff is empty.
            if (buffPtr == 0) {
                buffCount = read4(tmpBuff);
            }

            //eof
            if (buffCount == 0) {
                break;
            }
            //copy from temp buffer to buff as needed.
            while (ptr < n && buffPtr < buffCount) {
                buf[ptr++] = tmpBuff[buffPtr++];
            }

            //if temp buffer is exhausted then reset buffPtr
            if (buffPtr >= buffCount) {
                buffPtr = 0;
            }
        }

        return ptr;
    }

    public int read4(char [] buff) {
        Random rand = new Random();
        return 1 + rand.nextInt(4);
    }

}
