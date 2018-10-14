/**
 157. Read N Characters Given Read4
 The API: int read4(char *buf) reads 4 characters at a time from a file.

 The return value is the actual number of characters read.
 For example, it returns 3 if there is only 3 characters left in the file.

 By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

 Example 1:

 Input: buf = "abc", n = 4
 Output: "abc"
 Explanation: The actual number of characters read is 3, which is "abc".
 Example 2:

 Input: buf = "abcde", n = 5
 Output: "abcde"
 Note:
 The read function will only be called once for each test case.
 */
public class ReadNCharsRead4 {


}

class Reader4 {
     int read4(char[] buf){
         return 0;
     }
}

class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
    Understand how read4() works, Initially, I thought it takes input buf as the parameter. But actually, *buf is just as the name refers, it's a buffer char array of size 4.
    Realize the corner case where buf = "abcdef", n = 5. The last iteration within the while loop gets count = 2, while we only need 1 last character. This is why we need to compare "count" with "n - total".
    If the length of buf can be divided by 4, then we need this check if (count == 0) break; to terminate the loop
     */
    public int read(char[] buf, int n) {

        int total = 0;
        char [] tmp = new char[4];
        while (total < n) {
            int count = read4(tmp);

            if (count == 0) {
                break;
            }
            count = Math.min(count, n-total);

            for (int i=0; i<count; i++) {
                buf[total++] = tmp[i];
            }
        }

        return total;

    }
}
