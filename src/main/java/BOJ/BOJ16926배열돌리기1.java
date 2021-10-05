package BOJ;

import java.io.*;
import java.util.*;

public class BOJ16926배열돌리기1 {

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

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();

    }

    private static void solve() {
        while (R-- > 0) rotation(0, 0, N, M);
        print();
    }

    private static void rotation(int r, int c, int rowSize, int colSize) {

        int layer = Math.min(rowSize, colSize) / 2;

        for (int i = 0; i < layer; i++) {
            int sr = r + i;
            int sc = c + i;
            int start = map[sr][sc];

            int dir = 0;
            while (dir < 4) {
                int xx = sr + dx[dir];
                int yy = sc + dy[dir];

                if (xx < r + i || xx >= N - (r + i) || yy < c + i || yy >= M - (c + i)) {
                    dir++;
                } else {
                    map[sr][sc] = map[xx][yy];
                    sr = xx;
                    sc = yy;
                }
            }
            map[r + i + 1][c + i] = start;
        }

    }


    public static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}

