package BOJ;

import javax.net.ssl.SSLContextSpi;
import java.io.*;
import java.util.*;

public class BOJ21608상어초등학교 {

    static int N;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static int[] scores = {0, 1, 10, 100, 1000};
    static int[] orders;
    static List<Child> childs;
    static int[][] map = new int[21][21];

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        orders = new int[401];
        childs = new ArrayList<>();

        for (int i = 1; i <= N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int likes[] = new int[4];
            for (int j = 0; j < 4; j++) {
                likes[j] = Integer.parseInt(st.nextToken());
            }
            orders[n] = i-1;
            childs.add(new Child(n, likes));
        }


        int ret = solve();
        System.out.println(ret);

    }

    private static int solve() {
        assign();
        int ret = calc();
        return ret;
    }

    public static void print(){
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int calc() {
        int ret = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int n = orders[map[i][j]];
                Child child = childs.get(n);

                int cnt = 0;
                for (int d = 0; d < 4; d++) {
                    int xx = i + dx[d];
                    int yy = j + dy[d];
                    if (xx < 1 || xx > N || yy < 1 || yy > N) continue;
                    for (int k = 0; k < 4; k++) {
                        if (map[xx][yy] == child.likes[k]) cnt++;
                    }
                }

                ret += scores[cnt];
            }
        }

        return ret;
    }

    private static void assign() {
        for (int l = 0; l < childs.size(); l++) {
            Child child = childs.get(l);
            int num = child.n;
            int[] likes = child.likes;

            int finalX = -1, finalY = -1;
            int maxFriend = -1, maxBlank = -1;
            List<int[]> lists = new ArrayList<>();
            for (int x = 1; x <= N; x++) {
                for (int y = 1; y <= N; y++) {
                    if (map[x][y] != 0) continue;
                    int friend = 0, blank = 0;
                    for (int d = 0; d < 4; d++) {
                        int xx = x + dx[d];
                        int yy = y + dy[d];
                        if (xx < 1 || xx > N || yy < 1 || yy > N) continue;

                        for (int k = 0; k < 4; k++) {
                            if (map[xx][yy] == likes[k]) friend++;
                        }
                        if (map[xx][yy] == 0) blank++;
                    }

                    if (maxFriend == friend) {
                        if (blank > maxBlank) {
                            maxBlank = blank;
                            finalX = x;
                            finalY = y;
                        }
                    } else {
                        if (friend > maxFriend) {
                            maxFriend = friend;
                            maxBlank = blank;
                            finalX = x;
                            finalY = y;
                        }
                    }

                }
            }
                    map[finalX][finalY] = num;
        }
    }

    public static class Child {
        int n;
        int[] likes = new int[4];

        public Child(int n, int[] likes) {
            this.n = n;
            this.likes = likes;
        }
    }
}
