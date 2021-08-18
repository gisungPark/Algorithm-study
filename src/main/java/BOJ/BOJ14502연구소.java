package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ14502연구소 {

    static int N, M, ans;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map, copyMap;
    static Queue<int[]> q;
    static List<int[]> booms;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        copyMap = new int[N][M];
        q = new LinkedList<>();
        booms = new ArrayList<>();

        // input
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) booms.add(new int[]{i, j});
            }
        }

        ans = 0;
        combi(0);
        System.out.println(ans);
    }

    public static void combi(int idx) {
        if (idx == 3) simulation(); // 벽 3개 세운 경우
        else {
            for (int i = 0; i < N * M; i++) {
                if (map[i / M][i % M] != 0) continue;
                map[i / M][i % M] = 1;
                combi(idx + 1);
                map[i / M][i % M] = 0;
            }
        }
    }

    private static void simulation() {
        init();
        for (int i = 0; i < booms.size(); i++) {
            q.add(booms.get(i));
        }
        // 바이러스 번식 start
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int xx = cur[0] + dx[d];
                int yy = cur[1] + dy[d];

                if (xx < 0 || xx >= N || yy < 0 || yy >= M      // 바이러스가 번식할 수 없는 칸
                        || copyMap[xx][yy] != 0) continue;
                copyMap[xx][yy] = 2;
                q.add(new int[]{xx, yy});
            }
        }
        // 바이러스 번식 end

        ans = Math.max(ans, countZero()); // 빈 영역 계산

    }

    private static int countZero() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyMap[i][j] == 0) cnt++;
            }
        }
        return cnt;
    }

    public static void init() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyMap[i][j] = map[i][j];
            }
        }
    }
}
