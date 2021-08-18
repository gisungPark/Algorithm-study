package BOJ;

import java.util.*;
import java.io.*;

public class BOJ16918봄버맨 {

    static int R, C, N, first, second;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] map;
    static Queue<Pix>[] queues;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new boolean[R][C];
        queues = new LinkedList[2];
        queues[0] = new LinkedList<>();
        queues[1] = new LinkedList<>();

        first = second = 0;
        for (int i = 0; i < R; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < input.length; j++) {
                if (input[j] == '.') continue;
                first++; // 초기 폭탄!
                map[i][j] = true;
                queues[0].add(new Pix(i, j));
            }
        }
        simulation();
        print();

    }

    private static void simulation() {

        for (int r = 0; r < N; r++) {
            second = first;
            first = 0;

            if (r % 2 == 0) {
                while (!queues[1].isEmpty()) {
                    Pix now = queues[1].poll();
                    if (!map[now.x][now.y]) continue;
                    map[now.x][now.y] = false;
                    for (int d = 0; d < 4; d++) {
                        int xx = now.x + dx[d];
                        int yy = now.y + dy[d];
                        if (xx < 0 || xx >= R || yy < 0 || yy >= C) continue;
                        map[xx][yy] = false; // 폭발!
                    }
                }
            } else {
                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++) {
                        if (map[i][j]) continue;
                        first++;
                        map[i][j] = true;
                        queues[0].add(new Pix(i, j));
                    }
                }
            }

            for (int i = 0; i < second; i++) {
                queues[1].add(queues[0].poll());
            }
            second = 0;
        }
    }

    public static void print() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j]) System.out.print("O");
                else System.out.print(".");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    public static class Pix {
        public int x, y;

        public Pix(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
