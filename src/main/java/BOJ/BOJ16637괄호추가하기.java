package BOJ;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ16637괄호추가하기 {

    static int N;
    static long ans;
    static List<Integer> num;
    static List<Character> op;
    static StringBuilder sb;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        sc.nextLine();

        String str = sc.nextLine();
        char[] express = str.toCharArray();

        num = new ArrayList<>();
        op = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) num.add(express[i] - '0');
            else op.add(express[i]);
        }

        ans = Long.MIN_VALUE;
        DFS(num.get(0), 0);
        System.out.println(ans);
    }

    private static void DFS(long sum, int idx) {
        if(idx >= op.size()){
            ans = Math.max(ans, sum);
        }else{
            // 1. 가로 x
            long cur = calc(sum, num.get(idx + 1), op.get(idx));
            DFS(cur, idx+1);

            // 2. 가로 o
            if(idx+1 < op.size()){
                long next = calc(num.get(idx+1), num.get(idx+2), op.get(idx+1));
                cur = calc(sum, next, op.get(idx));
                DFS(cur, idx+2);
            }
        }

    }


    private static long calc(long num1, long num2, char op) {
        if(op == '+') return num1 + num2;
        if(op == '-') return num1 - num2;
        if(op == '*') return num1 * num2;
        return -1;
    }


}
