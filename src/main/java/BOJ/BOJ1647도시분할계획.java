package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1647도시분할계획 {

    static int N, M;
    static int[] parents;
    static Edge[] edges;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parents = new int[N+1];
        visited = new boolean[N+1];
        edges = new Edge[M];
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(from, to, weight);
        }
        System.out.println(solve());

    }

    private static int solve() {
        int sum = 0;
        int edge = 0;

        make();
        Arrays.sort(edges);

        for(Edge e :edges){
            if(union(e.from, e.to)){
                edge++;
                sum += e.weight;
            }
            if(edge == N-2) break;
        }
        return sum;
    }

    private static boolean union(int from, int to) {
        int aRoot = find(from);
        int bRoot = find(to);
        if (aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }

    private static int find(int v) {
        if (parents[v] == v) return v;
        else return parents[v] = find(parents[v]);
    }

    private static void make() {
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
    }

    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }

    }

}
