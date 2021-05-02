package 코테준비;

import java.util.Scanner;

public class JollyJumpers {

    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        int[] arr = new int[N];
        int[] ans = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();

            if(i>0){
                int idx = Math.abs(arr[i] - arr[i - 1]);
                ans[idx] = 1;
            }
        }

        for(int i=1; i<N; i++){
            if(ans[i] == 0) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }
}
