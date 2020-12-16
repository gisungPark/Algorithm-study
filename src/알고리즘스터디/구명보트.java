package 알고리즘스터디;

import java.util.Arrays;

public class 구명보트 {
	
	public static int solution(int[] people, int limit) {
		
		int ans = 0;
		int start = 0;
		int end = people.length-1;

		Arrays.sort(people);
		
		if(people[start] > 50) return (end-start+1);
		
		
		// 1. 혼자서만 탑승가능한 경우를 찾는다.
		while(true) {
			if(people[start]+people[end] <= limit/2) break;
			
			end--;
			ans++;
		}
		
		// 2. 2명이서 탑승 가능한 경우를 찾는다.
		while(start<end) {
			// 3. 몸무게의 최솟값이 limit의 절반 이상인 경우 더이상 조건 체크 필요없이
			// 	현재 인원수 만큼 구명보트 필요
			if(people[start] > limit/2) {
				ans = ans + (end-start+1);
				break;
			}
			
			if(people[start]+people[end] > limit) {
				ans++;
				end--;
			}else {
				start++;
				end--;
				ans++;
			}
		}
		if(start == end) ans++;
		
		return ans;
	}
	public static void main(String[] args) {
		int a = solution(new int[] {70,50,80}, 100);
		System.out.println(a);
	}

}
