package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ19236청소년상어 {

	static ArrayList<Fish> list;
	static Shark shark;
	static int ans = 0;
	static int[] dx = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dy = { 1, 0, -1, -1, -1, 0, 1, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// 1. input
		int[][] map = new int[4][4];
		list = new ArrayList<>();
		list.add(new Fish(-1, -1, -1, -1));	// 0번째 물고기 임의값
		list.get(0).life = false;
		
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(in.readLine());
			Fish fish = null;
			int no = 0, pos = 0;
			for (int j = 0; j < 4; j++) {
				no = Integer.parseInt(st.nextToken());
				pos = Integer.parseInt(st.nextToken());
				fish = new Fish(i, j, no, pos);
				list.add(fish);
				map[i][j] = no;
			}
		}

		Collections.sort(list);
		int no = map[0][0];
		map[0][0] = 0;
		Solve(0, 0, list.get(no).dir, copyOfMap(map), 15, no);

		System.out.println(ans);
	}

							// 상어 좌표, 방향, 현재 맵, 물고기 수, 상어가 먹은 물고기 점수
	private static void Solve(int x, int y, int dir, int[][] map, int fishCnt, int point) {
		ans = Math.max(ans, point);
		//1. 물고기 이동
		move(map, x, y);
		
		//2. 상어 이동
		int xx = x;
		int yy = y;
		for(int i=1; i<4; i++) {
			xx = xx + dx[dir%8];
			yy = yy + dy[dir%8];
			
			if(isPossible(xx, yy, x, y)) {
				if(map[xx][yy] == 0) continue;
				Fish f = list.get(map[xx][yy]);
				f.life = false;	// 물고기 사망
				Solve(xx, yy, f.dir, copyOfMap(map), fishCnt-1, point+f.no);
				f.life = true;
			}
		}
		
	}




	private static int[][] copyOfMap(int[][] map) {
		int[][] copyMap = new int[4][4];
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		return copyMap;
	}

	private static void move(int[][] map, int sx, int sy) {
		Collections.sort(list);

		// 물고기 자리 이동
		for (int i = 0; i < list.size(); i++) {
			Fish fish = list.get(i);
			if (!fish.life) continue; // 이미 죽은 물고기라면 continue;
			
			int dir = fish.dir;
			int xx = fish.x + dx[dir % 8];
			int yy = fish.y + dy[dir % 8];
			while (!isPossible(xx, yy, sx, sy)) {
				dir++;
				xx = fish.x + dx[dir % 8];
				yy = fish.y + dy[dir % 8];
			}
			fish.dir = dir;
			if (map[xx][yy] != 0) {
				// 물고기 위치 변경
				Fish obj = list.get(map[xx][yy]);
				
				swap(fish.x, obj.x);
				swap(fish.y, obj.y);
			} else {
				map[fish.x][fish.y] = 0;
				fish.go(xx, yy);
				map[xx][yy] = fish.no;
			}
		}

	}

	private static void swap(int a, int b) {
		int tmp = a;
		a = b;
		b = tmp;
	}

	// 물고기가 다음 위치로 이동가능한지
	private static boolean isPossible(int xx, int yy, int sx, int sy) {
		if(xx == sx && yy == sy) return false;
		if (xx < 0 || xx >= 4 || yy < 0 || yy >= 4) return false;
		return true;
	}

	static class Fish implements Comparable<Fish> {

		public int x, y, no, dir;
		public boolean life;

		public Fish(int x, int y, int no, int dir) {
			this.x = x;
			this.y = y;
			this.no = no;
			this.dir = dir;
			this.life = true;
		}

		// 해당좌표로 이동
		public void go(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Fish o) {
			return this.no - o.no;
		}

		@Override
		public String toString() {
			return "Fish [x=" + x + ", y=" + y + ", no=" + no + ", dir=" + dir + ", life=" + life + "]";
		}

	}

	static class Shark extends Fish {

		public int eatPoint;

		public Shark(int x, int y, int no, int dir) {
			super(x, y, no, dir);
			eatPoint = 0;
		}

		public int getEatPoing() {
			return eatPoint;
		}

		public int eat(int no) {
			eatPoint += no; // 상어가 먹은 물고기 번호 계산

			Fish f = list.get(no);
			go(f.x, f.y); // 위치 이동
			this.dir = f.dir; // 방향 전환
			f.life = false;
			return no;
		}

		@Override
		public String toString() {
			return "Shark [eatPoint=" + eatPoint + "dir=" + this.dir + "]";
		}

	}
}
