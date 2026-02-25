import java.io.*;
import java.util.*;

public class Main {

    static char[][] m;
    static int[] out = new int[7];
    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//        st = new StringTokenizer(br.readLine(), " ");
        m = new char[5][5];
        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            for (int j = 0; j < 5; j++) {
                m[i][j] = line.charAt(j);
            }
        }

        go(0, 0, 0);

        System.out.println(cnt);
    }

    static int cnt = 0;

    public static void go(int st, int idx, int cntS) {
        if (idx == 7) {
            if (cntS >= 4) {
                if (isConnected()) cnt++;
            }
            return;
        }

        for (int i = st; i < 25; i++) {
            out[idx] = i;
            int r = i / 5;
            int c = i % 5;

            if (m[r][c] == 'S') {
                go(i + 1, idx + 1, cntS + 1);
            }
            else go(i + 1, idx + 1, cntS);
        }
    }

    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    public static boolean isConnected() {
        boolean[] v = new boolean[7];
        Deque<Integer> q = new ArrayDeque<>();

        q.offer(out[0]);
        v[0] = true;
        int link = 1;

        while (!q.isEmpty()) {
            int cur = q.poll();
            int ci = cur / 5, cj = cur % 5;

            for (int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if (ni < 0 || ni >= 5 || nj < 0 || nj >= 5) continue;
                for (int i = 1; i < 7; i++) {
                    if (!v[i] && out[i] == ni * 5 + nj) {
                        // 방문하지않았고,
                        // 행은 나누고, 열은 그냥 더하면 idx가 그 좌표나 마찬가지 -> 이게 인접하는지?
                        // 인접하면 출발
                        v[i] = true;
                        q.offer(out[i]);
                        link++;
                    }
                }
            }
        }
        return link == 7;
    }
}