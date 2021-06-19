package BOJ;

import java.util.*;

public class BOJ9019DSLR {

    static int T;
    static obj[] visited;
    static int A, B;
    static char[] command = {'D', 'S', 'L', 'R'};

    public static class obj{
        int parents;
        char op;

        public obj(int parents, char op) {
            this.parents = parents;
            this.op = op;
        }
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();
        for (int tc = 0; tc < T; tc++) {
            visited = new obj[10000];
            A = sc.nextInt();
            B = sc.nextInt();
            process(A, B);
        }
    }

    private static void process(int from, int to) {

        Queue<Integer> q = new LinkedList<>();
        q.add(from);
        visited[from] = new obj(from, ' ');

        loop: while(!q.isEmpty()){
            int cur = q.poll();                     // 현재위치
            for (int i = 0; i < 4; i++) {             // 다음위치
                int next = go(cur, i);
                if (visited[next] != null) continue;
                // 방문처리!!
                visited[next] = new obj(cur, command[i]);
                q.add(next);

                if (next == to) {
                    List<Character> list = new ArrayList<>();
                    int parents = next;
                    // 부모가 출발점이 아닐때까지,
                    while (parents != from) {
                        list.add(visited[parents].op);
                        parents = visited[parents].parents;
                    }
                    for (int k = list.size() - 1; k >= 0; k--) {
                        System.out.print(list.get(k));
                    }
                    System.out.println();
                    break loop;
                }
            }
        }
    }

    private static int go(int pos, int flag) {
        if (flag == 0) return (pos * 2) % 10000;
        if (flag == 1) return pos == 0 ? 9999 : (pos - 1);
        if (flag == 2) return (pos % 1000) * 10 + (pos / 1000);
        if (flag == 3) return (pos % 10) * 1000 + (pos / 10);
        return -1;
    }
}
