import java.io.*;
import java.util.*;

public class Main {

    static int n, p, q;
    static StringBuilder sb = new StringBuilder();
    static int[] human;

    static class Node {
        int e, c;

        Node(int e, int c) {
            this.e = e;
            this.c = c;
        }
    }

    static ArrayList<Node>[] graph;
    static final int INF = 100_000_000;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            p = Integer.parseInt(st.nextToken());
            q = Integer.parseInt(st.nextToken());

            human = new int[n];
            for (int i = 0; i < n; i++) {
                human[i] = Integer.parseInt(br.readLine());
            }

            graph = new ArrayList[p + 1];
            for (int i = 0; i <= p; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < q; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                graph[a].add(new Node(b, d));
                graph[b].add(new Node(a, d));
            }

            go();
        }

        System.out.print(sb);
    }

    static void go() {
        int[][] dist = new int[p + 1][p + 1];

        for (int i = 1; i <= p; i++) {
            for (int j = 1; j <= p; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = INF;
                }
            }
        }

        for (int i = 1; i <= p; i++) {
            for (Node node : graph[i]) {
                dist[i][node.e] = Math.min(dist[i][node.e], node.c);
            }
        }// 플워는 무조건 직접거리만 담아두고 시작해야된다

        for (int k = 1; k <= p; k++) {
            // 중간 정점으로 1~k까지만 허용했을 때 답 탐색
            for (int i = 1; i <= p; i++) {
                if (dist[i][k] == INF) continue;
                for (int j = 1; j <= p; j++) {
                    // i,k기준으로 j를 보는거니까
                    if (dist[k][j] == INF) continue;
                    // 중간에 k를 거쳐 가는 게 더 짧으면 갱신
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        gogo(dist);
    }

    static void gogo(int[][] dist) {
        long mn = Long.MAX_VALUE;
        int res = -1;

        for (int i = 1; i <= p; i++) {
            long cost = 0;
            boolean possible = true;

            for (int h : human) {
                if (dist[h][i] == INF) {
                    // 못가
                    possible = false;
                    break;
                }
                cost += (long) dist[h][i] * dist[h][i];
            }

            if (possible && cost < mn) {
                res = i;
                mn = cost;
            }
        }

        sb.append(res).append(" ").append(mn).append("\n");
    }
}