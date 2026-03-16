import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static final int INF = 100000001;
    static boolean[] s, t;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/boj.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine().trim());

        String line = br.readLine();
        s = new boolean[n];
        for (int i = 0; i < n; i++) {
            s[i] = line.charAt(i) == '1';
        }

        line = br.readLine();
        t = new boolean[n];
        for (int i = 0; i < n; i++) {
            t[i] = line.charAt(i) == '1';
        }

        int res1 = go(s.clone(), t, false);
        int res2 = go(s.clone(), t, true);

        int answer = Math.min(res1, res2);
        System.out.println(answer == INF ? -1 : answer);
    }

    public static int go(boolean[] cur, boolean[] target, boolean first) {
        int cnt = 0;

        // 0번 스위치를 누르는 경우
        if (first) {
            cnt++;
            flip(cur, 0);
        }

        for (int i = 1; i < n; i++) {
            if (cur[i - 1] != target[i - 1]) {
                cnt++;
                flip(cur, i);
            }
        }
        if (cur[n - 1] == target[n - 1]){
            return cnt;
        }

        return INF;
    }

    public static void flip(boolean[] arr, int idx) {
        for (int i = idx - 1; i <= idx + 1; i++) {
            // 반전술식
            if (i >= 0 && i < n) {
                arr[i] = !arr[i];
            }
        }
    }
}