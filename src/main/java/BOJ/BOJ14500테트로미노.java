package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14500테트로미노 {

    static int N, M, ans;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = 0;
        solve();
        System.out.println(ans);

    }

    private static void solve() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                DFS(i, j, 1, 0);
                anotherShap(i, j);
                visited[i][j] = false;
            }
        }

    }

    public static void anotherShap(int r, int c) {

        if (check(r, c) && check(r + 1, c) && check(r + 2, c) && check(r + 1, c + 1)) {
            int tmp = map[r][c] + map[r + 1][c] + map[r + 2][c] + map[r + 1][c + 1];
            ans = Math.max(ans, tmp);
        }
        if (check(r, c) && check(r + 1, c) && check(r + 2, c) && check(r + 1, c - 1)) {
            int tmp = map[r][c] + map[r + 1][c] + map[r + 2][c] + map[r + 1][c - 1];
            ans = Math.max(ans, tmp);
        }
        if (check(r, c) && check(r, c + 1) && check(r, c + 2) && check(r - 1, c + 1)) {
            int tmp = map[r][c] + map[r][c + 1] + map[r][c + 2] + map[r - 1][c + 1];
            ans = Math.max(ans, tmp);
        }
        if (check(r, c) && check(r, c + 1) && check(r, c + 2) && check(r + 1, c + 1)) {
            int tmp = map[r][c] + map[r][c + 1] + map[r][c + 2] + map[r + 1][c + 1];
            ans = Math.max(ans, tmp);
        }

    }

    public static boolean check(int r, int c) {
        if (r < 0 || r >= N || c < 0 || c >= M) return false;
        return true;
    }

    private static void DFS(int r, int c, int cnt, int sum) {
        if (cnt > 4) {
            ans = Math.max(ans, sum);
        } else {
            for (int i = 0; i < 4; i++) {
                int xx = r + dx[i];
                int yy = c + dy[i];

                if (xx < 0 || xx >= N || yy < 0 || yy >= M
                        || visited[xx][yy]) continue;
                visited[xx][yy] = true;
                DFS(xx, yy, cnt + 1, sum + map[xx][yy]);
                visited[xx][yy] = false;

            }
        }
    }


}
