package 모의역량테스트;

import java.util.Scanner;

public class SWEA8382방향전환 {
	
	static int x1,y1,x2,y2;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=1; tc<=T; tc++) {
			x1 = sc.nextInt();
			y1 = sc.nextInt();
			x2 = sc.nextInt();
			y2 = sc.nextInt();
			
			int width = Math.abs(x2-x1);
			int height = Math.abs(y2-y1);
			int ans = 0;
			
			ans+= width<height? width*2: height*2;
			int remain = Math.abs(width-height);
			if(remain%2==0)
				ans+= remain*2;
			else 
				ans+= remain*2-1;
			
			System.out.println("#"+tc+" " +ans);
				
		}
	}

}
