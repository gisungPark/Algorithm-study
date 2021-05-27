package 인프런;

import java.io.*;
import java.util.*;

public class 세번째성적 {

    public static void main(String[] args) throws IOException{

        int[] buf = new int[102];
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        for(int i=0; i<N; i++){
            buf[sc.nextInt()]++;
        }
        int cnt = 0, cur = 0;
        for(cur=100; cur>=0; cur--){
            if(buf[cur] != 0) cnt++;
            if(cnt == 3) break;
        }
        System.out.println(cur);



    }
}
