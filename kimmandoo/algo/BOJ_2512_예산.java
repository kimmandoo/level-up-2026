import java.io.*;
import java.util.*;

public class Main {

    static int[] input;
    static int n;
    static int tot;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        input = new int[n];
        for (int i=0; i<n; i++){
            input[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(input);
        tot = Integer.parseInt(br.readLine());
        go();
    }

    public static void go() {
        int l = 0;
        int r = input[n-1];
        long ans = 0;
        while (l <= r){
            int m = (l+r)/2;
            if (high(m)){
                ans = m;
                l = m+1;
            }else{
                r = m -1;
            }
        }
        System.out.println(ans);
    }

    public static boolean high(int m){
        long sum = 0;
        for (int i=0; i<n; i++){
            int t = input[i];
            if (t > m) sum += m;
            else{
                sum += input[i];
            }
            if (sum > tot) return false;
        }

        return sum <= tot;
    }
}