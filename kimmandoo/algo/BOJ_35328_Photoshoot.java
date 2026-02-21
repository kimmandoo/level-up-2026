import java.io.*;
import java.util.*;

public class Main {

    static int n, k, q;
    static int[][] ns;
    static int[][] prev;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken()); // nxn
        k = Integer.parseInt(st.nextToken()); // kxk
        q = Integer.parseInt(br.readLine());

        ns = new int[n + 1][n + 1];
        prev = new int[n + 1][n + 1];
        // 1-base...
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            go(r, c, v);
            calc(r, c);
        }
    }

    static int mx = -1;

    public static void calc(int r, int c) {
        // r, c 기준
        // +-k 다 조사하기
        int ie = Math.min(n, r + k - 1);
        int je = Math.min(n, c + k - 1);

        for (int i = r; i <= ie; i++) {
            for (int j = c; j <= je; j++) {
                if (i - k < 0 || j - k < 0) continue;
                int sum = prev[i][j] - prev[i - k][j] - prev[i][j - k] + prev[i - k][j - k];
                mx = Math.max(mx, sum);
            }
        }
        System.out.println(mx);
    }

    public static void go(int r, int c, int v) {
        ns[r][c] = v; // 소

        for (int i = r; i <= n; i++) {
            for (int j = c; j <= n; j++) {
                prev[i][j] = prev[i - 1][j] + prev[i][j - 1] - prev[i - 1][j - 1] + ns[i][j];
            }
        }

//        for (int i = 0; i <= n; i++) {
//            System.out.println(Arrays.toString(prev[i]));
//        }
    }
}
