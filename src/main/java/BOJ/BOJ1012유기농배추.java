package BOJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ1012유기농배추 {

    static int[][] map;
    static int T, M, N, K;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        for(int tc = 1; tc<=T; tc++){
            M = sc.nextInt();
            N = sc.nextInt();
            K = sc.nextInt();
            map = new int[N][M];

            // 1. 맵 초기화
            for(int i=0; i<K; i++){
                int x = sc.nextInt();
                int y = sc.nextInt();
                map[y][x] = 1;
            }

            System.out.println(process());
        }

    }

    private static int process() {
        int ans = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] == 1) {
                    ans++;
                    BFS(i,j);
                }
            }
        }
        return ans;
    }

    private static void BFS(int i, int j) {
        Queue<int[] > q = new LinkedList<>();
        q.add(new int[]{i, j});

        while(!q.isEmpty()){
            int[] peek = q.poll();
            for(int d=0; d<4; d++){
                int xx = peek[0] + dx[d];
                int yy = peek[1] + dy[d];
                if(xx<0 || xx >= N || yy<0 || yy >= M
                        || map[xx][yy] != 1) continue;
                map[xx][yy] = 0;
                q.add(new int[]{xx, yy});
            }
        }

    }
}
