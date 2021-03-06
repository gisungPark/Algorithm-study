package BOJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ2644촌수계산 {

    static int N, M;
    static int[][] map;
    static boolean[] viseted;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        viseted = new boolean[N+1];
        map = new int[N + 1][N + 1];
        int obj = sc.nextInt();
        int target = sc.nextInt();

        M = sc.nextInt();
        for (int i = 0; i < M; i++) {
            int parent = sc.nextInt(); // 부모
            int child = sc.nextInt(); // 자식

            map[parent][child] = 1; // 부모, 자식간의 관계를 연결해준다.
        }
        System.out.println(BFS(obj, target));
    }

    static class Turn{
        int val;
        int cnt;
        public Turn(int val, int cnt){
            this.val = val;
            this.cnt = cnt;
        }
    }

    private static int BFS(int obj, int target) {

        Queue<Turn> q = new LinkedList<>();
        q.add(new Turn(obj, 0));

        while(!q.isEmpty()){

            Turn cur = q.poll();
            for(int i=0; i<= N; i++){
                if(viseted[i]) continue; // 한번 방문한 사람은 다시 방문하지 않는다.
                if(map[cur.val][i] == 1 || map[i][cur.val] == 1){
                    if(i == target) return cur.cnt+1;

                    viseted[i] = true;
                    q.add(new Turn(i, cur.cnt+1));
                }
            }
        }

        return -1;
    }
}

