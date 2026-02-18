import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/boj.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        arr = new int[n + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr[n] = -1;

        go();
    }

    static Deque<Integer> dq = new ArrayDeque<>();

    public static void go() {
        int[] ans = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            int cur = arr[i];

            // cur보다 작거나 같은 애들은 오큰수가 될 수 없음
            while (!dq.isEmpty() && dq.peek() <= cur) {
                dq.pop();
            }

            if (dq.isEmpty()) ans[i] = -1;
            else {
                ans[i] = dq.peek();
            }

            dq.push(cur);
        }

        for (int i = 0; i < n; i++) {
            sb.append(ans[i]).append(' ');
        }
        System.out.println(sb);
    }
}