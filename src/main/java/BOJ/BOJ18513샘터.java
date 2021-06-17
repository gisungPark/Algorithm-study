package BOJ;

import java.io.*;
import java.util.*;

public class BOJ18513샘터 {

    static int N, K, HALF;
    static List<Integer> water, home;
    static int[] visited;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        HALF = 100000000;

        visited = new int[HALF * 2 + 2];
        water = new ArrayList<>();
        home = new ArrayList<>();

        // 1. 샘물 입력
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            int pos = Integer.parseInt(st.nextToken());
            visited[pos+HALF] = 1;
            water.add(pos);
        }

        Collections.sort(water);

        int round = 1;
        loop: while(K > 0){
            for(int i=0; i<water.size(); i++){
                int cur = water.get(i); // 샘물 좌표
                int left = cur - round; // 왼쪽 좌표
                int right = cur + round; // 오른쪽 좌표

                if(left >= HALF * -1
                            && visited[HALF+left] == 0){
                    
                    // 2. 샘물과의 최소값 구하기
                    visited[HALF+left] = getMinDis(left, i);
                    home.add(left);
                    if(--K <= 0) break loop;
                }
                if(right <= HALF
                            && visited[HALF+right] == 0){

                    // 2. 샘물과의 최소값 구하기
                    visited[HALF+right] = getMinDis(right, i);
                    home.add(right);
                    if(--K <= 0) break loop;
                }
            }
            round++;
        }
        int sum = 0;
        for(int i=0; i < home.size(); i++){
            sum += visited[HALF+home.get(i)];
        }
        System.out.println(sum);
    }

    /**
     * pos 위치에서 샘물과의 최소값 계산
     */
    private static int getMinDis(int pos, int idx) {
        int prev, cur, next;
        prev = cur = next = water.get(idx);

        if(idx - 1 >= 0 ) prev = water.get(idx-1);
        if(idx + 1 < N) next = water.get(idx+1);
        cur = water.get(idx);


        return Math.min(dis(pos, prev),
                Math.min(dis(pos, cur), dis(pos, next)));
    }

    public static int dis(int from, int to){
        if(from > to) return from - to;
        else return to - from;
    }

}
