package 알고리즘스터디;

import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 이중우선순위큐 {

	public static int[] solution(String[] operations) {
		
		ArrayList<Integer> list = new ArrayList<>();
		for(String operation : operations) {
			
			// 1. 연산 명령어 분석
			StringTokenizer st = new StringTokenizer(operation," ");
			String op = st.nextToken();
			String n = st.nextToken();
			int num = Integer.parseInt(n);	//문자열 형식의 숫자 데이터를 int값으로 변경
			
			// 2. 명령어에 따른 연산 수행
			if(op.equals("I")) {			// 삽입 연산
				list.add(num);
			}else {							// 삭제 연산
				// 3. 리스트에 삭제될 데이터 없다면 continue
				if(list.size()==0) continue;
				// 4. 삭제 연산 전 리스트 정렬
				Collections.sort(list);
				// 5-1. 최솟값 삭제
				if(num == -1) {	 
					list.remove(0);
				}else {
				// 5-2. 최댓값 삭제
					list.remove(list.size()-1); 
				}
			}
		}
		
		// 6. 결과값 
		int[] answer = new int[] {0,0};
		if(list.size() != 0) {
			Collections.sort(list);
			int max = list.get(list.size()-1);
			int min = list.get(0);
			answer = new int[] {max, min};
		}
		
		return answer;
	}
	
	public static void main(String[] args) {
		int[] ans = solution(new String[] {
				"I 4", "I 3", "I 2", "I 1", "D 1", "D 1", "D -1", "D -1", "I 5", "I 6"
				});
//		"I 5", "I 5", "D 1", "I 7", "D -1", "I 8"
//		"I 4", "I 3", "I 2", "I 1", "D 1", "D 1", "D -1", "D -1", "I 5", "I 6"
		System.out.println(ans[0] +" " + ans[1]);
	}
}
