package 알고리즘스터디;

public class 보행자천국 {

	static int MOD = 20170805;
	public static int solution(int m, int n, int[][] cityMap) {
		int[][] right = new int[m+1][n+1];
		int[][] down = new int[m+1][n+1];
		
		right[1][1] = down[1][1] = 1;
		
		for(int i=1; i<=m; i++) {
			for(int j=1; j<=n; j++) {
				if(cityMap[i-1][j-1] == 0) {
					down[i][j] = right[i][j] = (right[i][j-1]+down[i-1][j])%MOD;
				}else if(cityMap[i-1][j-1] == 1) {
					down[i][j] = right[i][j] = 0;
				}else {
					right[i][j] = right[i][j-1];
					down[i][j] = down[i-1][j];
				}
			}
		}
		print(right);
		print(down);
		
		return (right[m][n]+down[m][n])%MOD;
	}

	private static void print(int[][] map) {
		for(int i=1; i<map.length; i++) {
			for(int j=1; j<map.length; j++) {
				System.out.print(map[i][j] +" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		solution(3,3, 
				new int[][] { {0,0,0},
			{0,0,0},
			{0,0,0}
			
		});
	}
}
