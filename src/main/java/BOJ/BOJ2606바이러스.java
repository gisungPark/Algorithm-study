package BOJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ2606바이러스 {

    public static int N, K, ans;
    public static Node[] nodes;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        nodes = new Node[N+1];
        for (int i = 0; i < K; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();

            nodes[from] = new Node(to, nodes[from]);
            nodes[to] = new Node(from, nodes[to]);
        }
        ans = 0;
        process();
        System.out.println(ans);
    }

    private static void process() {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        q.add(1);
        visited[1] = true;

        while(!q.isEmpty()){
            Integer val = q.poll();

            for(Node next = nodes[val]; next != null; next = next.node){
                if(visited[next.val]) continue;

                q.add(next.val);
                visited[next.val] = true;
                ans++;
            }
        }

    }

    public static class Node{
        public int val;
        public Node node;

        public Node(int val, Node node){
            this.val = val;
            this.node = node;
        }
    }
}
