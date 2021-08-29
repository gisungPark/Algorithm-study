package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1368물대기 {

    static int N;
    static final int INF = Integer.MAX_VALUE;
    static int[] minEdge;
    static int[][] adjMatrix;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        minEdge = new int[N];
        adjMatrix = new int[N][N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            minEdge[i] = Integer.parseInt(br.readLine());
        }
        StringTokenizer st = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                adjMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve());
    }

    private static int solve() {
        int sum = 0, vertexCnt = 0;

        for (int i = 0; i < N; i++) {
            int minIdx = 0, minCost = INF;

            // 최소 비용의 정점 확인
            for (int j = 0; j < N; j++) {
                if (visited[j]) continue;
                if (minCost > minEdge[j]) {
                    minIdx = j;
                    minCost = minEdge[j];
                }
            }

            // 최소 비용 정점 방문 처리
            visited[minIdx] = true;
            sum += minCost;
            vertexCnt++;

            for (int j = 0; j < N; j++) {
                if (visited[j]) continue;
                if (minEdge[j] > adjMatrix[minIdx][j])
                    minEdge[j] = adjMatrix[minIdx][j];
            }
        }

        return sum;
    }
}
