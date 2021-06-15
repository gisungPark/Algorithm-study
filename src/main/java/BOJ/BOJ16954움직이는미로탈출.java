package BOJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ16954움직이는미로탈출 {

    static int N, wallCnt, ans;
    static int dx[] = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
    static int dy[] = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
    static char[][] map;
    static boolean[][] walls;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = 8;
        map = new char[N][];
        walls = new boolean[N][N];
        Queue<po> user = new LinkedList<>();

        for(int i=0; i<N; i++){
            map[i] = sc.nextLine().toCharArray();
            for(int j=0; j<N; j++){
                if(map[i][j] == '#') {
                    wallCnt++;
                    walls[i][j] = true;
                }
            }
        }
        ans = 0;
        user.add(new po(7,0));

        int round = 0;
        loop: while(!user.isEmpty()){
            round++;
            // 1. 사람 이동
            if(userMove(user) || wallCnt == 0 || round > 8) {
                ans = 1;
                break;
            }

            // 2. 벽 이동
            wallCnt = moveWall();

        }
        System.out.println(ans);
    }

    private static boolean userMove(Queue<po> user) {
        int len = user.size();

        for(int j=0; j<len; j++){
            po u = user.poll();

            if(walls[u.x][u.y]) continue;

            // 1-1. 9방향 탐색
            for (int i = 0; i < 9; i++) {
                int xx = u.x + dx[i];
                int yy = u.y + dy[i];

                // 1-2. 사람이 이동 불가능한 지점
                if (xx < 0 || xx >= N || yy < 0 || yy >= N
                        || walls[xx][yy]) continue;

                if (xx == 0 && yy == N - 1) return true;
                user.add(new po(xx, yy));
            }
        }
        return false;
    }

    private static int moveWall() {
        int wallCnt = 0;
        for(int i=N-1; i>0; i--){
            for(int j=0; j<N; j++){
                if(walls[i-1][j]) wallCnt++;
                walls[i][j] = walls[i-1][j];
            }
        }
        for(int i=0; i<N; i++){
            walls[0][i] = false;
        }
        return wallCnt;
    }

    public static class po{
        int x;
        int y;

        public po(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
