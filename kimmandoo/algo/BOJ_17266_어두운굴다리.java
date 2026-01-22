import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[] b;
    static int[] pos;
    static int[] road;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        pos = new int[m];
        for (int i = 0; i < m; i++) {
            pos[i] = Integer.parseInt(st.nextToken());
        }
        go();
    }

    public static void go() {
        int l = 0, r = n; // max 값은 길 길이 만큼
        int ans = n;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (check(mid)) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        System.out.println(ans);
    }

    public static boolean check(int mid) {
        int last = 0;

        for (int i = 0; i < m; i++) {
            if (pos[i] - mid > last) {
                return false;
            }
            last = pos[i] + mid;
        }

        return last >= n;
    }
}