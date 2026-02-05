import java.io.*;
import java.util.*;

public class Main {

    static int n, m, k;
    static boolean[] ks;
    static StringBuilder sb = new StringBuilder();

    static class Log {
        int t, a, b;

        Log(int t, int a, int b) {
            this.t = t;
            this.a = a;
            this.b = b;
        }
    }

    static List<Log> lg = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken()); // n개의 컴퓨터
        m = Integer.parseInt(st.nextToken()); //
        k = Integer.parseInt(st.nextToken()); // 처음 1대가 k대를 감염시킴
        ks = new boolean[n+1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < k; i++) {
            ks[Integer.parseInt(st.nextToken())] = true;
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            lg.add(new Log(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        lg.sort(new Comparator<Log>() {
            @Override
            public int compare(Log o1, Log o2) {
                return o1.t - o2.t;
            }
        });

        go();
//        System.out.println(sb);
    }

    public static void go() {
        // 감염 시점을 찾아야됨
        for (int c = 1; c <= n; c++) {
            if (!ks[c]) continue;

            // 감염됬나봐야지..
            boolean[] inf = new boolean[n + 1];
            inf[c] = true;

            for (Log log : lg) {
                if (inf[log.a]) {
                    inf[log.b] = true;
                }
            }

            boolean same = true;
            for (int i = 1; i <= n; i++) {
                if (inf[i] != ks[i]) {
                    same = false;
                    break;
                }
            }
            if (same){
                System.out.println(c);
                return;
            }
        }
    }
}