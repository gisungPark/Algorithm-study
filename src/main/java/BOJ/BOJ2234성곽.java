package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2234성곽 {

    static int N, M;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    static int[] roomSize;
    static int[][] map, islandNumber;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());


        // 1. 입력
        map = new int[N][M];
        islandNumber = new int[N][M];
        roomSize = new int[N*M+10];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        //2. 방 갯수, 방 크기 초기화
        int roomNum = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (islandNumber[i][j] != 0) continue;
                BFS(i, j, roomNum);
                roomNum++;
            }
        }

        // 3. 가장 넓은 방 찾기
        int roomMaxSize = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                roomSize[islandNumber[i][j]]++;
                roomMaxSize = Math.max(roomMaxSize, roomSize[islandNumber[i][j]]);
            }
        }

        // 4. 2개의 방을 합친 경우 중 가장 큰 방 찾기
        int subTwoRooms = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                for (int d = 0; d < 4; d++) {
                    if ((map[i][j] & (1 << d)) != 0) {

                        int xx = i + dx[d];
                        int yy = j + dy[d];
                        if (xx < 0 || xx >= N || yy < 0 || yy >= M) continue;
                        if(islandNumber[i][j] == islandNumber[xx][yy]) continue;

                        subTwoRooms = Math.max(subTwoRooms, (roomSize[islandNumber[i][j]] + roomSize[islandNumber[xx][yy]]));
                    }

                }
            }
        }
        System.out.println(roomNum - 1);
        System.out.println(roomMaxSize);
        System.out.println(subTwoRooms);

    }

    // 방 찾기
    private static void BFS(int r, int c, int roomNum) {

        Queue<int[]> q = new LinkedList<>();
        islandNumber[r][c] = roomNum;
        q.add(new int[]{r, c});

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                if ((map[cur[0]][cur[1]] & (1 << i)) == 0) {
                    int xx = cur[0] + dx[i];
                    int yy = cur[1] + dy[i];
                    if (xx < 0 || xx >= N || yy < 0 || yy >= M || islandNumber[xx][yy] != 0) continue;
                    islandNumber[xx][yy] = roomNum;
                    q.add(new int[]{xx, yy});
                }
            }
        }
    }

    public static void print(int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

}
