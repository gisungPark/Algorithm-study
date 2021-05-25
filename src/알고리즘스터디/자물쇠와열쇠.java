package 알고리즘스터디;

public class 자물쇠와열쇠 {

	public static boolean solution(int[][] key, int[][] lock) {

		boolean isOk = true;
		int len = lock.length;
		int[] leftDown = new int[] { 0, len };
		int[] rightUp = new int[] { len, 0 };

		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {

				// 1. Lock에서 열쇠구멍 찾기
				if (lock[i][j] == 0) {
					isOk = false;
					if (i < rightUp[0]) rightUp[0] = i;
					if (j > rightUp[1]) rightUp[1] = j;
					if (i > leftDown[0]) leftDown[0] = i;
					if (j < leftDown[1]) leftDown[1] = j;
				}
			}
		}
		if(isOk) return true;

		// 2. Lock에서 열쇠 홈 뽑아내기
		int[][] holes = makeHoles(lock, rightUp, leftDown);
//		print(holes);

		if(tryOpen(key, holes)) return true;
		else return false;
		
	}

	private static boolean tryOpen(int[][] key, int[][] holes) {
		
		// 키를 총 4번 회전시켜가며 확인
		for(int tc=0; tc<4; tc++) {
			for(int i=0; i<key.length; i++) {
				for(int j=0; j<key.length; j++) {
					if(check(key, i, j, holes)) return true;
				}
			}
			
			// key 90도 회전
			key = rotateKey(key);
		}
		
		return false;
	}

	private static int[][] rotateKey(int[][] key) {
		int n = key.length;
		int[][] tmp = new int[n][n];

		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				tmp[i][j] = key[n-j-1][i];
			}
		}
		return tmp;
	}

	private static boolean check(int[][] key, int row, int col, int[][] holes) {

		// 열쇠구멍 사이즈와 key 배열의 크기가 맞지 않으면 비교할 필요 없다.
		int holeRow = holes.length;
		int holeCol = holes[0].length;
		if(key.length < row+holeRow
				|| key[0].length < col + holeCol) return false; 
		
		// 열쇠구멍과 key 배열은 서로 반대 관계여야 한다. 
		for(int i=row, l=0; i< row+holeRow; i++, l++) {
			for(int j=col, m=0; j< col+holeCol; j++,m++) {
				if(holes[l][m] == key[i][j]) return false;
			}
		}
		
		return true;
	}

	private static int[][] makeHoles(int[][] lock, int[] rightUp, int[] leftDown) {
		int a = leftDown[0] - rightUp[0] + 1;
		int b = rightUp[1] - leftDown[1] + 1;
		int[][] holes = new int[a][b];

		for (int l = 0, i = rightUp[0]; i <= leftDown[0]; i++, l++) {
			for (int m = 0, j = leftDown[1]; j <= rightUp[1]; j++, m++) {
				holes[l][m] = lock[i][j];
			}
		}

		return holes;
	}


	// 2차원 배열 출력 함수
	private static void print(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}

	public static void main(String[] args) {
		int[][] key = new int[][] { { 0, 0, 0 }, { 1, 0, 0 }, { 0, 1, 1 } };

		int[][] lock = new int[][] { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };
		if(solution(key, lock)) System.out.println("true");
		else System.out.println("false");
	}
}
