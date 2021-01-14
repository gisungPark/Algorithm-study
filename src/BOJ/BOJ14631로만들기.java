package BOJ;

import java.util.Scanner;

public class BOJ14631로만들기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int ans = 0;
		while(N > 1) {
			if(N%3 == 0) N /= 3;
			else if(N%2 == 0) N/=2;
			else N--;
			ans++;
			System.out.println(N+" -> "+ ans);
		}
		System.out.println(ans);
	}
}
