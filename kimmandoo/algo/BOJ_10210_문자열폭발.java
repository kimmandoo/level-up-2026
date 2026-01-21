import java.io.*;
import java.util.*;

public class Main {

    static String target, boom;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        target = br.readLine();
        boom = br.readLine();
        go();
    }

    public static void go() {
        // 문자열 넣어두기
        StringBuilder sb = new StringBuilder();
        int bSize = boom.length();
        for(char c: target.toCharArray()){
            sb.append(c);
            if (sb.length() >= bSize && sb.substring(sb.length()-bSize).equals(boom)){
                sb.replace(sb.length()-bSize,sb.length(),"");
            }
        }
        if (sb.length() == 0){
            System.out.println("FRULA");
            return;
        }
        System.out.println(sb);
    }
}