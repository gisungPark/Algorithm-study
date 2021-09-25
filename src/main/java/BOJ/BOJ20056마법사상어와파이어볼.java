package BOJ;

import javax.xml.transform.Source;
import java.util.*;
import java.io.*;

public class BOJ20056마법사상어와파이어볼 {

    static int N, M, K;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static Queue<Fireball> q;
    static Map<Integer, List<Fireball>> info;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        q = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            q.add(new Fireball(r, c, m, s, dir));
        }

        System.out.println(solve());

    }

    private static int solve() {

        info = new HashMap<>();

        for(int k=0; k<K; k++){
            int len = q.size();
            for (int i = 0; i < len; i++) {
                Fireball now = q.poll();

                int xx = (now.r + dx[now.dir]*now.s + N)%N;
                int yy = (now.c+dy[now.dir]*now.s + N)%N;

                if (!info.containsKey(xx * 100 + yy)) {
                    List<Fireball> f = new ArrayList<>();
                    f.add(now);
                    info.put(xx * 100 + yy, f);
                } else {
                    List<Fireball> fireballs = info.get(xx * 100 + yy);
                    fireballs.add(now);
                }

            }

            for (Integer key : info.keySet()) {
                List<Fireball> fireballs = info.get(key);
                int r = key / 100;
                int c = key % 100;

                if (fireballs.size() == 1) {
                    Fireball fireball = fireballs.get(0);
                    q.add(new Fireball(r, c, fireball.m, fireball.s, fireball.dir));
                } else {
                    int m = 0, s = 0, even = 0, odd = 0;
                    for (Fireball f : fireballs) {
                        m += f.m;
                        s += f.s;
                        if(f.dir%2 == 0) even++;
                        else odd++;
                    }

                    int cnt = fireballs.size();
                    if(m/5 < 1) continue;

                    if (even == cnt || odd == cnt) {

                        for (int i = 0, j = 0; j < 4; i += 2, j++) {
                            q.add(new Fireball(r, c, m / 5, s / cnt, i));
                        }
                    }else{
                        for (int i = 1, j = 0; j < 4; i += 2, j++) {
                            q.add(new Fireball(r, c, m / 5, s / cnt, i));
                        }
                    }

                }
            }
            info.clear();
        }
        int ans = 0;
        for (Fireball fireball : q) {
                ans += fireball.m;
        }
        return ans;
    }

    public static class Fireball {
        int r, c, m, s, dir;

        public Fireball(int r, int c, int m, int s, int dir) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.dir = dir;
        }
    }
}
