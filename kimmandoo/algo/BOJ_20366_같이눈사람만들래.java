import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] snow;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        snow = new int[n]; // 눈덩이 지름
        for (int i = 0; i < n; i++) {
            snow[i] = Integer.parseInt(st.nextToken());
        }

        go();
//        System.out.println(sb);
    }

    public static void go() {
        // 2,2 두개 골랐을 때, 두 그룹의 지름 차이가 가장 작은 걸 고르기
        int mn = Integer.MAX_VALUE;

        // i와 j로 첫 번째 눈사람을 고정
        for (int i = 0; i < n; i++) {
            for (int j = i + 3; j < n; j++) {
                int snowman1 = snow[i] + snow[j];

                int l = i + 1;
                int r = j - 1;

                while (l < r) {
                    int snowman2 = snow[l] + snow[r];
                    int diff = snowman1 - snowman2;

                    mn = Math.min(mn, Math.abs(diff));

                    if (diff == 0) {
                        System.out.println(0);
                        return;
                    }

                    // 첫 번째 눈사람이 더 크면 두 번째 눈사람을 더 키워야 함
                    if (diff > 0) {
                        l++;
                    } else {
                        // 두 번째 눈사람이 더 크면 두 번째 눈사람을 줄여야 함
                        r--;
                    }
                }
            }
        }
        System.out.println(mn);
    }
}