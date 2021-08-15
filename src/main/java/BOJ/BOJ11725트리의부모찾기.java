package BOJ;

import java.util.Scanner;

public class BOJ11725트리의부모찾기 {

    static int N, cnt;
    static int[] nums;
    static Node[] nodes;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        nums = new int[N + 1];
        nodes = new Node[N + 1];

        for (int i = 1; i <= N; i++) {
            nums[i] = i;
        }

        for (int i = 0; i < N - 1; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            nodes[from] = new Node(to, nodes[from]);
            nodes[to] = new Node(from, nodes[to]);
        }

        nums[1] = -1;
        cnt = 1;
        process(1);
        for (int i = 2; i <= N; i++) {
            System.out.println(nums[i]);
        }
    }

    private static void process(int idx) {
        if(cnt == N) return;

        for(Node next = nodes[idx]; next != null; next = next.node){
            if(nums[next.val] != next.val) continue;

            cnt++;
            nums[next.val] = idx;
            process(next.val);
        }
    }

//    private static void process() {
//        Queue<Integer> q = new LinkedList<>();
//        q.add(1);
//        nums[1] = -1; // root 노드 방문 처리
//
//        while (!q.isEmpty()) {
//            Integer now = q.poll();
//            for (Node next = nodes[now]; next != null; next = next.node) {
//                if (nums[next.val] != next.val) continue;
//
//                nums[next.val] = now; // 방문처리
//                q.add(next.val);
//            }
//        }
//
//    }


    public static class Node {
        public int val;
        public Node node;

        public Node(int val, Node node) {
            this.val = val;
            this.node = node;

        }
    }
}
