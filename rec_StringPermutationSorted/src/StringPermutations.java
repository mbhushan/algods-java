import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * generate all string permutations of strings in lexicographically sorted order where repetition of characters is
 * possible.
 */

public class StringPermutations {
    public static void main(String [] args) {
        StringPermutations sp = new StringPermutations();
        String [] inputs = {"abcab", "abcabcc", "cats", "doggy", "eata"};
        for (int i=0; i<inputs.length; i++) {
            sp.permute(inputs[i]);
        }
    }

    public void permute(String str) {
        if (str == null || str.length() <= 1) {
            return;
        }
        char [] input = str.toCharArray();
        int len = input.length;
        Arrays.sort(input);
        System.out.println("sorted: " + String.valueOf(input));
        Map<Character, Integer> countMap = new HashMap<Character, Integer>();
        StringBuffer sbChar = new StringBuffer();
        StringBuffer sbCount = new StringBuffer();
        char prev = input[0];
        int count = 1;
        List<Integer> countList = new ArrayList<>();

        for (int i=1; i<input.length; i++) {
            if (prev == input[i]) {
                ++count;
            } else {
                sbChar.append(prev);
                sbCount.append(count);
                countList.add(count);
                count = 1;
                prev = input[i];
            }
        }
        if (prev == input[len-1]) {
            sbChar.append(input[len-1]);
            sbCount.append(count);
            countList.add(count);
        }

        System.out.println("characters: " + sbChar.toString());
        System.out.println("counts: " + sbCount.toString());
        int[] countArr = countList.stream().mapToInt(i->i).toArray();
        char [] result = new char[input.length];
        permuteUtil(sbChar.toString().toCharArray(), countArr, result, 0);
    }

