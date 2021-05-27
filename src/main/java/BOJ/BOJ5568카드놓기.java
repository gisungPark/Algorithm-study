package BOJ;

import java.util.*;

public class BOJ5568카드놓기 {

    static int N, K;
    static int[] sel;
    static boolean[] selected;
    static Set<String> set;
    static List<Integer> list;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        list = new ArrayList<>();

        for(int i=0; i<N; i++){
            list.add(sc.nextInt());
        }

        int ans = 0;
        sel = new int[K];
        selected = new boolean[N];
        set = new HashSet<>();

        perm(0, 0);

        for(String s: set){
            ans++;
        }

        System.out.println(ans);
    }
    public static void perm(int idx, int cnt){
        if(idx == K){
            // K개 선택완료!
            String sub = "";
            for(int i=0; i<K; i++){
                sub += Integer.toString(sel[i]);
            }
            set.add(sub);
            return;
        }
        for(int i=0; i<N; i++){
            if(selected[i]) continue;

            selected[i] = true;
            sel[idx] = list.get(i);
            perm(idx+1, cnt+1);
            selected[i] = false;
        }
    }
}
