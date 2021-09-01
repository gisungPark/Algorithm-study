package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 플로이드 와샬
 */
public class BOJ14938서강그라운드 {

    static int N, M, R;
    static int INF = 1000000;
    static boolean[] visited;
    static int[] items;
    static int[][] adjMatrix;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        items = new int[N + 1];
        adjMatrix = new int[N + 1][N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            adjMatrix[from][to] = dis;
            adjMatrix[to][from] = dis;
        }

        System.out.println(solve());

    }

    private static int solve() {
        floydWarshall();

        int res = calcMax();
        return res;
    }

    private static int calcMax() {
        int max = 0;
        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= N; j++) {
                if (adjMatrix[i][j] > M) continue;
                sum += items[j];
            }
            max = Math.max(max, sum);
        }
        return max;
    }


    private static void floydWarshall() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(i == j) adjMatrix[i][j] = 0;
                else if (adjMatrix[i][j] == 0) adjMatrix[i][j] = INF;
            }
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
                }
            }
        }
    }
}
