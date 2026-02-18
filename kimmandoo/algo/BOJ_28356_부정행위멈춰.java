import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/boj.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        go();
    }

    public static void go() {
        if (n == 1 && m == 1) {
            sb.append(1).append('\n');
            sb.append(1).append('\n');
            System.out.print(sb);
            return;
        }

        // 한 줄이면 대각선 인접이 사실상 없고, 좌우상하만 인접 -> 2종이면 충분
        if (n == 1 || m == 1) {
            sb.append(2).append('\n');
            if (n == 1) { // 1 x M
                for (int c = 0; c < m; c++) {
                    sb.append((c % 2) + 1);
                    if (c + 1 < m) sb.append(' ');
                }
                sb.append('\n');
            } else { // N x 1
                for (int r = 0; r < n; r++) {
                    sb.append((r % 2) + 1).append('\n');
                }
            }
            System.out.print(sb);
            return;
        }
        // 대각선이 있는 경우!
        sb.append(4).append('\n');
        for (int r = 0; r < n; r++) {
            int rb = r % 2;
            for (int c = 0; c < m; c++) {
                int cb = c % 2;

                int val;
                if (rb == 0 && cb == 0) val = 1;
                else if (rb == 0 && cb == 1) val = 2;
                else if (rb == 1 && cb == 0) val = 3;
                else val = 4;

                sb.append(val);
                if (c + 1 < m) sb.append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }
}