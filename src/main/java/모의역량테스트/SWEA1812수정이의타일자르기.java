package 모의역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA1812수정이의타일자르기 {

	static class Rectangle implements Comparable<Rectangle>{
		int max, min;

		public Rectangle(int width, int height) {
			min = Math.min(width, height);
			max = Math.max(width, height);
		}

		@Override
		public int compareTo(Rectangle o) {
			return o.min - this.min;
		}
	}
	
	static int N,M,size[], cnt;
	static PriorityQueue<Rectangle> q = null;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			size = new int[N];
			cnt = 0;
			
			st = new StringTokenizer(in.readLine(), " ");
			for(int i=0; i<N; i++) {
				size[i] = Integer.parseInt(st.nextToken());
			}
			
			cut();
			System.out.println("#" + tc + " " + cnt);
		}
		
		
		
	}
	private static void cut() {
		// 1. 크기가 큰 타일부터 처리
		Arrays.sort(size);
		q = new PriorityQueue<>();
		// 2. 타일 1장 구매
		q.add(new Rectangle(M, M)); 
		++cnt;
		
		for(int i=N-1; i>=0; i--) {
			go(1<<size[i]);
		}
	}
	private static void go(int size) {
		
		
		// 자투리 타일중에 min변의 크기가 최대인 놈 꺼내서 비교
		Rectangle r = q.poll();
		// 3-1. 원하는 크기의 타일을 만들 수 있다면 만들고 잘라낸 2개의 자투리 타일을 다시 보관
		if(r.min >=  size) {
			q.add(new Rectangle(r.min-size, size));
			q.add(new Rectangle(r.min, r.max-size));
			
		}else {
		// 3-2. 원하는 타일을 만들 수 없다면 새로 타일을 구매해서 잘라내고,
		// 		잘라낸 2개의 자투리 타일 보관하고, 사용하지 않은 타일 다시 보관
			++cnt;
			q.add(r);
			q.add(new Rectangle(M-size, size));
			q.add(new Rectangle(M-size, M));
			
		}
	}
}














