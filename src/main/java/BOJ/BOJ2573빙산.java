package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2573빙산 {

    static int N, M, ans;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] map, prev;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 1. 입력
        map = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = 0;
        int total = 1;
        // 2. 연산 시작
        while( total == 1) {
            // 3. 맵 복사
            prev = copyOf(map);
            
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(prev[i][j] == 0) continue;
                    // 4. 사방 탐색 후, 빙하 크기 감소
                    int ice = 0;

                    for(int d=0; d<4; d++){
                        int xx = i + dx[d];
                        int yy = j + dy[d];
                        if (xx < 0 || xx >= N || yy < 0 || yy >= M) continue;
                        if(prev[xx][yy] == 0) ice++;
                    }
                    map[i][j] -= ice;
                    map[i][j] = map[i][j] < 0 ? 0 : map[i][j];
                    
                }
            }
            ans++;
            int[][] forCount = copyOf(map);
            // 5. 빙하 갯수 카운팅
            total = countIceMount(forCount);

        }
        if(total == 0) System.out.println(0);
        else System.out.println(ans);
    }

    /**
     *  현재 빙하가 몇 조각으로 나눠져 있는지 체크
     */
    private static int countIceMount(int[][] map) {
        int cnt = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] != 0) {
                    cnt++;
                    map[i][j] = 0;
                    BFS(map, i, j); // BFS 탐색을 통해 연결된 빙하를 모두 지운다.
                }
            }
        }
        return cnt;
    }
    /**
     * 빙하 갯수 확인을 위해
     * i, j 위치와 연결된 빙하를 모두 지운다.
     */
    private static void BFS(int[][] map, int i, int j) {
        Queue<int[] > q = new LinkedList<>();
        q.add(new int[]{i, j});

        while(!q.isEmpty()){
            int[] cur = q.poll();

            for(int d=0; d<4; d++){
                int xx = cur[0] + dx[d];
                int yy = cur[1] + dy[d];
                if (xx < 0 || xx >= N || yy < 0 || yy >= M) continue;
                if(map[xx][yy] != 0){
                    map[xx][yy] = 0;
                    q.add(new int[]{xx,yy});
                }
            }
        }
    }

    /**
     * 이차원 배열 복사 함수 
     */
    private static int[][] copyOf(int[][] map) {
        int[][] newMap = new int[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                newMap[i][j] = map[i][j];
            }
        }
        return newMap;
    }

}
