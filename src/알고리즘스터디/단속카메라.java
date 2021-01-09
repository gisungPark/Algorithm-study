package 알고리즘스터디;

import java.util.Comparator;
import java.util.PriorityQueue;

public class 단속카메라 {

	public static int solution(int[][] routes) {
		
		PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1]-o2[1];
			}
		});
		
		for(int[] r: routes) {
			q.add(r);
		}
		
		int ans = 0;
		int prev = Integer.MIN_VALUE;
		for(int[] r: q) {
			if(r[0] > prev) {
				prev = r[1];
				ans++;
			}
		}
		
		return ans;
    }
	
	public static void main(String[] args) {
		int ans = solution(new int[][] {
				{0,12},
				{1,12},
				{2,12},
				{3,12},
				{3,6},
				{6,12},
				{10,12}
		});
		
		System.out.println(ans);
	}
	
	
/*
print(solution([[-2,-1], [1,2],[-3,0]])) #2
print(solution([[0,0],])) #1
print(solution([[0,1], [0,1], [1,2]])) #1
print(solution([[0,1], [2,3], [4,5], [6,7]])) #4
print(solution([[-20,-15], [-14,-5], [-18,-13], [-5,-3]])) #2
print(solution([[-20,15], [-14,-5], [-18,-13], [-5,-3]])) #2
print(solution([[-20,15], [-20,-15], [-14,-5], [-18,-13], [-5,-3]])) #2

 * */
	
}
