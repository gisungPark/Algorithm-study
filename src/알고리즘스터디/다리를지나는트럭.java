package 알고리즘스터디;

import java.util.LinkedList;
import java.util.Queue;

public class 다리를지나는트럭 {

						//다리 길이, 다리가 견디는 하중, 트럭 무게 배열
	public static int solution(int bridge_length, int weight, int[] truck_weights) {
		
		Queue<Integer> prevQ = new LinkedList<Integer>(); // 대기상트의 트럭
		Queue<int[]> curQ = new LinkedList<int[]>(); // 현재 다리위인 트럭과 트럭 상태
		// 1. 트럭 큐에 삽입
		for(Integer w: truck_weights) {
			prevQ.add(w);
		}
		
		int time = 0, total = 0; // 현재시간, 현재 다리 위 무게 
		// 2. 큐에서 트럭 하나씩 꺼내 조건 비교
		while(true) {
			
			// 3. 1초 경과후 다리위 트럭 상태 갱신
			for(int[] st : curQ) {
				st[1]++;
			}
			// 4. 선두 차량이 다리를 벗어났는지 확인
			if(!curQ.isEmpty()) {
				int[] top = curQ.peek();
				if(top[1] >= bridge_length) {
					total -= top[0];
					curQ.poll();
				}
			}
			
			// 5. 대기 상태의 트럭이 추가로 진입 가능한지 확인
			if(!prevQ.isEmpty() && total + prevQ.peek() <= weight) {
				int next = prevQ.poll();
				total += next;	// 추가로 진입한 트럭 무게 덧셈
				curQ.add(new int[] {next, 0});
			}
			++time;
			if(prevQ.isEmpty() && curQ.isEmpty()) break;
		}
		
		
		return time;
	}

	public static void main(String[] args) {

		int ans = solution(100, 100, new int[] {10});
		System.out.println(ans);
	}
}
