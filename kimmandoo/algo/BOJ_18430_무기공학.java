import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] b;
    static int mx = -1;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/boj.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        b = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                b[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] vis = new boolean[n][m];
        go(0, 0, vis, 0);
        if (mx == -1) mx = 0;
        System.out.println(mx);
    }

    public static void go(int ci, int cj, boolean[][] vis, int sum) {
        if (cj == m) {
            go(ci + 1, 0, vis, sum);
            return;
        }

        if (ci == n) {
            mx = Math.max(mx, sum);
            return;
        }

        // 지금 칸이 노란칸이라고 할때... 모양 4개 다 처리하자
        if (!vis[ci][cj]) {
            // case1
            if (isValid(ci, cj - 1, vis) && isValid(ci + 1, cj, vis)) {
                vis[ci][cj] = vis[ci][cj - 1] = vis[ci + 1][cj] = true;
                go(ci, cj + 1, vis, sum + getScore(ci, cj, ci, cj - 1, ci + 1, cj));
                vis[ci][cj] = vis[ci][cj - 1] = vis[ci + 1][cj] = false;
            }
            // case2
            if (isValid(ci, cj - 1, vis) && isValid(ci - 1, cj, vis)) {
                vis[ci][cj] = vis[ci][cj - 1] = vis[ci - 1][cj] = true;
                go(ci, cj + 1, vis, sum + getScore(ci, cj, ci, cj - 1, ci - 1, cj));
                vis[ci][cj] = vis[ci][cj - 1] = vis[ci - 1][cj] = false;
            }
            // case3
            if (isValid(ci - 1, cj, vis) && isValid(ci, cj + 1, vis)) {
                vis[ci][cj] = vis[ci - 1][cj] = vis[ci][cj + 1] = true;
                go(ci, cj + 1, vis, sum + getScore(ci, cj, ci - 1, cj, ci, cj + 1));
                vis[ci][cj] = vis[ci - 1][cj] = vis[ci][cj + 1] = false;
            }
            // case4
            if (isValid(ci + 1, cj, vis) && isValid(ci, cj + 1, vis)) {
                vis[ci][cj] = vis[ci + 1][cj] = vis[ci][cj + 1] = true;
                go(ci, cj + 1, vis, sum + getScore(ci, cj, ci + 1, cj, ci, cj + 1));
                vis[ci][cj] = vis[ci + 1][cj] = vis[ci][cj + 1] = false;
            }
        }

        go(ci, cj + 1, vis, sum); // 지금 칸을 노란칸으로 안쓸때
    }

    public static int getScore(int i1, int j1, int i2, int j2, int i3, int j3) {
        return (b[i1][j1] * 2) + b[i2][j2] + b[i3][j3];
    }

    public static boolean isValid(int ni, int nj, boolean[][] vis) {
        return ni < n && ni >= 0 && nj < m && nj >= 0 && !vis[ni][nj];
    }
}