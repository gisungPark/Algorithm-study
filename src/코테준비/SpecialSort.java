package 코테준비;

import java.util.*;
public class SpecialSort {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] buf = new int[N];
        // 1. 입력
        for (int i = 0; i < N; i++) {
            buf[i] = sc.nextInt();
        }

        // 2. 정렬
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < N - 1; j++) {
                if (buf[i] < 0 && buf[j] < 0) {
                    if(buf[i] > buf[j]){
                        int tmp = buf[j];
                        buf[j] = buf[i];
                        buf[i] = tmp;
                    }
                } else {
                    if (buf[j] > buf[i]) {
                        int tmp = buf[j];
                        buf[j] = buf[i];
                        buf[i] = tmp;
                    }
                }
            }
        }
        for(Integer i: buf){
            System.out.print(i + " ");
        }
    }

}
