import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static char[][] b;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        b = new char[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                b[i][j] = line.charAt(j);
            }
        }

        go(0, 0, 1);
        // 최대길이?
        System.out.println(mx);
    }

    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, 1, 0, -1};
    static int mx = -1;
    static HashSet<Character> hs = new HashSet<>(); // 여기 들어가있으면 안됨

    public static void go(int ci, int cj, int cnt) {
        hs.add(b[ci][cj]);
        int find = 0;
        for (int d = 0; d < 4; d++) {
            int ni = ci + di[d];
            int nj = cj + dj[d];
            if (ni >= n || nj >= m || ni < 0 || nj < 0 || hs.contains(b[ni][nj])) continue;
            find++;
            go(ni, nj, cnt+1);
            hs.remove(b[ni][nj]); // 원복
        }
        if (find == 0){
            mx = Math.max(mx, cnt);
        }
        return;
    }
}