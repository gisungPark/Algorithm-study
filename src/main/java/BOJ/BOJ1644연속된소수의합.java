package BOJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ1644연속된소수의합 {

    static int N, ans;
    static boolean[] map;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        //2. 소수 판별을 위한 맵 생성
        map = new boolean[N+2];
        Queue<Integer> q = new LinkedList<>();

        ans = 0;
        int sum = 0;
        for(int i=2; i<=N; i++){
            // 2-1. 소수가 아니면 건너뛴다
            if(map[i]) continue;

            int tmp = 0;
            int idx = i, j = 2;
            while(tmp <= N){
                tmp = idx * j++;
                if(tmp <= N) map[tmp] = true;
            }

            // 2-2. 소수 연산 처리
            q.add(i);
            //3. 처음부터 더해가며, N보다 클경우 앞에서 뺀다. -> 큐 이용
            sum += i;
            if(sum > N){
                while(sum > N){
                    sum -= q.poll();
                }
            }
            if(sum == N) ans++;
        }
        System.out.println(ans);
    }
}
