import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        // stan은 1~10사이 수 생각하고 있음
        PriorityQueue<Integer> mx = new PriorityQueue<>(((o1, o2) -> {
            return o2 - o1;
        }));

        PriorityQueue<Integer> mn = new PriorityQueue<>();

        while (true) {
            int olly = Integer.parseInt(br.readLine());
            if (olly == 0) break;
            String stan = br.readLine();
            if (stan.equals("right on")) {
                // 거짓말 판별이 필요함
                // 생각한 수가 mn peek, mx peek이랑 상이할때
                boolean isDishonest = false;

                // mx보단 olly가 커야 함
                if (!mx.isEmpty() && mx.peek() >= olly) isDishonest = true;
                // mn 보다 olly가 작아야 함
                if (!mn.isEmpty() && mn.peek() <= olly) isDishonest = true;

                if (isDishonest) {
                    sb.append("Stan is dishonest").append("\n");
                } else {
                    sb.append("Stan may be honest").append("\n");
                }
                mx.clear();
                mn.clear();
            } else {
                // k보다 작은 경우
                if (stan.equals("too low")){
                    mx.offer(olly);
                }else{
                // k보다 큰 경우
                    mn.offer(olly);
                }
            }
        }
        System.out.println(sb);
    }

    public static void go(int olly) {

    }
}