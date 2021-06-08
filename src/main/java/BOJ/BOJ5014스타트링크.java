package BOJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ5014스타트링크 {

    static int F, S, G, U, D;
    static boolean[] visited;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        F = sc.nextInt();
        S = sc.nextInt();
        G = sc.nextInt();
        U = sc.nextInt();
        D = sc.nextInt();

        visited = new boolean[F+1];
        // 1. 목표 층이 전체 층보다 크다면 계산하지 않는다.
        if(G > F) {
            System.out.println("use the stairs");
        }
        else {
            // 2. 현재층과 목표 층이 동일하다면 이동하지 않는다.
            if(S == G) System.out.println(0);   
            else {
                // 3. 목표층까지의 연산 횟수를 계산한다.
                long ans = BFS();
                if (ans < 0) System.out.println("use the stairs");
                else System.out.println(ans);
            }
        }
    }

    private static long BFS() {
        long ans = 0;

        Queue<Integer> q = new LinkedList<>();
        q.add(S);
        visited[S] = true;

        while(!q.isEmpty()){
            ans++;
            int len = q.size();
            for(int i=0; i<len; i++){
                int cur = q.poll();
                int up = cur + U;   
                int down = cur - D;
                if(up == G || down == G) return ans;

                if(up <= F && !visited[up] ){   // 윗층이 전체층보다 크거나,
                    visited[up] = true;         // 이미 방문했다면 생략
                    q.add(up);
                }
                if(down > 0 && !visited[down]  ){// 아랫층이 1보다 작거나,
                    visited[down] = true;       // 이미 방문했다면 생략
                    q.add(down);
                }
            }
        }

        return -1;
    }
}
