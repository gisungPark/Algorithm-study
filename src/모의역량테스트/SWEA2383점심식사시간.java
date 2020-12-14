package 모의역량테스트;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class SWEA2383점심식사시간 {


	static int N;
	static int[][] stair;
	static boolean[] selected;
	static ArrayList<int[]> list;
	static ArrayList<person >stairA, stairB;
	static final int go = 0, wait = 1, move = 2, complete = 3;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=1; tc<=T; tc++) {
			N = sc.nextInt();
			
			// 0. 사람들과 계단의 좌표값 입려
			list = new ArrayList<>();
			stair = new int[2][];
			int idx = 0, n = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					n = sc.nextInt();
					if(n == 0) continue;
					
					// 1. 사람 좌표 리스트 
					if(n == 1) list.add(new int[] {i,j});
					// 2. 계단 좌표 리스트
					else stair[idx++] = new int[] {i,j, n};
				}
			}
			
			selected = new boolean[list.size()];
			// 3. 각각의 사람들의 계단 선택 경우의 수 
			divide(0);
			
		}
	}
	
	// 각각의 사람들을 A, B 계단에 분배하는 함수 
	private static void divide(int k) {
		if(k == selected.length) {
			makeList();
		}else {
			selected[k] = true;
			divide(k+1);
			selected[k] = false;
			divide(k+1);
		}
	}

	private static void makeList() {
		stairA = new ArrayList<>();
		stairB = new ArrayList<>();
		
		int dis = 0;
		for(int i=0; i<selected.length; i++) {
			if(selected[i]) {	// A 계단인 경우
				dis = dis(list.get(i), stair[0]);
				stairA.add(new person(dis, go, 0));
			}else {				// B 계단인 경우
				dis = dis(list.get(i), stair[1]);
				stairB.add(new person(dis, go, 0));
			}
		}
		
		// stairA, B 정렬
		Collections.sort(stairA, new Comparator<person>() {

			@Override
			public int compare(person o1, person o2) {
				return o1.distance - o2.distance;
			}
		});
		
		Collections.sort(stairB, new Comparator<person>() {

			@Override
			public int compare(person o1, person o2) {
				return o1.distance - o2.distance;
			}
		});
		process();
	}

	// A, B 계단에 분배된 사람들을 대상으로 계단 내려가는 작업 진행
	private static void process() {
		
		int cntA = 0, cntB = 0;
		int time = 0;
		while(true) {
			person p = null;
			for(int i=0; i<stairA.size(); i++) {
				p = stairA.get(i);
				if(p.state == go && p.distance == time) {
					p.state = wait;
					continue;
				}
				if(p.state == wait && cntA <= 2) {
					p.state = move;
					cntA++;
					continue;
				}
				if(p.state == move ) {
					p.cnt++;
					if(p.cnt == stair[0][2]+1) {
						cntA--;
						p.state = complete;
					}
				}
				
			}
			for(int i=0; i<stairB.size(); i++) {
				p = stairB.get(i);
				if(p.state == go && p.distance == time) p.state = wait;				
			}
		}
		
	}

	// 두 점 사이의 거리
	private static int dis(int[] from, int[] to) {
		return Math.abs(from[0]-to[0]) + Math.abs(from[1] - to[1]);
	}

	public static class person{
		int distance, state, cnt;

		public person(int distance, int state, int cnt) {
			super();
			this.distance = distance;
			this.state = state;
			this.cnt = cnt;
		}
		
	}
}
