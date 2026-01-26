import java.io.*;
import java.util.*;

public class Main {

    static int n, q;
    static int[] pos;
    static long[] pre;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken()); // 나무
        q = Integer.parseInt(st.nextToken()); // 사진 q번찍음
        pos = new int[n+1]; // 1-based
        st = new StringTokenizer(br.readLine(), " ");
        for (int i=1; i<=n; i++){
            pos[i] = Integer.parseInt(st.nextToken()); // 나무 위치
        }
        // 나무가 정렬되어있는지? -> 그건 아닌것같음
        Arrays.sort(pos); // 밑에서 이분탐색 아이디어
        pre = new long[n+1];
        for (int i = 1; i <= n; i++) {
            pre[i] = pre[i - 1] + pos[i];
        }
        for (int i=0; i<q; i++){
            int x = Integer.parseInt(br.readLine());
            go(x); // 점수매기기
        }
        System.out.println(sb);
    }

    public static void go(int x) {
        // 사진의 점수는 사진을 찍은 위치로부터 각 나무까지의 거리 합
        // sol1-naive
//        long sum = 0;
//        for(int i=1; i<=n; i++){
//            sum += Math.abs(x-pos[i]);
//        }
//        sb.append(sum).append("\n");
        // 펑 - 매번 합을 구하면 n,q가 커졌을 때 답이 없다.
        // 합 횟수가 문제니까 누적합을 써보자
//        System.out.println(Arrays.toString(pre));
        // 그러면 pre[j] 에는 j보다 왼쪽에 있는 나무들의 누적합이 있을 것
        // 그러면.. j까지의 누적합 + (n까지의 누적합 - j까지 누적합) 처리하면 됨
        int l = 1, r = n;
        int idx = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (pos[mid] <= x) {
                idx = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        long left = ((long) x * idx) - pre[idx];
        long right = Math.abs(((long) x * (n - idx)) - (pre[n] - pre[idx]));
        sb.append(left+right).append("\n");
    }
}