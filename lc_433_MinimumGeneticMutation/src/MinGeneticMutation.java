import java.util.HashSet;
import java.util.Set;

/**
 433. Minimum Genetic Mutation
 https://leetcode.com/problems/minimum-genetic-mutation/description/

 A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".

 Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation is defined as
 ONE single character changed in the gene string.

 For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.

 Also, there is a given gene "bank", which records all the valid gene mutations. A gene must be in
 the bank to make it a valid gene string.

 Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations
 needed to mutate from "start" to "end". If there is no such a mutation, return -1.

 Note:

 Starting point is assumed to be valid, so it might not be included in the bank.
 If multiple mutations are needed, all mutations during in the sequence must be valid.
 You may assume start and end string is not the same.

 */
public class MinGeneticMutation {

    public static void main(String[] args) {
        MinGeneticMutation mg  = new MinGeneticMutation();

        String start = "AACCGGTT";
        String end =   "AAACGGTA";
        String [] bank = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};

        System.out.println(mg.minMutation(start, end, bank));
    }

    public int minMutation(String start, String end, String[] bank) {
        int [] count = new int[1];
        count[0] = Integer.MAX_VALUE;

        Set<String> visited = new HashSet<>();

        findMinMutation(start, end, bank, visited, 0, count);
        return count[0];
    }

    private void findMinMutation(String start, String end, String [] bank, Set<String> visited, int level, int []
            count) {
        if (start.equals(end)) {
            count[0] = Math.min(count[0], level);
        }

        for (String s: bank) {
            int diff = 0;
            for (int i=0; i<start.length(); i++) {
                if (start.charAt(i) != s.charAt(i)) {
                    ++diff;
                }
                if (diff > 1) {
                    break;
                }
            }
            if (diff == 1 && !visited.contains(s)) {
                visited.add(s);
                findMinMutation(s, end, bank, visited, level+1, count);
                visited.remove(s);
            }
        }

    }
}
