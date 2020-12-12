package 알고리즘스터디;

import java.util.Arrays;

public class H_Index {

	public static int solution(int[] citations) {
		
		//1. 배열 정렬
		Arrays.sort(citations);
		int len = citations.length;
		
		// 출력
		for(int i=len-1,j=1; i>=0; i--,j++) {
			System.out.print(citations[i]+ " ");
		}
		System.out.println();
		for(int i=len-1,j=1; i>=0; i--,j++) {
			System.out.print(j+ " ");
		}
		System.out.println(); // end 출력
		
		int ans = 0;
		int j=len-1;
		for(int i=citations[len-1]; i>=0; i--) {
		}
		
		return ans;
	}

	public static void main(String[] args) {
		int ans = solution(new int[] {6, 6, 6, 6, 6, 1});
		System.out.println(ans);
	}
}
