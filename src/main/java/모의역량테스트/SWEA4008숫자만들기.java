package 모의역량테스트;

import java.util.Scanner;

public class SWEA4008숫자만들기 {
	
	static int N, min, max;
	static int[] op, nums, sel;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=1; tc<=T; tc++) {
			N = sc.nextInt();
			op = new int[4];
			nums = new int[N];
			
			// 1. 연산자 및 숫자 입력
			for(int i=0; i<4; i++) {
				op[i] = sc.nextInt();
			}
			for(int i=0; i<N; i++) {
				nums[i] = sc.nextInt();
			}

			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			sel = new int[N-1];
			perm(0);
			
			System.out.println("#"+tc+" " + (max-min));
		}
	}
	private static void perm(int k) {
		if(k == sel.length) {
			int sum = nums[0];
			for(int i=0; i<N-1; i++) {
				switch (sel[i]) {
				case 0:
					sum+=nums[i+1];
					break;
				case 1:
					sum-=nums[i+1];
					break;
				case 2:
					sum*=nums[i+1];
					break;
				case 3:
					sum/=nums[i+1];
					break;
				}
			}
			min = Math.min(sum, min);
			max = Math.max(sum, max);
			
		}else {
			for(int i=0; i<4; i++) {
				if(op[i]<=0) continue;
				sel[k] = i;
				op[i]--;
				perm(k+1);
				op[i]++;
			}
		}
	}

}
