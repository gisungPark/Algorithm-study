package 알고리즘스터디;

import java.util.Scanner;

public class 상품배치추천 {

	static int N;
	static int[] ans;
	static int[][] map;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sc.nextLine();
		ans = new int[N + 1];
		map = new int[N][N];

		// 1. 맵 입력
		for(int i=0; i<N; i++) {
			String input = sc.nextLine();
			for(int j=0; j<input.length(); j++) {
				map[i][j] = input.charAt(j) - '0';
				if(map[i][j] == 1) ans[1]++;
			}
		}
		
//		print(map);
		

		// 2. 공간 확인
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0) continue;

				int size = 2; // 검사 시작 사이즈
				while (i + size < N && j + size < N) {
					int[][] subMap = make(size);
					boolean isOk = true; 
					for(int a = 0; a<size; a++) {
						for(int b= 0; b<size; b++) {
							if(map[i+a][j+b] != subMap[a][b]) isOk = false;
						}
						if(!isOk) break;
					}
					if(!isOk) break;
					else ans[size] ++;
					size++;
				}

			}
		}
		for(int i=1; i<=N; i++) {
			if(ans[i] != 0) System.out.println("size["+i+"]: " + ans[i]);
		}

	}

	private static int[][] make(int size) {
		int[][] sub = new int[size][size];
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				sub[i][j] = 1;
			}
		}
		return sub;
	}
	
	public static void print(int[][] arr) {
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
		System.out.println();
		}
	}
}




























