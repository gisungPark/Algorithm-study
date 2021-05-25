package 모의역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA3349최솟값으로이동하기 {

	static int dx[] = { -1, 1, 0, 0, 1 };
	static int dy[] = { 0, 0, -1, 1, 1 };
	static int W, H, N, ans;
	static int[][] dis;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringTokenizer st = null;
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(in.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			int x, y;
			ArrayList<int[]> list = new ArrayList<>();
			// 1. 이동 경로 좌표값 저장
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(in.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				list.add(new int[] {x,y});
			}
			
			ans = 0;
			for(int i=1; i<N; i++) {
				int n = go(list.get(i-1), list.get(i));
				ans+= n;
			}
			
			System.out.println("#"+tc+" "+ans);
		}
	}

	private static int go(int[] from, int[] to) {
		int ans = 0;
		int xDis = to[0] - from[0];
		int yDis = to[1] - from[1];
		
		if(xDis*yDis >0) {
			ans = Math.max(Math.abs(xDis), Math.abs(yDis));
		}else {
			ans = Math.abs(xDis) + Math.abs(yDis);
		}

		return ans;
	}
}
