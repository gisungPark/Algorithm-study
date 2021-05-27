package 알고리즘스터디;

public class 다음큰숫자_솔루션 {

	public static int solution(int n) {
		
		String binary = Integer.toBinaryString(n);
		// 1의 갯수를 센다.
		int oneCnt = countOne(binary);

		// 다음 수를 찾는다.
		String next = "";
		int newOneCnt = 0; 
		while(true) {
			n++;
			next = Integer.toBinaryString(n);
			newOneCnt = countOne(next);
			
			if(oneCnt == newOneCnt) break;
		}
		
		return n;
	}
	private static int countOne(String binary) {
		int cnt = 0;
		for(int i=0; i<binary.length(); i++) {
			if(binary.charAt(i) == '1') cnt++;
		}
		return cnt;
	}
	public static void main(String[] args) {
		int ans = solution(78);
		System.out.println(ans);
	}
}
