package 알고리즘스터디;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 가장먼노드 {

	public static List<Integer>[] adj;
	public static int[] dis;
	public static int solution(int n, int[][] edge) {

		dis = new int[n+1];
		adj = new ArrayList[n+1];
		for(int i=0; i<=n; i++) {
			adj[i] = new ArrayList<>();
		}
		
		// 1. 그래프 간선 연결 초기화
		int from = 0, to = 0;
		for(int[] e: edge) {
			from = e[0];
			to = e[1];
			adj[from].add(to);
			adj[to].add(from);
		}
		
		// 2. BFS
		BFS();
		
		int ans = 0;
		int max = -1;
		for(int i=1; i<dis.length; i++) {
			max = Math.max(dis[i], max);
		}
		
		for(int i=1; i<dis.length; i++) {
			if(dis[i] == max) ans++;
		}
        return ans;
    }
	private static void BFS() {
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		dis[1] = 1;
		
		// 3. 그래프 순회
		while(!q.isEmpty()) {
			int cur  = q.poll();
			for(int i=0; i<adj[cur].size(); i++) {
				int next = adj[cur].get(i);
				// 4. 이미 방문한 노드 건너띄기
				if(dis[next] != 0) continue;
				
				// 5. 방문 처리후 큐에 삽입
				dis[next] = dis[cur] + 1;
				q.add(next);
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println(
				solution(6, new int[][] {
					{3,6},{4,3},{3,2},{1,3},{1,2},{2,4}, {5,2}
				}
					
		));
	}
}
