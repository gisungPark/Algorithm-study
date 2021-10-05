package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20057마법사상어와토네이도 {

    static int N, x, y, num, ans = 0;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int[][][] tornado = {
            {{-2, 0}, {-1, -1}, {-1, 0}, {-1, 1}, {0, -2}, {1, -1}, {1, 0}, {1, 1}, {2, 0}, {0, -1}},
            {{0, -2}, {1, -1}, {0, -1}, {-1, -1}, {2, 0}, {1, 1}, {0, 1}, {-1, 1}, {0, 2}, {1, 0}},
            {{-2, 0}, {-1, 1}, {-1, 0}, {-1, -1}, {0, 2}, {1, 1}, {1, 0}, {1, -1}, {2, 0}, {0, 1}},
            {{0, 2}, {-1, 1}, {0, 1}, {1, 1}, {-2, 0}, {-1, -1}, {0, -1}, {1, -1}, {0, -2}, {-1, 0}}}; // 각 방향별 흩어지는 위치
    static int[] percent = {2, 10, 7, 1, 5, 10, 7, 1, 2}; // 각 위치 별 모래가 흩어지는 비율
    static int[][] map;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        simulation();
        System.out.println(ans);
    }

    private static void simulation() {
        x = y = N / 2;
        int dir = 0, dis = 1, disCnt = 0;
        loop:
        while (true) {

            for (int i = 0; i < dis; i++) {
                int xx = x + dx[dir];
                int yy = y + dy[dir];

                if (xx < 0 || xx >= N || yy < 0 || yy >= N) break loop;
                x = xx;
                y = yy;

                storm(dir);
            }
            if (++disCnt == 2) {
                disCnt = 0;
                dis++;
            }
            dir = ++dir % 4;
        }

    }

    private static void storm(int dir) {

        int send = 0;
        for (int i = 0; i < 9; i++) { //모래가 이동할 0방향
            int xx = x + tornado[dir][i][0];
            int yy = y + tornado[dir][i][1];

            if (xx < 0 || xx >= N || yy < 0 || yy >= N) { // 맵 범위를 벗어난 경우
                ans += map[x][y] * percent[i] / 100;
            } else {
                map[xx][yy] += map[x][y] * percent[i] / 100;
            }

            send += map[x][y] * percent[i] / 100;   // alpa를 제외한 지역으로 이동하는 모래 총량
        }

        int xx = x + tornado[dir][9][0];
        int yy = y + tornado[dir][9][1];

        if (xx < 0 || xx >= N || yy < 0 || yy >= N) { // 맵 범위를 벗어난 경우
            ans += (map[x][y] - send);
        } else {
            map[xx][yy] += (map[x][y]  - send);
        }
        map[x][y] = 0;
    }



}
