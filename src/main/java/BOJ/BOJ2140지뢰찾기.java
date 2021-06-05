package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2140지뢰찾기 {

    static int N, blanks;
    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        blanks = N*N - 4*(N-1);
        if(blanks<=0) {
            System.out.println();
            return;
        }

        map = new char[N][];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        po[][] iMap = init();
        process();


    }

    private static void process() {
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){

            }
        }
    }

    public static po[][] init(){
        po[][] p = new po[N][N];

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                p[i][j] = check(i,j);
            }
        }

        return p;
    }

    private static po check(int r, int c) {
        int val = map[r][c], blank = 0, boom = 0;

        for(int i=0; i<8; i++){
            int rr = r + dx[i];
            int cc = c + dy[i];
            if(rr< 0 || rr >=N || cc<0 || cc>=N) continue;
            if(map[rr][cc] == '#') blank++;
            else if(map[rr][cc] == '*') boom++;
        }
        return new po(val,blank, boom);
    }

    public static class po{
        int val;
        int blank;
        int boom;

        public po(int val, int blank, int boom ){
            this.val = val;
            this.blank = blank;
            this.boom = boom;
        }
    }


}
