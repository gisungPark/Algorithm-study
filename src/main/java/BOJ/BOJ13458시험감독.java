package BOJ;

import java.util.Scanner;

public class BOJ13458시험감독 {

    static int N;
    static int rooms[];
    static int mains, sub;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        rooms = new int[N];
        for(int i=0; i<N; i++){
            rooms[i] = sc.nextInt();
        }
        mains = sc.nextInt();
        sub = sc.nextInt();

        long ans = 0;
        for(int i=0; i<N; i++){
            //1. 학생수 빼기 메인 감독관이 관리할 수 있는 학생수
            int remain = rooms[i] - mains;
            ans++;
            if(remain <= 0) continue;
            ans += calc(remain, sub);
        }

        System.out.println(ans);
    }

    private static int calc(int remain, int sub) {
        if(remain <= sub) return 1;
        if(remain%sub == 0){
            return remain/sub;
        }

        return remain/sub + 1;
    }

}
