package 모의역량테스트;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class SWEA5658보물상자비밀번호 {

	static int T, N, K, ans;
	static int[] numbers;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for(int tc=1; tc<=T; tc++) {
			N = sc.nextInt();
			K = sc.nextInt();
			sc.nextLine();
			
			Map<Integer, Integer> map = new HashMap<>();
			PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
			StringBuilder sb =new StringBuilder(sc.nextLine());
			// 1. 사각형의 회전 수
			for(int k=0; k<N/4; k++) {
				// 2. 현재 사각형에서 4개의 번호를 뽑는다.
				for(int i=1; i<=sb.length(); i++) {
					if(i%(N/4) == 0) {
						int num = converToInt(sb.substring(i-N/4, i));
						// 3. 중복여부를 확인하고 pq에 삽입
						if(!map.containsKey(num)) {
							map.put(num, num);
							pq.add(num);
						}
					}
				}
				
				// 4. 사각형 회전
				int last = sb.length();
				char ch = sb.charAt(last-1);
				sb.deleteCharAt(last-1);
				sb.insert(0, ch);
			}
			
			int cnt = 0;
			while(!pq.isEmpty()) {
				if(++cnt == K) {
					ans = pq.poll();
					break;
				}
				pq.poll();
			}
			
			System.out.println("#"+tc+" " + ans);
		}
	}
	private static int converToInt(String str) {
		int size = str.length();
		int num = 0;
		
		for(int i=0; i<size; i++) {
			int n = change(str.charAt(i));
			num+= n*Math.pow(16, (size-1-i));
		}
		
		return num;
	}
	private static int change(char ch) {
		int ans = 0;
		
		switch (ch) {
		case 'A':
			ans = 10;
			break;
		case 'B':
			ans = 11;
			break;
		case 'C':
			ans = 12;
			break;
		case 'D':
			ans = 13;
			break;
		case 'E':
			ans = 14;
			break;
		case 'F':
			ans = 15;
			break;

		default:
			ans = ch-'0';
			break;
		}
		
		return ans;
	}
}
