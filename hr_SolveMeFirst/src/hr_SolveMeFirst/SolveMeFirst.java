package hr_SolveMeFirst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.hackerrank.com/challenges/solve-me-first
 * @author manib
 */

public class SolveMeFirst {

	public static void main(String [] args) {
		readInput();
	}
	
	public static void readInput() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		try {
			while ((line = br.readLine()) != null && line.length()!=0) {
				String [] strs = line.split(" ");
				int x = Integer.parseInt(strs[0]);
				int y = Integer.parseInt(strs[1]);
				System.out.println(x+y);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
