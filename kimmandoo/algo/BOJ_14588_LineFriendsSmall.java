import java.util.*;
import java.io.*;

public class Main {
    static int n, q;
    static int[][] dist;
    static int INF = 1000000000;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int[][] friends;
    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dist = new int[n + 1][n + 1];
        for (int[] r : dist) {
            Arrays.fill(r, INF);
        }
        friends = new int[n+1][2];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            friends[i][0] = Integer.parseInt(st.nextToken()); // l
            friends[i][1] = Integer.parseInt(st.nextToken()); // r
        }
        // 친구관계확립해야됨
        q = Integer.parseInt(br.readLine());
        gogo();
        go();
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(dist[a][b]).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void gogo(){
        // 인접한 친구들만 일단 1로
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++){
                if (i == j) {
                    dist[i][j] = 0;
                    continue;
                }
                if(friends[i][1] < friends[j][0] || friends[i][0] > friends[j][1]) continue;
                dist[i][j] = 1; // 친구데스
            }
        }
//        for (int[] r : dist) {
//            System.out.println(Arrays.toString(r));
//        }
    }

    public static void go() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                if (dist[i][k] == INF) continue;
                for (int j = 1; j <= n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] == INF) dist[i][j] = -1;
            }
        }
//        System.out.println(sb.toString().trim());
    }
}