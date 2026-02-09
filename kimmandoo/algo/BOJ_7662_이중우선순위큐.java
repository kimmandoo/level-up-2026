import java.io.*;
import java.util.*;

public class Main {

    static int k;
    static StringBuilder sb = new StringBuilder();
    // 지리네..
    // 또는 static PriorityQueue<Integer> mx = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
    static PriorityQueue<Integer> mx = new PriorityQueue<>(Collections.reverseOrder());
    static PriorityQueue<Integer> mn = new PriorityQueue<>();
//    static HashMap<Integer,Integer> mxdel;
//    static HashMap<Integer,Integer> mndel;
    static HashMap<Integer, Integer> cnt;
    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        while (tc-- > 0){
            mx.clear();
            mn.clear();
            cnt = new HashMap<>();
            k = Integer.parseInt(br.readLine());
            for (int i=0; i<k; i++){
                st = new StringTokenizer(br.readLine(), " ");
                String cmd = st.nextToken(); // I or D
                int n = Integer.parseInt(st.nextToken());
                if (cmd.equals("D")){
                    del(n);
                }else{
                    // ins
                    ins(n);
                }
            }
            process();
        }
        System.out.println(sb);
    }

    public static void process(){
        // mx 정리
        while (!mx.isEmpty() && cnt.getOrDefault(mx.peek(), 0) == 0) {
            mx.poll();
        }
        // mn 정리
        while (!mn.isEmpty() && cnt.getOrDefault(mn.peek(), 0) == 0) {
            mn.poll();
        }

        if (cnt.isEmpty()) {
            sb.append("EMPTY").append("\n");
        } else {
            sb.append(mx.poll()).append(" ").append(mn.poll()).append("\n");
        }
    }

    public static void del(int cmd){
        PriorityQueue<Integer> target;

        if (cmd == 1) {
            target = mx;
        } else {
            target = mn;
        }

        // 큐의 맨 위에 있는 놈이 이미 삭제됨? -> 그냥 버리기
        while (!target.isEmpty()) {
            int top = target.peek();
            if (cnt.getOrDefault(top, 0) > 0) {
                break;
            }
            target.poll();
        }

        // 이제 진짜 살아있는 놈 삭제
        if (!target.isEmpty()) {
            int tmp = target.poll();
            cnt.put(tmp, cnt.get(tmp) - 1);
            if (cnt.get(tmp) == 0) {
                cnt.remove(tmp);
            }
        }
    }

    public static void ins(int t) {
        mx.offer(t);
        mn.offer(t);
        cnt.put(t, cnt.getOrDefault(t, 0) + 1);
    }
}