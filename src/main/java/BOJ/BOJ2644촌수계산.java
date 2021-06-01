package BOJ;

import java.util.*;

public class BOJ2644촌수계산 {

    static int N, K;
    static int[][] relation;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int target1 = sc.nextInt();
        int target2 = sc.nextInt();

        relation = new int[N+1][+1];

        K = sc.nextInt();
        for(int i=0; i<K; i++){
            int parent = sc.nextInt();
            int child = sc.nextInt();

            relation[child][parent] = 1;
            relation[parent][child] = 1;
        }


    }



}

