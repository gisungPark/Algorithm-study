package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2422한윤정이아이스크림을사먹는데 {


    static int N, M, ans;
    static int[] sel;
    static boolean[] visited;
    static boolean[][] notRec;
    public static void main(String[] args) throws  Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sel = new int[3];
        visited = new boolean[N+1];
        notRec = new boolean[N+1][N+1];
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            notRec[a][b] = true;
            notRec[b][a] = true;
        }

        ans = 0;
        process();
        System.out.println(ans);
    }

    private static void process() {
        combi(1, 0);
    }

    private static void combi(int start, int idx) {
        if(idx == 3){
            if(!check()) ans++;
        }else{
            for(int i=start; i<N+1; i++){
                if(visited[i]) continue;
                visited[i] = true;
                sel[idx] = i;
                combi(i+1, idx+1);
                visited[i] = false;
            }
        }
    }

    private static boolean check() {
        return notRec[sel[0]][sel[1]] || notRec[sel[1]][sel[2]] || notRec[sel[2]][sel[0]];
    }


}
