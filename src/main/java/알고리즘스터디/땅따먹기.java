package 알고리즘스터디;

public class 땅따먹기 {

	public static int[][] dy;
	public static int solution(int[][] land) {
		int len = land.length;
		dy = new int[len][4];
		
		dy[0] = land[0];
		for(int i=1; i<len; i++) {
			dy[i][0] = biggerNum(land, i, 0);
			dy[i][1] = biggerNum(land, i, 1);
			dy[i][2] = biggerNum(land, i, 2);
			dy[i][3] = biggerNum(land, i, 3);
		}
		
		
		int ans = 0;
		for(int i=0; i<4; i++) {
			ans = Math.max(ans, dy[len-1][i]);
		}
		

		return ans;
	}

	private static int biggerNum(int[][] land, int row, int col) {
		
		int maxNum = 0;
		
		for(int i=0; i<4; i++) {
			if(i==col) continue;
			maxNum = Math.max(maxNum, land[row][col] + dy[row-1][i]);
		}
		
		return maxNum;
	}


	public static void main(String[] args) {
		int ans = solution(
			new int[][]{
				{1,2,3,5},
				{5,6,7,8},
				{4,3,2,1}
		});
		
		System.out.println(ans);
	}
}
