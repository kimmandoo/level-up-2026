import java.io.*;
import java.util.Arrays;

public class Main {

    static int n;
    static StringBuilder sb = new StringBuilder();
    static int[] input;
    static int[][] memo; // [계단번호][연속]

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        input = new int[n];
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }
        memo = new int[n][3];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        int res1 = 0;
        int res2 = 0;

        if (n > 0) {
            res1 = go(0, 1);
        }
        if (n > 1) {
            res2 = go(1, 1);
        }

        System.out.println(Math.max(res1, res2));
    }

    public static int go(int depth, int cnt) {
        if (depth == n - 1) {
            return input[depth];
        }

        // 이미 계산한 적이 있다면 그 값을 즉시 반환
        if (memo[depth][cnt] != -1) {
            return memo[depth][cnt];
        }

        int res = -10000;

        // 한 칸
        if (depth + 1 < n && cnt < 2) {
            res = Math.max(res, go(depth + 1, cnt + 1));
        }

        // 두 칸
        if (depth + 2 < n) {
            // 언제든 가능
            res = Math.max(res, go(depth + 2, 1));
        }

        if (res < 0 && depth != n - 1) { // 마지막꺼를 못밟는 경우다
            return memo[depth][cnt] = -10000;
        }

        return memo[depth][cnt] = res + input[depth];
    }
}