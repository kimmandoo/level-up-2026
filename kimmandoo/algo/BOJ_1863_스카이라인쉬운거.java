import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static final int INF = 100000001;

    public static void main(String[] args) throws Exception {
         System.setIn(new FileInputStream("res/boj.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int pos = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            while (!stack.isEmpty() && stack.peek() > h) {
                // 단조스택 스끼리
                stack.pop();
                cnt++;
            }

            if (h == 0) continue;

            if (stack.isEmpty() || stack.peek() < h) {
                stack.push(h);
            }
        }
        while (!stack.isEmpty()){
            stack.pop();
            cnt++;
        }
        System.out.println(cnt);
    }
}