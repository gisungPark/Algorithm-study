package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14442벽부수고이동하기2 {

    static int N, M, K, ans;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static char[][] map;
    static int[][][] dis;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        map = new char[N][];
        dis = new int[K+1][N][M];
        
        for(int i=0; i<N; i++){
            map[i] = br.readLine().toCharArray();
        }
        // 출발점 갱신
        Queue<obj> q= new LinkedList<>();
        dis[0][0][0] = 1;
        q.add(new obj(0, 0, 0));

        loop: while(!q.isEmpty()){
            obj cur = q.poll();
            for(int i=0; i<4; i++){
                int xx = cur.x + dx[i];
                int yy = cur.y + dy[i];
                if(xx < 0 || xx >= N || yy < 0 || yy >= M) continue;
                if(map[xx][yy] == '1'){
                    if(cur.cnt == K) continue;
                    if(dis[cur.cnt+1][xx][yy] != 0) continue;
                    dis[cur.cnt+1][xx][yy] = dis[cur.cnt][cur.x][cur.y] + 1;
                    q.add(new obj(xx, yy, cur.cnt + 1));
                }else{
                    if(dis[cur.cnt][xx][yy] != 0) continue;
                    dis[cur.cnt][xx][yy] = dis[cur.cnt][cur.x][cur.y] + 1;
                    q.add(new obj(xx, yy, cur.cnt));
                }
                if(xx == N-1 && yy == M -1) break loop;
            }
        }

        ans = 0;
        for(int i=0; i<K+1; i++){
            ans = Math.max(ans, dis[i][N-1][M-1]);
        }
        if(ans == 0) System.out.println(-1);
        else System.out.println(ans);

    }

    public static class obj{
        int x;
        int y;
        int cnt;
        public obj(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

    }
}
