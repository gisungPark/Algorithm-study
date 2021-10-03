package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ21610마법사상어와비바라기 {

    static int N, M;
    static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[][] diagonal = {{-1, -1}, {1, 1}, {-1, 1}, {1, -1}};
    static int[][] map = new int[51][51];
    static boolean[][] visited = new boolean[51][51];
    static List<int[]> clouds, newClouds;
    static List<int[]> op = new ArrayList<>();

    public static void main(String[] args) throws Exception {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            op.add(new int[]{d, s});
        }

        System.out.println(solve());


    }

    private static int solve() {

        clouds = new ArrayList<>();
        clouds.add(new int[]{N, 1});
        clouds.add(new int[]{N, 2});
        clouds.add(new int[]{N - 1, 1});
        clouds.add(new int[]{N - 1, 2});

        for (int i = 0; i < M; i++) {
            move(i);
            magic();
        }
        int ret = calc();
//        print();
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

    private static int  calc() {
        int ret = 0;
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                ret += map[i][j];
            }
        }

        return ret;
    }

    private static void magic() {
        init();
        newClouds = new ArrayList<>();
        int size = clouds.size();
        for (int i = 0; i < size; i++) {
            int[] cloud = clouds.get(i);
            int xx = cloud[0];
            int yy = cloud[1];

            visited[xx][yy] = true;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (visited[i][j]) continue;

                if (map[i][j] >= 2) {
                    map[i][j] -= 2;
                    newClouds.add(new int[]{i, j});
                }
            }
        }

        clouds = newClouds;
    }

    public static void init() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                visited[i][j] = false;
            }
        }
    }

    private static void move(int idx) {
        int size = clouds.size();
        for (int i = 0; i < size; i++) {
            int[] cloud = clouds.get(i);
            int[] operator = op.get(idx);

            int xx = cloud[0] + dx[operator[0]] * operator[1];
            int yy = cloud[1] + dy[operator[0]] * operator[1];

            xx = func(xx);
            yy = func(yy);
            map[xx][yy]++;
            cloud[0] = xx;
            cloud[1] = yy;
        }


        for (int i = 0; i < size; i++) {
            int[] cloud = clouds.get(i);

            int cnt = 0;
            for (int d = 0; d < 4; d++) {
                int xx = cloud[0] + diagonal[d][0];
                int yy = cloud[1] + diagonal[d][1];

                if (xx < 1 || xx > N || yy < 1 || yy > N) continue;
                if (map[xx][yy] != 0) cnt++;
            }

            map[cloud[0]][cloud[1]] += cnt;
        }
    }

    private static int func(int v) {
        int ret = 0;

        while (v < 1) {
            v += N;
        }
        while (v > N) {
            v -= N;
        }
        return v;


    }
}
