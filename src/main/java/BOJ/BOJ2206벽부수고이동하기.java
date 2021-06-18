package BOJ;

import java.io.*;
import java.util.*;

public class BOJ2206벽부수고이동하기 {

    static int N, M;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static char[][] map;
    static int[][][] dis;
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][];
        dis = new int[2][N][M];
        for(int i=0; i<N; i++){
            map[i] = br.readLine().toCharArray();
        }

        Queue<obj> q = new LinkedList<>();
        dis[0][0][0] = 1;
        q.add(new obj(0, 0 , false));

        loop: while(!q.isEmpty()){
            obj cur = q.poll();

            // 2. 사방 탐색
            for(int i=0; i<4; i++){
                int xx = cur.x + dx[i];
                int yy = cur.y + dy[i];

                if(xx < 0 || xx >= N || yy < 0 || yy >= M) continue;
                if(!cur.flag && dis[0][xx][yy] != 0) continue;
                if(cur.flag && dis[1][xx][yy] != 0) continue;

                if(map[xx][yy] == '1'){
                    if(cur.flag) continue;
                    q.add(new obj(xx,yy,true));
                    dis[1][xx][yy] = dis[0][cur.x][cur.y] + 1;
                }else{
                    if(cur.flag) dis[1][xx][yy] = dis[1][cur.x][cur.y] + 1;
                    else dis[0][xx][yy] = dis[0][cur.x][cur.y] + 1;

                    q.add(new obj(xx,yy,cur.flag));
                }

                // 3. 목적지 도착
                if(xx == N-1 && yy == M-1) break loop;
            }
        }
        int ans = Math.max(dis[0][N-1][M-1], dis[1][N-1][M-1]);
        if(ans == 0) System.out.println(-1);
        else System.out.println(ans);

    }

    public static class obj{
        int x;
        int y;
        boolean flag;

        public obj(int x, int y, boolean flag){
            this.x = x;
            this.y = y;
            this.flag = flag;
        }
    }
}
