import java.io.*;
import java.util.*;

public class Main {

    //    static int n, m;
//    static char[][] b;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int tc = Integer.parseInt(br.readLine());
        for (int i = 0; i < tc; i++) {
            String line = br.readLine();
            int k = Integer.parseInt(br.readLine());
            if (k == 1) {
                sb.append("1 1").append("\n");
                continue;
            }
            go(line, k);
        }
        System.out.println(sb);
    }

    static int mn, mx;

    public static void go(String line, int k) {
        ArrayList<Integer>[] abc = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            abc[i] = new ArrayList<>();
        }

        for (int i = 0; i < line.length(); i++) {
            abc[line.charAt(i) - 'a'].add(i);
        }

        int mn = Integer.MAX_VALUE;
        int mx = -1;

        for (int i = 0; i < 26; i++) {
            if (abc[i].size() < k) continue;

            for (int j = 0; j <= abc[i].size() - k; j++) {
                int s = abc[i].get(j);
                int e = abc[i].get(j + k - 1); // k번째 뒤에 나온다...
                int tmp = e - s + 1;

                mn = Math.min(mn, tmp);
                mx = Math.max(mx, tmp);
            }
        }

        if (mx == -1) {
            sb.append("-1").append("\n");
        } else {
            sb.append(mn).append(" ").append(mx).append("\n");
        }
    }
}