    public void permuteUtil(char [] A, int [] count, char [] result, int index) {
        if (index == result.length) {
            System.out.println(result);
            return;
        }

        for (int i=0; i<A.length; i++) {
            if (count[i] == 0) {
                continue;
            }
            result[index] = A[i];
            --count[i];
            permuteUtil(A, count, result, index+1);
            ++count[i];
        }
    }
}
/**
 ================
 INPUT / OUTPUT:
 ================

 sorted: aabbc
 characters: abc
 counts: 221
 aabbc
 aabcb
 aacbb
 ababc
 abacb
 abbac
 abbca
 abcab
 abcba
 acabb
 acbab
 acbba
 baabc
 baacb
 babac
 babca
 bacab
 bacba
 bbaac
 bbaca
 bbcaa
 bcaab
 bcaba
 bcbaa
 caabb
 cabab
 cabba
 cbaab
 cbaba
 cbbaa

 sorted: aabbccc
 characters: abc
 counts: 223
 aabbccc
 aabcbcc
 aabccbc
 aabcccb
 aacbbcc
 aacbcbc
 aacbccb
 aaccbbc
 aaccbcb
 aacccbb
 ababccc
 abacbcc
 abaccbc
 abacccb
 abbaccc
 abbcacc
 abbccac
 abbccca
 abcabcc
 abcacbc
 abcaccb
 abcbacc
 abcbcac
 abcbcca
 abccabc
 abccacb
 abccbac
 abccbca
 abcccab
 abcccba
 acabbcc
 acabcbc
 acabccb
 acacbbc
 acacbcb
 acaccbb
 acbabcc
 acbacbc
 acbaccb
 acbbacc
 acbbcac
 acbbcca
 acbcabc
 acbcacb
 acbcbac
 acbcbca
 acbccab
 acbccba
 accabbc
 accabcb
 accacbb
 accbabc
 accbacb
 accbbac
 accbbca
 accbcab
 accbcba
 acccabb
 acccbab
 acccbba
 baabccc
 baacbcc
 baaccbc
 baacccb
 babaccc
 babcacc
 babccac
 babccca
 bacabcc
 bacacbc
 bacaccb
 bacbacc
 bacbcac
 bacbcca
 baccabc
 baccacb
 baccbac
 baccbca
 bacccab
 bacccba
 bbaaccc
 bbacacc
 bbaccac
 bbaccca
 bbcaacc
 bbcacac
 bbcacca
 bbccaac
 bbccaca
 bbcccaa
 bcaabcc
 bcaacbc
 bcaaccb
 bcabacc
 bcabcac
 bcabcca
 bcacabc
 bcacacb
 bcacbac
 bcacbca
 bcaccab
 bcaccba
 bcbaacc
 bcbacac
 bcbacca
 bcbcaac
 bcbcaca
 bcbccaa
 bccaabc
 bccaacb
 bccabac
 bccabca
 bccacab
 bccacba
 bccbaac
 bccbaca
 bccbcaa
 bcccaab
 bcccaba
 bcccbaa
 caabbcc
 caabcbc
 caabccb
 caacbbc
 caacbcb
 caaccbb
 cababcc
 cabacbc
 cabaccb
 cabbacc
 cabbcac
 cabbcca
 cabcabc
 cabcacb
 cabcbac
 cabcbca
 cabccab
 cabccba
 cacabbc
 cacabcb
 cacacbb
 cacbabc
 cacbacb
 cacbbac
 cacbbca
 cacbcab
 cacbcba
 caccabb
 caccbab
 caccbba
 cbaabcc
 cbaacbc
 cbaaccb
 cbabacc
 cbabcac
 cbabcca
 cbacabc
 cbacacb
 cbacbac
 cbacbca
 cbaccab
 cbaccba
 cbbaacc
 cbbacac
 cbbacca
 cbbcaac
 cbbcaca
 cbbccaa
 cbcaabc
 cbcaacb
 cbcabac
 cbcabca
 cbcacab
 cbcacba
 cbcbaac
 cbcbaca
 cbcbcaa
 cbccaab
 cbccaba
 cbccbaa
 ccaabbc
 ccaabcb
 ccaacbb
 ccababc
 ccabacb
 ccabbac
 ccabbca
 ccabcab
 ccabcba
 ccacabb
 ccacbab
 ccacbba
 ccbaabc
 ccbaacb
 ccbabac
 ccbabca
 ccbacab
 ccbacba
 ccbbaac
 ccbbaca
 ccbbcaa
 ccbcaab
 ccbcaba
 ccbcbaa
 cccaabb
 cccabab
 cccabba
 cccbaab
 cccbaba
 cccbbaa

 sorted: acst
 characters: acst
 counts: 1111
 acst
 acts
 asct
 astc
 atcs
 atsc
 cast
 cats
 csat
 csta
 ctas
 ctsa
 sact
 satc
 scat
 scta
 stac
 stca
 tacs
 tasc
 tcas
 tcsa
 tsac
 tsca

 sorted: dggoy
 characters: dgoy
 counts: 1211
 dggoy
 dggyo
 dgogy
 dgoyg
 dgygo
 dgyog
 doggy
 dogyg
 doygg
 dyggo
 dygog
 dyogg
 gdgoy
 gdgyo
 gdogy
 gdoyg
 gdygo
 gdyog
 ggdoy
 ggdyo
 ggody
 ggoyd
 ggydo
 ggyod
 godgy
 godyg
 gogdy
 gogyd
 goydg
 goygd
 gydgo
 gydog
 gygdo
 gygod
 gyodg
 gyogd
 odggy
 odgyg
 odygg
 ogdgy
 ogdyg
 oggdy
 oggyd
 ogydg
 ogygd
 oydgg
 oygdg
 oyggd
 ydggo
 ydgog
 ydogg
 ygdgo
 ygdog
 yggdo
 yggod
 ygodg
 ygogd
 yodgg
 yogdg
 yoggd

 sorted: aaet
 characters: aet
 counts: 211
 aaet
 aate
 aeat
 aeta
 atae
 atea
 eaat
 eata
 etaa
 taae
 taea
 teaa
 */