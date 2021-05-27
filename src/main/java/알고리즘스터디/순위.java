package 알고리즘스터디;

import java.util.LinkedList;
import java.util.Queue;

public class 순위 {

	public static int solution(int n, int[][] results) {

		int ans = 0, gtCnt, ltCnt;
		// 그래프 초기화
		int[][] matrix = new int[n+1][n+1];
		for(int i=0; i<results.length; i++) {
			matrix[results[i][0]][results[i][1]] = 1;
		}
		
		Queue<Integer> winQ = null, loseQ = null;
		boolean[] selected1 = null;
		boolean[] selected2 = null;
		
		for(int i=1; i<n+1; i++) {	// n명의 선수 확인
			winQ = new LinkedList<>();
			loseQ = new LinkedList<>();
			selected1 = new boolean[n+1];
			selected2 = new boolean[n+1];
			gtCnt = ltCnt = 0;
			
			winQ.add(i);
			loseQ.add(i);
			
			// 이긴 경우
			while(!winQ.isEmpty()) {
				int next = winQ.poll();
				for(int j=1; j<=n; j++) {
					if(!selected1[j] && matrix[j][next] == 1) {
						selected1[j] = true;
						gtCnt++;
						winQ.add(j);
					}
				}
			}
			
			// 진 경우
			while(!loseQ.isEmpty()) {
				int next = loseQ.poll();
				for(int j=1; j<=n; j++) {
					if(!selected2[j] && matrix[next][j] == 1) {
						selected2[j] = true;
						ltCnt++;
						loseQ.add(j);
					}
				}
			}
			
			if(gtCnt+ltCnt == n-1) {
				ans ++;
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		int a  = solution(5, new int[][] {
			new int[] {3,5},
			new int[] {4,2},
			new int[] {4,5},
			new int[] {5,1},
			new int[] {5,2},
		});
		System.out.println(a);
		
	}
	
	public static void print(int[][] map) {
		for(int i=1; i<map.length; i++) {
			for(int j=1; j<map[i].length; j++) {
				System.out.print(map[i][j] +" ");
			}
			System.out.println();
		}
	}
}
