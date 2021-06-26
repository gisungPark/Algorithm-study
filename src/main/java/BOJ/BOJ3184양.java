package BOJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ3184양 {

    static int N, M, sheep, wolf;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static char[][] map;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        sc.nextLine();

        map = new char[N][];

        for(int i=0; i<N; i++){
            String input = sc.nextLine();
            map[i] = input.toCharArray();
        }
        sheep = wolf = 0;
        process();
        System.out.println(sheep + " " + wolf);
    }

    private static void process() {
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] != '#') BFS(i,j);
            }
        }
    }

    private static void BFS(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});

        int s = 0, v = 0;
        if(map[r][c] == 'o') s++;
        else if(map[r][c] == 'v') v++;
        map[r][c] = '#';

        while(!q.isEmpty()){
            int[] cur = q.poll();

            for(int i=0; i<4; i++){
                int xx = cur[0] + dx[i];
                int yy = cur[1] + dy[i];

                if(xx < 0 || xx >= N || yy < 0 || yy >= M || map[xx][yy] == '#' ) continue;
                q.add(new int[]{xx,yy});
                if(map[xx][yy] == 'o') s++;
                else if(map[xx][yy] == 'v') v++;
                map[xx][yy] = '#';
            }
        }
        if(s > v) sheep += s;
        else wolf += v;
    }
}
