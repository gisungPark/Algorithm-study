package 알고리즘스터디;

public class 단체사진찍기 {

	static int ans;
	static String kakao = "ACFJMNRT";
	static int[] sel;
	static boolean[] selected;
	public static int solution(int n, String[] data) {
		
		ans = 0;
		sel = new int[8];
		selected = new boolean[8];
		
		Perm(0, n, data);
		return ans;
	}

	private static void Perm(int cnt, int n, String[] data) {
		if(cnt == 8) {
			// 만들어진 순열로 조건검색
			if(check(sel, n, data)) ans++;
		}else {
			for(int i=0; i<8; i++) {
				if(selected[i]) continue;
				selected[i] = true;
				sel[cnt] = i;
				Perm(cnt+1, n, data);
				selected[i] = false;
			}
		}
	}

	// 해당 순열이 조건을 만족하는지 확인
	private static boolean check(int[] arr, int n, String[] data) {

		for(int i=0; i<n; i++) {
			int a = convert(data[i].charAt(0));
			int b = convert(data[i].charAt(2));
			int distance = data[i].charAt(4)-'0';
			char op = data[i].charAt(3); 
			
			int aIdx = 0, bIdx = 0;
			for(int j=0; j<arr.length; j++) {
				if(arr[j] == a) aIdx = j;
				else if(arr[j] == b) bIdx = j;
			}
			
			int dis = Math.abs(aIdx-bIdx);
			if(op == '<') {
				if(dis >= distance+1) return false;
			}else if(op == '>') {
				if(dis <= distance+1) return false;
			}else if(op == '='){
				if(dis != distance+1) return false;
			}else {
				return false;
			}
			
		}
		return true;
	}

	// 해당 문자별 숫자로 변환하는 컨버터
	private static int convert(char ch) {
		switch(ch) {
		case 'A':
			return 0;
		case 'C':
			return 1;
		case 'F':
			return 2;
		case 'J':
			return 3;
		case 'M':
			return 4;
		case 'N':
			return 5;
		case 'R':
			return 6;
		case 'T':
			return 7;
		}
		return -1;
	}

	public static void main(String[] args) {
		int ans = solution(2, new String[] {"N~F=0", "R~T>2"});
		System.out.println(ans);
	}
}
