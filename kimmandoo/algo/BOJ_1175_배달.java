import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static char[][] map;
    static int si, sj;
    static int c1i, c1j, c2i, c2j;

    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, 1, 0, -1};

    static class Pos {
        int i, j, t, d;
        int got1, got2;

        Pos(int i, int j, int t, int d, int got1, int got2) {
            this.i = i;
            this.j = j;
            this.t = t;
            this.d = d;
            this.got1 = got1;
            this.got2 = got2;
        }
    }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        c1i = c2i = c1j = c2j = -1;
        map = new char[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                char c = line.charAt(j);
                map[i][j] = c;
                if (c == 'S') {
                    si = i;
                    sj = j;
                } else if (c == 'C') {
                    if (c1i == -1) {
                        c1i = i;
                        c1j = j;
                    } else {
                        c2i = i;
                        c2j = j;
                    }
                }
            }
        }

        go();
    }

    static void go() {
        Deque<Pos> q = new ArrayDeque<>();

        boolean[][][][][] v = new boolean[n][m][5][2][2];

        q.offer(new Pos(si, sj, 0, 4, 0, 0));
        v[si][sj][4][0][0] = true;

        while (!q.isEmpty()) {
            Pos now = q.poll();

            if (now.got1 == 1 && now.got2 == 1) {
                System.out.println(now.t);
                return;
            }

            for (int d = 0; d < 4; d++) {
                if (now.d != 4 && d == now.d) continue;

                int ni = now.i + di[d];
                int nj = now.j + dj[d];

                if (ni < 0 || nj < 0 || ni >= n || nj >= m) continue;
                if (map[ni][nj] == '#') continue;

                int ng1 = now.got1;
                int ng2 = now.got2;

                if (ni == c1i && nj == c1j) ng1 = 1;
                if (ni == c2i && nj == c2j) ng2 = 1;

                if (v[ni][nj][d][ng1][ng2]) continue;
                v[ni][nj][d][ng1][ng2] = true;
                q.offer(new Pos(ni, nj, now.t + 1, d, ng1, ng2));
            }
        }

        System.out.println(-1);
    }
}
