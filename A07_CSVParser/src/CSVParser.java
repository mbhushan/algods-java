import java.util.ArrayList;
import java.util.List;

/**
 CSV Parser
 http://creativyst.com/Doc/Articles/CSV/CSV01.htm#EmbedBRs
 */

public class CSVParser {

    public static void main(String[] args) {
        String [] input = {
                            "\"Alexandra \"\"Alex\"\"\",Menendez,alex.menendez@gmail.com,Miami,1",
                            "Jane,Roberts,janer@msn.com,\"San Francisco, CA\",0",
                            "John,Smith,john.smith@gmail.com,Los Angeles,1"
                            };

        System.out.println();

        CSVParser csv = new CSVParser();

        for (String s: input) {
            System.out.println("input: "  + s);
            System.out.println("output: " + csv.csvParser(s));
            System.out.println();

        }
    }

    public String csvParser(String text) {
        if (text == null || text.length() < 1) {
            return text;
        }

        List<String> res = new ArrayList<String>();
        int len = text.length();
        boolean inQuote = false;
        StringBuffer sb = new StringBuffer();

        for (int i=0; i<len; i++) {
            if (inQuote) {
                if (text.charAt(i) == '\"') {
                    if ((i < len-1) && (text.charAt(i+1) == '\"')) {
                        sb.append("\"");
                        i++;
                    } else {
                        inQuote = false;
                    }
                } else {
                    sb.append(text.charAt(i));
                }

            } else {
                if (text.charAt(i) == '\"') {
                    inQuote = true;
                } else if (text.charAt(i) == ',') {
                    res.add(sb.toString());
                    sb.setLength(0);
                } else {
                    sb.append(text.charAt(i));
                }

            }
        }
        if (sb.length() > 0) {
            res.add(sb.toString());
        }

        return String.join("|", res);
    }
}
