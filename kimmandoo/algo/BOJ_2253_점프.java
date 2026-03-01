import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[] dol;
    static int NO = 1000000;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dol = new int[n + 1];
        memo = new int[n + 1][150];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(memo[i], -1);
        }
        for (int i = 0; i < m; i++) {
            int stone = Integer.parseInt(br.readLine());
            dol[stone] = NO; // 못감
        }


        go(1, 1);
//        for (int[] d : memo) {
//            System.out.println(Arrays.toString(d));
//        }

        if (memo[1][1] >= NO){
            System.out.println(-1);
        }else{
            System.out.println(memo[1][1]);
        }
    }

    static int[][] memo; // idx부터 n까지 가는 데 추가로 필요한 점프 횟수

    public static int go(int idx, int hop) {
        if (hop > 150 || hop < 1) return NO;
        if (idx > n) return NO;
        if (memo[idx][hop] != -1) return memo[idx][hop];
        if (memo[idx][hop] == NO || dol[idx] == NO) return NO;
        if (idx == n) {
            // 도착쓰
            return 0;
        }
        if (idx == 1) {
            memo[idx][hop] = go(idx + 1, 1) + 1;
        } else {
            int res = Math.min(Math.min(go(idx + (hop - 1), hop - 1), go(idx + hop, hop)), go(idx + (hop + 1), hop + 1)) + 1;
            if (res >= NO) {
                memo[idx][hop] = NO;
            } else {
                memo[idx][hop] = res;
            }
        }
        return memo[idx][hop];
    }
}