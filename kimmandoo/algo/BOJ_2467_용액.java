import java.io.*;
import java.util.*;

public class Main {

    static int n, m, r;
    static StringBuilder sb = new StringBuilder();
    static long[] arr;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        // 0에 가깝게 만들어보자!
        arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        go();
    }

    public static void go() {
        int l = 0, r = n - 1;
        long best = Long.MAX_VALUE;
        long a = 0, b = 0;

        while (l < r) {
            long dist = arr[l] + arr[r];

            if (Math.abs(dist) < best) {
                best = Math.abs(dist);
                a = arr[l];
                b = arr[r];
            }

            if (dist < 0) {
                // 0에 가까워지려면 커져야됨
                l++;
            } else if (dist > 0) {
                r--;
            } else {
                break;
            }
        }

        System.out.println(a + " " + b);
    }
}