package BOJ;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ16637괄호추가하기 {

    static int N, ans;
    static char[] num;
    static char[] op;
    static StringBuilder sb;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        sc.nextLine();

        num = new char[N/2+1];
        op = new char[N/2];
        String str = sc.nextLine();
        char[] express = str.toCharArray();

        int j=0, k = 0;
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) num[k++] = express[i];
            else op[j++] = express[i];
        }
        ans = 0;
        DFS("", 0, false);
        System.out.println(ans);
    }

    private static void DFS(String express, int idx, boolean flag) {
        if(idx >= op.length){
            // 계산해서 최댓값 조회
            if(!flag) {
                sb = new StringBuilder(express);
                sb.append(num[idx]);
                express = sb.toString();
            }
            System.out.println(express+" = " + calc(express));
            ans = Math.max(ans, calc(express));
        }else {
            sb = new StringBuilder(express);
            // 2. 이전 라운드 가로 o
            if(flag){
                sb.append(op[idx]);
                DFS(sb.toString(), idx+1, false);
            }else{
                // 3 이전 라운드에서 가로 x
                // 3-1. 이번 라운드 가로 x
                sb = new StringBuilder(express);
                sb.append(num[idx]).append(op[idx]);
                DFS(sb.toString(), idx+1, false);

                // 3-2. 이번 라운드 가로 o
                sb = new StringBuilder(express);
                int res = calc(num[idx], num[idx + 1], op[idx]);
                sb.append(res);
                DFS(sb.toString(), idx+1, true);
            }

        }
    }

    private static int calc(String express) {
        List<Integer> num = new ArrayList<>();
        List<Character> op = new ArrayList<>();

        int n = 0;
        for(int i = 0; i<express.length(); i++){
            // 연산잔의 위치 확인
            if(express.charAt(i) == '+' || express.charAt(i) == '-' || express.charAt(i) == '*'){
                op.add(express.charAt(i));
                num.add(n);
                n = 0;
            }else{
                n = n * 10 + (express.charAt(i)-'0');
            }
        }
        num.add(n);

        for (int i = 0; i < op.size(); i++) {
            num.set(i+1, calc(num.get(i), num.get(i+1), op.get(i)));
        }
        return num.get(op.size());
    }
    private static int calc(Integer num1, Integer num2, char op) {
        if(op == '+') return num1 + num2;
        if(op == '-') return num1 - num2;
        if(op == '*') return num1 * num2;
        return -1;
    }
    private static int calc(char num1, char num2, char op) {
        int first = num1 - '0';
        int second = num2 - '0';
        if(op == '+') return first + second;
        if(op == '-') return first - second;
        if(op == '*') return first * second;
        return -1;
    }


}
