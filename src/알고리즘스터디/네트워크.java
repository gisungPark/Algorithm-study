package 알고리즘스터디;

import java.util.HashSet;
import java.util.Set;

public class 네트워크 {
	
	public static int[] parents;
	public static int solution(int n, int[][] computers) {
		parents = new int[n];
		for(int i=0; i<n; i++) {
			parents[i] = i;
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(i==j) continue;
				if(computers[i][j]==1) union(i,j);
			}
		}
		
		Set<Integer> set = new HashSet<>();
		for(int i=0; i<n; i++) {
			set.add(find(i));
		}
		
		return set.size();
	}
	
	public static int find(int v) {
		if(parents[v] == v) return v;
		return parents[v] = find(parents[v]);
	}
	
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot==bRoot) return false;
		else {
			if(aRoot<bRoot) parents[bRoot] = aRoot;
			else parents[aRoot] = bRoot;
			return true;
		}
	}
	
	public static void main(String[] args) {
		int[][] arr = new int[][] {{1,1,0},{1,1,0},{0,0,1}};
		int res = solution(3,arr);
		System.out.println(res);
	}

}
