package BOJ;

import java.util.*;
import java.io.*;
/*
 * 플로이드 와샬 알고리즘
 */
public class BOJ11265끝나지않는파티 {

    static int N, M;
    static int[][] adjMatrix;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjMatrix = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                adjMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            if (adjMatrix[from][to] <= dis) System.out.println("Enjoy other party");
            else System.out.println("Stay here");
        }


    }

    private static void solve() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (adjMatrix[i][j] > adjMatrix[i][k] + adjMatrix[k][j])
                        adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
                }
            }
        }
    }

}
