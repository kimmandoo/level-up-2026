import java.io.*;
import java.util.*;

public class Main {

    static int n, m, r;
    static int[][] map;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 총 도시 개수
        m = Integer.parseInt(br.readLine()); // 여행계획
        StringTokenizer st = null;
        make();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                int link = Integer.parseInt(st.nextToken());
                if (link == 1) {
                    union(i, j);
                }
            }
        }
//        System.out.println(Arrays.toString(p));
        st = new StringTokenizer(br.readLine(), " ");
        int pr = find(Integer.parseInt(st.nextToken())-1);
//        System.out.println(pr);
        for (int i=1; i<m; i++){
            int c = Integer.parseInt(st.nextToken());
            if (pr != find(c-1)){
                System.out.println("NO");
                return;
            }
        }
        // 경로
        System.out.println("YES");
    }

    static int[] p;

    public static void make() {
        p = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            p[i] = i;
        }
    }

    public static int find(int a) {
        if (p[a] == a) return a;
        return p[a] = find(p[a]);
    }

    public static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) {
            // 같은 부모
            return;
        }
        p[pb] = pa;
    }

    public static void go() {

    }
}