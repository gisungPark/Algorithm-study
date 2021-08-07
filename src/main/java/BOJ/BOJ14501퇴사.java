package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14501퇴사 {

    static int N, max;
    static int[][] arr;
    static int[][] sum;
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1][2];
        sum = new int[N+1][N+1];

        for(int i=1; i<N+1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            arr[i][0] = t;
            arr[i][1] = p;
        }

        max = 0;
        for(int i=1; i<N+1; i++){
            for(int j=1; j<N+1; j++) {
                int nextIdx = arr[j][0] + j;
                if (nextIdx > N) continue;
                sum[i][nextIdx] = Math.max(sum[i][nextIdx], (sum[i-1][j] + arr[i][1]));
            }
        }


        System.out.println(max);
    }
}
