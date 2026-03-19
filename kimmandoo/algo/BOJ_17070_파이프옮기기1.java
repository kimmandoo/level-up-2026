import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[][] map;
    static int cnt = 0;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        go(0, 1, 0);
        System.out.println(cnt);
    }

    static void go(int ci, int cj, int dir) {
        if (ci == n - 1 && cj == n - 1) {
            cnt++;
            return;
        }
        int ni, nj;
        // 가로
        ni = ci;
        nj = cj + 1;
        if (dir == 0 || dir == 2) {
            if (nj < n && map[ni][nj] == 0) {
                go(ni, nj, 0);
            }
        }

        // 세로
        ni = ci + 1;
        nj = cj;
        if (dir == 1 || dir == 2) {
            if (ni < n && map[ni][nj] == 0) {
                go(ni, nj, 1);
            }
        }

        // 대각
        ni = ci + 1;
        nj = cj + 1;
        if (ni < n && nj < n && map[ci][nj] == 0 && map[ni][cj] == 0 && map[ni][nj] == 0) {
            go(ni, nj, 2);
        }
    }
}