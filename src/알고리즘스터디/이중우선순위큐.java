package 알고리즘스터디;

import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 이중우선순위큐 {

	public static int[] solution(String[] operations) {
		
		ArrayList<Integer> list = new ArrayList<>();
		for(String operation : operations) {
			StringTokenizer st = new StringTokenizer(operation," ");
			String op = st.nextToken();
			String n = st.nextToken();
			int num = Integer.parseInt(n);
			if(op.equals("I")) {	// 삽입 연산
				list.add(num);
			}else {	
				if(list.size()==0) continue;
				// 삭제 연산 전 리스트 정렬
				Collections.sort(list);
				if(num == -1) { // 최솟값 삭제
					list.remove(0);
				}else {
					list.remove(list.size()-1); // 최댓값 삭제
				}
			}
		}
		
		int[] answer = new int[] {0,0};
		if(list.size() != 0) {
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
