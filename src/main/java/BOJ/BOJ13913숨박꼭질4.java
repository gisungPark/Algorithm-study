package BOJ;

import java.util.*;

public class BOJ13913숨박꼭질4 {

    static int N, K;
    static int[] parents;
    static Set<Integer> set;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        parents = new int[100003];
        set = new HashSet<>();
        parents[N] = N;
        set.add(N);

        if(N == K){
            System.out.println(0);
            System.out.println(N);
        }else {
            Queue<int[]> q = new LinkedList<>();
            // 1. 출발점 초기화
            q.add(new int[]{N, 0});

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                // 1. 걷기
                if (move(q, cur, 0)) break;
                if (move(q, cur, 1)) break;
                if (move(q, cur, 2)) break;
            }
        }
    }

    private static boolean move(Queue<int[]> q, int[] cur, int flag) {

        int xx = 0;
        switch (flag){
            case 0:
                xx = cur[0] + 1;
                break;
            case 1:
                xx = cur[0] -1;
                break;
            case 2:
                xx = cur[0] * 2;
                break;
        }
        if(xx < 0 || xx > 100000 || set.contains(xx)) return false;
        parents[xx] = cur[0];
        set.add(xx);

        // 목적지 도착!!
        if(xx == K) {
            int child = xx;
            System.out.println(cur[1] + 1);
            List<Integer> path = new ArrayList<>();
            path.add(child);
            while(parents[child] != N){
                child = parents[child];
                path.add(child);
            }

            System.out.print(N+" ");
            for(int i=path.size()-1; i>=0; i--){
                System.out.print(path.get(i) + " ");
            }

            return true;
        }



        q.add(new int[]{xx, cur[1] + 1});
        return false;
    }

}
