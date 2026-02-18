import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int xa, ya, xb, yb, xc, yc;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/boj.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        xa = Integer.parseInt(st.nextToken());
        ya = Integer.parseInt(st.nextToken());
        xb = Integer.parseInt(st.nextToken());
        yb = Integer.parseInt(st.nextToken());
        xc = Integer.parseInt(st.nextToken());
        yc = Integer.parseInt(st.nextToken());

        if (delta(xa, ya, xb, yb, xc, yc)) {
            System.out.println(-1.0);
            return;
        }

        go();
    }

    static boolean delta(int xa, int ya, int xb, int yb, int xc, int yc) {
        double left = (yb - ya) * (xc - xa);
        double right = (yc - ya) * (xb - xa);
        return left == right;
    }

    static double dist(int x1, int y1, int x2, int y2) {
        double dx = x1 - x2;
        double dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static void go() {
        double ab = dist(xa, ya, xb, yb);
        double bc = dist(xb, yb, xc, yc);
        double ac = dist(xa, ya, xc, yc);

        double p1 = 2.0 * (ab + ac);
        double p2 = 2.0 * (ab + bc);
        double p3 = 2.0 * (ac + bc);

        double mx = Math.max(p1, Math.max(p2, p3));
        double mn = Math.min(p1, Math.min(p2, p3));

        System.out.println(mx - mn);
    }
}