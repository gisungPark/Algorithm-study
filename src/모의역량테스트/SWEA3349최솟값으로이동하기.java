package 모의역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA3349최솟값으로이동하기 {

	static int dx[] = { -1, 1, 0, 0, 1 };
	static int dy[] = { 0, 0, -1, 1, 1 };
	static int W, H, N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringTokenizer st = null;
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(in.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			for(int i=1; i<N; i++) {
				
			}
			
		}
	}
}
