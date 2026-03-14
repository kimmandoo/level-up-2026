import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int n, m, l, k;
    static StringBuilder sb = new StringBuilder();
    static int[] xp;
    static int[] yp;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/boj.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        xp = new int[k];
        yp = new int[k];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            xp[i] = x;
            yp[i] = y;
        }
        go();
//        System.out.println(sb);
    }

    public static void go() {
        int mx = 0;

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                // 좌표 완탐
                int startX = xp[i];
                int startY = yp[j];

                int cnt = 0;
                for (int t = 0; t < k; t++) {
                    boolean xc = startX <= xp[t] && xp[t] <= startX + l;
                    boolean yc = startY <= yp[t] && yp[t] <= startY + l;
                    if (xc && yc) {
                        cnt++;
                    }
                }

                mx = Math.max(mx, cnt);
            }
        }

        System.out.println(k - mx);
    }
}