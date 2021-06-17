package BOJ;

import java.io.*;
import java.util.*;

public class BOJ18513샘터 {

    static int N, K, HALF;
    static Queue<int[]> water;
    static Set<Integer> set;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        HALF = 100000000;

        set = new HashSet<>();
        water = new LinkedList<>();

        // 1. 샘물 입력
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            int pos = Integer.parseInt(st.nextToken());
            set.add(pos);
            water.add(new int[]{pos, 0});
        }

        long sum = 0;

        loop: while(!water.isEmpty()){
            int[] cur = water.poll();
            int left = cur[0] - 1;
            int right = cur[0] + 1;
            // 왼쪽 비교
            if(!set.contains(left)){
                set.add(left);
                water.add(new int[]{left, cur[1]+1});
                sum += cur[1]+1;
                if(--K <= 0) break loop;

            }
            
            // 오른쪽 비교
            if(!set.contains(right)){
                set.add(right);
                water.add(new int[]{right, cur[1]+1});
                sum += cur[1]+1;
                if(--K <= 0) break loop;
            }

        }
        System.out.println(sum);
    }
}
