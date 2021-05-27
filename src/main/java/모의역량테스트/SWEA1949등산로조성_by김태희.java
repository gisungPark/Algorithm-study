package 모의역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1949등산로조성_by김태희 {

	static int N, K, max, top;
	static int[][] map;
	static boolean[][] visited;
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			visited = new boolean[N][N];
			max = 0; // 최장 경로 길이
			top = 0; // 봉우리 높이
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					top = Math.max(top, map[i][j]);
				}
			}
			findTop();
			System.out.println("#"+tc+" "+max);
		}
	}
	private static void findTop() {

		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == top) makeLoad(i, j, map[i][j], false, 1);
			}
		}
	}
	
	// DFS
	private static void makeLoad(int r, int c, int height, boolean isUsed, int distance) {
		
		max = Math.max(max, distance);
		visited[r][c] = true;
		
		int nr, nc;
		for(int d=0; d<4; d++) {
			nr = r+dx[d];
			nc = c+dy[d];
			
			if(nr<0 || nr>=N || nc<0 || nc>=N || visited[nr][nc]) continue;
			if(height > map[nr][nc]) {
				 makeLoad(nr, nc, map[nr][nc], isUsed, distance+1);
			}else if(!isUsed && map[nr][nc] - K < height) {
				makeLoad(nr, nc, height-1, true, distance+1);
			}
		}
		
		visited[r][c] = false;
	}
}




















