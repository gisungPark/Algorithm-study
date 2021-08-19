package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ16234인구이동 {

    static int N, L, R, ans;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;
    static boolean[][] visited;
    static Queue<int[]> q, afterQ;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N][N];
        q = new LinkedList<>();
        afterQ = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        ans = 0;
        simulation();

        System.out.println(ans);
    }

    private static void simulation() {

        while (true) {
            int cnt = N * N;
            if (ans != 0) init();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j]) continue;
                    cnt--;
                    bfs(i, j);
                }
            }
            if (cnt == 0) break;
            ans++;
        }
    }

    private static void init() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                visited[i][j] = false;
            }
        }
    }

    private static void bfs(int x, int y) {
        int sum = 0;
        q.add(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            sum += map[now[0]][now[1]];
            afterQ.add(now);

            for (int d = 0; d < 4; d++) {
                int xx = now[0] + dx[d];
                int yy = now[1] + dy[d];

                if (xx < 0 || xx >= N || yy < 0 || yy >= N
                        || visited[xx][yy] || !union(now[0], now[1], xx, yy)) continue;

                visited[xx][yy] = true;
                q.add(new int[]{xx, yy});
            }
        }

        int avg = sum / afterQ.size();

        while (!afterQ.isEmpty()) {
            int[] now = afterQ.poll();
            map[now[0]][now[1]] = avg;
        }
    }

    private static boolean union(int x, int y, int xx, int yy) {
        int diff = Math.abs(map[x][y] - map[xx][yy]);
        if (diff >= L && diff <= R) return true;
        return false;
    }
}
