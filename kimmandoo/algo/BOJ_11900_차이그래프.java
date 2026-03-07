import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int n, q;
    static int[] A;
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Node>[] graph;

    static class Node {
        int e, c;

        Node(int e, int c) {
            this.e = e;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/boj.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 정점 개수
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        A = new int[n + 1];
        for (int i = 1; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken()); // a[i];
        }
//        make();
        dist = new int[n];
        visited = new boolean[n];
        q = Integer.parseInt(br.readLine());
        go();
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken()); // a출발 b도착
            int target = (a - b + n) % n;
            int ans = dist[target];
            if (ans == NO) {
                sb.append("-1");
            } else {
                sb.append(ans);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void make() {
        // 가중치 생성 로직
        // 노섹시
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                if (u == v) continue;
                if (u > v) {
                    if (A[u - v] > 0) {
                        graph[u].add(new Node(v, A[u - v]));
                    }
                } else {
                    if (A[u - v + n] > 0) {
                        graph[u].add(new Node(v, A[u - v + n]));
                    }
                }
            }
        }
    }

    static PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
        return o1.c - o2.c; // cost가 작은 순으로 -> 최단 거리
    });
    static int[] dist;
    static boolean[] visited;
    static int NO = 100000000;
//    public static void go(int a, int b) {
//        // a에서 b까지의 최단거리
//        pq.clear();
//        Arrays.fill(visited, false);
//        Arrays.fill(dist, NO); // 도달 못할곳
//        pq.offer(new Node(a, 0));// a에서 a가는 건 공짜..
//        dist[a] = 0;
//        while (!pq.isEmpty()) {
//            Node now = pq.poll();
//            int u = now.e;
//            if (visited[u]) continue;
//            if (now.c > dist[u]) continue;
//            visited[u] = true;
//            if (u == b) break;
//
//            for (int v = 0; v < n; v++) {
//                if (v == u || visited[v]) continue;
//                int vc = NO;
//                if (u > v) {
//                    if (A[u - v] > 0) {
//                        vc = A[u - v];
//                    }
//                } else {
//                    if (A[u - v + n] > 0) {
//                        vc = A[u - v + n];
//                    }
//                }
//                if (vc == NO) continue;
//                int tmp = dist[now.e] + vc; // 지금꺼 오는 데 걸린 가중치 + 다음꺼 까중치
//                if (dist[v] > tmp) {
//                    dist[v] = tmp;
//                    //바꾸고
//                    pq.offer(new Node(v, tmp));
//                }
//            }
//        }
//        if (dist[b] == NO) {
//            dist[b] = -1;
//        }
//        System.out.println(dist[b]);
////        sb.append(dist[b]).append("\n");
////        System.out.println(Arrays.toString(dist));
//    }

    // 아예 다 구해두고?
    public static void go() {
        Arrays.fill(dist, NO);
        dist[0] = 0;
        pq.offer(new Node(0, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int u = now.e;
            if (now.c > dist[u]) continue;

            for (int v = 0; v < n; v++) {
                if (u == v) continue;
                int cost = A[(v - u + n) % n];
                if (cost == 0) continue;
                if (dist[v] > dist[u] + cost) {
                    dist[v] = dist[u] + cost;
                    pq.offer(new Node(v, dist[v]));
                }
            }
        }
    }
}