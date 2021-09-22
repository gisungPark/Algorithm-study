package BOJ;

import java.util.Scanner;

public class BOJ1978소수찾기 {

    static int N, ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        ans = 0;
        for (int i = 0; i < N; i++) {
            if (isPrime(sc.nextInt())) ans++;
        }
        System.out.println(ans);
    }

    private static boolean isPrime(int v) {
        if (v <= 1) return false;

        for (int i = 2; i * i <= v; i++) {
            if (v % i == 0) return false;
        }
        return true;
    }
}
