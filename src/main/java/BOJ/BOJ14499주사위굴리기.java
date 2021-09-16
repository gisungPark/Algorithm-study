package BOJ;

import java.util.*;
import java.io.*;

public class BOJ14499주사위굴리기 {


    static int M, N, r, c;
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};
    static int[] op;
    static int[][] map;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        op = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }

        solve();

    }

    private static void solve() {
        Dice d = new Dice();
        for (int cc : op) {
            int xx = r + dx[cc];
            int yy = c + dy[cc];
            if (xx < 0 || xx >= N || yy < 0 || yy >= M) continue;
            int val = d.move(cc, map[xx][yy]);
            if (map[xx][yy] != 0) map[xx][yy] = 0;
            else map[xx][yy] = val;

            r = xx;
            c = yy;
        }
    }

    public static class Dice {
        int[] dice = new int[7];

        public int move(int dir, int val) {

            int tmp = 0;
            if (dir == 1) { // 동쪽
                tmp = dice[3];
                dice[3] = dice[1];
                dice[1] = dice[4];
                dice[4] = dice[6];
                if (val != 0) dice[6] = val;
                else dice[6] = tmp;
            }
            if (dir == 2) { // 서쪽
                tmp = dice[4];
                dice[4] = dice[1];
                dice[1] = dice[3];
                dice[3] = dice[6];
                if (val != 0) dice[6] = val;
                else dice[6] = tmp;

            }
            if (dir == 3) { // 북쪽
                tmp = dice[2];
                dice[2] = dice[1];
                dice[1] = dice[5];
                dice[5] = dice[6];
                if (val != 0) dice[6] = val;
                else dice[6] = tmp;
            }
            if (dir == 4) { // 남쪽
                tmp = dice[5];
                dice[5] = dice[1];
                dice[1] = dice[2];
                dice[2] = dice[6];
                if (val != 0) dice[6] = val;
                else dice[6] = tmp;
            }

            System.out.println(dice[1]);
            return tmp;
        }

        public void print() {
            System.out.println("  " + dice[2] + "  ");
            System.out.println(dice[4] + " " + dice[1] + " " + dice[3]);
            System.out.println("  " + dice[5] + "  ");
            System.out.println("  " + dice[6] + "  ");
            System.out.println();
        }

    }
}
