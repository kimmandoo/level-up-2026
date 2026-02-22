import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int[] b = new int[w];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < w; i++) b[i] = Integer.parseInt(st.nextToken());

        Deque<Integer> dq = new ArrayDeque<>(); // 인덱스
        int sum = 0;

        for (int i = 0; i < w; i++) {
            // 오른쪽 벽이 생겨서 골짜기가 닫히는 동안 반복 처리
            while (!dq.isEmpty() && b[i] > b[dq.peek()]) {
                int bottom = dq.pop(); // 바닥
                if (dq.isEmpty()) break; // 왼쪽 벽이 없으면 물 못 고임

                int left = dq.peek();           
                int width = i - left - 1;
                int height = Math.min(b[left], b[i]) - b[bottom];

                if (height > 0) sum += width * height;
            }
            dq.push(i);
        }

        System.out.println(sum);
    }
}