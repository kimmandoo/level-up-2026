import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static StringBuilder sb = new StringBuilder();
    static int[] tower;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/boj.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        tower = new int[n];
        for (int i = 0; i < n; i++) {
            tower[i] = Integer.parseInt(st.nextToken());
        }

        go();
    }

    static class Pos {
        int h, idx;

        Pos(int h, int idx) {
            this.h = h;
            this.idx = idx;
        }
    }

    public static void go() {
        // 레이저가 왼쪽으로만 나감
//        int[] out = new int[n];
//        for (int i=0; i<n; i ㅌ++){
//            for (int j=i-1; j>0; j--){
//                if (tower[j] >= tower[i]){
//                    out[i] = j+1;
//                    break;
//                }
//            }
//        }
        int[] out = new int[n];
        ArrayDeque<Pos> dq = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            int h = tower[i];

            // 나보다 낮은 탑은 전부 제거 - 얘가 핵심
            while (!dq.isEmpty() && dq.peek().h < h) {
                dq.pop();
            }

            if (dq.isEmpty()){
                out[i] = 0;
            }else {
                out[i] = dq.peek().idx + 1;
            }
            dq.push(new Pos(h, i)); // 얘도 후보가 될 수 있다
        }
        for (int i : out) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
}