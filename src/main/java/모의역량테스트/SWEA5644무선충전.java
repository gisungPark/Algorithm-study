package 모의역량테스트;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA5644무선충전 {

    static int T, M, A, ans;
    static int dx[] = {0, -1, 0, 1, 0};
    static int dy[] = {0, 0, 1, 0, -1};
    static int[][] moves;
    static person[] persons;
    static List<Battery> batterys;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());

            moves = new int[2][M];
            for(int i=0; i<2; i++){
                st = new StringTokenizer(br.readLine(), " ");
                for(int j=0; j<M; j++) {
                    moves[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            batterys = new ArrayList<>();

            for(int i=0; i<A; i++){
                st = new StringTokenizer(br.readLine(), " ");
                int C = Integer.parseInt(st.nextToken());
                int R = Integer.parseInt(st.nextToken());
                int dis  = Integer.parseInt(st.nextToken());
                int power = Integer.parseInt(st.nextToken());

                batterys.add(new Battery(R, C, dis, power));
            }
            ans = 0;

            solve();
            System.out.println("#"+tc + " " + ans);
        }
    }

    private static void solve() {
        persons = new person[2];
        persons[0] = new person(1,1);
        persons[1] = new person(10,10);

        // 0. 해당 위치에서 충전가능한지 찾기!
        charge(0);

        for(int t=0; t<M; t++){
            // 1. 이동하기
            move(t);
            // 2. 해당 위치에서 충전 가능한 BC 찾아 충전하기
            charge(t);
        }

    }

    private static void charge(int t) {
        int[] maxIndex = new int[]{-1,-1};
        int[] secondIndex = new int[]{-1,-1};
        for(int i=0; i<2; i++) {
            for (int j = 0; j < A; j++) {
                Battery battery = batterys.get(j);
                // 해당위치에서 배터리 충전이 가능하다면,
                if(distance(persons[i].r, persons[i].c, battery.r, battery.c) <= battery.dis){
                    // 사람 번호, 해당 인원의 가장 큰 값, 두번째로 큰 값, 현재 배터리 인텍스, 배터리
                    indexing(i, maxIndex, secondIndex, j, battery);
                }
            }
        }

        // 해당 위치에서 충전 시작
        if(maxIndex[0] == -1 || maxIndex[1] == -1){
            ans += maxIndex[0] == -1 ? 0 : batterys.get(maxIndex[0]).power;
            ans += maxIndex[1] == -1 ? 0 : batterys.get(maxIndex[1]).power;
        }else if(maxIndex[0] != maxIndex[1]){

            ans += batterys.get(maxIndex[0]).power;
            ans += batterys.get(maxIndex[1]).power;
        }else{
            int maxValue = batterys.get(maxIndex[0]).power;
            if(secondIndex[1] != -1)
                maxValue = Math.max(maxValue, (batterys.get(maxIndex[0]).power + batterys.get(secondIndex[1]).power));
            if(secondIndex[0] != -1)
                maxValue = Math.max(maxValue, (batterys.get(maxIndex[1]).power + batterys.get(secondIndex[0]).power));
            ans+=maxValue;
        }

    }



    private static void indexing(int i, int[] maxIndex, int[] secondIndex, int j, Battery newBattery) {
        if(maxIndex[i] < 0){
            maxIndex[i] = j;
            return;
        }else{
            Battery maxBattery = batterys.get(maxIndex[i]);
            if(maxBattery.power < newBattery.power){
                secondIndex[i] = maxIndex[i];
                maxIndex[i] = j;
            }else {
                if(secondIndex[i] < 0){
                    secondIndex[i] = j;
                }else{
                    Battery secondBattery = batterys.get(secondIndex[i]);
                    if(secondBattery.power < newBattery.power){
                        secondIndex[i] = j;
                    }
                }
            }
        }
    }

    private static int distance(int r, int c, int r1, int c1) {
        return Math.abs(r-r1) + Math.abs(c - c1);
    }

    private static void move(int t) {
        for(int i=0; i<2; i++){
            persons[i].r += dx[moves[i][t]];
            persons[i].c += dy[moves[i][t]];
        }
    }

    static class person{
        int r, c;
        public person(int r, int c){
            this.r = r;
            this.c = c;
        }

    }

    static class Battery{
        int r, c, dis, power;
        public Battery(int r, int c, int dis, int power){
            this.r = r;
            this.c = c;
            this.dis = dis;
            this.power = power;
        }
    }

}
