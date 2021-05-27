package 알고리즘스터디;

import java.util.Scanner;

public class 배송전략실험 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		//1. input
		int n = sc.nextInt();
		sc.nextLine();
		String str = sc.nextLine();
		char[] ch = str.toCharArray();

		// 2. dy 배열선언
		int[] dy = new int[n];
		if(ch[1] == '1') dy[1] = 1;
		if(ch[2] == '1') dy[2] = dy[1] + 1;
		
		for(int i=3; i<n; i++) {
			if(ch[i] == '0') continue;
			dy[i] = dy[i-1]+dy[i-2];
		}
	
		System.out.println(dy[n-1]);
	}
}
