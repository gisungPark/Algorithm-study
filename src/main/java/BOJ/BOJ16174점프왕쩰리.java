package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16174점프왕쩰리 {

    static int N;
    static int[] dx = {1, 0};
    static int[] dy = {0, 1};
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if(process()) System.out.println("HaruHaru");
        else System.out.println("Hing");

    }

    private static boolean process() {
        Queue<po> q = new LinkedList<>();
        q.add(new po(0, 0));
        visited[0][0] = true;

        while(!q.isEmpty()){
            po cur = q.poll();
            for(int i=0; i<2; i++){
                int size = map[cur.x][cur.y];
                int xx = cur.x + dx[i]*size;
                int yy = cur.y + dy[i]*size;

                if(xx < 0 || xx >= N || yy < 0 || yy >= N
                        || visited[xx][yy]) continue;
                if(xx == N-1 && yy == N-1) return true;

                visited[xx][yy] = true;
                q.add(new po(xx,yy));
            }
        }

        return false;
    }

    public static class po{
        int x;
        int y;
        public po(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
