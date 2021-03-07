package 알고리즘스터디;

public class 가장긴부분문자열 {

	public static int solution(String s) {
		int ans = 0;

		// 1. 문자열의 처음부터 끝까지 조회
		for(int i=0; i< s.length(); i++) {
			// 2. 부분문자열 길이 경웅[ 따른 문자열 전체 조회
			for (int j = 5; j >= 1; j--) {
				ans = Math.max(ans, check(s, i, j));
			}
		}
		return ans;
	}

	// 3. len 길이만큼의 부분문자열로 이루어진 증가수열 체크
	private static int check(String s, int sIdx, int len) {
		if (s.length() < sIdx + len) return 0;

		// 첫번째 숫자.
		String sub = s.substring(sIdx, sIdx + len);
//		System.out.println("첫번째 : " + sub);
		int maxSize = 0, next = 0;
		int prev = Integer.parseInt(sub);
		int lastIdx = 0;
		for (int i = sIdx+len; i < s.length(); i += len) {

			String upper = Integer.toString(prev);
			upper = upper.replace("9", "");
			if(upper.equals("")) {
				lastIdx = len+1;
				if(lastIdx <= 5) next = getNext(s, i, lastIdx);
				else next = -1;
			}
			else {
				next = getNext(s, i, len);
				lastIdx = len;
			}
			
//			System.out.println("next : " + next);
			// 4-1. 다음수가 1증가 수열인 경우.
			if (next == prev + 1) {
//				System.out.println(prev + " -> " + next);
				prev = next;
				maxSize = (i+lastIdx) - sIdx;
			} else {
//				System.out.println("최댓값:" + maxSize);
//				System.out.println();
				break;
			}

		}
		return maxSize;
	}

	// 4. 해당 위치에서 해당 길이만큼의 문자열을 잘라 정수로 변화해주는 함수.
	private static int getNext(String s, int start, int len) {
		if (start + len > s.length())
			return -1;

		String sub = s.substring(start, start + len);
		if (len > 1 && sub.charAt(0) == '0')
			return -1;

		return Integer.parseInt(sub);
	}

	public static void main(String[] args) {
		System.out.println("기댓값 : 9 " + "\t결과값: " + solution("8234235236239240") );
		System.out.println("기댓값 : 5 " + "\t결과값: " + solution("123499100") );
		System.out.println("기댓값 : 2 " + "\t결과값: " + solution("01020304") );
		System.out.println("기댓값 : 9 " + "\t결과값: " + solution("999910000") );
		System.out.println("기댓값 : 10 " + "\t결과값: " + solution("9999899999100000") );
		System.out.println("기댓값 : 9 " + "\t결과값: " + solution("99999100000") );
		System.out.println("기댓값 : 3 " + "\t결과값: " + solution("12356") );
		System.out.println("기댓값 : 0 " + "\t결과값: " + solution("13579") );
		System.out.println("기댓값 : 0 " + "\t결과값: " + solution("4") );

		
	}
}
