public class RegExp1 {
    /*
        Regular Expression
     */

    public static void main(String[] args) {
        RegExp1 re = new RegExp1();
       // re.test1();

        String [] S = {
                "saaac",
                "saaabbbc"
        };

        String [] P = {
                 ".a+b*c",
                ".a+b*c"
        };

        for (int i=0; i<S.length; i++) {
            System.out.println("String: " + S[i]);
            System.out.println("Pattern: " + P[i]);
            boolean ans = re.solveRegex(S[i].toCharArray(), P[i].toCharArray());
            System.out.println(ans);
            System.out.println();
        }


    }

    public boolean solveRegex(char [] S, char [] P) {

        if (S == null || P == null) {
            return  false;
        }

        boolean [][] T = new boolean[S.length+1][P.length+1];
        T[0][0] = true;
        for (int i=1; i<T[0].length; i++) {
            if (i>1 && P[i-1] == '*') {
                T[0][i] = T[0][i-2];
            }
        }

        for (int i=1; i<T.length; i++) {
            for (int j=1; j<T[0].length; j++) {
                if (S[i-1] == P[j-1] || P[j-1] == '.') {
                    T[i][j] = T[i-1][j-1];
                } else if (P[j-1] == '*') {
                    T[i][j] = T[i][j-2]; //zero occrances.
                    if (S[i-1] == P[j-2] || P[j-2] == '.') {
                        T[i][j] = T[i-1][j];
                    }
                } else if (P[j-1] == '+') {
                    if (S[i-1] == P[j-2]) {
                        T[i][j] = T[i-1][j] || T[i][j-1];
                    }
                } else {
                    T[i][j] = false;
                }
            }
        }
        printMatrix(T);
        return T[S.length][P.length];
    }

    public void printMatrix(boolean [][] T) {
        for (int i=0; i<T.length; i++) {
            for (int j=0; j<T[0].length; j++) {
                if (T[i][j]) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }


    public class Solution {
        public boolean regMatch(String source, String pattern) {
            if (pattern.length() == 0) return source.length() == 0;
            if (pattern.length() == 1) {
                if (source.length() > 1 || source.length() == 0) return false;
                return source.charAt(0) == pattern.charAt(0);
            }

            if (source.length() != 0 && (pattern.charAt(0) == '.' || pattern.charAt(0) == source.charAt(0))) {
                if (pattern.charAt(1) == '*') {
                    return regMatch(source.substring(1), pattern) || regMatch(source, pattern.substring(2));
                } else if (pattern.charAt(1) == '+') {
                    return regMatch(source.substring(1), pattern.substring(2)) || regMatch(source.substring(1), pattern.substring(2));
                } else {
                    return regMatch(source.substring(1), pattern.substring(1));
                }
            }
            return pattern.charAt(1) == '*' && regMatch(source, pattern.substring(2));
        }
    }

    /*
        Regular Expression - Dynamic Programming
        AirBnB Interview Question
     */
    public class Solution_2 {
        public boolean regMatch(String s, String p) {
            if (s == null || p == null) return false;
            boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
            dp[0][0] = true;
            for (int i = 1; i <= p.length(); i++) {
                if (p.charAt(i - 1) == '*' && dp[i - 2][0]) dp[i][0] = true;
            }

            for (int i = 1; i <= p.length(); i++) {
                for (int j = 1; j <= s.length(); j++) {
                    if (p.charAt(i - 1) == '.' || p.charAt(i - 1) == s.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else if (p.charAt(i - 1) == '*' || p.charAt(i - 1) == '+') {
                        if (p.charAt(i - 2) == '.' || p.charAt(i - 2) == s.charAt(j - 1)) {
                            if (p.charAt(i - 1) == '*') {
                                dp[i][j] = dp[i - 2][j] || dp[i - 2][j - 1] || dp[i][j - 1];
                            } else {
                                dp[i][j] = dp[i - 2][j - 1] || dp[i][j - 1];
                            }
                        } else {
                            dp[i][j] = p.charAt(i - 1) == '*' && dp[i - 2][j];
                        }
                    }
                }
            }
            return dp[p.length()][s.length()];
        }
    }

        public void test1() {
            Solution sol = new RegExp1().new Solution();
            System.out.println(sol.regMatch("saaaa", "s+a*"));
            System.out.println(sol.regMatch("saaaa", "s+b*"));
            System.out.println(sol.regMatch("saaaab", "s+a*"));
            System.out.println(sol.regMatch("saaaab", "s+b*"));
        }


}

