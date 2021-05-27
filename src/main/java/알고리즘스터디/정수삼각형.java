package 알고리즘스터디;

public class 정수삼각형 {

	public static int solution(int[][] triangle) {
        int r = triangle.length;
		
        for(int i=r-1; i>0; i--) {	// 삼각형 아래에서 위로 
        	int c = triangle[i].length;
        	for(int j=0; j<c-1; j++) {
        		int max = Math.max(triangle[i][j], triangle[i][j+1]);
        		triangle[i-1][j]+=max;
        	}
        }
		
        return triangle[0][0];
    }
	
	public static void main(String[] args) {
		int a = solution(new int[][] {
			new int[] {7}, 
			new int[] {3,8},
			new int[] {8,1,0},
			new int[] {2,7,4,4},
			new int[] {4,5,2,6,5}
			});
		System.out.println(a);
	}
}
