import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        PriorityQueue<Integer> mxHeap = new PriorityQueue<>((o1, o2) -> {
            return o2 - o1;
        });
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i=0; i<n; i++){
            int tmp = Integer.parseInt(br.readLine());
            if (minHeap.size() >= mxHeap.size()){
                // 크기 차이가 날때
                mxHeap.offer(tmp);
            }else {
                minHeap.offer(tmp);
            }
            if (minHeap.size() > 0 && mxHeap.peek() > minHeap.peek()){
                int tp = mxHeap.poll();
                int tp2 = minHeap.poll();
                mxHeap.offer(tp2);
                minHeap.offer(tp);
            }

            sb.append(mxHeap.peek()).append("\n");
        }
        System.out.println(sb);
    }

    public static void go() {

    }
}