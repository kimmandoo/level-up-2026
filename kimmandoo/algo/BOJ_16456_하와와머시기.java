import java.io.*;
import java.util.*;

public class Main {

    static int n, m, k;
    static boolean[] ks;
    static StringBuilder sb = new StringBuilder();
    static final int MOD = 1000000009;
    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        if (n == 1) {
            System.out.println(1);
            return;
        }
        // N=2 1, 2 -> 1가지
        if (n == 2) {
            System.out.println(1);
            return;
        }
        // N=3 1, 2, 3, 1, 3, 2 -> 2가지
        if (n == 3) {
            System.out.println(2);
            return;
        }

        long[] dp = new long[n + 1];

        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 2;

        // dp[i] = dp[i-1] + dp[i-3]
        for (int i = 4; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 3]) % MOD;
        }

        System.out.println(dp[n]);
//        System.out.println(sb);
    }

    public static void go() {
        if (n == 1) {
            System.out.println(1);
            return;
        }
        // N=2 1, 2 -> 1가지
        if (n == 2) {
            System.out.println(1);
            return;
        }
        // N=3 1, 2, 3, 1, 3, 2 -> 2가지
        if (n == 3) {
            System.out.println(2);
            return;
        }

        long[] dp = new long[n + 1];

        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 2;

        // dp[i] = dp[i-1] + dp[i-3]
        for (int i = 4; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 3]) % MOD;
        }

        System.out.println(dp[n]);
    }
}