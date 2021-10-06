package BOJ;

import java.util.*;
import java.io.*;

public class BOJ19238스마트택시 {

    static int N, M, charge;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static int[][] map, dis;
    static boolean[][] visited;

    static Queue<int[]> q;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        charge = Integer.parseInt(st.nextToken());

        map = new int[21][21];
        visited = new boolean[21][21];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        q = new LinkedList<>(); // 택시 출발지
        q.add(new int[]{x, y, 0});
        visited[x][y] = true;

        for (int i = 2; i < M + 2; i++) {
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());

            map[sx][sy] = i;
            map[ex][ey] = i * 500;
        }

        solve();
        if (charge <= 0 || M > 0) System.out.println("-1");
        else System.out.println(charge);

    }

    private static void solve() {


        loop:
        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int xx = now[0] + dx[d];
                int yy = now[1] + dy[d];
                if (xx < 1 || xx > N || yy < 1 || yy > N
                        || map[xx][yy] == 1 || visited[xx][yy]) continue;
                if (map[xx][yy] == 0 || map[xx][yy] > 500) {
                    visited[xx][yy] = true;
                    q.add(new int[]{xx, yy, now[2] + 1});
                } else { // 사람을 찾은경우
                    charge -= (now[2] + 1);
//                    System.out.println("사람 [" + xx + ", " + yy + "] 연료 : " + charge);
                    if (charge <= 0) break loop;

                    // 도착지까지 가는 연산
                    goToDestination(xx, yy);
                    M--;
                    break;
                }
            }

            if (charge <= 0 || M <= 0) break;
        }

    }

    private static void goToDestination(int x, int y) {
        init();
        q.clear();
        visited[x][y] = true;
        q.add(new int[]{x, y, 0});

        loop:
        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int d = 0; d < 4; d++) {
                int xx = now[0] + dx[d];
                int yy = now[1] + dy[d];

                if (xx < 1 || xx > N || yy < 1 || yy > N
                        || map[xx][yy] == 1 || visited[xx][yy]) continue;

                if (map[xx][yy] == (map[x][y] * 500)) {
                    now[2]++;
                    charge -= now[2];
                    if(charge < 0) break loop;

                    charge += (now[2]*2);
//                    System.out.println("소비연료: " + now[2]);
//                    System.out.println("도착지 [" + xx + ", " + yy + "] 연료 : " +charge);
                    map[x][y] = map[xx][yy] = 0;

                    q.clear();  //  큐 초기화
                    init();     // 방문 배열 초기화
                    q.add(new int[]{xx, yy, 0});
                    visited[xx][yy] = true;
                    break loop;
                } else {
                    visited[xx][yy] = true;
                    q.add(new int[]{xx, yy, now[2] + 1});
                }
            }
        }
    }

    private static void init() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                visited[i][j] = false;
            }
        }
    }

}
