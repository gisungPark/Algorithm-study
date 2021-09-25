package BOJ;

import java.util.*;
import java.io.*;

public class BOJ14503로봇청소기 {

    static int N, M, ans;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    static boolean[][] visited;
    static Robot robot;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());
        robot = new Robot(x, y, dir);

        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = 0;
        solve();
        System.out.println(ans);

    }

    private static void solve() {


        while (true) {

            if (robot.nowCheck()) continue;     // 현재 칸이 청소 되었는지 확인
            print();
            if (robot.move()) {
                int back = robot.getBack();     // 후진할 방향 확인
                int xx = robot.r + dx[back];    // 한칸 후진한 값
                int yy = robot.c + dy[back];

                if (xx > 0 || xx <= N || yy > 0 || yy >= M
                        || map[xx][yy] == 1) break;

                robot.r = xx;
                robot.c = yy;
                robot.cnt = 0;


            }

        }

    }

    public static void print(){
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(visited[i][j]) System.out.print("1 ");
                else System.out.print("0 ");
            }
            System.out.println();
        }
        System.out.println("로봇 위치 ("+ robot.r+", "+ robot.c+") 방향" + robot.dir);
        System.out.println();
    }

    public static class Robot {
        int r, c, dir, cnt;

        public Robot(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cnt = 0;
        }

        public int getLeft() {
            int next = -1;
            if (this.dir == 0) next = 3;
            if (this.dir == 1) next = 0;
            if (this.dir == 2) next = 1;
            if (this.dir == 3) next = 2;
            return next;
        }

        public int getBack() {
            int back = -1;
            if (this.dir == 0) back = 2;
            if (this.dir == 1) back = 3;
            if (this.dir == 2) back = 0;
            if (this.dir == 3) back = 1;

            return back;
        }

        public boolean leftCheck() {
            int left = getLeft();

            int xx = this.r + dx[left];
            int yy = this.c + dy[left];
            if (xx < 0 || xx >= N || yy < 0 || yy >= M ||
                    map[xx][yy] == 1 || visited[xx][yy]) return false;

            return true;
        }

        public boolean nowCheck() {
            if (!visited[this.r][this.c]) {
                visited[this.r][this.c] = true;
                ans++;
                return true;
            }
            return false;
        }

        public boolean move() {
            // 1. 왼쪽 칸으로 이동 가능한지 확인
            if (!leftCheck()) {
                this.cnt++;
                this.dir = getLeft();   // 2. 이동이 불가능하다면 방향만 회전
            } else {
                int left = getLeft();
                int xx = this.r + dx[left];
                int yy = this.c + dy[left];
                this.r = xx;
                this.c = yy;
                this.dir = left;
                this.cnt = 0;
            }

            if (this.cnt == 4) return true;
            else return false;
        }


    }
}
