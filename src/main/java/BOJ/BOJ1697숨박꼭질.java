package BOJ;

import java.util.*;

public class BOJ1697숨박꼭질 {
    static int N, M;
    static int[] visited;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        visited = new int[100001];

        Queue<Integer> q = new LinkedList<>();
        visited[N] = 1;
        q.add(N);

        while (!q.isEmpty()) {
            Integer now = q.poll();
            if (now == M) break;

            int jump1 = now * 2;
            int jump2 = now - 1;
            int jump3 = now + 1;

            if (jump1 <= 100000 && visited[jump1] == 0) {
                visited[jump1] = visited[now] + 1;
                q.add(jump1);
            }
            if (jump2 >= 0 && visited[jump2] == 0) {
                visited[jump2] = visited[now] + 1;
                q.add(jump2);
            }
            if (jump3 <= 100000 && visited[jump3] == 0) {
                visited[jump3] = visited[now] + 1;
                q.add(jump3);
            }
        }
        System.out.println(visited[M] - 1);

    }

}
