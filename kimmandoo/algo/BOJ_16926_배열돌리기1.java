import java.io.*;
import java.util.*;

public class Main {

    static int n, m, r;
    static int[][] map;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
//        System.out.println(Arrays.toString(p));
        go();
        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++){
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    public static void go() {
        int ring = Math.min(n, m) / 2;

        for (int k = 0; k < ring; k++) {
            ArrayList<Integer> list = new ArrayList<>(); // 얘가 각 링임.
            // 위쪽 변
            for (int j = k; j < m - k; j++) {
                list.add(map[k][j]);
            }
            // 오른쪽 변
            for (int i = k + 1; i < n - k; i++) {
                list.add(map[i][m - 1 - k]);
            }
            // 아래쪽 변
            for (int j = m - 2 - k; j >= k; j--) {
                list.add(map[n - 1 - k][j]);
            }
            // 왼쪽 변
            for (int i = n - 2 - k; i > k; i--) {
                list.add(map[i][k]);
            }

            int len = list.size(); // 링 전체가 들어있음
            int rr = r % len; // 링 길이로 나머지

            List<Integer> rotated = new ArrayList<>();
            for (int i = rr; i < len; i++) rotated.add(list.get(i)); // rr만큼 밀고 시작
            for (int i = 0; i < rr; i++) rotated.add(list.get(i));

            // 채우기...
            int idx = 0;
            for (int j = k; j < m - k; j++) {
                map[k][j] = rotated.get(idx++);
            }
            for (int i = k + 1; i < n - k; i++) {
                map[i][m - 1 - k] = rotated.get(idx++);
            }
            for (int j = m - 2 - k; j >= k; j--) {
                map[n - 1 - k][j] = rotated.get(idx++);
            }
            for (int i = n - 2 - k; i > k; i--) {
                map[i][k] = rotated.get(idx++);
            }
        }
    }
}