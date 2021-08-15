package BOJ;

import java.util.*;
import java.io.*;

public class BOJ1325효율적인해킹 {

    static int N, M, max;
    static int[] counts;
    static List<Integer>[] nodes;
    static boolean[] visited;
    static Queue<Integer> q;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        counts = new int[N + 1];
        nodes = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            nodes[to].add(from);
        }
        max = Integer.MIN_VALUE;
        visited = new boolean[N+1];
        q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            BFS(i);
        }
        for (int i = 1; i <= N; i++) {
            if (counts[i] == max) System.out.print(i + " ");
        }
    }

    private static void BFS(int idx) {
        if(idx != 1) init();
        int cnt = 1;
        q.add(idx);
        visited[idx] = true;

        while (!q.isEmpty()) {
            Integer val = q.poll();
            for (int i = 0; i < nodes[val].size(); i++) {
                Integer next = nodes[val].get(i);
                if (visited[next]) continue;
                q.add(next);
                visited[next] = true;
                counts[idx]++;
            }
        }
        max = Math.max(max, counts[idx]);
    }

    public static void init(){
        q.clear();

        for(int i=1; i<N+1; i++){
            visited[i] = false;
        }
    }

}
