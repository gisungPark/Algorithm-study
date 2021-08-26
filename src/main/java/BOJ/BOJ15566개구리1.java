package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15566개구리1 {
    static boolean flag;
    static int N, M;
    static int[][] map;
    static int[] sel;
    static Flog[] flogs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        flogs = new Flog[N + 1];
        sel = new int[N + 1];

        // 각 개구리 관심분야
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int food = Integer.parseInt(st.nextToken());
            int hobby = Integer.parseInt(st.nextToken());
            int family = Integer.parseInt(st.nextToken());
            int study = Integer.parseInt(st.nextToken());
            flogs[i] = new Flog(food, hobby, family, study);
        }

        int remain = N;
        // 선호지역 입력
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            flogs[i].setFRegion(a, b);
            if (a == b) {
                sel[a] = i;
                remain--;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            map[from][to] = val;
            map[to][from] = val;
        }

        flag = false;
        perm(1, remain);
        if (!flag) System.out.println("NO");

    }

    private static void perm(int start, int remain) {
        if(flag) return;
        if(remain == 0){
            for(int i=1; i<N; i++){
                for(int j=i+1; j<N+1; j++){
                    if(map[i][j] == 0) continue;
                    int type = map[i][j];
                    if(flogs[sel[i]].interests[type] != flogs[sel[j]].interests[type]) return;
                }
            }
            flag = true;
            System.out.println("YES");
            for(int i=1; i<=N; i++){
                System.out.print(sel[i] + " ");
            }
        }else{
            if(start > N) return;
            int a = flogs[start].FRegion1;
            int b = flogs[start].FRegion2;

            if (a == b) perm(start + 1, remain);
            else {
                if (sel[a] == 0) {
                    sel[a] = start;
                    perm(start + 1, remain - 1);
                    sel[a] = 0;
                }
                if (sel[b] == 0) {
                    sel[b] = start;
                    perm(start + 1, remain - 1);
                    sel[b] = 0;
                }
            }
        }
    }

    public static class Flog {
        int interests[] = new int[5];
        int FRegion1, FRegion2;

        public Flog(int food, int hobby, int family, int study) {
            this.interests[1] = food;
            this.interests[2] = hobby;
            this.interests[3] = family;
            this.interests[4] = study;
        }

        public void setFRegion(int a, int b) {
            this.FRegion1 = a;
            this.FRegion2 = b;
        }
    }
}
