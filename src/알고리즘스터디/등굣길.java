package 알고리즘스터디;

// https://velog.io/@ajufresh/%EB%93%B1%EA%B5%A3%EA%B8%B8

public class 등굣길 {

	static int[][] dis;
	public static int solution(int m, int n, int[][] puddles) {
		
		dis = new int[n][m];
		for(int[] p : puddles) {
			dis[p[1]-1][p[0]-1] = -1;
		}

		dis[0][0] = 1;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(dis[i][j] == -1) {
					dis[i][j] = 0;
				}else {
					if(i != 0) {
						dis[i][j] += dis[i-1][j];
					}
					
					if(j != 0) {
						dis[i][j] += dis[i][j-1];
					}
					dis[i][j] %= 1000000007;
				}
			}
		}
		return dis[n-1][m-1];
	}
	
	private static void print(int[][] d) {
		for(int i=0; i<d.length; i++) {
			for(int j=0; j<d[i].length; j++) {
				System.out.print(d[i][j] +" ");
			}
			System.out.println();	
		}
		System.out.println();
	}


	public static void main(String[] args) {
		int a = solution(4, 3, new int[][] {{2,2}});
		System.out.println(a);
	}

}