package 모의역량테스트;

import java.util.Scanner;

public class SWEA1949등산로조성 {

	static int N, K, ans;
	static int[][] map;
	static boolean[][] visited;
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=1; tc<=T; tc++) {
			N = sc.nextInt();
			K = sc.nextInt();
			map = new int[N][N];

			int maxVal = 0;
			// 1. map 입력
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = sc.nextInt();
					maxVal = Math.max(maxVal, map[i][j]);
				}
			}
			
			visited = new boolean[N][N];
			ans = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] == maxVal) {
						visited[i][j] = true;
						DFS(i, j, map[i][j], 1, false);
						visited[i][j] = false;
					}
				}
			}
			System.out.println("#"+tc+" " + ans);
		}// end tc
	}
	private static void DFS(int x, int y, int val, int len, boolean check) {
		ans = Math.max(ans, len);
		for(int i=0; i<4; i++) {
			int xx = x+dx[i];
			int yy = y+dy[i];
			
			if(xx<0 || xx>=N || yy<0 || yy>=N
					|| visited[xx][yy] ) continue;
			
			if(map[xx][yy] - val >= K) continue;
			if(map[xx][yy] < val) {
				// 1. 높이 조절없이 낮은곳으로 이동하기
				visited[xx][yy] = true;
				DFS(xx, yy, map[xx][yy], len+1, check);
				visited[xx][yy] = false;
				
			}else {
				// 2. 높이 조절하고 이동하기
				if(check) continue;
				for(int j=1; j<=K; j++) {
					if(map[xx][yy] - j < val) {
						visited[xx][yy] = true;
						DFS(xx, yy, map[xx][yy]-j, len+1, true);
						visited[xx][yy] = false;
						break;
					}
				}
			}
		}
	}
}




















