package 모의역량테스트;

import java.util.Scanner;

public class SWEA6808규영이와인영이의카드게임 {
	
	
	static int winCnt, loseCnt;
	static int[] personA, personB, sel;
	static boolean[] selected;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc<= T; tc++) {

			boolean[] nums = new boolean[19];
			// 1. 규영이의 카드 값 입력
			personA = new int[9];
			personB = new int[9];
			for(int i=0; i<9; i++) {
				personA[i] = sc.nextInt();
				nums[personA[i]] = true;
			}
			
			// 2. 규영이가 선택하고 남은 카드 인영이에게 배분
			int idx = 0;
			for(int i=1; i<=18; i++) {
				if(!nums[i]) {
					personB[idx++] = i;
				}
			}
			
			// 3. 이긴경우, 진경우 카운트 변수 초기화 
			winCnt = loseCnt = 0;
//			System.out.println(Arrays.toString(personA));
//			System.out.println(Arrays.toString(personB));
			
			// 4. 인영이의 카드 순열 정렬
			selected = new boolean[9];
			sel = new int[9];
			perm(0, 0);
			
			System.out.println("#"+tc+" " +winCnt +" " + loseCnt);
		}
	}
	private static void perm(int start, int k) {
		if(k == sel.length) {
//			System.out.println(Arrays.toString(sel));
			int pointA = 0, pointB = 0;
			for(int i=0; i<9; i++) {
				if(personA[i] > sel[i]) pointA = pointA + personA[i] + sel[i];
				else pointB = pointB + personA[i] + sel[i];
			}
			
			if(pointA > pointB) winCnt++;
			else if(pointB > pointA) loseCnt++;
		}else {
			for(int i=0; i<personB.length; i++) {
				if(selected[i]) continue;
				sel[k] = personB[i];
				selected[i] = true;
				perm(i+1, k+1);
				selected[i] = false;
			}
		}
		
	}
}












