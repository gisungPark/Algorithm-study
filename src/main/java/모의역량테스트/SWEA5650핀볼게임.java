package 모의역량테스트;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA5650핀볼게임 {

    static int T, N, ANS;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;
    static List<int[]>[] warm;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());

            map = new int[N][N];
            ANS = 0;

            // 웜홀 초기화
            warm = new ArrayList[11];
            for (int i = 6; i < 11; i++) {
                warm[i] = new ArrayList<>();
            }

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] > 5) {
                        warm[map[i][j]].add(new int[]{i, j}); // 웜홀 위치 저장
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] != 0) continue;

                    for (int d = 0; d < 4; d++) {
                        simulation(i, j, d);
                    }
                }
            }

            System.out.println("#" + tc + " " + ANS);
        }
    }

    private static void simulation(int sx, int sy, int dir) {
        int x = sx;
        int y = sy;
        int score = 0;

        while (true) {
            int xx = x + dx[dir];
            int yy = y + dy[dir];

            // 벽을 만난 경우
            if (xx < 0 || xx >= N || yy < 0 || yy >= N) {
                dir = opposite(dir); // 방향 전환
                score++;
                x = xx;
                y = yy;
                continue;
            }

            // 블랙홀을 만난 경우
            if (map[xx][yy] == -1 || (xx == sx && yy == sy)) {
                ANS = Math.max(ANS, score);
                return;
            }

            // 평지인 경우
            if (map[xx][yy] == 0) {
                x = xx;
                y = yy;
                continue;
            }

            // 웜홀을 만난 경우
            if (map[xx][yy] > 5) {
                for (int i = 0; i < 2; i++) {
                    int[] w = warm[map[xx][yy]].get(i);
                    if (w[0] == xx && w[1] == yy) continue;
                    x = w[0];
                    y = w[1];
                }
                continue;
            }

            // 도형을 만난 경우
            if (map[xx][yy] > 0 && map[xx][yy] < 6) {
                score++;
                dir = wall(xx, yy, dir);
                x = xx;
                y = yy;
            }
        }
    }


    public static int wall(int xx, int yy, int dir) {
        switch (map[xx][yy]) {
            case 1:
                if (dir == 0) return 1;
                if (dir == 1) return 3;
                if (dir == 2) return 0;
                if (dir == 3) return 2;
                break;
            case 2:
                if (dir == 0) return 3;
                if (dir == 1) return 0;
                if (dir == 2) return 1;
                if (dir == 3) return 2;
                break;
            case 3:
                if (dir == 0) return 2;
                if (dir == 1) return 0;
                if (dir == 2) return 3;
                if (dir == 3) return 1;
                break;
            case 4:
                if (dir == 0) return 1;
                if (dir == 1) return 2;
                if (dir == 2) return 3;
                if (dir == 3) return 0;
                break;

        }
        return opposite(dir);
    }

    // 반대방향 반환 함수
    public static int opposite(int dir) {
        if (dir == 0) return 1;
        if (dir == 1) return 0;
        if (dir == 2) return 3;
        return 2;
    }

}
