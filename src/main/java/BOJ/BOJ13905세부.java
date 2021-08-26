package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ13905세부 {

    static int N, M, s, e, ans;
    static Edge[] edges;
    static int[] parents;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        edges = new Edge[M];
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(from, to, val);
        }
        Arrays.sort(edges);

        ans = 0;
        solution();
        System.out.println(ans);
    }

    private static void solution() {
        make();

        int cnt = 0;
        for(Edge edge : edges){
            if(union(edge.from, edge.to)) {
                if(find(s) == find(e)){
                    ans = edge.val;
                    break;
                }
                if(++cnt == N-1) break;
            }
        }

    }

    private static void make() {
        parents = new int[N+1];
        for(int i=1; i<= N; i++){
            parents[i] = i;
        }
    }

    public static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;

        parents[bRoot] = aRoot;
        return true;
    }

    private static int find(int a) {
        if(parents[a] == a) return a;
        else return parents[a] = find(parents[a]);
    }

    public static class Edge implements Comparable<Edge> {
        int from, to, val;

        public Edge(int from, int to, int val) {
            this.from = from;
            this.to = to;
            this.val = val;
        }

        @Override
        public int compareTo(Edge o) {
            return o.val - this.val;
        }
    }
}
