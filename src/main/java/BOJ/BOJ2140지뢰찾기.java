package BOJ;

import java.util.Scanner;

public class BOJ2140지뢰찾기 {

    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
    static int N, blanks, ans;
    static char[][] map;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        sc.nextLine();

        ans = 0;
        blanks = (N-2)*(N-2);
        map = new char[N][];

        for(int i=0; i<N; i++){
            String str = sc.nextLine();
            map[i] = str.toCharArray();
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(map[i][j] == '#' || map[i][j] == '*' || map[i][j] == ' ') continue;

                if(map[i][j] == '0') fillTheBlank(i, j,' ');
                else{
                    check(i,j);
                }
            }
        }

        if(N<=2) System.out.println(0);
        else System.out.println(blanks);
    }

    private static void check(int i, int j) {
        int boom = 0, blank = 0;
        int val = map[i][j]-'0';
        for(int d=0; d<8; d++){
            int rr = i + dx[d];
            int cc = j + dy[d];
            if(rr < 0 || rr >= N || cc < 0 || cc >= N ) continue;
            if(map[rr][cc] == '*') boom++;
            else if(map[rr][cc] == '#') blank++;
        }

        if(val == boom) fillTheBlank(i, j, ' ');
        else if((val - boom) == blank){
            fillTheBlank(i, j, '*');
        }
    }

    private static void fillTheBlank(int r, int c, char mark) {
        for(int d=0; d<8; d++){
            int rr = r + dx[d];
            int cc = c + dy[d];

            if(rr < 0 || rr >= N || cc<0 || cc >=N
                                || map[rr][cc] != '#') continue;
            map[rr][cc] = mark;
            if(mark != '*') blanks--;
        }
    }
}
