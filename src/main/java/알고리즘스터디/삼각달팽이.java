package 알고리즘스터디;

public class 삼각달팽이 {
	
	static int[] dx = { 1, 0, -1 };
	static int[] dy = { 0, 1, -1 };
	public static int[] solution(int n) {
		int[][] map = new int[n][n];

		int cnt = count(n);
		int idx = 0, x = 0, y = 0, num = 1;
		while (num <= cnt) {
			map[x][y] = num++;
			int xx = x + dx[idx];
			int yy = y + dy[idx];
			if (xx < 0 || xx >= n || yy < 0 || yy >= n
							|| map[xx][yy] != 0) {
				idx = ++idx % 3;
				x += dx[idx];
				y += dy[idx];
			} else {
				x = xx;
				y = yy;
			}
		}

		idx = 0;
		int[] answer = new int[cnt];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 0)
					continue;
				answer[idx++] = map[i][j];
			}
		}
		return answer;
	}

	public static int count(int n) {
		int cnt = 0;
		for (int i = 1; i <= n; i++) {
			cnt += i;
		}
		return cnt;
	}

	public static void main(String[] args) {

	}
}
