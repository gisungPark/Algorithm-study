package 모의역량테스트;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWEA1949등산로조성_BFS {

	static int N, K, ans;
	static int[][] map;
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			K = sc.nextInt();
			map = new int[N][N];
			ans = Integer.MIN_VALUE;
			
			int maxValue = 0;
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					map[r][c] = sc.nextInt();
					// 1. map에서 가장 큰 값을 찾는다.
					maxValue = Math.max(maxValue, map[r][c]);
				}
			}
			
			Queue<point> q = new LinkedList<>();
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					// 2. 가장 큰 값의 위치를 시작지점으로 한다.
					if(map[r][c] == maxValue) {
						boolean[][] visited = new boolean[N][N];
						visited[r][c] = true;
						q.add(new point(r, c, maxValue, 1, false, visited));
					}
				}
			}
			
			while(!q.isEmpty()) {
				point cur = q.poll();
				int x = cur.r;
				int y = cur.c;
				ans = Math.max(ans, cur.length);
				
				// 3. 현재 위치에서 사방탐색을 진행한다.
				for(int i=0; i<4; i++) {
					int xx = x+dx[i];
					int yy = y+dy[i];
					
					if(xx<0 || xx>=N || yy<0 || yy>=N || cur.visited[xx][yy]) continue;
					// 4-1. 다음 위치가 현재위치 보다 크거나 같다면,
					if(map[xx][yy] >= cur.val) {
						// 5. 이미 한번 공사한 적이 있다면 continue
						if(cur.check) continue;
						// 6. 다음 위치가 현재위치 + k보다 크다면 continue
						if(map[xx][yy] - map[x][y] >= K) continue;
						
						for(int j=1; j<=K; j++) {
							// 7. 한칸부터 k칸만큼 줄였을 경우 각각의 경우를 q에 삽입
							if(map[xx][yy] - j < map[x][y]) {
								boolean[][] visited = copy(cur.visited);
								visited[xx][yy] = true;
								q.add(new point(xx, yy, map[xx][yy]-j, cur.length+1, true, visited));
								break;
							}
						}
						
					}else {
					// 4-2. 다음 위치가 현재위치보다 작다면 go
						boolean[][] visited = copy(cur.visited);
						visited[xx][yy] = true;
						q.add(new point(xx, yy, map[xx][yy], cur.length+1, cur.check, visited));
					}
				}
						
			}// end while
			System.out.println("#"+ tc+" " + ans);
		}// end tc
	}
	public static boolean[][] copy(boolean[][] map){
		int size = map.length;
		boolean[][] copyMap = new boolean[size][size];
		
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		return copyMap;
	}
	
	public static class point {
		int r, c, val, length;
		boolean[][] visited;
		boolean check;
		
		public point(int r, int c, int val, int length, boolean check, boolean[][] visited) {
			super();
			this.r = r;
			this.c = c;
			this.val = val;
			this.length = length;
			this.check = check;
			this.visited = visited;
		}
		
	}
}
