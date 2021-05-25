package BOJ;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ2584가장가까운공통조상 {

    static int T, N;
    static int[] parents;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        for(int tc = 0; tc<T; tc++){
            N = sc.nextInt();
            parents = new int[N+1];

            //1. 자기 자신으로 초기화
            for(int i=0; i<=N; i++){
                parents[i] = i;
            }

            for(int i=0; i<N-1; i++){
                int from = sc.nextInt();
                int to = sc.nextInt();

                parents[to] = from;
            }
            int A = sc.nextInt();
            int B = sc.nextInt();

            List<Integer> list1 = new ArrayList<>();
            List<Integer> list2 = new ArrayList<>();
            while(true){
                list1.add(A);
                if(parents[A] == A) break;
                A = parents[A];
            }

            while(true){
                list2.add(B);
                if(parents[B] == B) break;
                B = parents[B];
            }

            loop: for(int i=0; i<list1.size(); i++){
                for(int j=0; j<list2.size(); j++){
                    if(list1.get(i) == list2.get(j)){
                        System.out.println(list1.get(i));
                        break loop;
                    }
                }
            }
        }
    }

}
