package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ2224명제증명 {
    static int N, M, len;
    static boolean[][] adjMatrix;
    static List<String> str;
    static PriorityQueue<String> pq;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        str = new ArrayList<>();

        len = 52;
        String input = null;
        adjMatrix = new boolean[len][len];

        for (int i = 0; i < N; i++) {
            input = br.readLine();
            str.add(input);
            int from = alphaToInt(input.charAt(0));
            int to = alphaToInt(input.charAt(5));

            adjMatrix[from][to] = true;
        }


        solve();
        System.out.println(pq.size());
        for (String s : pq) {
            System.out.println(s);
        }


    }

    private static void solve() {
        pq = new PriorityQueue<>();
        for (int k = 0; k < len; k++) {
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    if (adjMatrix[i][j]) continue;
                    if (adjMatrix[i][k] && adjMatrix[k][j]) adjMatrix[i][j] = true;
                }
            }
        }

        for(int i=0; i<len; i++){
            for(int j=0; j<len; j++){
                if(!adjMatrix[i][j] || i == j) continue;
                char from = intToAlpha(i);
                char to = intToAlpha(j);
                pq.add(from + " => " + to);
            }
        }
    }

    public static int alphaToInt(char c) {
        if (c >= 'A' && c <= 'Z') {
            return c - 65;
        } else if (c >= 'a' && c <= 'z') {
            return c - 'a' + 26;
        }
        return -1;
    }

    public static char intToAlpha(int i) {
        if (i < 26) return (char) (i + 65);
        return (char) (i - 26 + 'a');
    }
}