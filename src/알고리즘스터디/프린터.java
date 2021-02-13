package 알고리즘스터디;

import java.util.LinkedList;

public class 프린터 {
	
	public static int solution(int[] priorities, int location) {

		LinkedList<int[]> q = new LinkedList<>();
		for(int i=0; i<priorities.length; i++) {
			q.add(new int[] {i, priorities[i]});
		}
		
		int cnt = 0;
		int[] first = null;
		while(!q.isEmpty()) {
			int max = findMax(q);
			first = q.poll();
			if(first[1] == max) {
				cnt++;
				if(first[0] == location) return cnt;
			}else {
				q.addLast(first);
			}
		}
		
		return -1;
	}

	private static int findMax(LinkedList<int[]> q) {
		int max = 0;
		for(int i=0; i<q.size(); i++) {
			max = Math.max(max, q.get(i)[1]);
		}
		return max;
	}

	public static void main(String[] args) {
		int ans = solution(new int[]{1,9,1,1,1}, 0);
		System.out.println(ans);
	}
}
