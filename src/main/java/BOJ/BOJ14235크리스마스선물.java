package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ14235크리스마스선물 {

    static int N;
    static PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cur = Integer.parseInt(st.nextToken());
            if(cur == 0){
                if(q.size() == 0) System.out.println("-1");
                else System.out.println(q.poll());
            }else{
                for(int j=0; j < cur; j++){
                    q.add(Integer.parseInt(st.nextToken()));
                }
            }
        }
    }
}
