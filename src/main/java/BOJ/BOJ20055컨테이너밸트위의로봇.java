package BOJ;

import java.util.*;
import java.io.*;

public class BOJ20055컨테이너밸트위의로봇 {

    static int N, M, cnt;
    static boolean[] visited;
    static int[][] map;
    static Spot[] spots;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] nums = new int[2*N +1];
        st = new StringTokenizer(br.readLine());

        spots = new Spot[N*2 +1];
        for (int i = 1; i < N * 2 + 1; i++) {
            int val = Integer.parseInt(st.nextToken());
            spots[i] = new Spot(val,false);
        }

        int ret = solve();
        System.out.println(ret);

    }

    public static int solve() {
        int ret = 0;
        cnt = M;
        while(true) {
            ret++;
            rotation();
            move();
            push();
            if(cnt <= 0) break;
        }
        return ret;
    }

    private static void rotation() {
        int len = spots.length;
        Spot last = spots[len - 1];
        for(int i=len-1; i>1; i--){
            spots[i] = spots[i-1];
        }
        spots[N].flag = false;
        spots[1] = last;
    }

    private static void move() {
        for(int i=N; i>1; i--){
            if(spots[i].val == 0 || spots[i].flag || !spots[i-1].flag) continue;
            if(--spots[i].val == 0) cnt--;
            spots[i].flag = spots[i-1].flag;
            spots[i-1].flag = false;
        }
        spots[N].flag = false;
    }

    private static void push() {
        if(spots[1].val == 0) return;
        if(--spots[1].val == 0) cnt--;
        spots[1].flag = true;

    }


    public static void print(){
        for(int i=1; i<=N; i++){
            System.out.print(spots[i].val + "/" + (spots[i].flag ? "1 " : "0 "));
        }
        System.out.println();
        for(int i=N+1; i<=2*N; i++){
            System.out.print(spots[i].val + "/" + (spots[i].flag ? "1 " : "0 "));
        }
        System.out.println();
        System.out.println("=========");
    }

    public static class Spot{
        int val;
        boolean flag;

        public Spot(int val, boolean flag) {
            this.val = val;
            this.flag = flag;
        }
    }



}
