package 알고리즘스터디;

import java.util.Arrays;
import java.util.Comparator;

public class 섬연결하기 {

	static int ans;
	static int[] parents;
	public static int solution(int n, int[][] costs) {
		
		// 1. 다리 건설의 cost값을 기준으로 오름차순 정렬
		Arrays.sort(costs, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2]-o2[2];
			}
		});
		
		
		int cnt = 0, from = 0, to = 0;
		ans = 0;
		parents = new int[n];
		
		// 2. 각 정점의 부모를 자기자신으로 설정
		make();
		for(int[] c: costs) {
			// 3. 연결된 다리 갯수가 n-1개면 종료
			if(cnt == n-1) break;
			from = c[0];
			to = c[1];
			
			// 4. 정점의 연결관계를 확인하여 비용 계산
			if(union(from, to)) {
				ans+=c[2];
				cnt++;
			}
		}
		
		return ans;
	}

	private static void make() {
		for(int i=0; i<parents.length; i++) {
			parents[i] = i;
		}
	}

	private static boolean union(int from, int to) {
		int aRoot = find(from);
		int bRoot = find(to);
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}

	private static int find(int v) {
		if(parents[v] == v) return v;
		else return parents[v] = find(parents[v]);
	}

	public static void main(String[] args) {
		int[][] cost = {
				{0,1,1},
				{0,2,2},
				{1,2,5},
				{1,3,1},
				{2,3,8},
		};
		int a = solution(4, cost);
		System.out.println(a);
	}
}
