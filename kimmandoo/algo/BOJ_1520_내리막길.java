import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[][] b;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        b = new int[n][m];
        memo = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                b[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }

        go(0, 0);
//        for (int[] d : memo) {
//            System.out.println(Arrays.toString(d));
//        }
        System.out.println(memo[0][0]);
    }

    static int[][] memo; // 각 칸은 끝까지 이동하는 경우의 수를 기록함
    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, 1, 0, -1};

    public static int go(int ci, int cj) {
        // 항상 높이가 더 낮은 곳으로 이동
        if (ci == n - 1 && cj == m - 1) {
            // 끝에서 끝은 없다.
            return 1;
        }
        if (memo[ci][cj] != -1) {
            // 이미 왔던 곳
            return memo[ci][cj];
        }
        memo[ci][cj] = 0;
        for (int d = 0; d < 4; d++) {
            int ni = ci + di[d];
            int nj = cj + dj[d];
            if (ni >= n || nj >= m || ni < 0 || nj < 0 || b[ci][cj] <= b[ni][nj]) continue;

            // 내려갈 수 있음
            memo[ci][cj] += go(ni, nj);
        }

        return memo[ci][cj];
    }
}