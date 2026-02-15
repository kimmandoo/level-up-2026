import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static StringBuilder sb = new StringBuilder();
    static Deque<int[]> dq;
    static PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));

    public static void main(String[] args) throws Exception {
//         System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int tc = Integer.parseInt(st.nextToken());

        for (int t = 0; t < tc; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            dq = new ArrayDeque<>();
            pq.clear();

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < n; i++) {
                int q = Integer.parseInt(st.nextToken());
                dq.offer(new int[]{q, i});
                pq.offer(q);
            }
            go();
        }
        System.out.println(sb);
    }

    public static void go() {
        int cnt = 1;
        while (!dq.isEmpty()) {
            int[] dqv = dq.poll();

            if (dqv[0] == pq.peek()) {
                pq.poll();

                if (dqv[1] == m) {
                    sb.append(cnt).append("\n");
                    return;
                }
                cnt++;
            } else {
                dq.offer(dqv);
            }
        }
    }
}