package 모의역량테스트;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class SWEA2383점심식사시간 {


	static int N;
	static int[] map;
	static int[][] stair;
	static boolean[] selected;
	static ArrayList<person> list;
	static final int go = 0, wait = 1, move = 2, finish = 3;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=1; tc<=T; tc++) {
			N = sc.nextInt();
			
			list = new ArrayList<>();
			stair = new int[2][];
			int idx = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					int n = sc.nextInt();
					if(n == 0) continue;
					
					// 1. 사람 좌표 리스트 
					if(n == 1) list.add(new person(i, j, go));
					else {
					// 2. 계단 좌표 리스트
						stair[idx++] = new int[] {i,j};
					}
				}
			}
			
			selected = new boolean[list.size()];
			// 3. 각각의 사람들의 계단 선택 경우의 수 
			powerSet(0);
			
		}
	}
	
	private static void powerSet(int k) {
		if(k == selected.length) {
//			System.out.println(Arrays.toString(selected));
			
			PriorityQueue<person> Q1 = new PriorityQueue<>(new Comparator<person>() {
				@Override
				public int compare(person o1, person o2) {
					return o1.len - o2.len;
				}
			});
			PriorityQueue<person> Q2 = new PriorityQueue<>(new Comparator<person>() {
				@Override
				public int compare(person o1, person o2) {
					return o1.len - o2.len;
				}
			});
			
			for(int i=0; i<selected.length; i++) {
				person p = list.get(i);
				int len = 0;
				if(selected[i]) {
					len = Math.abs(p.x-stair[0][0]+ p.y-stair[0][1]);
					p.len = len;
					Q1.add(p);
				}else {
					len = Math.abs(p.x-stair[1][0]+ p.y-stair[1][1]);
					p.len = len;
					Q2.add(p);
				}
			}
			
			
			int t = 0;
			while(true) {
				
				
		
				
				
				
				
			}
			
			
			
			
		}else {
			selected[k] = true;
			powerSet(k+1);
			selected[k] = false;
			powerSet(k+1);
		}
	}

	public static class person{
		int x, y, state, len;
		
		person(int x, int y, int state){
			this.x = x;
			this.y = y;
			this.state = state;
			
		}
	}
}
