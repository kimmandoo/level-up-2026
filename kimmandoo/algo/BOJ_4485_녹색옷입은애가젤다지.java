import java.io.*;
import java.util.*;

public class Main {

    static int n, m, r;
    static int[][] map;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int idx = 1;
        while (true) {
            n = Integer.parseInt(br.readLine());
            if (n == 0) break;
            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            // map 채움
            go(idx++);
        }
        System.out.println(sb);
    }

    static class Node {
        int i, j, c;

        Node(int i, int j, int c) {
            this.i = i;
            this.j = j;
            this.c = c;
        }
    }

    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, 1, 0, -1};

    public static void go(int idx) {
        // 최소비용으로 n-1, n-1까지 가야됨
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.c - o2.c; // 비용 낮은애를 먼저 빼기
        });
        int[][] dist = new int[n][n];
        int NO = 1000000001;
        for (int[] r : dist) {
            Arrays.fill(r, NO); // 도달할 수 없음
        }
        boolean[][] vis = new boolean[n][n];

        pq.add(new Node(0, 0, map[0][0]));

        dist[0][0] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (vis[now.i][now.j]) continue;
            vis[now.i][now.j] = true;

            for (int i = 0; i < 4; i++) {
                int ni = now.i + di[i];
                int nj = now.j + dj[i];
                if (ni >= n || nj >= n || ni < 0 || nj < 0) continue;

                int tmp = map[ni][nj] + now.c;
                if (tmp < dist[ni][nj]) {
                    dist[ni][nj] = tmp;
                    pq.offer(new Node(ni, nj, tmp));
                }
            }
        }
//        for (int[] r : dist) {
//            System.out.println(Arrays.toString(r));
//        }
        sb.append("Problem ").append(idx).append(": ").append(dist[n - 1][n - 1]).append("\n");
//        System.out.println(dist[n - 1][n - 1]);
    }
}