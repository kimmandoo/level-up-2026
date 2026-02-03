import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt")); // 제출 시 주석
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        String a = st.nextToken();
        String b = st.nextToken();

        if (a.length() > b.length()) {
            String temp = a;
            a = b;
            b = temp;
        }

        int mn = Integer.MAX_VALUE;
        
        for (int i = 0; i <= b.length() - a.length(); i++) {
            int df = 0;
            for (int j = 0; j < a.length(); j++) {
                if (a.charAt(j) != b.charAt(i + j)) {
                    df++;
                }
            }
            mn = Math.min(mn, df);
        }

        System.out.println(mn);
    }
}