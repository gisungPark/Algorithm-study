package BOJ;

import java.util.*;

public class BOJ13913숨박꼭질4 {

    static int N, K;
    static Set<Integer> set;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        set = new HashSet<>();
        set.add(N);

        Queue<path> q = new LinkedList<>();
        // 1. 출발점 초기화
        q.add(new path(N, 0, new ArrayList()));

        while(!q.isEmpty()){
            path cur = q.poll();
            // 1. 걷기
            if(move(q, cur, 0)) break;
            if(move(q, cur, 1)) break;
            if(move(q, cur, 2)) break;
        }
    }

    private static boolean move(Queue<path> q, path cur, int flag) {

        int xx = 0;
        switch (flag){
            case 0:
                xx = cur.x + 1;
                break;
            case 1:
                xx = cur.x -1;
                break;
            case 2:
                xx = cur.x * 2;
                break;
        }
        if(xx < 0 || xx > 100000 || set.contains(xx)) return false;

        // 목적지 도착!!
        if(xx == K) {
            System.out.println(cur.dis + 1);
            for(int i=0; i<cur.list.size(); i++){
                System.out.print(cur.list.get(i) + " ");
            }
            System.out.println(xx);
            return true;
        }

        q.add(new path(xx, cur.dis+1, cur.list));
        return false;
    }


    public static class path{
        int x;
        int dis;
        List<Integer> list = new ArrayList<>();

        public path(int x, int dis, List list){
            this.x = x;
            this.dis = dis;
            this.list.addAll(list);
            this.list.add(x);
        }
    }
}
