package BOJ;

import java.util.Scanner;

public class BOJ18868멀티버스 {


    static int M, N, ans;
    static int[] sel;
    static int[][] nums;
    static boolean[] visited;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();

        sel = new int[2];
        nums = new int[M][N];
        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                nums[i][j] = sc.nextInt();
            }
        }

        visited = new boolean[M];
        for(int i=0; i<M-1; i++){
            boolean flag = false;
            for(int j= i+1; j<M; j++){
                // 균등한 행성이다.
                if(Combi(i, j, 0, 0)){
                    ans++;
                }
            }
        }
        System.out.println(ans);

    }

    private static boolean Combi(int aa, int bb, int idx, int selIdx) {
        if(selIdx >= 2){
            int first = sel[0];
            int second = sel[1];

            if(nums[aa][first] > nums[aa][second] && nums[bb][first] <= nums[bb][second]) return false;
            if(nums[aa][first] == nums[aa][second] && nums[bb][first] != nums[bb][second]) return false;
            if(nums[aa][first] < nums[aa][second] && nums[bb][first] >= nums[bb][second]) return false;
        }else{

            for(int i=idx; i<N; i++){
                sel[selIdx] = i;
                if(!Combi(aa, bb,idx+1, selIdx+1)) return false;
            }

        }
        return true;
    }

}
