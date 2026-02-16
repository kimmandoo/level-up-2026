import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, k;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/boj.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        go();
    }

    static class Pos{
        int p, t;
        Pos(int p, int t){
            this.p = p;
            this.t = t;
        }
    }
    static final int MAX = 100000;
    public static void go() {
        if (n == k) {
            System.out.println(0);
            return;
        }
        // 수빈이는 +1, -1, +2*pos다
        ArrayDeque<Pos> q = new ArrayDeque<>();
        boolean[] vis = new boolean[MAX + 1];

        q.offer(new Pos(n, 0));
        vis[n] = true; // 방문처리

        while (!q.isEmpty()) {
            Pos cur = q.poll();

            int np;

            // -1
            np = cur.p - 1;
            if (np >= 0 && !vis[np]) { // 가는 곳이 방문을 안했을 때만 이동.
                if (np == k) { System.out.println(cur.t + 1); return; }
                vis[np] = true;
                q.offer(new Pos(np, cur.t + 1));
            }

            // +1
            np = cur.p + 1;
            if (np <= MAX && !vis[np]) {
                if (np == k) { System.out.println(cur.t + 1); return; }
                vis[np] = true;
                q.offer(new Pos(np, cur.t + 1));
            }

            // *2
            np = cur.p * 2;
            if (np <= MAX && !vis[np]) {
                if (np == k) { System.out.println(cur.t + 1); return; }
                vis[np] = true;
                q.offer(new Pos(np, cur.t + 1));
            }
        }
    }
}