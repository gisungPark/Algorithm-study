package BOJ;

import java.util.*;
import java.io.*;

public class BOJ6064카잉달력 {

    static int T, M, N, xx, yy, ans;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            xx = Integer.parseInt(st.nextToken());
            yy = Integer.parseInt(st.nextToken());
            xx--;
            yy--;
            ans = -1;

            for (int k = xx; k < (M * N); k += M) {
                if (k % N == yy) {
                    ans = k + 1;
                    break;
                }
            }
            System.out.println(ans);
        }
    }


}
