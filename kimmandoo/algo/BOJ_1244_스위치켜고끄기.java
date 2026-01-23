import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[] s;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        s = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            go(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < n; i++) {
            sb.append(s[i]).append(" ");
            if ((i + 1) % 20 == 0) {
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    public static void go(int gender, int t) {
        if (gender == 1) {
            for (int i = t - 1; i < n; i += t) {
                s[i] = Math.abs(s[i] - 1);
            }
        } else { // 여자
            // t를 기준으로 양쪽을 하나씩 늘리면서 본다
            int ts = t - 1;
            s[ts] = Math.abs(s[ts]-1);
            int len = 1;
            while (ts + len < n && ts - len >= 0){
                // 범위 안일 때
                if(s[ts+len] != s[ts-len]) break;
                s[ts+len] = Math.abs(s[ts+len]-1);
                s[ts-len] = Math.abs(s[ts-len]-1);
                len++;
            }
        }
    }
}