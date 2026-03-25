import java.io.*;
import java.util.*;

public class Main {

    static class Word {
        String s;
        int idx;

        Word(String s, int idx) {
            this.s = s;
            this.idx = idx;
        }
    }

    static int n;
    static String[] input;
    static Word[] arr;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        input = new String[n];
        arr = new Word[n];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            input[i] = s;
            arr[i] = new Word(s, i);
        }

        Arrays.sort(arr, new Comparator<Word>(){
            @Override
            public int compare(Word o1, Word o2) {
                return o1.s.compareTo(o2.s);
            }
        }); // 사전순 정렬

        int maxLen = 0;

        // 인접한 단어끼리 비교
        for (int i = 0; i < n - 1; i++) {
            maxLen = Math.max(maxLen, findCommon(arr[i].s, arr[i + 1].s));
        }

        boolean[] cand = new boolean[n];

        for (int i = 0; i < n - 1; i++) {
            int len = findCommon(arr[i].s, arr[i + 1].s);
            if (len == maxLen) { // 길이가 maxLen이랑 같으면
                cand[arr[i].idx] = true;
                cand[arr[i + 1].idx] = true;
            }
        }

        int s = -1;

        for (int i = 0; i < n; i++) {
            if (!cand[i]) continue;

            for (int j = i + 1; j < n; j++) {
                int len = findCommon(input[i], input[j]);
                if (len == maxLen) {
                    s = i;
                    break;
                }
            }

            if (s != -1) break;
        }

        // T 찾기
        int t = -1;
        for (int j = s + 1; j < n; j++) {
            if (findCommon(input[s], input[j]) == maxLen) {
                t = j;
                break;
            }
        }

        System.out.println(input[s]);
        System.out.println(input[t]);
    }

    static int findCommon(String a, String b) {
        int len = Math.min(a.length(), b.length());
        int i = 0;
        while (i < len && a.charAt(i) == b.charAt(i)){
            i++;
        }
        return i;
    }
}