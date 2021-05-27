package 알고리즘스터디;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class 기둥과보설치 {
	public static int[][] solution(int n, int[][] build_frame) {

		int[][][] map = new int[2][n + 2][n + 2];

		for (int[] command : build_frame) {
			build(map, command);
			for(int i = map[0].length-1; i>=0; i--){
				System.out.println(Arrays.toString(map[0][i]));
			}
			System.out.println("***********************");
			System.out.println("***********************");
		}
		System.out.println();
		System.out.println();
		
		List<int[]> list = new ArrayList<>();
		// 결과값 배열 생성
		for (int row = 0; row <= n; row++) {
			for (int col = 0; col <= n; col++) {
				if (map[1][row][col] > 0) {
					if(map[1][row][col] >= 3) {
						map[1][row][col] -= 3;
						list.add(new int[] { col, row, 1 });
					}
					if(map[1][row][col] >= 2) {
						map[1][row][col] -= 2;
						list.add(new int[] { col, row, 0 });
					}
					map[0][row][col] = 0;
				}
			}
		}

		Collections.sort(list, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0] && o1[1] == o2[1]) {
					return o1[2] - o2[2];
				} else if (o1[0] == o2[0]) {
					return o1[1] - o2[1];
				} else {
					return o1[0] - o2[0];
				}
			}
		});

		int[][] answer = new int[list.size()][3];
		for (int i = 0; i < list.size(); i++) {
			answer[i] = list.get(i);
		}
		return answer;
	}

	public static void build(int[][][] map, int[] command) {
		int col = command[0];
		int row = command[1];
		int a = command[2];
		int b = command[3];
		// 조건이 기둥일때,
		if (a == 0) {
			if (b == 0) { // 삭제일때,
				map[1][row][col] -= 2;
				map[0][row][col] = 0;
				map[0][row+1][col] = 0;
			} else { // 설치 일때,
				map[1][row][col] += 2;
				map[0][row][col] = 1;
				map[0][row+1][col] = 1;
				
			}
		} else {
			// 보일때.
			if (b == 0) { // 삭제일때,
				map[1][row][col] -= 3;
				map[0][row][col+1] = 0;
				map[0][col][col] = 0;
			} else { // 설치 일때,
				map[1][row][col] += 3;
				map[0][row][col+1] = 1;
				map[0][row][col] = 1;
			}
		}

	}
	public static void main(String[] args) {
		int[][] ans = solution(5, new int[][] {
			{0,0,0,1},
			{2,0,0,1},
			{4,0,0,1},
			{0,1,1,1},
			{1,1,1,1},
			{2,1,1,1},
			{3,1,1,1},
			{2,0,0,0},
			{1,1,1,0},
			{2,2,0,1}
		});
		
		for(int i=0;i <ans.length; i++) {
			System.out.println(Arrays.toString(ans[i]));
		}
	}
}
