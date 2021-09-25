package BOJ;

import java.util.*;
import java.io.*;

public class BOJ20056마법사상어와파이어볼_모범답안 {

    static int N, M, K;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static List<Fireball> fireballs;
    static List<Integer>[][] map;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        fireballs = new ArrayList<>();
        map = new List[N + 1][N + 1];
        init(map);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            fireballs.add(new Fireball(r, c, m, s, dir));
        }

        System.out.println(solve());

    }

    private static void init(List<Integer>[][] map) {
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < N + 1; j++) {
                map[i][j] = new ArrayList<>();
            }
        }
    }

    private static int solve() {
        for (int i = 0; i < K; i++) {
            move();
            sum();
//            System.out.println("========================");
            for(Fireball f : fireballs) {
//                System.out.println("xx:" + f.r + " yy:" + f.c + " m:" + f.m + " s:" + f.s + " dir:" + f.dir);
            }
//            System.out.println();
        }

        int ret = 0;
        for (Fireball f : fireballs) {
            ret += f.m;
        }
        return ret;
    }

    private static void sum() {

        List<Fireball> newFireballs = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < N + 1; j++) {
                if (map[i][j].size() == 0) continue;
                if (map[i][j].size() == 1) {
                    int idx = map[i][j].get(0);
                    newFireballs.add(fireballs.get(idx));
                    continue;
                }

                int mSum = 0, sSum = 0;
                boolean even = true, odd = true;
                for (int k = 0; k < map[i][j].size(); k++) {
                    Fireball fireball = fireballs.get(map[i][j].get(k));
                    mSum += fireball.m;
                    sSum += fireball.s;
                    if (fireball.dir % 2 == 0) odd = false;
                    else even = false;
                }

                mSum /= 5;
                sSum /= map[i][j].size();

                if (mSum == 0) continue;

                for (int k = 0; k < 4; k++) {
                    if (even || odd) {
                        newFireballs.add(new Fireball(i, j, mSum, sSum, k * 2));

                    } else {
                        newFireballs.add(new Fireball(i, j, mSum, sSum, k * 2 + 1));
                    }
                }

            }
        }
        fireballs = newFireballs;
    }

    private static void move() {
        List<Integer>[][] newMap = new List[N + 1][N + 1];
        init(newMap);

        for (int i = 0; i < fireballs.size(); i++) {

            Fireball f = fireballs.get(i);
            int m = f.m;
            int s = f.s % N;
            int dir = f.dir;

            int xx = (f.r + (dx[dir] * s) + N) % N;
            int yy = (f.c + (dy[dir] * s) + N) % N;

            f.r = xx;
            f.c = yy;

            newMap[xx][yy].add(i);
        }

        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < N + 1; j++) {
                map[i][j] = newMap[i][j];
            }
        }

    }

    public static class Fireball {
        int r, c;
        int m, s, dir;

        public Fireball(int r, int c, int m, int s, int dir) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.dir = dir;
        }
    }
}
