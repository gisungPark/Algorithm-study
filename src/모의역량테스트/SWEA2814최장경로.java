package 모의역량테스트;

import java.util.Scanner;

public class SWEA2814최장경로 {

	static int N, M, ans;
	static int adj[][];
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc=1; tc<=T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			ans = Integer.MIN_VALUE;
					
			adj = new int[N+1][N+1];
			for(int i=0; i<M; i++) {
				int start = sc.nextInt();
				int to = sc.nextInt();
				adj[start][to] = 1;
				adj[to][start] = 1;
			}
			
			for(int i=1; i<=N; i++) {
				boolean[] visited = new boolean[N+1];
				visited[i] = true;
				DFS(i, 1, visited);
			}
			
			System.out.println("#"+tc+ " " + ans);
		}// end tc 
	}
	private static void DFS(int idx, int length, boolean[] visited) {
		ans = Math.max(ans, length);
		
		for(int i=1; i<=N; i++) {
			if(adj[idx][i]==1 && !visited[i]) {
				boolean[] copyVisi = copy(visited);
				copyVisi[i] = true;
				DFS(i, length+1, copyVisi);
			}
		}
	}
	private static boolean[] copy(boolean[] visited) {

		boolean[] copy = new boolean[visited.length];
		for(int i=0; i<visited.length; i++) {
			copy[i] = visited[i];
		}
		return copy;
	}
}









