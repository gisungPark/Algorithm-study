package 알고리즘스터디;

import java.util.Arrays;

public class H_Index {

	public static int solution(int[] citations) {
		
		//1. 배열 내림차순 정렬
		Arrays.sort(citations);
		int[] rev = reverse(citations);
		System.out.println(Arrays.toString(rev));
		
		int ans = rev[0];
		int idx = 0;
		while(ans>=0) {
			if(ans<=idx+1) break;
			// 배열의 인덱스값 증가
			if(rev[idx] == ans) idx++;
			ans--;
		}
		
		return ans;
	}

	// int 배열 순서를 뒤집는 함수
	private static int[] reverse(int[] citations) {
		int len = citations.length;
		int[] rev = new int[len];

		for(int i=0; i<len; i++) {
			rev[i] = citations[len-i-1];
		}
		return rev;
	}

	public static void main(String[] args) {
		int ans = solution(new int[] {8,10,10,15,17,22,24,28,32,42,47});
		System.out.println(ans);
	}
}
