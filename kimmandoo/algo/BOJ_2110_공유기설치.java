import java.io.*;
import java.util.*;

public class Main {

    static int n, c;
    static int[] home;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        home = new int[n];
        for (int i = 0; i < n; i++) {
            home[i] = Integer.parseInt(br.readLine());
        }
        go();
//        System.out.println();
    }


    public static void go() {
        Arrays.sort(home);
        // 공유기 간격을 최대한 멀리
        int l = 0;
        int r = 1_000_000_000;
        int res = 0;
        while (l <= r) {
            int m = (l + r) / 2;
            if (check(m)) {
                res = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        System.out.println(res);
    }

    public static boolean check(int m) {
        int prev = home[0];
        int cnt = 1;
        for (int i = 1; i < n; i++) {
            if (prev + m <= home[i]) {
                cnt++;
                prev = home[i];
            }
            if (cnt >= c) return true;
        }

        return false;
    }
}