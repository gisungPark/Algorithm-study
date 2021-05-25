package BOJ;

import java.util.Scanner;

public class BOJ1593문자해독 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ans = 0, count = 0;
        int subL = sc.nextInt();
        int mainL = sc.nextInt();
        sc.nextLine();

        char[] sub = sc.nextLine().toCharArray();
        char[] main = sc.nextLine().toCharArray();
        int[] flag1 = new int[200];
        int[] flag2 = new int[200];

        for(int i=0; i<subL; i++){
            flag1[sub[i]]++;
        }

        for(int i=0; i<subL; i++){
            flag2[main[i]]++;
        }

        if(compare(flag1, flag2)) ans++;
        for(int i= subL; i<mainL; i++){
            flag2[main[i-subL]] --;
            flag2[main[i]]++;

            if(compare(flag1, flag2)) ans++;
        }
        System.out.println(ans);
    }
    public static boolean compare (int[] a, int[] b){

        for(int i=0; i<a.length; i++){
            if(a[i] != b[i]) return false;
        }
        return true;
    }
}
