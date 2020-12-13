package 모의역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA4008숫자만들기_by김태희 {

	static int max, min, N; // 최댓값,최솟값, 갯수
	static int ops[], opsCnt[], numbers[]; //연산자순열, 연산자 갯수, 숫자배열
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringTokenizer st = null;
		opsCnt = new int[4];
		
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(in.readLine());
			numbers = new int[N];
			ops = new int[N-1];
			
			st = new StringTokenizer(in.readLine(), " ");
			for(int i=0; i<4; i++) {
				opsCnt[i] = Integer.parseInt(st.nextToken());
			}// 연산자 갯수 저장
			
			st = new StringTokenizer(in.readLine());
			for(int i=0; i<N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			
			permutation(0);
			
			System.out.println("#" + tc + " " + (max-min));
			
		}
		
	}

	// +:0 -:1 *:2 /:3
	private static void permutation(int cnt) {	//연산자의 순열 생성
		
		if(cnt == N-1) {	//연산자 순열 완성
			calc();
			return;
		}
		
		// 모든 연산자를 다 시도해보자.
		for(int i=0; i<4; i++) {
			if(opsCnt[i] == 0) continue;
			
			ops[cnt] = i;
			opsCnt[i]--;
			permutation(cnt+1);
			opsCnt[i]++;
		}
		
	}
	private static void calc() {	//연산자의 순열 상태를 이용하여 수식계산
		
		int result = numbers[0];
		for(int i=1; i<N; i++) {
			int currNumber = numbers[i];
			
			switch (ops[i-1]) {
			case 0:
				result += currNumber;
				break;
			case 1:
				result -= currNumber;
				break;
			case 2:
				result *= currNumber;
				break;
			case 3:
				result /= currNumber;
				break;

			}
		}
		
		max = Math.max(max, result);
		min = Math.min(min, result);
	}
}
