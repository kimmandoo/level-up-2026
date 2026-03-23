import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static char[][] map;

    static int si, sj;

    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];

        ArrayDeque<int[]> fire = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);

                if (map[i][j] == 'J') {
                    si = i;
                    sj = j;
                } else if (map[i][j] == 'F') {
                    fire.offer(new int[]{i, j});
                }
            }
        }

        int ans = go(fire);
        if (ans == -1) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(ans);
        }
    }

    static int go(ArrayDeque<int[]> fire) {
        int[][] fDist = new int[n][m];
        int[][] jDist = new int[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(fDist[i], -1);
            Arrays.fill(jDist[i], -1);
        }

        ArrayDeque<int[]> fq = new ArrayDeque<>(fire);

        for (int[] f : fire) {
            int i = f[0];
            int j = f[1];
            fDist[i][j] = 0;
            fq.offer(new int[]{i, j});
        }// 불 다 초기화
        
        while (!fq.isEmpty()) {
            int[] now = fq.poll();
            int ci = now[0];
            int cj = now[1];

            if (fDist[ci][cj] == -1) {
                fDist[ci][cj] = 0;
            }

            for (int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];

                if (ni < 0 || nj < 0 || ni >= n || nj >= m) continue;
                if (map[ni][nj] == '#') continue;
                if (fDist[ni][nj] != -1) continue; // 불이 번진 곳

                fDist[ni][nj] = fDist[ci][cj] + 1;
                fq.offer(new int[]{ni, nj});
            }
        }

        ArrayDeque<int[]> jq = new ArrayDeque<>();
        jq.offer(new int[]{si, sj});
        jDist[si][sj] = 0;

        while (!jq.isEmpty()) {
            int[] now = jq.poll();
            int ci = now[0];
            int cj = now[1];

            for (int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];
                int nt = jDist[ci][cj] + 1;

                // 범위 밖이면 탈출 성공
                if (ni < 0 || nj < 0 || ni >= n || nj >= m) {
                    return nt;
                }

                if (map[ni][nj] == '#') continue;
                if (jDist[ni][nj] != -1) continue;

                // 불이 먼저 오거나 동시에 오면 못 감
                if (fDist[ni][nj] != -1 && fDist[ni][nj] <= nt) continue;

                jDist[ni][nj] = nt;
                jq.offer(new int[]{ni, nj});
            }
        }

        return -1;
    }
}