package BOJ;

import java.util.*;
import java.io.*;

public class BOJ1956운동 {
    static int N, M, INF = 80000000, ans;
    static boolean[] visited;
    static int[][] adjMatrix;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjMatrix = new int[N + 1][N + 1];
        visited = new boolean[N + 1];
        init();
        for (int j = 0; j < M; j++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            adjMatrix[from][to] = Math.min(adjMatrix[from][to], dis);

        }
        ans = INF;
        solve();
        if (ans == INF) System.out.println("-1");
        else System.out.println(ans);
    }

    private static void init() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                adjMatrix[i][j] = INF;
            }
        }
    }

    private static void solve() {

        floyd();
        for (int i = 1; i <= N; i++) {
            ans = Math.min(ans, adjMatrix[i][i]);
        }
    }


    private static void floyd() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
                }
            }
        }
    }

}
