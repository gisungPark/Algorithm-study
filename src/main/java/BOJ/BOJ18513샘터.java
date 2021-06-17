package BOJ;

import java.io.*;
import java.util.*;

public class BOJ18513샘터 {

    static int N, K, LEN, HALF;
    static List<Integer> water, home;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        LEN = 200000002;
        HALF = 100000000;

        visited = new boolean[LEN];
        water = new ArrayList<>();
        home = new ArrayList<>();

        // 1. 샘물 입력
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            int pos = Integer.parseInt(st.nextToken());
            visited[pos+HALF] = true;
            water.add(pos);

        }
        int round = 1;
        loop: while(K > 0){
            for(int i=0; i<water.size(); i++){
                int cur = water.get(i); // 샘물 좌표
                int left = cur - round; // 왼쪽 좌표
                int right = cur + round; // 오른쪽 좌표
                if(!visited[HALF+left]){
                    visited[HALF+left] = true;
                    home.add(left);
                    if(--K <= 0) break loop;
                }
                if(!visited[HALF+right]){
                    visited[HALF+right] = true;
                    home.add(right);
                    if(--K <= 0) break loop;
                }
            }
            round++;
        }

        Collections.sort(water);
        Collections.sort(home);

        // 3. 불행도 계산
        int sum = 0;
        for(int i=0; i<home.size(); i++){
            int min = dis(home.get(i), water.get(0));
            for(int j=1; j<water.size(); j++){
                int next = dis(home.get(i), water.get(j));
                if(next > min) break;
                else min = next;
            }
            sum += min;
        }
        System.out.println(sum);

    }

    public static int dis(int from, int to){
        if(from > to) return from - to;
        else return to - from;
    }

}
