import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int mx = -1;
    static int[][] memo;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/boj.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        int[] input = new int[n];
        for (int i = 0; i < n; i++) {
            int target = Integer.parseInt(br.readLine());
            input[i] = target;
            mx = Math.max(mx, target);
        }

        memo = new int[mx + 1][4];
        for (int i = 0; i <= mx; i++) {
            Arrays.fill(memo[i], -1);
        }

        StringBuilder sb = new StringBuilder();
        for (int e : input) {
            sb.append(go(e, 1)).append('\n');
        }
        System.out.print(sb);
    }

    static int go(int idx, int start) {
        if (idx == 0) return 1;
        if (idx < 0) return 0;
        if (memo[idx][start] != -1) return memo[idx][start];

        int sum = 0;

        for (int num = start; num <= 3; num++) {
            sum += go(idx - num, num);
        }

        memo[idx][start] = sum;
        return sum;
    }
}