package BOJ;

import java.util.*;
import java.io.*;

public class BOJ1197최소스패닝트리 {

    static int V, E;
    static int[] parents;
    static boolean[] visited;
    static Edge[] edges;
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        visited = new boolean[V+1];
        edges = new Edge[E];
        parents = new int[V+1];

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(from, to, weight);
        }
        System.out.println(solve());
    }

    private static long solve() {
        long sum = 0L;
        make();

        Arrays.sort(edges);
        int edge = 0;
        for(Edge e: edges){
            if(union(e.from, e.to)){
                edge++;
                sum += e.weight;
            }
            if(edge == V-1) break;
        }
        return sum;
    }

    private static boolean union(int from, int to) {
        int aRoot = find(from);
        int bRoot = find(to);
        if(aRoot == bRoot) return false;

        parents[bRoot] = aRoot;
        return true;
    }

    private static int find(int v) {
        if(parents[v] == v) return v;
        else return parents[v] = find(parents[v]);

    }

    private static void make() {
        for(int i=0; i<V+1; i++){
            parents[i] = i;
        }
    }

    public static class Edge implements Comparable<Edge>{
        int from, to;
        long weight;

        public Edge(int from, int to, long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o){
            return Long.compare(this.weight, o.weight);
        }
    }
}
