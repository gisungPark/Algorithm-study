package 모의역량테스트;

import java.util.*;
import java.io.*;

public class SWEA5656벽돌깨기 {

    static int T, N, W, H, ans;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;
    static Queue<int[]> q;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            map = new int[H][W];
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            ans = Integer.MAX_VALUE;
            simulation(0, mapCopy(map));
            System.out.println("#" + tc + " " + ans);
        }

    }


    private static void simulation(int cnt, int[][] roundMap) {
        if (cnt >= N) {
            ans = Math.min(ans, count(roundMap));
        } else {
            for (int c = 0; c < W; c++) { // 열 순회
                int[][] copy = mapCopy(roundMap);
                int removeIdx = 0;
                while (removeIdx < H && copy[removeIdx][c] == 0) removeIdx++;

                if (removeIdx >= H) {
                    simulation(cnt + 1, mapCopy(copy)); // 재귀 반복
                }else {
                    removeBlock(removeIdx, c, copy); // 블럭 깬다.
                    initMap(copy);                   // 블럭 재정렬
                    simulation(cnt + 1, mapCopy(copy)); // 재귀 반복
                }
            }
        }
    }

    private static void initMap(int[][] copy) {
        for (int c = 0; c < W; c++) {
           int curIdx = H-1, replaceIdx = H-1;
           while(curIdx > 0){
               if(copy[curIdx][c] != 0) curIdx--;
               else{
                   replaceIdx = curIdx;
                   while(replaceIdx >= 0){
                       if (copy[replaceIdx][c] != 0) break;
                       else replaceIdx--;
                   }

                   if(replaceIdx >= 0) {
                       copy[curIdx][c] = copy[replaceIdx][c];
                       copy[replaceIdx][c] = 0;
                   }
                   else break;
               }
           }
        }
    }

    private static void removeBlock(int r, int c, int[][] copy) {
        q = new LinkedList<>();
        q.add(new int[]{r, c});

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if (copy[cur[0]][cur[1]] > 1) {
                for (int d = 0; d < 4; d++) {   // 4방향 순회
                    int x = cur[0];
                    int y = cur[1];

                    for (int k = 1; k < copy[cur[0]][cur[1]]; k++) {
                        int xx = x + dx[d];
                        int yy = y + dy[d];
                        if (xx < 0 || xx >= H || yy < 0 || yy >= W) break;
                        if (copy[xx][yy] > 1) q.add(new int[]{xx, yy});
                        else copy[xx][yy] = 0;

                        x = xx;
                        y = yy;
                    }
                }
            }
            copy[cur[0]][cur[1]] = 0;
        }

    }

    private static int count(int[][] copy) {
        int cnt = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (copy[i][j] > 0) cnt++;
            }
        }
        return cnt;
    }

    private static int[][] mapCopy(int[][] map) {
        int[][] copy = new int[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                copy[i][j] = map[i][j];
            }
        }
        return copy;
    }

    public static void print(int[][] maps) {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                System.out.print(maps[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
}
