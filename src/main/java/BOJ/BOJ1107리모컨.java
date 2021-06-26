package BOJ;

import java.util.Scanner;

public class BOJ1107리모컨 {


    static int N, M, size, maxNum, ans;
    static boolean[] nums;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        nums = new boolean[10];
        maxNum = Integer.MAX_VALUE;
        for (int i = 0; i < M; i++) {
            int n = sc.nextInt();
            nums[n] = true; // 사용불가는 true 처리!
        }

        if (N == 100) System.out.println(0);
        else {
            ans = Math.abs(N - 100);
            PowerSet(0, 0);
            System.out.println(ans);
        }
    }

    /**
     * 부분 집합
     */
    private static void PowerSet(int num, int cnt) {
        for (int i = 0; i < 10; i++) {
            if (nums[i]) continue;
            int newNum = num * 10 + i;
            ans = Math.min(ans, Math.abs(N - newNum) + cnt + 1);
            if (newNum != 0 && newNum < N) PowerSet(newNum, cnt + 1);
        }
    }

}
