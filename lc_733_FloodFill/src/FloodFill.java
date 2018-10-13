/**
 733. Flood Fill
 https://leetcode.com/problems/flood-fill/description/

 An image is represented by a 2-D array of integers, each integer representing the pixel value
 of the image (from 0 to 65535).

 Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill,
 and a pixel value newColor, "flood fill" the image.

 To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally
 to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the newColor.

 At the end, return the modified image.

 Example 1:
 Input:
 image = [[1,1,1],[1,1,0],[1,0,1]]
 sr = 1, sc = 1, newColor = 2
 Output: [[2,2,2],[2,2,0],[2,0,1]]
 Explanation:
 From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected
 by a path of the same color as the starting pixel are colored with the new color.
 Note the bottom corner is not colored 2, because it is not 4-directionally connected
 to the starting pixel.

 */
public class FloodFill {

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {

        if (image[sr][sc] != newColor) {
            helper(image, sr, sc, newColor, image[sr][sc]);
        }


        return image;
    }

    private void helper(int [][]M, int r, int c, int color, int curr) {

        if (r < 0 || c < 0 || r >= M.length || c >= M[0].length || M[r][c] != curr) {
            return;
        }

        M[r][c] = color;

        helper(M, r+1, c, color, curr);
        helper(M, r, c+1, color, curr);
        helper(M, r-1, c, color, curr);
        helper(M, r, c-1, color, curr);
    }
}
