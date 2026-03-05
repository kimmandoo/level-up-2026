import java.io.*;
import java.util.*;

public class Main {

    static int n, m, k;
    static int[] money;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        money = new int[n];
        for (int i = 0; i < n; i++) {
            money[i] = Integer.parseInt(st.nextToken());
        }
        make();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }
//        System.out.println(Arrays.toString(p));
        go();
    }

    static int[] p;

    public static void make() {
        p = new int[n + 1]; // 0~n
        for (int i = 0; i <= n; i++) {
            p[i] = i; // 자기자신이 부모로 초기화
        }
    }

    public static int find(int a) {
        if (a == p[a]) return a;
        return p[a] = find(p[a]);
    }

    static HashMap<Integer, Integer> hs = new HashMap<>();

    public static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        // a랑 b의 부모를 찾고,
        if (pa != pb) {
            // 부모합치기
            if (money[pa-1] > money[pb-1]){
                p[pa] = pb;
            }else{
                p[pb] = pa;
            }
            return true;// 합쳤음
        }

        return false;
    }


    public static void go() {
        // 친구의 친구는 친구다
        // 같은 idx중 가장
        int sum = 0;
        HashSet<Integer> s = new HashSet<>();
        for (int i=1; i<=n; i++) {
            s.add(find(i));
        }
        for (int ss: s){
            sum += money[ss-1];
        }
        if (sum > k){
            System.out.println("Oh no");
        }else {
            System.out.println(sum);
        }
    }
}