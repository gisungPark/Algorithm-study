package 알고리즘스터디;

public class ㄷ모양땅구하기 {

	public static int soultion(String[] str) {

		char[][] map = new char[str.length][];
		for (int i = 0; i < str.length; i++) {
			map[i] = str[i].toCharArray();
		}

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				process(map, i, j);
			}
		}

		return -1;
	}

	private static void process(char[][] map, int row, int col) {
		int colMax, rowMax;
		colMax = rowMax = 0;

		// row, col 자리의 문자열로 그릴수 있는 가장 큰 사각형 좌표 4개 추출
		for (int i = row; i < map.length; i++) {
			if (map[i][col] == map[row][col])
				rowMax++;
		}

		for (int j = col; j < map[row].length; j++) {
			if (map[row][j] == map[row][col])
				colMax++;
		}

		square(map, row, col, row + rowMax - 1, col + colMax - 1);
	}

	private static void square(char[][] map, int row, int col, int rowMax, int colMax) {
		if(rowMax - row < 2 || colMax - row < 2) return;
		func1(map, row, col, rowMax, colMax);
	}

	private static void func1(char[][] map, int row, int col, int rowMax, int colMax) {
		
		
		
	}

	public static void main(String[] args) {
		int ans = soultion(new String[] { "AAA", "AAA", "AAA", "AAA", "AAA" });

		System.out.println(ans);
	}
}
