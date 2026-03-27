import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] node;
    static StringTokenizer st = null;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        node = new int[n + 1]; // 1base
        make();
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= n; j++) {
                int c = Integer.parseInt(st.nextToken());
                if (c == 0) {
                    union(i, j);
                }
            }
        }
        // 안정여부
        int[] cnt = new int[n + 1];
        for (int i : p) {
            if (i == 0) continue;
            cnt[i]++;
        }
        int gcnt = 0;
        for (int i : cnt) {
            if (i == 1) {
                System.out.println(0);
                return;
            }
            if (i > 1) {
                gcnt++;
            }
        }
        System.out.println(gcnt);
        // 소집단 사람 여부
        StringBuilder sb = new StringBuilder();
        HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            hm.putIfAbsent(p[i], new ArrayList<>());
        }
        for (int i = 1; i <= n; i++) {
            hm.get(p[i]).add(i);
        }
        for (ArrayList<Integer> arr : hm.values()) {
            for (int a: arr){
                sb.append(a).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int[] p;

    public static void make() {
        p = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            p[i] = i;
        }
    }

    public static int find(int a) {
        if (a == p[a]) return a;
        return p[a] = find(p[a]);
    }

    public static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) return;

        p[pb] = pa; // pb의 부모를 pa와 동일하게
    }
}