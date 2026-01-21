import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, x;
    static int[] input;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/boj.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        input = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i=0; i<n; i++){
            input[i] = Integer.parseInt(st.nextToken());
        }
        go();
    }

    public static void go(){
        long sum = 0; // 25만 x 8000 200만*000 200억?

        for (int i = 0; i < x; i++) {
            sum += input[i];
        }
        // 구간 컷

        long mx = sum;
        int mxCnt = 1;

        for (int i = x; i < n; i++) {
            sum += input[i];      // 오른쪽 끝 추가
            sum -= input[i - x];  // 왼쪽 끝 제거 -> 일정 구간으로 유지 가능

            if (sum > mx) {
                mx = sum;
                mxCnt = 1;
            } else if (sum == mx) {
                mxCnt++;
            }
        }

        if (mx == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(mx);
            System.out.println(mxCnt);
        }
    }
}