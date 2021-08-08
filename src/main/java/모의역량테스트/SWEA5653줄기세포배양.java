package 모의역량테스트;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA5653줄기세포배양 {

    static int T, N, M, K, X, ans;
    static int SIZE = 800;
    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1};
    static int[][] map;
    static Queue<Cell> inActiveQ, activeQ, dieQ;
    static final char WAIT = 'W', ACTIVE = 'A', DIE = 'D';

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        map = new int[SIZE][SIZE];
        inActiveQ = new LinkedList<>();
        activeQ = new PriorityQueue<>();
        dieQ = new LinkedList<>();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            // 초기화
            if (tc != 1) {
                init();
                inActiveQ.clear();
                activeQ.clear();
                dieQ.clear();
            }

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int val = Integer.parseInt(st.nextToken());
                    map[i + SIZE / 2][j + SIZE / 2] = val;
                    if (val == 0) continue;

                    inActiveQ.add(new Cell((i + SIZE / 2), (j + SIZE / 2), val)); // 비활성화 큐에 삽입
                }
            }
            process();
            count();
            System.out.println("#" + tc + " " + ans);
        }

    }

    private static void count() {
        ans = inActiveQ.size() + activeQ.size();
        for (int i = 0; i < dieQ.size(); i++) {
            Cell now = dieQ.poll();
            if (now.state != DIE) ans++;
        }
    }

    private static void process() {
        int time = 0;
        while (++time <= K) {
            int inActiveLen = inActiveQ.size();
            int activeLen = activeQ.size();
            int dieLen = dieQ.size();

            for (int i = 0; i < dieLen; i++) {
                Cell now = dieQ.poll();
                now.aging();
                if(now.state != DIE) dieQ.add(now);
            }

            // 활성화 상태
            for (int i = 0; i < activeLen; i++) {
                Cell now = activeQ.poll();
                for (int d = 0; d < 4; d++) {
                    int xx = now.x + dx[d];
                    int yy = now.y + dy[d];
                    // 세포 분열
                    if (map[xx][yy] != 0) continue;
                    map[xx][yy] = now.val;
                    inActiveQ.add(new Cell(xx, yy, now.val));
                }
                now.aging();
                dieQ.add(now);
            }

            for (int i = 0; i < inActiveLen; i++) {
                Cell now = inActiveQ.poll();
                now.aging();
                if (now.state == ACTIVE) activeQ.add(now);
                else inActiveQ.add(now);
            }

        }
    }

    private static void print() {
        for (int i = 0 + SIZE / 2 - 10; i < N + SIZE / 2 + 10; i++) {
            for (int j = 0 + SIZE / 2 - 10; j < N + SIZE / 2 + 10; j++) {
                System.out.print(map[i][j] + " ");

            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    private static void init() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = 0;
            }
        }
    }

    public static class Cell implements Comparable<Cell> {
        public int x, y, val, time;
        public char state;

        public Cell(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
            this.time = 0;
            this.state = WAIT;
        }

        public Cell(int x, int y, int val, int time, char state) {
            this.x = x;
            this.y = y;
            this.val = val;
            this.time = time;
            this.state = state;
        }

        public void aging() {
            if (val == ++this.time) {
                this.time = 0;

                if (this.state == WAIT) this.state = ACTIVE;
                else if (this.state == ACTIVE) this.state = DIE;
            }
        }

        @Override
        public int compareTo(Cell o) {
            return o.val - this.val;
        }
    }
}
