import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class FunctionTimes {
    public static void main(String[] args) {

        String [] S = {"0:start:0",
                "1:start:2",
                "1:end:5",
                "0:end:6"};
        List<String> logs = new ArrayList<String>(Arrays.asList(S));

        FunctionTimes ft = new FunctionTimes();

        ft.exclusiveTime(2, logs);
    }

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Stack<int[]> stack = new Stack<>();

        for (String log : logs) {
            String[] s = log.split(":");
            int idx = Integer.valueOf(s[0]);
            int t = Integer.valueOf(s[2]);

            if (s[1].equals("start")) {
                stack.push(new int[]{idx, t});
            } else {
                int v = t - stack.pop()[1] + 1;
                res[idx] += v;
                if (!stack.empty()) res[stack.peek()[0]] -= v;
            }
        }
        System.out.println(Arrays.toString(res));
        return res;
    }
}
