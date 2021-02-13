package 알고리즘스터디;

public class 쿼드압축후개수세기 {
	
	static int oneCnt, zeroCnt;
	public static int[] solution(int[][] arr) {
		int N = arr.length;
		oneCnt = zeroCnt = 0;
		
		divede(arr, 0, 0, N);
		
		
		int[] answer = new int[2];
		answer[0] = zeroCnt;
		answer[1] = oneCnt;
		return answer;
	}


	private static void divede(int[][] arr, int sx, int sy, int n) {
		int res = check(arr, sx, sy, n);
		if(res == 0) zeroCnt++;
		else if(res== 1) oneCnt++;
		else {
			n = n/2;
			divede(arr, sx, sy, n);
			divede(arr, sx+n, sy, n);
			divede(arr, sx, sy+n, n);
			divede(arr, sx+n, sy+n, n);
		}
	}


	private static int check(int[][] arr, int sx, int sy, int n) {
		int res = arr[sx][sy];
		for(int i = sx; i<sx+n; i++) {
			for(int j=sy; i<sy+n; j++) {
				if(arr[i][j] != res ) return -1;
			}
		}
		return res;
	}


	public static void main(String[] args) {

	}
}
