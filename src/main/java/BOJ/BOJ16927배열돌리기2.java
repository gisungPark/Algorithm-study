package BOJ;

import java.util.*;
import java.io.*;

public class BOJ16927배열돌리기2 {

    static int N, M, R;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[][] map;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[300][300];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();

    }

    private static void solve() {
        rotation();
        print();
    }

    private static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void rotation() {

        int layer = Math.min(N, M) / 2;
        for (int d = 0; d < layer; d++) {

            int r = R % ((N - d * 2) * 2 + (M - d * 2) * 2 - 4);
            while (r-- > 0) {
                int sr = d;
                int sc = d;
                int start = map[sr][sc];


                for (int i = sc; i < M - sc - 1; i++)
                    map[sr][i] = map[sr][i + 1];

                for (int i = sr; i < N - sr - 1; i++)
                    map[i][M - sc - 1] = map[i + 1][M - sc - 1];

                for (int i = M - sc - 1; i > sc; i--)
                    map[N - sr - 1][i] = map[N - sr - 1][i - 1];

                for (int i = N - sr - 1; i > sr; i--)
                    map[i][sc] = map[i - 1][sc];

                map[sr + 1][sc] = start;
            }
        }
    }
}