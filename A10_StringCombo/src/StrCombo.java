import java.util.ArrayList;
import java.util.List;

public class StrCombo {

    public static void main(String[] args) {
        StrCombo sc = new StrCombo();

        String s = "abc";
        sc.findCombo(s);

        s = "airbnb";
        sc.findCombo(s);

    }

    public List<String> findCombo(String input) {
        List<String> result = new ArrayList<>();

        if (input == null || input.length() < 1) {
            return result;
        }

        int len = input.length();
        int num = (int)Math.pow(2, len);

        for (int i=0; i<num; i++) {

            int x = i;
            StringBuffer sb = new StringBuffer();
            int idx = 0;
            while (x != 0) {
                if ((x & 1) == 1) {
                    sb.append(Character.toUpperCase(input.charAt(idx)));
                } else {
                    sb.append(input.charAt(idx));
                }
                ++idx;
                x = x >> 1;
            }
            while (idx < len) {
                sb.append(input.charAt(idx));
                ++idx;
            }

            result.add(sb.toString());

        }
        System.out.println("result size: " + result.size());
        System.out.println(result);
        return result;
    }
}
