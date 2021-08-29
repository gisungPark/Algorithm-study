package 인프런;

import BOJ.BOJ13905세부;

public class MTS_프림알고리즘 {

    static int N, ans;
    static boolean[] visited;
    static int[] minEdge;
    static int[][] adjMatrix;

    public static void main(String[] args) {

        N = 5;
        ans = 0;
        visited = new boolean[N + 1];
        minEdge = new int[N + 1];
        adjMatrix = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            minEdge[i] = Integer.MAX_VALUE;
        }

        minEdge[0] = 0;

        for (int i = 0; i < N; i++) {
            int min = Integer.MAX_VALUE;
            int minIdx = 0;
            // 최소 비용의 정점 선택
            for (int j = 0; j < N; j++) {
                if (visited[i]) continue;
                if (min > minEdge[i]) minIdx = i;
            }
            visited[minIdx] = true;

            // 선택된 정점으로 거리 비용 초기화
            for (int j = 0; j < N; j++) {
                if (visited[j] && adjMatrix[minIdx][j] == 0) continue;
                if (minEdge[j] > adjMatrix[minIdx][j])
                    minEdge[j] = adjMatrix[minIdx][j];
            }

        }
    }
}
