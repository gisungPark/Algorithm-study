package BOJ;


import java.util.*;
import java.io.*;

public class BOJ10159저울 {

    static int N, M, INF;
    static int[] ans;
    static int[][] adjMatrix;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        adjMatrix = new int[N + 1][N + 1];
        StringTokenizer st = null;
        INF = 1000000000;
        init();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjMatrix[to][from] = 1;
        }

        ans = new int[N + 1];
        solve();
//        print();
        for (int i = 1; i < N + 1; i++) {
            System.out.println(ans[i]);
        }
    }

    private static void init() {
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (i == j) continue;
                adjMatrix[i][j] = INF;
            }
        }
    }

    private static void solve() {
        floyd();
        count();
    }

    private static void count() {
        for (int i = 1; i < N + 1; i++) {
            visited = new boolean[N + 1];
            for (int j = 1; j < N + 1; j++) {
                if (adjMatrix[i][j] != INF) visited[j] = true;
                if (adjMatrix[j][i] != INF) visited[j] = true;
            }
            for (int j = 1; j < N + 1; j++) {
                if (!visited[j]) ans[i]++;
            }
        }
    }


    private static void floyd() {
        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if (adjMatrix[i][j] > adjMatrix[i][k] + adjMatrix[k][j]) {
                        adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
                    }
                }
            }
        }
    }
}


