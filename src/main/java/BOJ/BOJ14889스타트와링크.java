package BOJ;

import java.util.*;
import java.io.*;

public class BOJ14889스타트와링크 {

    static int N, ans;
    static boolean[] sel;
    static int[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        sel = new boolean[N];
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        ans = 1000000000;
        solve();
        System.out.println(ans);
    }

    private static void solve() {
        powerSet(0, 0);

    }

    private static void powerSet(int idx, int cnt) {
        if (idx >= N) return;
        if (cnt >= N / 2) {
            int aRoot = 0, bRoot = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (sel[i] && sel[j]) {
                        aRoot += map[i][j];
                        aRoot += map[j][i];

                    } else if (!sel[i] && !sel[j]) {
                        bRoot += map[i][j];
                        bRoot += map[j][i];

                    }
                }
            }
            aRoot /= 2;
            bRoot /= 2;
            ans = Math.min(ans, Math.abs(aRoot - bRoot));

        } else {
            sel[idx] = true;
            powerSet(idx + 1, cnt + 1);
            sel[idx] = false;
            powerSet(idx + 1, cnt);
        }

    }
}
