import java.io.*;
import java.util.*;

public class Main {

    static int[][] input;
    static int n, k;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        input = new int[n][k];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < k; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        go();

    }


    public static void go() {
        int[][] dp = new int[n][3]; // 들고 다녀야 될 값들이 3개 - 최대값, 그 idx, 그 다음 최대값
        for (int i = 0; i < k; i++) {
            int cur = input[0][i];
            if (dp[0][0] < cur) {
                dp[0][1] = dp[0][0];
                dp[0][0] = cur;
                dp[0][2] = i;
            }else if (dp[0][1] < cur) { // 2등맨..
                dp[0][1] = cur;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < k; j++) {
                int cur = input[i][j];
                // 이번 고양이가 지금 목걸이를 차면 얻게 될 점수?
                if (j == dp[i-1][2]) {
                    cur += dp[i-1][1]; // 2등 점수 더하기
                } else {
                    cur += dp[i-1][0]; // 1등 점수 더하기
                }

                // check
                if (dp[i][0] < cur) {
                    dp[i][1] = dp[i][0];
                    dp[i][0] = cur;
                    dp[i][2] = j;
                }else if (dp[i][1] < cur) {
                    dp[i][1] = cur;
                }
            }
        }

//        for (int[] d: dp){
//            System.out.println(Arrays.toString(d));
//        }
        System.out.println(dp[n-1][0]);
    }
}