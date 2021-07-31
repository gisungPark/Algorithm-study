package 모의역량테스트;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA5648원자소멸시뮬레이션 {

    static int T, N, ANS;
    static int dx[] = {1, -1, 0, 0};
    static int dy[] = {0, 0, -1, 1};
    static int[][] map = new int[4005][4005];
    static List<Atom> atoms;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            ANS = 0;
            atoms = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());

                y = (y + 1000) * 2;
                x = (x + 1000) * 2;
                map[y][x]++;
                atoms.add(new Atom(y, x, d, k));
            }
            solve();
            System.out.println("#" + tc + " " + ANS);

        }
    }

    private static void solve() {
        while (true) {
            move();
            collision();
            removeAtoms();
            if (atoms.size() < 1) break;
        }

    }

    private static void move() {
        for (Iterator<Atom> it = atoms.listIterator(); it.hasNext(); ) {
            Atom cur = it.next();
            int xx = cur.r + dx[cur.dir];
            int yy = cur.c + dy[cur.dir];

            map[cur.r][cur.c]--; // 현재 위치 원소값 감소!
            if (xx < 0 || xx >= 4005 || yy < 0 || yy >= 4005) it.remove();
            else {
                cur.r = xx;
                cur.c = yy;
                map[xx][yy]++;
            }
        }
    }

    private static void removeAtoms() {
        for (Iterator<Atom> it = atoms.listIterator(); it.hasNext(); ) {
            Atom next = it.next();
            if (!next.life) it.remove();
        }
    }

    private static void collision() {
        for (int i = 0; i < atoms.size(); i++) {
            Atom cur = atoms.get(i);

            if (map[cur.r][cur.c] > 1 && cur.life) {
                for (int j = 0; j < atoms.size(); j++) {
                    Atom a = atoms.get(j);
                    if (a.r == cur.r && a.c == cur.c) {
                        a.life = false;
                        ANS += a.k;
                    }
                }
                map[cur.r][cur.c] = 0;
            }
        }

    }



    public static class Atom {
        int r, c, k, dir;
        boolean life;

        public Atom(int r, int c, int dir, int k) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.k = k;
            life = true;
        }
    }

}
