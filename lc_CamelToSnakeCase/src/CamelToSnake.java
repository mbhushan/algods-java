/**
 * Created by manib on 4/30/18.
 */
public class CamelToSnake {

    public static void main(String[] args) {
        CamelToSnake obj = new CamelToSnake();
        String [] S = {
                "nameUUID", "timeUUIDSpace"
        };

        for (int i=0; i<S.length; i++) {
            System.out.println(S[i] + ": " + obj.camelCaseToSnakeCase(S[i]));
        }

    }

    private String camelCaseToSnakeCase(String word) {
        if (word == null || word.isEmpty()) {
            return word;
        }
        StringBuilder sb = new StringBuilder();
        char lastChar = word.charAt(0);
        int len = word.length();
        for (int i=0; i<len; i++) {
            if (Character.isUpperCase(word.charAt(i)) && !Character.isUpperCase(lastChar)) {
                sb.append('_');
            }
            sb.append(word.charAt(i));
            lastChar = word.charAt(i);
        }
        return sb.toString().toLowerCase();
    }
}
